package com.ibm.mapping.servlet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.io.FileUtils;
import com.ibm.validation.*;


import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.urls.*;
import org.jfree.data.category.*; 
import org.jfree.data.general.*;
import org.jfree.chart.ChartColor;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;


@WebServlet("/validationServlet")
public class ValidationServlet extends HttpServlet {

	/* Creating global variables */

	private static final long serialVersionUID = 1L;
	public String rule = "";
	public String path;
	public String output_path = "";
	public String input_path = "C:\\Business_Validator\\Output";
	public Path dirPath;
	public List<String> fileList = new ArrayList<String>();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, FileNotFoundException {

		String ext = ""; // storing the extension
		char a;
					System.out.println("In validation");
		try {

			String fileName = "";
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			/*Deleting previously stored results*/
			File F1 = new File("C:\\Users\\Administrator\\Desktop\\Results\\Results.zip");
			if(F1.exists())
			F1.delete();
			
			File f3 = new File("C:\\Users\\Administrator\\Desktop\\Results\\graph.txt");
			if(f3.exists())
				F1.delete();
			
			File checkFiles = new File("C:\\Users\\Administrator\\Desktop\\Results\\Output");
			
			File files[] = checkFiles.listFiles();
			System.out.println(checkFiles.listFiles());
			int count =5;
			for(File f:files)
			{
				f.delete();/*Deleting Files inside the directory*/
			}
			int k=1;
				while(count>0)
				{
				File f4 = new File("C:\\Users\\Administrator\\Desktop\\Results\\Output\\level" + k++);
				if(f4.exists())
				{
				File subfiles[] = f4.listFiles();
				for(File f:subfiles)
				{
					f.delete();/*Deleting Files inside the directory*/
				}
				}
				count--;
				}
				for(File f:files)
				{
					f.delete();/*Deleting Files inside the directory*/
				}
				
				
			/*Fetching rule and File*/
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	            	rule = item.getString();
	            } else {
	                // Process form file field (input type="file").
	                 fileName = FilenameUtils.getName(item.getName());
	                 item.write(new File("C:\\Users\\Administrator\\Documents\\" + fileName));
	            }
	        }
	        
			 path = "C:\\Users\\Administrator\\Documents\\" + fileName;
			 System.out.println(path);
			System.out.println("File uploaded");
			for (int i = path.length() - 1; i > 0; i--) {
				a = path.charAt(i);
				if (a == '.') {
					break;
				} else
					ext = "" + a + ext;
			}
				/* processing if zip uploaded*/
			if (ext.equalsIgnoreCase("zip")) {
				request.setAttribute("flag", "1");
				unzip();
				/*Reading files from the unzipped folder*/
				int i = 1;
				File folder = new File(output_path);
				File fileList[] = folder.listFiles(); //Storing files in an array
				String temp_Path = output_path + "\\";
				for(File file : fileList)
				{
					if(file.isFile())
					{
						temp_Path = temp_Path + file.getName();
						validation(rule,temp_Path,i++);
						temp_Path = output_path + "\\";
					}
				}
				reset();
				generateFileList(new File("C:\\Users\\Administrator\\Desktop\\Results\\Output"));
				zip_back();
				
			} 
			/*Single File*/
			else if (ext.equalsIgnoreCase("xml")) 
			{
				validation(rule, path,1); /*for single file validation*/
				reset();
				generateFileList(new File("C:\\Users\\Administrator\\Desktop\\Results\\Output"));
				zip_back();
			}
			else
				System.err.println("Wrong input file");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateGraph();
		fileList.clear();
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		rd.forward(request, response);
	}

	/* Processing XML */

//	public void xml() throws IOException, FileNotFoundException {
//		try {
//			FileReader fis = new FileReader("path");
//			/*
//			 * SomeLogic here
//			 */
//			output_path = "C:\\Users\\GauravVENDORMalhotra\\Documents\\SametimeFileTransfers\\O_050_0000000023146201.octet-stream_IBMOutput_20190201.xml";
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	private void reset() {
		PEPPOL_BUSINESS_VALIDATOR.reset();
		
	}

	/* Unzipping ZIP */
	public void unzip() throws IOException, FileNotFoundException {
		output_path = "C:\\Users\\Administrator\\Documents\\Output";
		byte[] buffer = new byte[1024];
		try {
			// create output directory is not exists
			File folder = new File(output_path);
			if (!folder.exists()) {
				folder.mkdir();
			}
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(path));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(output_path + File.separator + fileName);
				System.out.println("Unzipped file : " + newFile.getAbsoluteFile());
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int length;
				while ((length = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
			System.out.println("unzipped");

			/*
			 * logic for processing zip files
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Zipping the results */
	public void zip_back() {

		String Output_path = "C:\\Users\\Administrator\\Desktop\\Results\\Results.zip";
		
		byte[] buffer = new byte[1024];
		try {
			FileOutputStream fos = new FileOutputStream(Output_path);
			ZipOutputStream zos = new ZipOutputStream(fos);
			System.out.println("Output to Zip- : " + Output_path);
			for (String file : this.fileList) {
				System.out.println("Zipped File : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\Results\\Output" + File.separator + file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			// remember close it
			zos.close();
			fos.close();
			System.out.println("Zipped");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node file or directory
	 */
	public void generateFileList(File node) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring("C:\\Users\\Administrator\\Desktop\\Results\\Output".length() + 1, file.length());
	}

	public void validation(String rule, String path, int i) {
		PEPPOL_BUSINESS_VALIDATOR pbv = new PEPPOL_BUSINESS_VALIDATOR();
		try {
			pbv.validation(rule, path, i++);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void generateGraph() throws NumberFormatException, IOException
	{
		
		final String error[] = new String[]{"Error","Warning","Correct"};
		final String level[] = new String[]{"XSD","BIIS","OPENPEPPOL","OPENPEPPOLCORE","COUNTRY"};

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		File f = new File("C:\\Users\\Administrator\\Desktop\\Results\\graph.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\Results\\graph.txt"));
		String line = null;
		int i=0,j=0;
		while ((line = br.readLine()) != null) {
		  String[] values = line.split(",");
		  for (String str : values) {
		dataset.addValue( Integer.parseInt(str) , error[i].toString() , level[j].toString());
		i++;
		if(i==3)
		{
			i=0;
			j++;
		}
		  }
		  
		}
		br.close();

		//Creating a bar chart
		JFreeChart barChart = ChartFactory.createBarChart(
		   "Results", 
		   "Levels", "Document", 
		   dataset,PlotOrientation.VERTICAL, 
		   true, true, false);



		//Setting chart background colour
		barChart.setBackgroundPaint(new Color(243, 229, 245));

		//Setting plot area colour
		final CategoryPlot plot = barChart.getCategoryPlot();
		plot.setBackgroundPaint((new Color(225,245,254)));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);


		//dfg
		CategoryAxis axis = plot.getDomainAxis();

		        CategoryPlot p = barChart.getCategoryPlot(); 
		         ValueAxis axis2 = p.getRangeAxis();

		        Font font = new Font("Georgia", Font.BOLD, 15);
		        axis.setTickLabelFont(font);
		        Font font2 = new Font("Georgia", Font.BOLD, 15);
		        axis2.setTickLabelFont(font2);
		 
		//dfgdf
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		//Disabling bar outlines
		final BarRenderer render = (BarRenderer) plot.getRenderer();
		        render.setDrawBarOutline(false);
		        render.setItemMargin(0.10);

		//Colouring bars        
		final GradientPaint bar1 = new GradientPaint(
		        0.0f, 0.0f, Color.red, 
		        0.0f, 0.0f, (new Color(239,154,154))
		         );

		final GradientPaint bar2 = new GradientPaint(
		        0.0f, 0.0f,(new Color(255,191,0)), 
		        0.0f, 0.0f, (new Color(255,231,158))
		         );

		final GradientPaint bar3 = new GradientPaint(
		        0.0f, 0.0f,Color.green, 
		        0.0f, 0.0f, (new Color(200,230,201))
		         );

		render.setSeriesPaint(0, bar1);
		render.setSeriesPaint(1, bar2);
		render.setSeriesPaint(2, bar3);
		   
		int width = 640;    /* Width of the image */
		int height = 480;   /* Height of the image */ 
		File BarChart = new File("C:\\Users\\Administrator\\Desktop\\Results\\barchart.jpeg" ); 
		
		
		ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
//		ChartUtilities.saveChartAsJPEG( RBarChart , barChart , width , height );

		
	}
	

}
