
<% 
	try{
  String filename = "Results.zip";   
  String filepath = "C:\\Users\\Administrator\\Desktop\\Results\\";   
  response.setContentType("APPLICATION/OCTET-STREAM");   
  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
  java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath + filename);  
            
  int i;   
  
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();
}

catch(Exception e)
{
    e.printStackTrace();	
}

%>