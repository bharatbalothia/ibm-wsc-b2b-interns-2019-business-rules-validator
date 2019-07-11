<jsp:include page="common\header.jsp" />

<body>
	<%
		if (session.getAttribute("name") == null) {
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
		}
		//out.print(session.getAttribute("projectNames"));
		String jsp = request.getRequestURI();
		//out.print("JSP:"+request.getRequestURI());
	%>


	<div id="wrapper">
		<div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<div id="content">
						<div class="post">
							<jsp:include page="common\logout.jsp" />
							<h2 class="date">CodeList Creator</h2><hr/>
							<div style="clear: both;">&nbsp;</div>
							<jsp:include page="common\usageType.jsp" />
							<input type="hidden" id="reportType" value="codelistcreate">
							<form method="post" enctype="multipart/form-data">

								<strong>Select file to upload</strong>&nbsp;<strong
									style="color: red;">*</strong>&nbsp;&nbsp; <input type="file"
									name="file" id="file" size="0" /> <br /> <br /> <br /> <input
									type="submit" value="Upload">
							</form>
						</div>
						<div class="post">
							<h2 class="date">Description</h2>
							<div style="clear: both;">&nbsp;</div>
							<div class="entry">
								<h3 class="date">Convert XML to xls format and vice-versa </h3><br>
								<div style="font-size: 15px">
								  <b>Input File Format:&nbsp;</b>.xml / .xls &nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;Refer&nbsp;<a href="resource/SampleSheetCodeList.xls"">SampleSheet</a>&nbsp;)<br>
								  <div style="padding-left: 70px"><b>Output:&nbsp;</b>.xml / .xls</div>
								  <div style="padding-top: 20px;padding-left: 25px/* ; font-weight: bold; */"><b>Contributors:</b>&nbsp;<i>Sanket Patil and Akhila Desai</i></div>							  
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