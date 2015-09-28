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
		<div class="heading">Party GL Report</div>
		<div align="center" class="content">
			<form action="saleReportPartyWise.html" method="post">
				<div>
					<%-- <div style="width: 370px;">
				<div class="row">
					<div class="element">
					<label for="fromDate">From Date</label>
					</div>
					<div class="element">
					 <input type="text" name="fromDate" id="fromDate" class="date" <c:if test="${fromDate != ''}">value="${fromDate}"</c:if>"/>
					</div>
				</div>
				<div class="row">
					<div class="element">
					<label for="toDate">To Date</label> 
					</div>
					<div class="element">
					<input type="text" name="toDate" id="toDate" class="date" <c:if test="${toDate != ''}">value="${toDate}"</c:if>/>
					</div>
				</div>
				<div class="row">
					<div class="element">
					<label for="partyAccId">Select Branch</label>
					</div>
					<div class="element">
					<select id="partyAccId" name="partyAccId" style="width: 110px;">
						<option value="">All</option>
						<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
						 	<option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
						</c:forEach>
					</select>	
					</div>
				</div>				
			</div> 
			<br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<br/><br/>
			<div class="row">
				<input type="submit" name="Submit" id="Submit" value="submit">
			</div>--%>
				</div>
			</form>
			<div>
				<div>${msg}</div>
				<table id="greenTable" width="100%">


					<tr>
						<td colspan="5">
							${PARTY_DETAIL.companyName}/${PARTY_DETAIL.termCode}/${PARTY_DETAIL.accType}/${PARTY_DETAIL.branchCode}
						</td>
					</tr>


					<tr>
						<th>Type</th>
						<th>Date</th>
						<th>Due Date</th>
						<th>Charges</th>
						<th>Credits</th>
					</tr>

					<tr>
						<td colspan="5">Op Balance - ${opBal}</td>
					</tr>
					<c:forEach var="l" items='${LIST}' varStatus="count">
						<tr>
							<td>${l.type}</td>
							<td>${l.date}</td>
							<td>${l.dueDate}</td>
							<td><c:if test="${l.type == 'SALES-INVOICE'}">
								${l.balance}
							</c:if></td>
							<td><c:if test="${l.type != 'SALES-INVOICE'}">
								${l.balance}
							</c:if></td>

						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">Closing Balance - ${clBal}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>