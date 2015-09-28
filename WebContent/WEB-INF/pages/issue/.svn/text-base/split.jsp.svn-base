<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#split').click(
						function() {
							if ($('input[name="rBox"]:checked').length > 0) {
								var count = prompt("Enter Number of Packets",
										"1");

								if (count != null && count != "") {
									$.post('stockSplit.html', {
										pktId : $('input[name="rBox"]:checked')
												.val(),
										count : count
									}, function(result) {
										WinId = window.open('', 'newwin',
												'width=950,height=500');
										WinId.document.open();
										WinId.document.write(result);
										WinId.document.close();
									});
								}
							} else {
								alert('select packet to split');
							}
							// var load = window.open('stockSplit.html?pktCode='+pktCode+'&count='+count,' ','scrollbars=no,menubar=no,height=600,width=1400,resizable=yes,toolbar=no,location=no,status=no');

						});
				$('.window .close').click(function(e) {
					//Cancel the link behavior
					e.preventDefault();
					$('#mask').hide();
					$('.window').hide();
				});

				//adjustHt();
			});
</script>

</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="split" />
	</jsp:include>
	<div id="content">
		<br /> <br />

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		<form id="stockErMx" name="stockErMx"
			action="stockEntryMixSubmit.html" method="post"
			onsubmit="return validateForm();">
			<table align="center">
				<tr>
					<td align="center" valign="top"><span id="title">Select
							Packets to Split</span><br />
						<table align="center" id="greenTable">
							<tr>
								<th>Select</th>
								<th>Pkt</th>
								<th>Total Cts</th>
								<th>Rate</th>
								<th>Shape</th>
								<th>Clarity</th>
								<th>Color</th>

							</tr>
							<c:forEach var="l" items="${list}" varStatus="c">
								<tr>
									<td style="text-align: center; width: 20px;"><input
										id="rBox_${c.index}" class="rbox" type="radio" name="rBox"
										value="${l.pktId}">
									</td>
									<td>${l.pktCode}</td>
									<td>${l.cts}</td>
									<td>${l.rate}</td>
									<td><span id="shFr_${c.index}">${l.shFr}</span>-<span
										id="">${l.shTo}</span>
									</td>
									<td>${l.puFr}-${l.puTo}</td>
									<td>${l.cFr}-${l.cTo}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" id="split" value="Split" />
			</div>

		</form>

	</div>
</body>
</html>








