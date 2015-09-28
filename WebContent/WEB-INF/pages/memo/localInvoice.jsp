<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<title>Projectile</title>
<style type="text/css">
.image{
	text-align: center;
	border-bottom: 1px solid black;
}
body{
	background-color: white;
	line-height: 1.0em;
	height: 842px;
 }
.phone{
	text-align: left;
	font-size: small;
}
.address{
	text-align: right;
	font-size: small;
}
.head{
	background-repeat: repeat-x;
	margin: auto;
	border-bottom: 1px solid #292928;
	font-size: 14px;
}
.mainBody{
	background-repeat: repeat-x;
	margin: auto;
}
p:first-letter {
   margin-left: 3em;
}
.small {
	font-size: 9px;
}
.bold{
	font-weight: bold;
}
.underline{
	text-decoration: underline;
}

.invoiceTable, .memoTable{
	background-color: #000000;
}
.invoiceTable th{
	font-size: 12px;
 	font-family: Arial,Helvetica,sans-serif;
	background-color: #ffffff;
	font-weight: normal;
	vertical-align: top;
}
.invoiceTable td{
	background-color: #ffffff;
	font-size: 12px;
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
<body>
	<div class="head">
		<table width="100%" >
			<tr >
				<td valign="bottom" style="width:25%">
					<div class="phone">
						<spring:message code="b2b.local.company.phoneno"></spring:message>
					</div>
				</td>
				<td style="width: 50%">
					<div class="image">
						<img src='<%="http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()%>/images/l.png'/><br/>
						<h3 style="align:center">ENTERPRISES</h3>
					</div>
				</td>
				<td style="width:25%" valign="bottom">
					<div class="address">
						<spring:message code="b2b.local.company.address"></spring:message>				
					</div>
				</td>
			</tr>
			<tr style="height:25">
				<td colspan="3" align="center">
					<spring:message code="b2b.local.company.tagline"></spring:message>
				</td>
			</tr>
		</table>
	</div>
	<br/><br/>
	<div class="mainBody" >
		<table width="100%">
			<tr style="height:25">
				<td align="center" colspan="3">
					<spring:message code="b2b.local.company.invoicetype"></spring:message>
				</td>
			</tr>
			
			<tr>
				<td style="width:50% align:left" valign="top" >
					SOLD TO:<br/>
						${invAddData.companyName}
						${invAddData.address1}
						${invAddData.address2}
						${invAddData.address3}
						${im.consigneeName}						
				</td>
				<td style="width: 50% align:right" valign="top" >
					DT:		${im.invoiceDate}<br/>
					INVOICE: ${im.invoiceCode}
				</td>
			</tr>
			<tr style="height:40"></tr>
			<tr>
				<td><spring:message code="b2b.local.company.through"></spring:message></td>
				<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TERMS:</td>
			</tr>
		</table>
		<br/>
		<table width="100%" class="invoiceTable">
			<tr align="center">
				<td >SR.</td>
				<td>DESCRIPTION</td>
				<td>WEIGHT</td>
				<td>RATE/CT. (Rs.)</td>
				<td>AMOUNT (Rs.)</td>
			</tr>
			
			<c:forEach items="${inDetails}" var="list" varStatus="status">
			<tr align="center" valign="top" >
				<td > ${status.count}</td>
				<td>
					Cut &amp; Polished Diamonds
				</td>
					<td align="center" id="Ex1">${list.cts}</td>
					<td align="center" id="Ex2"><fmt:formatNumber value="${list.rate*im.exRate}" maxFractionDigits="2"/></td>
					<td align="center"><fmt:formatNumber value="${list.totalRate*im.exRate}" maxFractionDigits="2"/></td>
			</tr>
			</c:forEach>
			<tr align="center" valign="top" >
				<td></td>
				<td align="center" class="bold">&nbsp;&nbsp;TOTAL</td>
				<td></td>
				<td></td>
				<td class="bold"><fmt:formatNumber value="${im.finalAmount*im.exRate}" maxFractionDigits="2"/></td>
			</tr>
			<c:if test="${im.finalAmount*im.exRate}>0">
				<tr align="center" valign="top" >
	                <td></td>
	                <td align="center" class="bold">&nbsp;&nbsp;Add Discount(%)</td>
	                <td></td>
	                <td><fmt:formatNumber value="${im.discount}" maxFractionDigits="2"/></td>
	                <td class="bold"><fmt:formatNumber value="${invAddData.discontValue}" maxFractionDigits="2"/></td>
	            </tr>
	            <tr align="center" valign="top" >
	                <td></td>
	                <td align="center" class="bold">&nbsp;&nbsp;Final Amount</td>
	                <td></td>
	                <td></td>
	                <td class="bold"><fmt:formatNumber value="${im.totalAmount}" maxFractionDigits="2"/></td>
	            </tr>
	       </c:if>
			 		
		</table>
		<br/>
		<strong>Amount Chargeble (In words) Total Rupees:</strong>
		<div class="underline">${fn:toUpperCase(invAddData.finalAmtStr)}</div>
		<br/>
		<table width="100%">
			<tr>
				<td width="50%" align="left">
					<spring:message code="b2b.local.company.confirmnote"></spring:message>
				</td>
				<td>
					<spring:message code="b2b.local.companyname"></spring:message>
				</td>
			</tr>	
			<tr>
				<td width="50%" height="50" align="left"></td>
				<td width="50%" align="center">
					PARTNER				
				</td>	
				<td width="50%" align="center" valign="top">
				</td>
			</tr>	
			<tr>
				<td height="50px" valign="top">
					<spring:message code="b2b.local.company.against"></spring:message>	
				</td>
			</tr>
			<tr>
				<td width="100%" class="small">
					<div style="width: 100%">
						<spring:message code="b2b.local.company.declaration"></spring:message>
					</div>					
				</td>				
			</tr>		
		</table>
		<br/>
		<div class="bold">
			<spring:message code="b2b.local.company.cstvat"></spring:message>
		</div>
		<div class="bold" align="right">
			<p>${invAddData.companyName}</p>
			<br/>
			${im.cstVat}
		</div>
		<br/>
		<div align="left">
			For <b>${invAddData.companyName}</b>
			<br/><br/>
			<p class="small">Director</p>
		</div>
	</div>	
</body>
</html>