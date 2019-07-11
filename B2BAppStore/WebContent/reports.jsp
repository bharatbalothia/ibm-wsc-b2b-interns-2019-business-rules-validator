<%--  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>  --%>


<jsp:include page="common\header.jsp" />
<body>
	<%
		if (session.getAttribute("name") == null) {
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
		}
	%>
	 <div id="wrapper"> 
		 <div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<div id="content">
						<div class="post">
							<jsp:include page="common\logout.jsp" />
							<h2 class="date">B2B AppStore</h2>
							<div style="clear: both;">&nbsp;</div>
						</div>
						<div class="post">
						<!-- 	<h3 class="date">Tools and utilities developed by Team
								members, to help during Map Creation, Testing and other phases.</h3> -->
							<div style="clear: both;">&nbsp;</div>

						</div>
						<div style="clear: both;">&nbsp;</div>
					</div> 
					<jsp:include page="common\sidebar_reports.jsp"></jsp:include>
					<div style="clear: both;">&nbsp;</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="common\footer.jsp"></jsp:include>
</body>
</html>
