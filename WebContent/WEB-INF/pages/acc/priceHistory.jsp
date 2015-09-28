<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function loadPriceHistory(pktCode) {
		/* window.location = 'loadPriceHistory.html?pktCode=' + pktCode; */
		func('loadPriceHistory.html?pktCode=' + pktCode);
	}
</script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="lab" />
	</jsp:include>
	<div class="container">
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		Enter Packet No. <input type="text" name="pktCode" id="pktCode"
			value="${pktCode}" /> <input type="button" value="go"
			onclick="loadPriceHistory($('#pktCode').val());";>
		<div id="heading">NO Price Changes</div>
		<table id="greenTable">
			<tr>
				<th>Pkt Code</th>
				<th>Last Price</th>
				<th>New Price</th>
				<th>Updated Rap</th>
				<th>Updated by</th>
				<th>Updated Date</th>
			</tr>
			<c:if test="${empty PKT_HISTORY}">
				<tr>
					<td colspan="6">No history Found</td>
				</tr>
			</c:if>
			<c:forEach var="ph" items="${PKT_HISTORY}">
				<tr>
					<td>${ph.pktCode}</td>
					<td>${ph.oldPrice}</td>
					<td>${ph.newPrice}</td>
					<td>${ph.newRap}</td>
					<td>${ph.updateBy}</td>
					<td>${ph.updateDate}</td>
				</tr>
			</c:forEach>
		</table>
		<br /> <br />
		<div id="heading">Memo History</div>
		<table id="greenTable">
			<tr>
				<th>Pkt Code</th>
				<th>Memo No</th>
				<th>Memo Date</th>
				<th>Party Name</th>
				<th>Term</th>
				<th>Price</th>
				<th>Original Issue Date</th>
				<th>Return Date</th>
				<th>Status</th>
			</tr>
			<c:if test="${empty PKT_MEMO_HISTORY}">
				<tr>
					<td colspan="8">No history Found</td>
				</tr>
			</c:if>
			<c:forEach var="p" items="${PKT_MEMO_HISTORY}">
				<tr>
					<td>${p.pktCode}</td>
					<td>${p.orderId}</td>
					<td>${p.orderDate}</td>
					<td>${p.companyName}</td>
					<td>${p.term}</td>
					<td>${p.rate}</td>
					<td>${p.issueDate}</td>
					<td>${p.returnDate}</td>
					<td><c:if test="${p.status == 1}">Pending</c:if> <c:if
							test="${p.status == 2}">Approved</c:if> <c:if
							test="${p.status == 3}">Rejected</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>