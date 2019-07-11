<!DOCTYPE html>
<html>
<head>
<title>Log in</title>
<!-- <link rel="stylesheet" href="./css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="./css/font-awesome.min.css" type="text/css">-->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="shortcut icon" href="//www.ibm.com/favicon.ico" type=image/vnd.microsoft.icon>
<style>
.container-fluid {
	margin-top: 68px;
}
</style>
</head>

<body background = "images/login_page.jpg" style="width:100%;">
	<!--Header Start-->
	<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="images/ibm.jpg" id="icon" alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form class="login100-form validate-form flex-sb flex-w" action="loginServlet" method="post">
       <div class="error"></div>
      <input type="text" id="UserName" class="fadeIn second" name="UserName" placeholder="IBM Intranet Email ID" required="required">
      <input type="password" id="Password" class="fadeIn third" name="Password" placeholder="IBM Intranet Password" required="required">
      <input type="submit" class="fadeIn fourth" value="Log In" >
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="https://www.ibm.com/ibmid/myibm/help/us/helpdesk.html">Forgot Password?</a>
    </div>

  </div>
</div>
</body>
</html>