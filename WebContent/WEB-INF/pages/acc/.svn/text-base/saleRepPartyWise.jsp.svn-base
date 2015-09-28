<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
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
		<div align="center" class="content">
			<form action="saleReportPartyWise.html" method="post">
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
							 <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 1}">
								<div class="element">
									<select id="partyAccId" name="partyAccId" style="width: 150px;">
										<option value="">All</option>
										<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
											<option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
										</c:forEach>
									</select>
								</div>
							</c:if>
							<c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 0}">
					        <div class="element">     
					             <select id="partyAccId" name="partyAccId" style="width: 150px;">
                                   <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
                                       <c:if test="${USER_SESSION.partyAccId == s.id}" >
                                         <option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
                                       </c:if>                                      
                                   </c:forEach>
                                  </select>
                             </div>     
							</c:if>
						</div>
					</div>
					<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
					<br /> <br />
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>
			</form>
			<div>
				<table id="greenTable" width="100%">
					<tr>
						<td colspan="25">${msg}</td>
					</tr>
					<tr>
						<th>PktNo</th>
						<th>Memo No</th>
						<th>Date</th>
						<th>LC/EX</th>
						<th>Shape</th>
						<th>Cts</th>
						<th>Color</th>
						<th>Clarity</th>
						<th>Rap Price</th>
						<th>Discount</th>
						<th>List Price($)</th>
						<th>Price($)/Cts</th>
						<th>USD Total</th>
						<th>Bank Rate</th>
						<th>INR Total</th>
						<th>Terms</th>
						<th>Days</th>
						<th>Due Date</th>
						<th>BrokerName</th>
						<th>Brokrage</th>
						<th>Net Amount(INR)</th>
						<th>Comments</th>
					</tr>
					<c:set var="partyAccId" value=""></c:set>
					<c:set var="compName" value=""></c:set>
					<c:set var="orderId" value="0"></c:set>
					<c:set var="tCts" value="0"></c:set>
					<c:set var="tRate" value="0"></c:set>
					<c:set var="avgRate" value="0"></c:set>
					<c:set var="tRateINR" value="0"></c:set>
					<c:set var="avgRateINR" value="0"></c:set>
					<c:set var="avgRap" value="0"></c:set>
					<c:set var="avgExRate" value="0"></c:set>
					<c:set var="pktCount" value="0"></c:set>
					<c:set var="totalRapPrice" value="0"></c:set>
					<c:set var="totalAvgCalPrice" value="0"></c:set>
					<c:set var="grandTotalInr" value="0"></c:set>
					<c:set var="netTotalInr" value="0"></c:set>
                    <c:set var="grandTotalUSD" value="0"></c:set>
					<c:forEach var="a" items='${pageList.results}' varStatus="count">
						<c:if test="${a.partyAccId != partyAccId}">
							<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
								<c:if test="${a.partyAccId == s.id}">
									<tr>
										<td colspan="22">For Branch : <strong>${s.companyName}/${s.branchCode}</strong>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
                        <c:if test="${a.orderId !=orderId && count.index > 0}">
							<tr>
								<td><strong>${pktCount}</strong></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><strong><fmt:formatNumber type="number" value="${tCts}"
										pattern="0.00" /></strong>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><strong><fmt:formatNumber type="number" 
								        value="${avgRap}" pattern="0.00" /></strong>
								</td>
								<td><strong><fmt:formatNumber type="number"
										value="${pktPrice}" pattern="0.00" /></strong>
								</td>
								<td><strong><fmt:formatNumber type="number" value="${tRate}"
										pattern="0.00" /></strong>
								</td>
								<td><strong><fmt:formatNumber type="number" value="${avgExRate}"
										pattern="0.00" /></strong>
								</td>
								<%-- <td><strong><fmt:formatNumber type="number" value="${avgRateINR/pktCount}" pattern="0.00" /></strong></td> --%>
								<td><strong><fmt:formatNumber type="number" value="${tRateINR}"
										pattern="0.00" /></strong>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr> 
						    <c:set var="addDiscount" value="${a.addDisc}"></c:set>
							<c:set var="remDiscountUSD" value="${((addDiscount)/100)*tRate}"></c:set>
	                        <c:set var="remDiscountINR" value="${((addDiscount)/100)*fmnumber}"></c:set>
							<tr>
							     <td colspan="5" align="center"><strong>Additional Discount(%): </strong></td>
							     <td colspan="1"><strong>${addDiscount}</strong></td>
							     <td colspan="6" align="center"><strong>FinalTotal(USD)</strong></td>
							     <td colspan="1"><strong>${tRate-remDiscountUSD}</strong></td>
							     <td colspan="4" align="center"><strong>FinalTotal(INR)</strong></td>
                                 <td colspan="5"><strong>${fmnumber-remDiscountINR}</strong></td>
                                 <c:set var="grandTotalInr" value="${grandTotalInr+(fmnumber-remDiscountINR)}"></c:set>
                                 <c:set var="netTotalInr" value="${netTotalInr +(fmnumber-remDiscountINR)}"></c:set>
                                 <c:set var="grandTotalUSD" value="${grandTotalUSD +(tRate-remDiscountUSD)}"></c:set>
							</tr>
							<c:set var="tCts" value="0"></c:set>
							<c:set var="tRate" value="0"></c:set>
							<c:set var="avgRate" value="0"></c:set>
							<c:set var="tRateINR" value="0"></c:set>
							<c:set var="avgRateINR" value="0"></c:set>
							<c:set var="avgRap" value="0"></c:set>
							<c:set var="avgExRate" value="0"></c:set>
							<c:set var="pktCount" value="0"></c:set>
							<c:set var="totalRapPrice" value="0"></c:set>
							<c:set var="totalAvgCalPrice" value="0"></c:set>
							<c:set var="pktPrice" value="0"></c:set>
						</c:if>
						<c:set var="partyAccId" value="${a.partyAccId}"></c:set>
						<c:set var="tCts" value="${tCts+ a.cts}"></c:set>
						<c:set var="pktCount" value="${pktCount+1}"></c:set>
						<c:set var="tRate" value="${tRate + (a.sellRate * a.cts)}"></c:set>
						<c:set var="avgRate" value="${(avgRate+ a.sellRate)}"></c:set>
						<c:set var="tRateINR"
							value="${tRateINR + (a.sellRate * a.exrate * a.cts)}"></c:set>
						<c:set var="avgRateINR"
							value="${(avgRateINR + (a.sellRate * a.exrate))}"></c:set>
						<c:if test="${a.rapPrice >  0}">
							<c:set var="totalRapPrice"
								value="${totalRapPrice + (a.rapPrice * a.cts)}"></c:set>
							<c:set var="totalAvgCalPrice"
								value="${totalAvgCalPrice + (a.sellRate * a.cts)}"></c:set>
						</c:if>
						<c:if test="${avgExRate == 0}">
							<c:set var="avgExRate" value="${avgExRate+ a.exrate}"></c:set>
						</c:if>
						<c:if test="${avgExRate != 0 && a.exrate != 0}">
							<c:set var="avgExRate" value="${(avgExRate+ a.exrate)/2}"></c:set>
						</c:if>
						<c:set var="avgRap" value="${((tRate/totalRapPrice)-1)*100}"></c:set> 
						<c:set var="pktPrice" value="${pktPrice+(avgRate/pktCount)}"></c:set>
						<c:if test="${a.companyName != compName}">
							<tr>
								<td colspan="22" style="border-bottom: 1px red solid;"><p></p><p></p>
								</td>
							</tr>
							<tr>
								<td colspan="22">Buyer Name :- <strong>${a.companyName}</strong>
								</td>
							</tr>
						</c:if>
						<tr>
							<td>${a.pktCode}</td>
							<td>${a.orderId}</td>
							<td>${a.orderDate}</td>
							<td>${a.accType}</td>
							<td>${a.sh}</td>
							<td>${a.cts}</td>
							<td>${a.c}</td>
							<td>${a.pu}</td>
							<td>${a.rapPrice}</td>
							<td>${a.rap}</td>
							<td>${a.memoRate}</td>
							<td><fmt:formatNumber type="number" value="${a.sellRate}"
									pattern="0.00" /></td>
							<td><fmt:formatNumber type="number"
									value="${a.cts * a.sellRate}" pattern="0.00" var="fmnumber" />
								<c:out value="${fmnumber}"></c:out></td>
							<%-- 							<td <c:if test ="${a.exrate==0}"> style="color:red;"</c:if>>
                                <fmt:formatNumber type="number" value="${a.sellRate * a.exrate}" pattern="0.00" var="fmnumber"/>
                                <c:out value="${fmnumber}"></c:out>
							</td>  --%>
							<td <c:if test ="${a.exrate==0}"> style="color:red;"</c:if>>${a.exrate}</td>
							<td <c:if test ="${a.exrate==0}"> style="color:red;"</c:if>>
								<fmt:formatNumber type="number"
									value="${a.cts * a.sellRate *a.exrate}" pattern="0.00"
									var="fmnumber" /> <c:out value="${fmnumber}"></c:out>
							</td>
							<td>${a.term}</td>
							<td>${a.days}</td>
							<td>${a.date}</td>
							<td>${a.brokerName}</td>
							<td>${a.brokerage}</td>
							<td><fmt:formatNumber type="number"
									value="${fmnumber - ((a.brokerage/100)*fmnumber)}"
									pattern="0.00" />
							</td>
							<td>${a.comments}</td>
						</tr>
						<c:set var="compName" value="${a.companyName}"></c:set>
						<c:set var="orderId" value="${a.orderId}"></c:set>
					</c:forEach>
					<tr>
						<td><strong>${pktCount}</strong></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>

						<td><strong><fmt:formatNumber type="number" value="${tCts}"
								pattern="0.00" /></strong></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><strong><fmt:formatNumber
								type="number" value="${avgRap}" pattern="0.00" /></strong>
						</td>
						<td><strong><fmt:formatNumber type="number"
								value="${pktPrice}" pattern="0.00" /></strong>
						</td>
						<td><strong><fmt:formatNumber type="number" value="${tRate}"
								pattern="0.00" /></strong>
						</td>
						<td><strong><fmt:formatNumber type="number" value="${avgExRate}"
								pattern="0.00" /></strong>
						</td>
						<%-- <td><strong><fmt:formatNumber type="number" value="${avgRateINR/pktCount}" pattern="0.00" /></strong></td> --%>
						<td><strong><fmt:formatNumber type="number" value="${tRateINR}"
								pattern="0.00" /></strong>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
                        <td colspan="5" align="center"><strong>Additional Discount(%): </strong></td>
                        <td colspan="1"><strong>${addDiscount}</strong></td>
                        <td colspan="6" align="center"><strong>FinalTotal(USD)</strong></td>
                        <td colspan="1"><strong>${tRate-remDiscountUSD}</strong></td>
                        <td colspan="4" align="center"><strong>FinalTotal(INR)</strong></td>
                        <td colspan="1"><strong>${fmnumber-remDiscountINR}</strong></td>
                    </tr>
                    <c:set var="grandTotalInr" value="${grandTotalInr+(fmnumber-remDiscountINR)}"></c:set>
                    <c:set var="netTotalInr" value="${netTotalInr +(fmnumber-remDiscountINR)}"></c:set>
                    <c:set var="grandTotalUSD" value="${grandTotalUSD +(tRate-remDiscountUSD)}"></c:set>
					<tr>
						<td><strong>${pageList.userdata['pktCode'] } </strong></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><strong>${pageList.userdata['cts'] } </strong>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<!-- <strong><fmt:formatNumber type="number" value="${pageList.userdata['RAP'] }" pattern="0.00" /> </strong>-->
						</td>
						<td><strong><fmt:formatNumber type="number"
									value="${pageList.userdata['selRate'] }" pattern="0.00" /> </strong>
						</td>
						<td><strong><fmt:formatNumber type="number"
                                    value="${grandTotalUSD} " pattern="0.00" /></strong></td>
						<td></td>
						<td><strong><fmt:formatNumber type="number"
									value="${grandTotalInr} " pattern="0.00" />
						</strong>
						</td>
						<%--  <td><strong>${pageList.userdata['baseRate'] } </strong></td> --%>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><strong><fmt:formatNumber type="number"
									value="${netTotalInr} " pattern="0.00" />
						</strong>
						</td>
						<td></td>
					</tr>

				</table>
			</div>
		</div>
	</div>
</body>
</html>