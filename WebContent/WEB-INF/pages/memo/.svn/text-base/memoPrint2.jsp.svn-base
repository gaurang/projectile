<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<title>Projectile:</title>
<style  lang="text/css">
	
.memoTable{
	background-color: #cccccc;

}
.textClass{
	font-size: 11px;
 	font-family: Arial,Helvetica,sans-serif;
}
.memoTable th{
	font-size: 11px;
 	font-family: Arial,Helvetica,sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
	padding: 3px;
}
.memoTable td{
	background-color: #ffffff;
	font-size: 11px;
 	font-family: Arial,Helvetica,sans-serif;
	
}
#title{
	font-size: 11px;
 	font-family: Arial,Helvetica,sans-serif;
 	font-weight: bold;
}
.mainTable{
	margin-left: 10px;
	margin-right: 5px; 
	width: 50%;
}
@page land { size:landscape; }
@page port { size:portrait; }
.landscapePage { page:land; }
.portraitPage { page:port; }
body{
	margin: 0;
}
</style>
</head>
<body class="landscapePage">
			
		<c:forEach begin="0" end="${memoCount-1}" var="c">
			<c:set var="startPt" value="${c*10}"/>
			<c:set var="endPt" value="${(c*10)+9}"/>
		<table >
		 <tr>
		  <td id="memo" align="right" width="50%" >
		  
			<table class="mainTable" width="100%" >
				<tr>
					<td align="left">
					<table width="100%">
						<tr align="center">
						<td >
							<img src='<%="http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()%>/images/l.png'/>
						</td>
						<td >
						<table width="100%">
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 30px;"><spring:message code="b2b.companyname" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #404040;font-size: 14px;"><spring:message code="b2b.company.tagline" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 14px;">
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
						</table>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						<table width="80%">
		                  <tr align="center">
		                    <td style="background-color: #ffffff;" align="center" >
									<span id="title" style="width: 70%;">Memo Details</span>
									<table class="memoTable" width="100%">
										<tr>
											<th>Memo No </th><td> ${orderMasterData.id}</td>
											<th>Memo Date </th><td> ${orderMasterData.orderDate}
											</td>
										</tr>	
										<tr>
											<th>Party Name</th><td> ${orderMasterData.companyName}</td>
											<th>Memo Type</th><td> ${orderMasterData.orderType}</td>
										</tr>
										<tr>
											<th>Term</th><td> ${otherDetails.termDesc}</td>
											<th>Contact No.</th><td> ${otherDetails.phoneNo1} <c:if test="${otherDetails.phoneNo2 != null}">/${otherDetails.phoneNo2}</c:if> </td>
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
		                    	<table width="80%">
		                    		<tr>
		                    			<td colspan="2">
		                    			</td>
		                    			
		                    		</tr>
		                    		<tr>
		                    			<td align="right" colspan="2" class="textClass">
		                    			<spring:message code="b2b.companyname" ></spring:message>
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td colspan="2">
		                    			&nbsp;
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td class="textClass">
		                    			Receiver's Signature
		                    			</td>
		                    			<td align="right" class="textClass">
		                    			Partner /Auth.
		                    			</td>
		                    		</tr>
		                    	</table>
		                   </td>
		                 </tr>
					  </table>
					
				 	</td>
				</tr>
		</table>
		</td>
		<td id="${param.copy}" width="50%" align="right">
			
			<c:if test="${param.copy ==2}">
				<table width="100%" class="mainTable">
				<tr>
					<td align="right">
					<table width="100%">
						<tr align="center">
						<td>
							<img src='<%="http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()%>/images/l.png'/>
						</td>
						<td >
						<table width="100%">
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 30px;"><spring:message code="b2b.companyname" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #404040;font-size: 14px;"><spring:message code="b2b.company.tagline" ></spring:message>
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 14px;">
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
						</table>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						<table width="80%">
		                  <tr align="center">
		                    <td style="background-color: #ffffff;" align="center" >
									<span id="title" style="width: 70%;">Memo Details</span>
									<table class="memoTable" width="100%">
										<tr>
											<th>Memo No </th><td> ${orderMasterData.id}</td>
											<th>Memo Date </th><td> ${orderMasterData.orderDate}
											</td>
										</tr>	
										<tr>
											<th>Party Name</th><td> ${orderMasterData.companyName}</td>
											<th>Memo Type</th><td> ${orderMasterData.orderType}</td>
										</tr>
										<tr>
											<th>Term</th><td> ${otherDetails.termDesc}</td>
											<th>Contact No.</th><td> ${otherDetails.phoneNo1} <c:if test="${otherDetails.phoneNo2 != null}">/${otherDetails.phoneNo2}</c:if> </td>
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
		                    	<table width="80%">
		                    		<tr>
		                    			<td colspan="2">
		                    			</td>
		                    			
		                    		</tr>
		                    		<tr>
		                    			<td align="right" colspan="2" class="textClass">
		                    			<spring:message code="b2b.companyname" ></spring:message>
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td colspan="2">
		                    			&nbsp;
		                    			</td>
		                    		</tr>
		                    		<tr>
		                    			<td class="textClass">
		                    			Receiver's Signature
		                    			</td>
		                    			<td align="right" class="textClass">
		                    			Partner /Auth.
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
		</td>
	  </tr>
	 </table>
</c:forEach>	
</body>
</html>