<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <jsp:include page="../inc/inc_header.jsp">
        <jsp:param name="page" value="purchase" />
        <jsp:param name="subPage" value="invalid" />
    </jsp:include>
    <div class="container">
	<table id="greenTable" width="100%">
		<tr>
			<td colspan="15">${msg}</td>
		</tr>
		<tr>
			<th>PktCode</th>
			<th>Memo No</th>
			<th>Rough</th>
			<th>Party Name</th>
			<th>BrokerName</th>
			<th>Brokrage</th>
			<th>Cash Price</th>
			<th>Rap</th>
			<th>Sale Price</th>
			<th>Total Price</th>
			<th>Ex Rate</th>
			<th>Shape</th>
			<th>Cts</th>
			<th>Color</th>
			<th>Clarity</th>
			<th>BaseRate</th>

		</tr>
		<c:if test="${empty stock}">
			<tr>
				<td colspan="17">No matching pkts found</td>
			</tr>
		</c:if>
		<c:forEach var="a" items="${stock}" varStatus="count">
			<tr>
				<td>${a.pktCode}</td>
				<td>${a.orderId}</td>
				<td>${a.rootPkt}</td>
				<td>${a.companyName}</td>
				<td>${a.brokerName}</td>
				<td>${a.brokerage}</td>
				<td>${a.rate}</td>
				<td>${a.rap}</td>
				<td>${a.sellRate}</td>
				<td>${a.totalRate}</td>
				<td>${a.exrate}</td>
				<td>${a.sh}</td>
				<td>${a.cts}</td>
				<td>${a.c}</td>
				<td>${a.pu}</td>
				<td>${a.baseRate}</td>

			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>