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
		<jsp:param name="subPage" value="lab" />
	</jsp:include>
	<div>
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>

		<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->
		<form action="labSubmit.html" method="post" id="labDtl">
			Packet No. : ${pktCode}
			<table id="greenTable">
				<thead>
					<tr>
						<c:forEach var="lst" items="${prpList}">
							<c:if test="${lst.prpDesc != null}">
								<th title="${lst.prpDesc}">${lst.prpDesc}</th>
							</c:if>
						</c:forEach>
						<th>Default</th>
					</tr>

				</thead>
				<tbody id="addStock">
					<c:forEach var="pd" items="${pktDetails}">
						<tr id="row_${pd['grpid']}">
							<c:forEach var="lst" items="${prpList}">
								<c:set var="prp" value="${lst.prp}_val" scope="page" />
								<c:if test="${lst.prp == 'CTS' }">
									<c:set var="prp" value="${lst.prp}" scope="page" />
								</c:if>
								<td>${pd[prp]}</td>
							</c:forEach>
							<td><c:if test="${pd['grpid'] == 1}">Default</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</form>
	</div>
	<div id="response"></div>
</body>
</html>