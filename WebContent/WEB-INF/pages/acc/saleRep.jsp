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
	<c:if test="${fromDate !=null }">
		$( "#fromDate" ).val('${fromDate}');
	</c:if>
	<c:if test="${toDate !=null }">
		$( "#toDate" ).val('${toDate}');
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
		<div class="heading">Sale Report</div>
		<div align="center" id="content">
			<form action="" method="post" id="saleForm" onsubmit="func('saleReport.html');">
				<div>
					<div style="width: 370px;">
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
								<input type="text" name="toDate" id="toDate" class="date"
									<c:if test="${toDate != ''}">value="${toDate}"</c:if> />
							</div>
						</div>

						<div class="row">
							<div class="element">
								<label for="pktCode">Packet Nos</label>
							</div>
							<div class="element">
								<input type="text" name="pktCode" id="pktCode"
									<c:if test="${pktCode != ''}">value="${pktCode}"</c:if> />
							</div>
						</div>
						<div class="row">
							<div class="element">
								<label for="order">Sort by</label>
							</div>
							<div class="element">
								<select id="sort" name="sort" style="width: 150px;">
									<option value="om.orderDate"
										<c:if test="${sort == 'om.orderDate'}">selected</c:if>>
										Sell Date</option>
									<option value="om.id"
										<c:if test="${sort == 'om.id'}">selected</c:if>>Sell
										Id</option>
								</select>

							</div>
						</div>
						<div class="row">
							<div class="element">
								<label for="sortType">Sorting</label>
							</div>
							<div class="element">
								<select id="sortType" name="sortType" style="width: 150px;">
									<option value="asc"
										<c:if test="${sortType == 'asc'}">selected</c:if>>Ascending</option>
									<option value="dsc"
										<c:if test="${sortType == 'dsc'}">selected</c:if>>Descending</option>
								</select>

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
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br /> <br />
					<br />
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>
			</form>
			<div>
				<table id="greenTable" width="100%">
					<tr>
						<td colspan="18">${msg}</td>
					</tr>
					<tr>
						<th>Memo No</th>
						<th>Date</th>
						<th>PktCode</th>
						<th>Lab</th>
						<th>Shape</th>
						<th>Cts</th>
						<th>Color</th>
						<th>Clarity</th>
						<th>Term</th>
						<th>LC/EX</th>
						<th>Party Name</th>
						<th>BrokerName</th>
						<th>Brokrage</th>
						<th>Discount</th>
						<th>Sale Price</th>
						<th>Total Price</th>
						<th>Ex Rate</th>
						<th>Total Price (INR)</th>

					</tr>
					<c:set var="partyAccId" value=""></c:set>
					<c:forEach var="a" items='${pageList.results}'>
						<c:if test="${a.partyAccId != partyAccId}">
							<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
								<c:if test="${a.partyAccId == s.id}">
									<tr>
										<td colspan="19">For Branch : <strong>${s.companyName}/${s.branchCode}</strong>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>

						<tr>
							<td>${a.orderId}</td>
							<td>${a.orderDate}</td>
							<td>${a.pktCode}</td>
							<td>${a.lab}</td>
							<td>${a.sh}</td>
							<td>${a.cts}</td>
							<td>${a.c}</td>
							<td>${a.pu}</td>
							<td>${a.term}</td>
							<td>${a.accType}</td>
							<td>${a.companyName}</td>
							<td>${a.brokerName}</td>
							<td>${a.brokerage}</td>
							<td>${a.rap}</td>
							<td>${a.sellRate}</td>
							<td>${a.totalRate}</td>
							<td <c:if test ="${a.exrate==0}"> style="color:red;"</c:if>>${a.exrate}</td>
							<td <c:if test ="${a.exrate==0}"> style="color:red;"</c:if>><fmt:formatNumber
									type="number" value="${a.totalRate*a.exrate}" pattern="0.00" />
							</td>

						</tr>

						<c:set var="partyAccId" value="${a.partyAccId}"></c:set>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td><strong>${pageList.userdata['pktCode'] } </strong></td>
						<td colspan="2"></td>
						<td><strong><fmt:formatNumber type="number"
									value="${pageList.userdata['cts'] }" pattern="0.00" />
						</strong>
						</td>
						<td colspan="7"></td>
						<td><strong>
								<!--<fmt:formatNumber type="number" value="${pageList.userdata['RAP'] }" pattern="0.00" /> -->
						</strong></td>
						<td><strong><fmt:formatNumber type="number"
									value="${pageList.userdata['selRate'] }" pattern="0.00" /> </strong></td>
						<td><strong><fmt:formatNumber type="number"
									value="${pageList.userdata['totalRate'] }" pattern="0.00" />
						</strong></td>
						<td colspan="2"></td>
					</tr>

				</table>
			</div>
		</div>
	</div>
</body>
</html>