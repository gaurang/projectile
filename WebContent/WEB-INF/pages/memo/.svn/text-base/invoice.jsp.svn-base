<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<title>Projectile</title>
<style type="text/css">
.invoiceTable, .memoTable{
	background-color: #000000;
}
.invoiceTable th{
	font-size: 9px;
 	font-family: Arial,Helvetica,sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
}
.invoiceTable td{
	background-color: #ffffff;
	font-size: 9px;
 	font-family: Arial,Helvetica,sans-serif;
	
}
.invoiceTable2{
	background-color: #000000;
	border-spacing: 1px;
}
.invoiceTable2 tr{
	font-size: 9px;
 	font-family: Arial,Helvetica,sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
}

</style>
</head>
<body style="background-color: #ffffff;" >
	<table width="100%">
	<tr><td align="center">Invoice</td></tr>
	<tr align="center">
	<td>
				<table width="100%" class="invoiceTable">
				<tr align="left">
					<td>
					<table>
						<tr>
							<td>
								<img src='<%="http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()%>/images/l.png'/><br/>
							</td>
							<td>
								Exporter : <strong><spring:message code="b2b.companyname.prefix" ></spring:message>&nbsp;<spring:message code="b2b.companyname" ></spring:message></strong>
								<br/>
								<spring:message code="b2b.invoice.selfAdd"></spring:message><br/>
								<spring:message code="b2b.invoice.contact"></spring:message>
							</td>
						</tr>
						</table>
					</td>
					<td>
						<table width="100%">
							<tr>
								<td style="width:50%;">Invoice No. : ${im.invoiceCode} <br/>Date : ${im.invoiceDate}  </td>
								<td>Exporters Ref. <br/>IEC No :${im.expRefNo} </td>
							</tr>
							<tr><td colspan="2">Buyers Order No &amp; Date <br/>&nbsp;</td></tr>
							<tr><td colspan="2">Other Reference(s)<br/>&nbsp;${im.othRefNo}</td></tr>
						</table>
					</td>
				</tr>
				<tr>
				<td valign="top">Consignee:
				<br/>
					<c:if test="${(im.consigneePartyId == null || im.consigneePartyId == -1) && (im.consigneeName == nul || im.consigneeName == '' )}">
						<strong>***DIRECT PARCEL***</strong>
					</c:if>
					<c:if test="${im.consigneePartyId > 0}">
						${invAddData.consigneePartyName}
					</c:if>
					<c:if test="${im.consigneeName != null}">
						${im.consigneeName}
					</c:if>
				</td>
				<td valign="top" style="padding: 0px;">
					<table width="100%" class="invoiceTable2">
						<tr>
							<td colspan="2" align="left">
							Buyers (If other then consignee)
							<br/>
							<c:if test="${im.consigneeName == null || im.consigneeName == ''}">
								${invAddData.companyName}
								<c:if test="${invAddData.address1 !=null && invAddData.address1 != ''}"><br/>${invAddData.address1}</c:if>
								<c:if test="${invAddData.address2 !=null && invAddData.address2 != ''}"><br/>${invAddData.address2}</c:if>
								<c:if test="${invAddData.address3 !=null && invAddData.address3 != ''}"><br/>${invAddData.address3}</c:if>
								<br/>${invAddData.city} ${invAddData.state} ${invAddData.pin} ${invAddData.country}
							</c:if>
							<c:if test="${im.consigneeName != null }"><br/><br/><br/><br/>
							</c:if>
							</td>
						</tr>
						<tr>
							<td style="width:50%;">Country of Origin Of Goods<br/>&nbsp;<spring:message code="b2b.invoice.country"></spring:message></td>
							<td >Country of final destination<br/>&nbsp;${im.destination}</td>
						</tr>
					</table>	
				</td>
				</tr>
				<tr valign="top">
					<td style="padding: 0px;">
						<table class="invoiceTable2" width="100%" >
							<tr>
								<td style="width: 50%;">Pre-Carriage By <br/>&nbsp;${invAddData.preCarrierPartyName} </td>
								<td>Place of reception by Pre-Carrier <br/>&nbsp;${im.placeOfPreCarrier} </td>
							</tr>
							<tr>
								<td>Vessel/Flight No <br/>&nbsp;${im.vesselFlight}  </td>
								<td>Port Of Loading <br/>&nbsp; ${im.portOfLoad}</td>
							</tr>
							<tr>
								<td>Port of discharge <br/>&nbsp; ${im.portOfDischarge}</td>
								<td>Destination <br/>&nbsp;${im.destination} </td>
							</tr>
						</table>
					</td>
					<td valign="top">
						Terms and Delivery of payment
						<br/>&nbsp;
						<br/>&nbsp;<br/>
						<spring:message code="b2b.invoice.bankDtl"></spring:message>
						
					</td>
				</tr>
                <tr align="center">
                    <td colspan="2" style="height: 350px;padding: 0px;" valign="top">
							<table width="100%"  class="invoiceTable2">
							<tr align="center">
								<th >Marks &amp; Nos/<br/>Container No.</th>
								<th>No &amp; kind of pkgs.</th>
								<th>Description</th>
								<th>Pcs</th>
								<th>Carats</th>
								<th>Rate <br/><strong>CFR</strong> <br/><strong>US $</strong></th>
								<th>Amount <br/><strong>US $</strong></th>
							</tr>
							<tbody >
								<c:forEach items="${inDetails}" var="list" varStatus="status" >
								<tr valign="top" align="center">
									<td > ${status.count}</td>
									<td> ${list.pktCode}</td>
									<td></td>
									<td align="right">${list.pcs}</td>
									<td align="right">${list.cts}</td>
									<td align="right">${list.rate}</td>
									<td align="right"><fmt:formatNumber value="${list.totalRate}" maxFractionDigits="2"/></td>
								</tr>
								</c:forEach>
								<tr align="center" valign="top" >
				                    <td></td>
				                    <td align="center" class="bold">&nbsp;&nbsp;Add Discount(%)</td>
				                    <td></td>
				                    <td></td>
				                    <td></td>
				                    <td align="right"><fmt:formatNumber value="${im.discount}" maxFractionDigits="2"/></td>
				                    <td class="bold" align="right"><fmt:formatNumber value="${invAddData.discontValue}" maxFractionDigits="2"/></td>
				                </tr>
				                <tr align="center" valign="top" >
				                    <td></td>
				                    <td align="center" class="bold">&nbsp;&nbsp;Final Amount</td>
				                    <td></td>
				                    <td></td>
                                    <td></td>
				                    <td></td>
				                    <td class="bold" align="right"><fmt:formatNumber value="${im.totalAmount}" maxFractionDigits="2"/></td>
				                </tr>
							</tbody>						
						</table>
					</td>
				</tr>
				<tr valign="bottom">
					<td colspan="2">
						<table width="100%">
							<tr>
								<td width="80%" align="left">Amount Chargeble <br/>(In words) Total CFR US  : ${invAddData.finalAmtStr}</td>
								<td align="right" ><strong>CFR US $ Total</strong> : <fmt:formatNumber value="${im.totalAmount}" maxFractionDigits="2"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center" >
                    <td colspan="2">
                    	<table width="100%">
                    		<tr>
                    			<td colspan="2">
                    				<spring:message code="b2b.invoice.note"></spring:message>
                    			</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2" align="left">
									<strong style="text-decoration: underline;">Payment Instruction:</strong> 
									<spring:message code="b2b.invoice.payment.instruction"></spring:message>
									<br/>
									<br/>
									<spring:message code="b2b.invoice.note" var="${invAddData.preCarrierPartyName}"></spring:message>
								</td>
                    		</tr>
                    		
                    		<tr align="left">
                    			
                    			<td>
                    			<spring:message code="b2b.invoice.confirmnote" ></spring:message>
										<br/>
									<spring:message code="b2b.invoice.declaration" ></spring:message>								
  								</td>
  								<td align="right">
	  								<table>
	  								 <tr>
	  									<td>
			                    			Signature &amp; Date
			                    			<br/><br/>
			                    			${im.invoiceDate}
	  								 	</td>
	  								 	<td>
			                    			<span style="white-space: nowrap;"> FOR <spring:message code="b2b.companyname"></spring:message></span>
			                    			<br/><br/>
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
	</tr>
	</table>
</body>
</html>