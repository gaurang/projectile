<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile :</title>
<script type="text/javascript">
$(document).ready(function(){
    
    $( "#fromDate").DatePicker({
        format:'d-m-Y',
        current: '${FROM_DATE}',
        calendars: 1,
        date: '${FROM_DATE}',
        onChange: function(formated, dates){
            $('#fromDate').val(formated);
            $('#fromDate').DatePickerHide();
        }
    });
    $("#fromDate").val('${FROM_DATE}');

    $("#toDate").DatePicker({
        format:'d-m-Y',
        current: '${TO_DATE}',
        calendars: 1,
        date: '${TO_DATE}',
        onChange: function(formated, dates){
            $('#toDate').val(formated);
            $('#toDate').DatePickerHide();
        }
    });
    $("#toDate").val('${TO_DATE}');
    $('#submitPerReport').validate();
});
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<div id="mainPerReport">
		<br />
		<form id="submitPerReport" action="submitPerReport.html" method="POST">
			<p>
				<label id=from> <spring:message code="prof.common.Util.From"></spring:message>
				</label> <input type="text" id="fromDate" name="fromDate" class="required" />
				<label id=to> <spring:message code="prof.common.Util.To"></spring:message>
				</label> <input type="text" id="toDate" name="toDate" class="required" /> <label
					id=party> <spring:message code="prof.common.Util.partyName"></spring:message>
				</label> <select name="partyName" id="partyName">
					<option value="All">All</option>
					<c:forEach items="${PARTY_LIST}" var="a" varStatus="count">
						<option value="${a.companyName}"
							<c:if test='${a.companyName == param.partyName}'>selected</c:if>>${a.companyName}</option>
					</c:forEach>
				</select> <input type="submit" id="submit" value="show" name="submit" />
			</p>
		</form>
		<table id="greenTable" width="100%">
			<tr>
				<th><spring:message code="prof.common.Util.date"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.dueDate"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.pktcode"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.purchaseId"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.cts"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.amount"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.expenses"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.soldCts"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.sold"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.profit"></spring:message>
				</th>
				<th><spring:message code="prof.common.Util.symbol"></spring:message>
				</th>
			</tr>
			<tr>
			</tr>
			<c:set var="totweight" value="0" scope="request"></c:set>
			<c:set var="totamount" value="0" scope="request"></c:set>
			<c:set var="totcts" value="0" scope="request"></c:set>
			<c:set var="totfinalRate" value="0" scope="request"></c:set>
			<c:set var="compName" value="" scope="request"></c:set>
			<c:set var="gtotweight" value="0" scope="request"></c:set>
			<c:set var="gtotamount" value="0" scope="request"></c:set>
			<c:set var="gtotcts" value="0" scope="request"></c:set>
			<c:set var="gtotfinalRate" value="0" scope="request"></c:set>

			<c:set var="count" value="0" scope="request"></c:set>
			<c:forEach var="p" items='${PER_DATA}' varStatus="cnt">

				<c:if test="${p.companyName != compName && cnt.index > 0}">
					<tr align="right">
						<td><strong>Total</strong>
						</td>
						<td></td>
						<td><strong><fmt:formatNumber type="number"
									value="${totweight}" pattern="0.00" />
						</strong>
						</td>
						<td><strong><fmt:formatNumber type="number"
									value="${totamount}" pattern="0.00" />
						</strong>
						</td>
						<td></td>
						<td><strong><fmt:formatNumber type="number"
									value="${totcts}" pattern="0.00" />
						</strong>
						</td>
						<td><strong><fmt:formatNumber type="number"
									value="${totfinalRate}" pattern="0.00" />
						</strong>
						</td>
						<td></td>
						<td></td>
					</tr>
					<c:set var="totweight" value="0" scope="request"></c:set>
					<c:set var="totamount" value="0" scope="request"></c:set>
					<c:set var="totcts" value="0" scope="request"></c:set>
					<c:set var="totfinalRate" value="0" scope="request"></c:set>

				</c:if>
				<c:if test="${p.companyName != compName }">
					<tr>
						<td colspan="9"><spring:message
								code="prof.common.Util.partyName"></spring:message> <strong>${p.companyName}</strong>
						</td>
					</tr>
					<tr>
					</tr>
				</c:if>

				<tr align="right">
					<td align="center">${p.purchaseDate}</td>
					<td align="center">${p.dueDate}</td>
					<td align="center">${p.pktcode}</td>
					<td align="center">${p.purchaseId}</td>
					<td>${p.weight}</td>
					<td>${p.amount}</td>
					<td>${p.expenses}</td>
					<td>${p.cts}</td>
					<td>${p.finalRate}</td>
					<td></td>
					<td></td>
				</tr>
				<c:set var="totweight" value="${totweight + p.weight}"
					scope="request"></c:set>
				<c:set var="totamount" value="${totamount + p.amount}"
					scope="request"></c:set>
				<c:set var="totcts" value="${totcts + p.cts}" scope="request"></c:set>
				<c:set var="totfinalRate" value="${totfinalRate + p.finalRate}"
					scope="request"></c:set>






				<c:set var="compName" value="${p.companyName}"></c:set>

				<c:set var="gtotweight" value="${gtotweight + p.weight}"
					scope="request"></c:set>
				<c:set var="gtotamount" value="${gtotamount + p.amount}"
					scope="request"></c:set>
				<c:set var="gtotcts" value="${gtotcts + p.cts}" scope="request"></c:set>
				<c:set var="gtotfinalRate" value="${gtotfinalRate + p.finalRate}"
					scope="request"></c:set>
			</c:forEach>
			<tr align="right">
				<td><strong>Total</strong>
				</td>
				<td></td>
				<td><strong><fmt:formatNumber type="number"
							value="${totweight}" pattern="0.00" />
				</strong>
				</td>
				<td><strong><fmt:formatNumber type="number"
							value="${totamount}" pattern="0.00" />
				</strong>
				</td>
				<td></td>
				<td><strong><fmt:formatNumber type="number"
							value="${totcts}" pattern="0.00" />
				</strong>
				</td>
				<td><strong><fmt:formatNumber type="number"
							value="${totfinalRate}" pattern="0.00" />
				</strong>
				</td>
				<td></td>
				<td></td>
			</tr>

			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr align="right">
				<td><strong>Grand Total</strong>
				</td>
				<td></td>
				<td><strong><fmt:formatNumber type="number"
							value="${gtotweight}" pattern="0.00" />
				</strong>
				</td>
				<td><strong><fmt:formatNumber type="number"
							value="${gtotamount}" pattern="0.00" />
				</strong>
				</td>
				<td></td>
				<td><strong><fmt:formatNumber type="number"
							value="${gtotcts}" pattern="0.00" />
				</strong>
				</td>
				<td><strong><fmt:formatNumber type="number"
							value="${gtotfinalRate}" pattern="0.00" />
				</strong>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<!--Main Per Report End Here  -->
</body>
</html>