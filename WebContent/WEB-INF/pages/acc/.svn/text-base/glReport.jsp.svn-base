<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#fromDate").DatePicker({
			format : 'd-m-Y',
			current : '${FROM_DATE}',
			calendars : 1,
			date : '${FROM_DATE}',
			onChange : function(formated, dates) {
				$('#fromDate').val(formated);
				$('#fromDate').DatePickerHide();
			}
		});
		$("#fromDate").val('${FROM_DATE}');
		$("#toDate").DatePicker({
			format : 'd-m-Y',
			current : '${TO_DATE}',
			calendars : 1,
			date : '${TO_DATE}',
			onChange : function(formated, dates) {
				$('#toDate').val(formated);
				$('#toDate').DatePickerHide();
			}
		});
		$("#toDate").val('${TO_DATE}');
		$("#accCode").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300
		});
	});
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<div>GL Report</div>
			<form action="glRepSubmit.html" method="post">
				<div style="width: 370px;">
					<div class="row">
						<div class="element">
							<label for="fromDate">From Date</label>
						</div>
						<div class="element">
							<input type="text" name="fromDate" id="fromDate" class="date" />
						</div>
					</div>
					<div class="row">
						<div class="element">
							<label for="toDate">To Date</label>
						</div>
						<div class="element">
							<input type="text" name="toDate" id="toDate" class="date" />
						</div>
					</div>
					<div class="row">
						<div class="element">
							<label for="accCode">GL Account Code</label>
						</div>
						<div class="element">
							<select id="accCode" name="accCode" style="width: 150px;" multiple="multiple">
								<option value="-1">All</option>
								<c:forEach var="a" items='${glAccList}'>
									<option value="${a.code}" <c:if test="${codes != null && codes == a.code}">selected="selected"</c:if>>${a.name}</option>
							     </c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>
			</form>
			<c:if test="${show == 1}">
				<div>
					<table id="greenTable" width="100%">
						<tr>
							<th>Type</th>
							<th>Ref</th>
							<th>Date</th>
							<th>Company Name</th>
							<th>Debit</th>
							<th>Credit</th>
							<th>Balance</th>
						</tr>
						<c:set var="accCode" value="0" scope="request" />
						<c:set var="accCodeClosing" value="0" scope="request" />
						<c:forEach var="a" items='${glList}'>
							<c:if test="${a.glAccNo != accCode}">
								<c:forEach var="b" items="${glClBalList}">
									<c:if test="${accCode != 0 && accCode == b.str1}">
										<tr>
											<td colspan="7"><strong>Closing Balance -
													${b.str2} </strong></td>
										</tr>
										<c:set var="accCodeClosing" value="${b.str1}" />
									</c:if>
								</c:forEach>
								<c:forEach var="op" items="${glOpBalList}">
									<c:if test="${a.glAccNo==op.str1}">
										<tr>
											<td colspan="7"><strong>Account ${op.str1}
													:${op.str3}
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													Opening Balance - ${op.str2} </strong></td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
							<tr>
								<td>${a.type}</td>
								<td>${a.ref}</td>
								<td>${a.date}</td>
								<td>${a.companyName}</td>
								<td>${a.debit}</td>
								<td>${a.credit}</td>
								<td>${a.balance}</td>
							</tr>
							<c:set var="accCode" value="${a.glAccNo}" scope="request" />
						</c:forEach>
						<c:forEach var="cl" items="${glClBalList}" varStatus="0">
							<c:if test="${accCode == cl.str1}">
								<tr>
									<td colspan="7"><strong>Closing Balance -
											${cl.str2} </strong></td>
								</tr>
							</c:if>
						</c:forEach>

					</table>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>