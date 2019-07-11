<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Master Dash board</title>
</head>
<body>
<body>

	<h4>
		Hello,
		<%=session.getAttribute("name")%></h4>

	<h1>File Upload</h1>
	<form method="post" action="UploadServlet"
		enctype="multipart/form-data">
		Select file to upload: <input type="file" name="file" size="60"
			required="required" /><br /> <br /> <input type="submit"
			value="Upload" />
	</form>

</body>
</html>
