<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script type="text/javascript">
$(document).ready(function(){
	$( "#fromDate" ).DatePicker({
		format:'d-m-Y',
		current: '${CURR_DATE}',
		calendars: 1,
		date: '${CURR_DATE}',
		onChange: function(formated, dates){
			$('#fromDate').val(formated);
			$('#fromDate').DatePickerHide();
		}
	});
	$( "#fromDate" ).val('${CURR_DATE}');
	$( "#toDate" ).DatePicker({
		format:'d-m-Y',
		current: '${CURR_DATE}',
		calendars: 1,
		date: '${CURR_DATE}',
		onChange: function(formated, dates){
			$('#toDate').val(formated);
			$('#toDate').DatePickerHide();
		}
	});
	$( "#toDate" ).val('${CURR_DATE}');
	<c:if test="${param.fromDate !=null }">
		$( "#fromDate" ).val('${param.fromDate}');
	</c:if>
	<c:if test="${param.toDate !=null }">
		$( "#toDate" ).val('${param.toDate}');
	</c:if>
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
			<form action="bankStatementReport.html" method="post">
				<div>
					<div style="width: 370px;">
						<div class="row">
							<div class="element">
								<label for="fromDate">From Date</label>
							</div>
							<div class="element">
								<input type="text" name="fromDate" id="fromDate" class="date" <c:if test="${param.fromDate != ''}">value="${param.fromDate}"</c:if>"/>
							</div>
						</div>
						<div class="row">
							<div class="element">
								<label for="toDate">To Date</label>
							</div>
							<div class="element">
								<input type="text" name="toDate" id="toDate" class="date"
									<c:if test="${param.toDate != ''}">value="${param.toDate}"</c:if> />
							</div>
						</div>
						<div class="row">
							<div class="element">
								<label for="bankAccId">Select Account</label>
							</div>
							<div class="element">
								<select id="bankAccId" name="bankAccId" style="width: 110px;">
									<option value="">All</option>
									<c:forEach items="${BANK_ACC_LIST}" var="s" varStatus="cnt">
										<option value="${s.id}"
											<c:if test="${param.bankAccId == s.id}">selected</c:if>>${s.bankAccountName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<br />
					<br />
					<br /> <br />
					<br />
					<br />
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>
			</form>
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


					<c:forEach var="b" items='${bList}'>
						<c:if test="${b.bankAccountName != bankAccountName}">
							<tr>
								<td colspan="7"><strong> Bank:
										${b.bankAccountName}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Opening
										Balance - ${bnkStmOpBal} </strong></td>
							</tr>
						</c:if>
						<tr>
							<td>${b.type}</td>
							<td>${b.ref}</td>
							<td>${b.date}</td>
							<td>${b.companyName}</td>
							<td>${b.debit}</td>
							<td>${b.credit}</td>
							<td>${b.balance}</td>
							<c:set var="bankAccountName" value="${b.bankAccountName}"></c:set>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7"><strong>Closing Balance -
								${bankStClBal} </strong></td>
					</tr>

				</table>

			</div>
		</div>
	</div>
</body>
</html>