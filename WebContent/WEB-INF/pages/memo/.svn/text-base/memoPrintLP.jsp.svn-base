<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<title>Projectile:</title>
<style lang="text/css">
/*	
.memoTable{
	background-color: #CCCCCC ;

}*/
.textClass {
	font-size: 9px;
	font-family: Arial, Helvetica, sans-serif;
}

.textClass2 {
	font-size: 11px;
	font-family: Arial, Helvetica, sans-serif;
}

.memoTable th {
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
	padding: 3px;
	font-weight: bold;
	width: auto;
	white-space: nowrap;
	border-bottom: 1px solid black;
	border-top: 1px solid black;
}

.memoTable td {
	background-color: #ffffff;
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
	border-bottom: 1px solid black;
}

.memoTable2 th {
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
	padding: 3px;
	font-weight: bold;
	width: auto;
	white-space: nowrap;
}

.memoTable2 td {
	background-color: #ffffff;
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
}

.memoTable tr:last-child td {
	background-color: #ffffff;
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}

/*.memoTable tr{
	border-bottom: 2px solid #000;
}*/
#title {
	font-size: 10px;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}

.mainTable {
	margin-left: 10px;
	margin-right: 5px;
	width: 50%;
}

@page land {
	size: landscape;
}

@page port {
	size: portrait;
}

.landscapePage {
	page: land;
}

.portraitPage {
	page: port;
}

body {
	margin: 0;
}
</style>
</head>
<body class="landscapePage">

	<c:forEach begin="0" end="${memoCount-1}" var="c">
		<c:set var="startPt" value="${c*10}" />
		<c:set var="endPt" value="${(c*10)+9}" />
		<table height="100%">
			<tr>
				<td id="memo" align="right" width="50%">
					<table class="mainTable" width="100%">
						<tr>
							<td align="center"></td>
						</tr>
						<tr>
							<td style="height: 100px;"></td>
						</tr>
						<tr>
							<td align="left">
								<table width="80%">
									<tr align="center">
										<td style="background-color: #ffffff;" align="center">
											<table class="memoTable2" width="100%" cellspacing="0">
												<tfoot>
													<tr>
														<th>Memo No</th>
														<td>${orderMasterData.id}</td>
														<th>Party</th>
														<td>${orderMasterData.companyName}</td>
														<th>Contact</th>
														<td>${otherDetails.phoneNo1} <c:if
																test="${otherDetails.phoneNo2 != null}">/${otherDetails.phoneNo2}</c:if>
															- ${orderMasterData.contactPerson}</td>
													</tr>
													<tr>
														<th>Term</th>
														<td>${otherDetails.termDesc}</td>
														<th>Date</th>
														<td>${orderMasterData.orderDate}</td>
													</tr>
												</tfoot>
											</table>

											<table class="memoTable" cellpadding="3" cellspacing="0"
												width="100%">
												<tr align="center">
													<th>Srl No.</th>
													<th>Packet No.</th>
													<th colspan="3">Description</th>
													<th>Carats</th>
													<th>Discount</th>
													<th>Price(Per Cts)</th>
													<th>Total</th>
													<th>Remark</th>
												</tr>
												<c:set var="cnt" value="0"></c:set>
												<c:forEach items="${orderMasterData.packetList}" var="list"
													varStatus="status" begin="${startPt}" end="${endPt}">
													<tr>
														<td align="center">${status.count}</td>
														<td>${list.pktCode}</td>
														<td colspan="3" style="white-space: nowrap;">
															${list.c} ${list.pu} ${list.lab}</td>

														<td><fmt:formatNumber value="${list.cts}"
																maxFractionDigits="2" minFractionDigits="2" />
														</td>
														<td><fmt:formatNumber value="${list.rap}"
																maxFractionDigits="0" />
														</td>
														<td><fmt:formatNumber value="${list.rate}"
																maxFractionDigits="2" minFractionDigits="2" />
														</td>
														<td><fmt:formatNumber value="${list.rate*list.cts}"
																maxFractionDigits="2" minFractionDigits="2" />
														</td>
														<td>${list.remark}</td>

													</tr>

													<c:set var="cnt" value="${cnt + 1 }"></c:set>

												</c:forEach>

												<c:forEach var="i" begin="${cnt}" end="10" step="1">
													<c:if test="${cnt le 10}">
														<tr>
															<td colspan="10">&nbsp;</td>
														</tr>
														<c:set var="cnt" value="${cnt + 1}"></c:set>
													</c:if>

												</c:forEach>


												<tr>
													<td colspan="1"><c:if
															test="${orderMasterData.accType == 'L'  && orderMasterData.exrate != null && orderMasterData.exrate > 0}">
									Ex Rate :${orderMasterData.exrate}</c:if>
													</td>
													<td colspan="4">Totals</td>
													<td colspan="1" align="center">${orderMasterData.totalCts}</td>
													<td colspan="2"></td>
													<td colspan="1">${orderMasterData.totalAmount}</td>
													<td colspan="1"></td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
					</table></td>
				<!-- 		<td id="${param.copy}" width="50%" align="right">
			<c:if test="${param.copy ==2}">
				<table width="100%" class="mainTable">
				<tr>
					<td align="center">
					<table width="100%">
						<tr>
							<td colspan="2" class="textClass" align="center"><spring:message code="b2b.memo.top" ></spring:message></td>
						</tr>
						<tr align="center">
						<td style="width:33%;">
							<img src='<%="http://" + request.getServerName() + ":"
							+ request.getLocalPort() + request.getContextPath()%>/images/l.png' width="60"/>
						</td>
						<td >
						<table width="100%">
							<tr>
								<td align="left">
								<span style="color: #3d555e;font-size: 14px;font-weight: bold;"><spring:message code="b2b.companyname" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #404040;font-size: 10px;"><spring:message code="b2b.company.tagline" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 10px;">
									<spring:message code="b2b.company.profile" ></spring:message>
								</span>
								</td>
							</tr>
						</table>
						</td>
						</tr>
						<tr>
							<td colspan="2" align="left"><hr /> </td>
						</tr>
						<tr>
							<td colspan="2" align="center" class="textClass">
							<spring:message code="b2b.memo.address" ></spring:message>	
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" class="textClass">
								<spring:message code="b2b.memo.ack" ></spring:message>			
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						<table width="100%">
		                  <tr align="center">
		                    <td style="background-color: #ffffff;" align="center" >
									<span id="title" style="width: 70%;">Memo Details</span>
									<table class="memoTable" width="100%">
											<tr>
											<th>Memo No </th><td> ${orderMasterData.id}</td>
											<th>Party Name</th><td> ${orderMasterData.companyName}</td>
											<th>Contact No.</th><td> ${otherDetails.phoneNo1} <c:if test="${otherDetails.phoneNo2 != null}">/${otherDetails.phoneNo2}</c:if> </td>
											
										</tr>	
										<tr>
											<th>Memo Type</th><td> ${orderMasterData.orderType}</td>
											<th>Broker</th><td> ${orderMasterData.brokerName}</td>
											<th></th><td></td>
										</tr>
										<tr>
											<th>Term</th><td> ${otherDetails.termDesc}</td>
											<th>Contact</th><td> ${orderMasterData.contactPerson}</td>
											<th>Memo Date </th><td> ${orderMasterData.orderDate}</td>
										</tr>
									</table>
							
								<span id="title" > Memo Packets</span>
								<table class="memoTable"  cellpadding="3" cellspacing="3" width="100%">
									<tr align="center">
										<th >Srl No.</th>
										<th>Packet No.</th>
										<th>Color</th>
										<th>Clarity</th>
										<th>Lab</th>
										<th>Pcs</th>
										<th>Carats</th>
										<th>Discount</th>
										<th>Price(Per Cts)</th>
										<th>Total</th>
									</tr>
									<c:forEach items="${orderMasterData.packetList}" var="list" varStatus="status" begin="${startPt}" end="${endPt}">
									<tr>
										<td> ${status.count}</td>
										<td> ${list.pktCode}</td>
										<td> ${list.c}</td>
										<td> ${list.pu}</td>
										<td> ${list.lab}</td>
										<td> ${list.pcs}</td>
										
										<td><fmt:formatNumber value="${list.cts}" maxFractionDigits="2" minFractionDigits="2" /></td>
										<td><fmt:formatNumber value="${list.rap}" maxFractionDigits="0" /></td>
										<td><fmt:formatNumber value="${list.rate}" maxFractionDigits="2" minFractionDigits="2" /></td>
										<td><fmt:formatNumber value="${list.rate*list.cts}" maxFractionDigits="2" minFractionDigits="2" /></td>
									</tr>
									</c:forEach>
									<tr>
										<th colspan="3"> <c:if test="${orderMasterData.accType == 'L'  && orderMasterData.exrate != null && orderMasterData.exrate > 0}">
										Ex Rate :${orderMasterData.exrate}</c:if></th>
										<th colspan="3"> Totals</th>
										<th colspan="1"> ${orderMasterData.totalCts}</th>
										<th colspan="2"> </th>
										<th colspan="1"> ${orderMasterData.totalAmount}</th>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="left">
		                    <td >
		                    	<table width="100%">
		                    		<tr>
		                    			<td colspan="2" class="textClass">
		                    			<spring:message code="b2b.memo.notice" ></spring:message>
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td class="textClass" align="center" >
		                    			&nbsp;
		                    			</td>
		                    			<td align="center" class="textClass">
		                    			<spring:message code="b2b.companyname" ></spring:message>
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td colspan="2">
		                    			&nbsp;
		                    			</td>
		                    		</tr>
		                    		<tr >
		                    			<td class="textClass" align="center" >
		                    			Receiver's Signature
		                    			</td>
		                    			<td align="center" class="textClass">
		                    			Partner /Auth.
		                    			<br/>
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td colspan="2">
		                    			&nbsp;
		                    			</td>
		                    		</tr>
		                    	</table>
		                   </td>
		                 </tr>
					  </table>
					
				 	</td>
				</tr>
		</table>
			</c:if>
		</td> -->
			</tr>
		</table>
	</c:forEach>
</body>
</html>





