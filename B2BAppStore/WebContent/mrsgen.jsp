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
							<h2 class="date">Tech MRS Generator</h2><hr/>
							<div style="clear: both;">&nbsp;</div>
							<jsp:include page="common\MRSusageType.jsp" />
							<input type="hidden" id="reportType" value="mrsgenerator">
							<form method="post" enctype="multipart/form-data">

								<strong>Select file to upload</strong>&nbsp;<strong
									style="color: red;">*</strong>&nbsp;&nbsp; <input type="file"
									name="file" size="0" id="file" /> <br /> <br /> <br /> <input
									type="submit" value="Upload">
							</form>
						</div>
						<div class="post">
							<h2 class="date">Description</h2>
							<div style="clear: both;">&nbsp;</div>
							<div class="entry">
								<h3 class="date">This utility to generates technical MRS.</h3><br><h3><b>Enhancement:</b>Updates qualifier description for EDI standard qualifiers for edi outbound map.</h3><br/>
								<div style="font-size: 15px">
								  <b>Input File Format:&nbsp;</b>.zip / .map / .mxl<br>
								  <div style="padding-left: 70px"><b>Output:&nbsp;</b>.zip</div>
								  <div style="padding-top: 20px;padding-left: 25px/* ; font-weight: bold; */"><b>Contributors:</b>&nbsp;<i>Manish Saki , Sanket Patil , Mark Duprey and Vidhya &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Balasubramanian</i></div>							  
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
