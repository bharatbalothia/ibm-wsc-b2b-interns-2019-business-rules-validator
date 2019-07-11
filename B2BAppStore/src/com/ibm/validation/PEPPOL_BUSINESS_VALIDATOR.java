package com.ibm.validation;

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
class StreamGobbler extends Thread
{
	InputStream is;
	String type;

	StreamGobbler(InputStream is, String type)
	{
		this.is = is;
		this.type = type;
	}

	public void run()
	{
		try
		{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line=null;
			while ( (line = br.readLine()) != null)
				System.out.println(type + ">" + line);    
		} catch (IOException ioe)
		{
			ioe.printStackTrace();  
		}
	}
}
public class PEPPOL_BUSINESS_VALIDATOR
{
	public FileWriter fw1; 
	public BufferedWriter WriteFileBuffer1;
	public static String outputArr[];
	public static int Output_length;
	public static int warnings[] = new int[5];
	public static int[] errors = new int[5];
	public static int success[] = new int[5];
	public void validation(String DocumentType, String temp_InputPath,int i) throws IOException,FileNotFoundException
	{
		Properties props = new Properties();
		File f1 = new File("C:\\Users\\Administrator\\Desktop\\Results\\Output\\svrl_report_final" +i+".txt");
		if(! f1.exists())
		{
			f1.createNewFile();
		}

		fw1 = new FileWriter("C:\\Users\\Administrator\\Desktop\\Results\\Output\\svrl_report_final" + i +".txt");
		WriteFileBuffer1 = new BufferedWriter(fw1);  
		//FileWriter fw2 = new FileWriter("C:\\Java\\PEPPOL-BIS-Test\\output\\svrl_report_" + i +".xml");
		//BufferedWriter WriteFileBuffer2 = new BufferedWriter(fw2);
		/*Getting File Name*/
		String File_Name="";
		char a;
		for(int j =temp_InputPath.length()-1; j>0;j--)
		{
			a = temp_InputPath.charAt(j);
			if(a == '\\')
				break;
			File_Name = a + File_Name;
		}

		WriteFileBuffer1.write("#######################################################################################################################################");
		WriteFileBuffer1.newLine();
		WriteFileBuffer1.write("| 														IBM PEPPOL VALIDATION REPORT											      |");
		WriteFileBuffer1.newLine();
		WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
		WriteFileBuffer1.newLine();

		WriteFileBuffer1.write("| File Name		|"+ File_Name);
		for(int k =1; k < 135-(17+File_Name.length()); k++)
			WriteFileBuffer1.write(" ");
		WriteFileBuffer1.write("|");
		WriteFileBuffer1.newLine();
		WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
		WriteFileBuffer1.newLine();
		String schema;
		if(DocumentType.contains("_"))
			schema = DocumentType;
		else
			schema="OPENPEPPOL";
		
		WriteFileBuffer1.write("|Document Type	|" + DocumentType+"										|PEPPOL Schmatron |" + schema);
		for(int k =0; k < 135-(75+DocumentType.length() + schema.length()-1); k++)
			WriteFileBuffer1.write(" ");
		WriteFileBuffer1.write("|");
		WriteFileBuffer1.newLine();
		WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
		WriteFileBuffer1.newLine();

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getDefault());
		String Date = df.format(date);
		WriteFileBuffer1.write("| Date		    | "+Date+"																								  |" );
		WriteFileBuffer1.newLine();
		WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
		WriteFileBuffer1.newLine();
		//java.net.URL url = PEPPOL_BUSINESS_VALIDATOR.class.getResource("/PEPPOL-BIS-Test/xml/DesptachAdvice.xml");
		//System.out.println("url" + url);
		//String a =Arrays.toString(url.toString().split("/PEPPOL-BIS-Test",2)); 
		//System.out.println(Arrays.toString(a.split(",",1)));

		props.setProperty("DispatchAdvice_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-DespatchAdvice-2.1.xsd");
		props.setProperty("DispatchAdvice_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\BIIRULES-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\OPENPEPPOL-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLTCore\\OPENPEPPOLCORE-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_Country", "C:\\Java\\PEPPOL-BIS-Test\\xsl\\PEPPOL\\ITNAT-UBL-T16.xsl");

		props.setProperty("Invoice_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-Invoice-2.1.xsd");
		props.setProperty("Invoice_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\BIIRULES-UBL-T10.xsl");
		props.setProperty("Invoice_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\OPENPEPPOL-UBL-T10.xsl");
		props.setProperty("Invoice_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLTCore\\OPENPEPPOLCORE-UBL-T10.xsl");

		props.setProperty("Invoice_SG_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-Invoice-2.1.xsd");
		props.setProperty("Invoice_SG_BIIS","C:\\Java\\ph-bdve-master\\ph-bdve-master\\ph-bdve-peppol\\src\\main\\resources\\sg-peppol\\xslt\\PEPPOL-EN16931-UBL-SG-Conformant.xslt");
		props.setProperty("Invoice_SG_OPENPEPPOL","C:\\Java\\ph-bdve-master\\ph-bdve-master\\ph-bdve-peppol\\src\\main\\resources\\sg-peppol\\xslt\\CEN-EN16931-UBL-SG-Conformant.xslt");

		props.setProperty("DispatchAdvice_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-DespatchAdvice-2.1.xsd");
		props.setProperty("DispatchAdvice_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\BIIRULES-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLT\\OPENPEPPOL-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\xsl\\BIS2.0-VA-20181004-3.7.0.RC1\\XSLTCore\\OPENPEPPOLCORE-UBL-T16.xsl");
		props.setProperty("DispatchAdvice_Country", "C:\\Java\\PEPPOL-BIS-Test\\xsl\\PEPPOL\\ITNAT-UBL-T16.xsl");

		//*******************************Orders******************************************************************
		props.setProperty("Orders_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-Order-2.1.xsd");
		props.setProperty("Orders_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\XSLT\\BIIRULES-UBL-T01.xsl");
		props.setProperty("Orders_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\XSLT\\OPENPEPPOL-UBL-T01.xsl");
		props.setProperty("Orders_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\XSLTCore\\XSLTCore\\OPENPEPPOLCORE-UBL-T01.xsl");

		//*******************************Creditnote******************************************************************
		props.setProperty("Creditnote_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-CreditNote-2.1.xsd");
		props.setProperty("Creditnote_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\XSLT\\BIIRULES-UBL-T14.xsl");
		props.setProperty("Creditnote_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\XSLT\\OPENPEPPOL-UBL-T14.xsl");
		props.setProperty("Creditnote_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\XSLTCore\\XSLTCore\\OPENPEPPOLCORE-UBL-T14.xsl");


		//*******************************Catalogue******************************************************************
		props.setProperty("Catalogue_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-Catalogue-2.1.xsd");
		props.setProperty("Catalogue_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\XSLT\\BIIRULES-UBL-T19.xsl");
		props.setProperty("Catalogue_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\XSLT\\OPENPEPPOL-UBL-T19.xsl");
		props.setProperty("Catalogue_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\XSLTCore\\XSLTCore\\OPENPEPPOLCORE-UBL-T19.xsl");


		//*******************************OrderResponse******************************************************************
		props.setProperty("OrderResponse_XSD","C:\\Java\\PEPPOL-BIS-Test\\xsd\\maindoc\\UBL-OrderResponse-2.1.xsd");
		props.setProperty("OrderResponse_BIIS", "C:\\Java\\PEPPOL-BIS-Test\\XSLT\\BIIRULES-UBL-T76.xsl");
		props.setProperty("OrderResponse_OPENPEPPOL","C:\\Java\\PEPPOL-BIS-Test\\XSLT\\OPENPEPPOL-UBL-T76.xsl");
		props.setProperty("OrderResponse_OPENPEPPOLCORE","C:\\Java\\PEPPOL-BIS-Test\\XSLTCore\\XSLTCore\\OPENPEPPOLCORE-UBL-T76.xsl");

		//String XSLT_RULE = props.getProperty(DocumentType); 	

		//*************************Level -1 XSD Validation********************
		String validation_report_final="";
		int j =1;
		String DocumentType_temp = DocumentType+"_XSD";
		String XSLT_RULE = props.getProperty(DocumentType_temp);

		if (XSLT_RULE != null) 
		{
			WriteFileBuffer1.write("|                                                          LEVEL -1: XSD VALIDATION												      |");
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();
			validation_report_final= XSD_Validation(XSLT_RULE,temp_InputPath,i,j);
			//outputArr = new String[Output_length];
			WriteFileBuffer1.write(outputArr[0]);
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();
			XSLT_RULE="";
			//System.out.println("validation_report_final : "+validation_report_final);
			//WriteFileBuffer2.write(validation_report_final);
			//WriteFileBuffer2.newLine();
			j++;
		}
		//*************************Level -2 XSD Validation********************
		DocumentType_temp = DocumentType+"_BIIS";
		XSLT_RULE = props.getProperty(DocumentType_temp);

		if (XSLT_RULE != null) 
		{
			validation_report_final="";

			validation_report_final = XSLT_Validation(XSLT_RULE,temp_InputPath,i,j);
			WriteFileBuffer1.write("|                                                          LEVEL -2: BIIS VALIDATION												  |");
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();

			if(Output_length == 0)
			{
				WriteFileBuffer1.write("|Status         | Success   																										  |");
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
				success[j-1]++;
			}
			for(int k=0; k<Output_length; k++)
			{
				WriteFileBuffer1.write(outputArr[k]);
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
			}

			//System.out.println(validation_report_final +"\n"+validation_report_final_2);
			//    	WriteFileBuffer2.write(validation_report_final);
			//    	WriteFileBuffer2.newLine();
			j++;
			XSLT_RULE="";
		}
		//*************************Level -3 XSD Validation********************

		DocumentType_temp = DocumentType+"_OPENPEPPOL";
		XSLT_RULE = props.getProperty(DocumentType_temp);

		if (XSLT_RULE != null) 
		{
			validation_report_final="";
			WriteFileBuffer1.write("|                                                          LEVEL -3: OPENPEPPOL VALIDATION											  |");
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();
			validation_report_final = XSLT_Validation(XSLT_RULE,temp_InputPath,i,j);
			if(Output_length == 0)
			{
				WriteFileBuffer1.write("|Status         | Success   																										  |");
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
				success[j-1]++;
			}
			for(int k=0; k<Output_length; k++)
			{
				WriteFileBuffer1.write(outputArr[k]);
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();

			}

			XSLT_RULE="";
			//    	WriteFileBuffer2.write(validation_report_final);
			j++;
			//    	WriteFileBuffer2.newLine();
		}
		//*************************Level -4 XSD Validation********************
		DocumentType_temp = DocumentType+"_OPENPEPPOLCORE";
		XSLT_RULE = props.getProperty(DocumentType_temp);

		if (XSLT_RULE != null) 
		{
			validation_report_final="";
			WriteFileBuffer1.write("|                                                          LEVEL -4: OPENPEPPOLCORE VALIDATION										  |");
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();
			validation_report_final = XSLT_Validation(XSLT_RULE,temp_InputPath,i,j);
			if(Output_length == 0)
			{
				WriteFileBuffer1.write("|Status         | Success   																										  |");
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
				success[j-1]++;
			}
			for(int k=0; k<Output_length; k++)
			{
				WriteFileBuffer1.write(outputArr[k]);
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
			}

			XSLT_RULE="";
			//       	WriteFileBuffer2.write(validation_report_final);
			j++;
			//    	WriteFileBuffer2.newLine();
		}
		//*************************Level -5 XSD Validation********************
		DocumentType_temp = DocumentType+"_Country";
		XSLT_RULE = props.getProperty(DocumentType_temp);
		//System.out.println(XSLT_RULE + "XSLT_RULE");
		if (XSLT_RULE != null) 
		{
			validation_report_final="";
			WriteFileBuffer1.write("|                                                          LEVEL -5: COUNTRY SPECIFIC VALIDATION									  |");
			WriteFileBuffer1.newLine();
			WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
			WriteFileBuffer1.newLine();
			validation_report_final = XSLT_Validation(XSLT_RULE,temp_InputPath,i,j);
			if(Output_length == 0)
			{
				WriteFileBuffer1.write("|Status         | Success   																										  |");
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
				success[j-1]++;
			}
			for(int k=0; k<Output_length; k++)
			{
				WriteFileBuffer1.write(outputArr[k]);
				WriteFileBuffer1.newLine();
				WriteFileBuffer1.write("|---------------|---------------------------------------------------------------------------------------------------------------------|");
				WriteFileBuffer1.newLine();
			}

			XSLT_RULE="";
			//       	WriteFileBuffer2.write(validation_report_final);
			//    	WriteFileBuffer2.newLine();
		}
		//    	WriteFileBuffer2.close();
		WriteFileBuffer1.write("***************************************************************************************************************************************");
		WriteFileBuffer1.close();
		fw1.close();
		rename("C:\\Users\\Administrator\\Desktop\\Results\\Output\\svrl_report_final", temp_InputPath,i, "C:\\Users\\Administrator\\Desktop\\Results\\Output\\");

	}

	public static String XSD_Validation (String XSLT_RULE, String temp_InputPath, int i,int j) throws  IOException 
	{
		String validation_report= "";
		try { 


			String cmd = "java -jar C:\\Java\\PEPPOL-BIS-Test\\xjparse.jar -c catalog.xml -S "+ XSLT_RULE + " " + temp_InputPath;

			//System.out.println(cmd);
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);

			// any error message?
			StreamGobbler errorGobbler = new 
					StreamGobbler(proc.getErrorStream(), "ERROR");

			File f = new File("C:\\Users\\Administrator\\Desktop\\Results\\level1");
			f.mkdir();

			FileWriter fw = new
					FileWriter("C:\\Users\\Administrator\\Desktop\\Results\\level1\\svrl_report_"+i+"_"+j+".xml");
			BufferedWriter WriteFileBuffer = new BufferedWriter(fw);
			BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			 //any output?

			String line = "";
			while((line = error.readLine()) != null)
			{
				WriteFileBuffer.write(line);   	            	
				WriteFileBuffer.newLine();
				validation_report = validation_report + line;

			}



			StreamGobbler outputGobbler = new 
					StreamGobbler(proc.getInputStream(), "OUTPUT");
			BufferedReader output = new BufferedReader(new InputStreamReader(proc.getInputStream()));   
			String line_Output= "";
			while((line_Output = output.readLine()) != null)
			{
				WriteFileBuffer.write(line_Output);
				WriteFileBuffer.newLine();
			}



			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			outputArr = new String[1];
			Output_length = 1;
			// any error???
			int exitVal = proc.waitFor();
			System.out.println("| ExitValue: " + exitVal);
			if (exitVal == 1)
			{
				System.out.println("|  Status = Error");
				outputArr[0] = "|Status         | Error   																										  |";    	            	
				validation_report = "LEVEL-1^E" + validation_report;
				errors[j-1]++;

			}
			else if (exitVal == 0)
			{
				System.out.println("|  Status = Success");
				outputArr[0] = "|Status         | Success   																										  |";
				validation_report = "LEVEL-1^S";
				success[j-1]++;
			}
			else if (exitVal == 2)
			{
				System.out.println("|  Status = Missing XSD");
				validation_report = "LEVEL-1^S";
				outputArr[0] = "|Status         | Missing XSD   																										  |";
				errors[j-1]++;
			}

			WriteFileBuffer.close();
			error.close();
			output.close();


		} catch (Throwable t)
		{
			t.printStackTrace();
		}
		return validation_report;

	}

	public static String XSLT_Validation (String XSLT_RULE, String temp_InputPath, int i,int j) throws  IOException 
	{
		String validation_report_3="";
		try { 
			String cmd = "java -jar C:\\Java\\PEPPOL-BIS-Test\\lib\\saxon9.jar -o C:\\Users\\Administrator\\Desktop\\Results\\level"+ j + "/svrl_report_Output"+ i+ "_" +j+".xml "+ temp_InputPath+  " "+XSLT_RULE;
			//String cmd = "java -jar C:\\Java\\PEPPOL-BIS-Test\\lib\\saxon9.jar " + temp_InputPath+  " "+XSLT_RULE;	
			//System.out.println(cmd);
			String path ="C:\\Users\\Administrator\\Desktop\\Results\\level" + j;
			File f = new File(path);
			f.mkdir();

			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			//create subfolder
			// any error message?
			StreamGobbler errorGobbler = new 
					StreamGobbler(proc.getErrorStream(), "ERROR");  
			/*
    	            FileWriter fw1 = new FileWriter("C:\\Java\\PEPPOL-BIS-Test\\output\\svrl_report_Output_1"+ i+".xml");
                    BufferedWriter WriteFileBuffer1 = new BufferedWriter(fw1);
    	            BufferedReader error1 = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
    	            // any output?

    	            String line1 = "";
    	            while((line1 = error1.readLine()) != null)
    	            {
    	           WriteFileBuffer1.write(line1);
    	           WriteFileBuffer1.newLine();
    	            }
			 */
			// any output?
			// StreamGobbler outputGobbler = new 
			//     StreamGobbler(proc.getInputStream(), "OUTPUT");
			// FileWriter fw2 = new FileWriter("C:\\Java\\PEPPOL-BIS-Test\\output\\svrl_report_Output_2"+i+".xml");
			// BufferedWriter WriteFileBuffer2 = new BufferedWriter(fw2);
			// BufferedReader output1 = new BufferedReader(new InputStreamReader(proc.getInputStream()));   
			// String line_Output= "";
			//while((line_Output = output1.readLine()) != null)
			// {
			//WriteFileBuffer2.write(line_Output);
			//WriteFileBuffer2.newLine();
			//  	System.out.println(line_Output);
			//  }
			//    	          // kick them off
			//    errorGobbler.start();
			//   outputGobbler.start();



			// any error???
			int exitVal = proc.waitFor();
			// System.out.println("ExitValue: " + exitVal);  
			if (exitVal == 1)
			{
				System.out.println("Status = Error");
				//validation_report = "LEVEL-1^E" + validation_report;
			}
			else if (exitVal == 0)
			{
				System.out.println("Status = Success");
				//validation_report = "LEVEL-1^S";
			}
			else if (exitVal == 2)
			{
				System.out.println("Status = Warning");
				//validation_report = "LEVEL-1^S";
			}         


			validation_report_3 =  XML_Report_extractor ("C:\\Users\\Administrator\\Desktop\\Results\\level" + j + "//svrl_report_Output"+ i+"_"+j+".xml",j);  

			//   output1.close();
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
		return validation_report_3;

	}

	public static String XML_Report_extractor (String filename,int i) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		String validation_report_2= ""; 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(filename);

		//Create XPath

		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();

		// 1) Get book titles written after 2001
		XPathExpression expr1 = xpath.compile("//*[local-name()='failed-assert']/@* | //*[local-name()='failed-assert']/*[local-name()='text']/text()");
		Object result1 = expr1.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes1 = (NodeList) result1;
		Output_length = nodes1.getLength();
		String temp1="",temp2="";
		outputArr = new String[Output_length+1];




		for (int j = 0; j < nodes1.getLength(); j++) {
			temp1 = nodes1.item(j).getNodeName();
			temp2 = nodes1.item(j).getNodeValue();
			//System.out.println(nodes1.item(j).getNodeName() +": "+nodes1.item(j).getNodeValue());
			String status = nodes1.item(j).getNodeValue();
			if(status.contains("fatal"))
			{
				errors[i-1]++;

				outputArr[0]= "|Status         | Warnings  |  " + warnings[i-1]+"	|  Errors  | "+ errors[i-1]+"   |" +
						"                                                                                |";

			}
			else if (status.contains("warning"))
			{
				warnings[i-1]++;
				outputArr[0]= "| Status        | Warnings  |  " + warnings[i-1]+"	|  Errors  | "+ errors[i-1]+"   |" +
						"                                                                                |";
			}
			outputArr[j+1] = "|";
			int temp3 = 15 -temp1.length();
			for(int k =0; k<temp3;k++)
				temp1 = temp1 + " ";
			outputArr[j+1] =outputArr[j+1] + temp1 + "|";
			temp3 = 117-temp2.length();
			if(temp3 > 0)
			{
				for(int k =0; k<temp3;k++)
					temp2 = temp2 + " ";
				temp2 =temp2 + "|";
				outputArr[j+1] =outputArr[j+1] + temp2;
			}
			if(temp3 < 0)
			{
				int k=0;
				int gap = 117;
				int end =gap;
				String s = "";
				while(k < temp2.length())
				{

					s = temp2.substring(k,end);
					if(117-s.length() > 0)
						break;
					outputArr[j+1]= outputArr[j+1] + s + "|\n" +"|\t\t\t\t|";
					k = end+1;
					end = gap + k;
					if(end > temp2.length())
						end = temp2.length();

				}
				end =117-s.length();
				for(k =0; k < end; k++)
					s = s + " ";
				s =s + "|";
				outputArr[j+1] =outputArr[j+1] + s;

			}

			System.out.println(outputArr[j+1]);
			validation_report_2 = validation_report_2 + "_"+j + nodes1.item(j).getNodeName() +": "+nodes1.item(j).getNodeValue();
			if (j % 5 == 0)
			{
				System.out.println("****************************************************");
			}

		}
		text_file();

		return validation_report_2;        

	}

	public static void text_file()
	{
		try {
			//Preparing the graph.txt file which shall contain values from all the levels
			File f = new File("C:\\Users\\Administrator\\Desktop\\Results\\graph.txt");
			if(!f.exists())
			{
				f.createNewFile();
			}
			FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop\\Results\\graph.txt");
			BufferedWriter GraphWriter = new BufferedWriter(fw);
			for(int i=0; i < 4; i++)

			{GraphWriter.write(errors[i] + "," + warnings[i]+ "," + success[i] + "\n");
			System.out.println(i + "-" + success[i]);
			}
			GraphWriter.write(errors[4] + "," + warnings[4]+ "," + success[4]);
			GraphWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void reset() {
		//Settting values to 0 so that values are not stored for next validation
		Arrays.fill(errors, 0);
		Arrays.fill(success, 0);
		Arrays.fill(warnings, 0);

	}

	//Renaming the result file name
	public static void rename(String FileName, String inputfile, int file_number, String path) {
		FileName = FileName + file_number+".txt";
		File f1 = new File(FileName);
		int i=0, warn =0, err =0; String NewName = ""; char a;
		for( i = inputfile.length() -5 ; i>0;i--)
		{
			a = inputfile.charAt(i);
			if(a == '\\')
				break;
			else
				NewName = ""+a+NewName;
		}
		path = path + NewName;
		
		//if warnings exists
		for(i=0; i<5; i++)
		{
			if(warnings[i] > 0)
			{
				if(!path.contains("_W"))
				{
				path = path + "_W";
				}
				warn = warn + warnings[i];
		}
		}
		
		if(warn > 0)
			path = path + warn;
		
		//If errors exists
		for(i=0; i<5; i++)
		{
			if( errors[i] > 0)
			{
			if(!path.contains("_E"))
				{
				path = path + "_E";
				}
			err=err + errors[i];
		}
		}
		
		
		if(err > 0)
			path = path + err;
		
		//No errors and no warnings
		if(err == 0 && warn ==0)
				path = path + "_P";
	
		path = path + ".txt";
		
		File f2 = new File(path);
		boolean b = f1.renameTo(f2);
		if(b)
			System.out.println("File Name changed successfully");
		b = f1.delete();
	}
}