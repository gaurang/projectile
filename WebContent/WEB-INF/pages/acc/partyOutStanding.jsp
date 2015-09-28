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

			<form id="prtoutStd" action="submitPartyOutStanding.html"
				method="post">
				<input type="hidden" name="refNo" id="refNo" value="${param.refNo}" />
				<div align="center" style="width: 100%;">
					<table id="greenTable" width="60%" border="0" align="center">
						<tbody>
							<tr>
								<td width="200px">ReferenceNo</td>
								<td><input type="text" name="refrno" id="refrno" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.referneceNo}</c:if>" />
								</td>

							</tr>
							<tr>
								<td>PartyId</td>
								<td><select id="prtyId" name="prtyId" style="width: 150px;">

										<c:forEach items="${PARTY_LIST}" var="p" varStatus="count">
											<!--  <option value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.partyID}</c:if>${p.id}">${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option> -->
											<option value="${p.id}"
												<c:if test="${PRTLT_BY_REF.referneceNo!=0 && PRTLT_BY_REF.partyID == p.id}">selected</c:if>>${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Date</td>
								<td><input type="text" name="date" id="date" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.fdate}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>DueDate</td>
								<td><input type="text" name="duDate" id="duDate" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.dueDate}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>BrokerId</td>
								<td><select id="brokId" name="brokId" style="width: 150px;">

										<c:forEach items="${BROKER_LIST }" var="b" varStatus="count">
											<!--  <option value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.brokerID}</c:if>${b.id }">${b.description}</option>  -->
											<option value="${b.id}"
												<c:if test="${PRTLT_BY_REF.referneceNo!=0 && PRTLT_BY_REF.brokerID == b.id}">selected</c:if>>${b.description}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Amount</td>
								<td><input type="text" name="amt" id="amt" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.ammount}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>Tax</td>
								<td><input type="text" name="tax" id="tax" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.tax}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>Discount</td>
								<td><input type="text" name="discnt" id="discnt" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.discount}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>Expence</td>
								<td><input type="text" name="expnce" id="expnce" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.expence}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>Final Amount</td>
								<td><input type="text" name="fnlamt" id="fnlamt" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.finalAmmount}</c:if>" />
								</td>
							</tr>
							<tr>
								<td>Description</td>
								<td><input type="text" name="descrb" id="descrb" size="20"
									value="<c:if test="${PRTLT_BY_REF.referneceNo!=0}">${PRTLT_BY_REF.description}</c:if>" />
								</td>
							</tr>
						</tbody>

					</table>
					<input type="submit" id="outstdsubm" name="outstdsubm"
						value="Submit" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>














