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
							<h2 class="date">File Transmission Utility</h2><hr/>
							<div style="clear: both;">&nbsp;</div>
							<jsp:include page="common\FileTransmissionUtility.jsp" />
							<input type="hidden" id="reportType" value="file_transmission">
						</div>
						<div class="post">
							<h2 class="date">Description</h2>
							<div style="clear: both;">&nbsp;</div>
							<div class="entry">
								<h3 class="date">This utility transmits generated/browsed file to the FTP Server.</h3><br>
								<div style="font-size: 15px">
								  <b>Input File Format:&nbsp;</b>Any file format (except .zip)<br>
								  <b>FTP Server Used:&nbsp;</b>209.95.232.130 &nbsp;&nbsp;&nbsp;&nbsp;<br>
								  <div style="padding-left: 65px"><b>Port No:&nbsp;</b>21</div>
								  <div style="padding-top: 20px;padding-left: 25px/* ; font-weight: bold; */"><b>Contributors:</b>&nbsp;<i>Pulkit Jain and Kritika Agarwal</i></div>							  
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
