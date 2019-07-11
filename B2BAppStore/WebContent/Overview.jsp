<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<link rel="shortcut icon" href="//www.ibm.com/favicon.ico" type=image/vnd.microsoft.icon>

</head>
<title>IBM Business Validator</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
		<a class="hover" onclick="alert('Activates after submitting form');">Analytics</a> 
		 
	<div class="logout">
	<a class="hover" href="LogoutServlet">Log Out</a>
	</div>
	<b> <%=session.getAttribute("name")%>
		</b>
	</div>

	<div class="heading">
  	<h1 ><b> IBM Business Document Validation</b></h1>
	</div>
	<div class="instruction">
		<p>

		
			<br> * Select the rule set available from the drop down list<br><br>
			<br> * Upload your file/zip <br><br>
			<br> * The validator will validate your file/zip <br><br>
			<br> * A report will be generated describing about the validity of the file<br><br>
			<br> * The report will also describe errors(if any)<br><br>
			<br> * In case of Zip, the erroneous reports are stored in sub folder according to the error level (1-5)<br><br>
			<br> * The generated report/reports can downloaded on the Analytics page.<br><br>
			<br> * The User can also visualize the reports through graphs displayed on the Analytics page<br><br>
		</p>
</div>

</body>
</html>
	