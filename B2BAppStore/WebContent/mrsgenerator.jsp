<jsp:include page="common\header.jsp" />

<body>
	<%
		if (session.getAttribute("name") == null) {
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
		}
		//out.print(session.getAttribute("projectNames"));
	%>


	<div id="wrapper">
		<div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<div id="content">
						<div class="post">
							<jsp:include page="common\logout.jsp" />
							<h2 class="date">Generate Technical MRS</h2>
							<div style="clear: both;">&nbsp;</div>
						
							
							<jsp:include page="common\MRSusageType.jsp" />
							
							
							<form method="post" enctype="multipart/form-data">
							  <input type="hidden" id="reportType" value="mrsgenerator"> 
						<br/>
							    
								<strong>Select file to upload</strong>&nbsp;&nbsp;&nbsp; <input
									type="file" name="file" size="60" required="required" /> <br />
								<br />
								


 						 <input type="submit" value="Upload">
							</form>
						</div>
						<div class="post">
							<h2 class="date">Description</h2>
							<div style="clear: both;">&nbsp;</div>
							<div class="entry">
								<h4 class="date">Generates Technical MRS.</h4><br>
								<div style="font-size: 15px">
								  <b>Input File Format:&nbsp;</b>.map / .mxl / .zip<br>
								  <div style="padding-left: 70px"><b>Output:&nbsp;</b>.xlsx</div>
								  <div style="padding-top: 20px;padding-left: 25px; font-weight: bold;">Contributors:&nbsp;Manish Saki</div>							  
								</div>
								<div style="clear: both;">&nbsp;</div>
							</div>
						</div>
						<div style="clear: both;">&nbsp;</div>
					</div>
					<jsp:include page="common\sidebar.jsp"></jsp:include>
					<div style="clear: both;">&nbsp;</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="common\footer.jsp"></jsp:include>


</body>
</html>
