
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<link rel="shortcut icon" href="//www.ibm.com/favicon.ico" type=image/vnd.microsoft.icon>

<style>
.container-fluid {
	margin-top: 68px;
}
</style>
</head>
<title>IBM Business Validator</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" style="width:100%;">
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



	<!--  Validator Form -->
	<div class="ruleBox">
	<form  action="validationServlet" method="post" enctype="multipart/form-data">
		<div class="ruleset">
				<b>Rule Set: &nbsp</b> 
				<select id="rule" name="rule" >
				<option value="" disabled selected hidden>---------Set Rule--------</option>
					<option value="DispatchAdvice">DispatchAdvice</option>
					 <option value="Invoice">Invoice</option>
					 <option value="Invoice_SG">Invoice_SG</option>
					 <option value="Orders">Orders</option>
					 <option value="Creditnote">Creditnote</option>
					 <option value="Catalogue">Catalogue</option>
					 <option value="OrderResponse">OrderResponse</option>
				</select>
		</div>
		<div class="fileupload">
			<p>
				<b>Upload<br>Document:
				</b>
			</p>
		</div>
		  <div class="uploader">
				<input name="uploadfile" type="file" required="required" multiple>
			</div>
		<div class="validate-button">
			<input type="submit" value="Validate">
		</div>
	</form>
	</div>
</body>
</html>
