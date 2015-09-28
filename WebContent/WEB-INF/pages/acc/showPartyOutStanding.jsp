<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="party" />
		<jsp:param name="subPage" value="addParty" />
	</jsp:include>

	<div class="container">
		<div class="content">
			<c:set var="endResult" scope="page" value="0" />
			<c:set var="currPage" scope="page"
				value="${param.currPage!=null?param.currPage:1}" />
			<c:set var="totRecord" scope="page" value="0" />
			<c:forEach items='${PRT_OST_LIST}' var='prtlst' varStatus="coutt">
				<c:set var="totRecord" scope="page" value="${totRecord + 1 }" />
			</c:forEach>
			<c:set var="step" scope="page" value="10" />

			<c:set var="startResult" scope="page"
				value="${(currPage - 1) * step }" />
			<c:choose>
				<c:when test="${totRecord < (startResult + step)}">
					<c:set var="endResult" scope="page" value="${totRecord}" />
				</c:when>
				<c:otherwise>
					<c:set var="endResult" scope="page" value="${startResult+step -1}" />
				</c:otherwise>
			</c:choose>

			<form action="">
				<table id="greenTable" width="95%" border="0" align="center">

					<tr>
						<th>ReferenceNo</th>
						<th>PartyId</th>
						<th>Date</th>
						<th>DueDate</th>
						<th>BrokerId</th>
						<th>Amount</th>
						<th>Tax</th>
						<th>Discount</th>
						<th>Expence</th>
						<th>Final Amount</th>
						<th>Description</th>
						<th>Edit</th>
					</tr>
					<c:forEach items='${PRT_OST_LIST}' var='prtlst'
						begin="${startResult}" end="${endResult}">
						<tr>
							<td>${prtlst.referneceNo}</td>
							<td>${prtlst.partyID}</td>
							<td>${prtlst.fdate}</td>
							<td>${prtlst.dueDate}</td>
							<td>${prtlst.brokerID}</td>
							<td>${prtlst.ammount}</td>
							<td>${prtlst.tax}</td>
							<td>${prtlst.discount}</td>
							<td>${prtlst.expence}</td>
							<td>${prtlst.finalAmmount}</td>
							<td>${prtlst.description}</td>
							<td><a
								href="partyOutStanding.html?refNo=${prtlst.referneceNo}">Edit</a>
							</td>
						</tr>
					</c:forEach>

					<fmt:formatNumber type="number" var="isLastPage" scope="page"
						value="${(totRecord/step)+((totRecord%step)>0?1:0)}" pattern="0" />
					<tr>
						<td colspan="12">
							<div>
								<c:if test="${currPage > 1}">
									<a
										href="showPartyOutStanding.html?currPage=${currPage-1}&step=${step}"><
										Previous</a>
								</c:if>
								<c:if test="${totRecord > endResult + 1}">
									<a
										href="showPartyOutStanding.html?currPage=${currPage+1}&step=${step}">
										Next></a>
								</c:if>
								Records ${startResult+1} to ${endResult+1} of ${totRecord}

								<%--   page   ${currPage} of ${isLastPage} total records ${totRecord}  per page ${step} --%>
							</div></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>











