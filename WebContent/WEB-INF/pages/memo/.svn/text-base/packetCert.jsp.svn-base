<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>

<script type="text/javascript">
	function checkAll() {
		if ($('#select_all').attr("checked") == true) {
			$('input[id*="select_"]').attr('checked', true);
		} else {
			$('input[id*="select_"]').attr('checked', false);
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<form action="packetCertSubmit.html" method="post" name="upload"
			id="upload">
			<table class="greenTable" style="text-align: center;">
				<tr>
					<th style="white-space: nowrap;"><input type="checkbox"
						value="all" name="selectAll" id="select_all" onclick="checkAll();">
					</th>
					<th>OrderId</th>
					<th>Packet ID</th>
					<th>Packet Code</th>
					<th>Shape</th>
					<th>Carats</th>
					<th>Color</th>
					<th>Purity</th>
					<th>LAB</th>
				</tr>
				<c:forEach var="s" items='${pageData.results}'>
					<tr>
						<td><input type="checkbox" value="${s.pktId}" name="select"
							id="select_${s.pktCode}"> <input type="hidden"
							value="${s.orderId}" name="memoId_${s.pktCode}"
							id="memoId_${s.pktCode}">
						</td>
						<td>${s.orderId}</td>
						<td>${s.pktId}</td>
						<td>${s.pktCode}</td>
						<td>${s.sh}</td>
						<td>${s.cts}</td>
						<td>${s.c}</td>
						<td>${s.pu}</td>
						<td>${s.lab}</td>
					</tr>
				</c:forEach>
			</table>
			<br> <input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>