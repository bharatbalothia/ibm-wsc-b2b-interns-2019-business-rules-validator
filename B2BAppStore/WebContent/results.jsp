<!DOCTYPE html>
<%@page import="sun.reflect.ReflectionFactory.GetReflectionFactoryAction,java.io.*"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<link rel="shortcut icon" href="//www.ibm.com/favicon.ico" type=image/vnd.microsoft.icon>

</head>
<title>IBM Business Validator</title>
<body background="images/blue.jpg">
	<%
		if (session.getAttribute("name") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}	
	%>
	


	<div class="topnav">
		<img src="images/ibm.jpg"> 
		<a class="hover" href="./Overview.jsp">Overview</a> 
		<a class="hover" href="./dashboard.jsp">Dashboard</a>
		<a class="hover">Analytics</a>
		 
	<div class="logout">
	<a class="hover" href="LogoutServlet">Log Out</a>
	</div>
	<b> <%=session.getAttribute("name")%>
		</b>
	</div>
	
	<div class="chart">
	<%String s = request.getContextPath();
	%>
	<img src="/Results/barchart.jpeg" border="0" usemap="#chart" >
	</div>
 	<p style= "color : red" align= "center">
 		<b>Download your results from the image displayed below:</b>
 	 	<br><br>
 	
 	<a href="download.jsp">
	<img src ="images/download.png" style="width:42px;height:42px;border:0;">
    </a> 
    </p>
</body>
</html>
