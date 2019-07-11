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
							<h2 class="date">Error 20001 Report</h2><hr/>
							<div style="clear: both;">&nbsp;</div>
							<jsp:include page="common\usageType.jsp" />
							<input type="hidden" id="reportType" value="error20001">
							<form method="post" enctype="multipart/form-data">
								<strong>Select file to upload</strong>&nbsp;<strong
									style="color: red;">*</strong>&nbsp;&nbsp; <input type="file"
									name="file" size="0" id="file" /> <br /> <br /> <br /> <input
									type="submit" value="Upload" />
							</form>
						</div>
						<div class="post">
							<h2 class="date">Description</h2>
							<div style="clear: both;">&nbsp;</div>
							<h3 class="date">Generates report which contain all fields which will throw the 20001 compilation error. Before and after saving map should be provided as input for generating report.<br /><br />Before saving map : mapname_before <br />After saving map : mapname_after.</h3><br>
								<div style="font-size: 15px">
								  <b>Input File Format:&nbsp;</b><!-- .map / .mxl /  -->.zip<br>
								  <div style="padding-left: 70px"><b>Output:&nbsp;</b>.xml</div>
								  <div style="padding-top: 20px;padding-left: 25px/* ; font-weight: bold; */"><b>Contributors:</b>&nbsp;<i>Pradeep, Bharath and Sanket</i></div>							  
								</div>
							<div style="clear: both;">&nbsp;</div>
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
