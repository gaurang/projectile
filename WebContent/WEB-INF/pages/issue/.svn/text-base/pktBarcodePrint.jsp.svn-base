<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--  <link rel="stylesheet" type="text/css" href="css/crm/styleflip.css" />-->

<title>Projectile ::</title>

<!--  <script type="text/javascript" src="js/flip.js"></script> -->
<style  lang="text/css">
@page land { size:landscape; }
@page port { size:portrait; }
.landscapePage { page:land; }
.portraitPage { page:port; }
body{
	margin: 0;
	background-color: #fff;
	font-size: 9px;
}

.rotate{
	-webkit-transform: rotate(-180deg);
-moz-transform: rotate(-180deg);
/* for ie */
filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=2);

}
.rotate90{
padding-left:10px;
font-size: 11px !important;
	-webkit-transform: rotate(-90deg);
-moz-transform: rotate(-90deg);
/* for ie */
filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=2);
}
</style>
</head>
<body class="portraitPage" >
	<!-- Main Content here -->
	<div align="center">
	<div style="width: 16cm;text-align:center;margin-left: 6.5cm;max-height: 19.3cm;" align="center">
	<div  style="width: 4cm; height: 6.5cm; text-align:center;">
	</div>
	<div  class="rotate90" style="width: 4cm; height: 4cm; text-align:left;vertical-align: middle;">
		<c:choose>
		 <c:when test="${pktDetails.lab == 'GIA' }"> 
		<c:if test="${pktDetails.lab != null &&  pktDetails.lab != 'self' && pktDetails.certId != null }">
			<div ><strong>${pktDetails.lab} Report</strong>: ${pktDetails.certId}</div>
		</c:if>	
		<c:if test="${pktDetails.li == 'Y'}">
			<div ><strong>Ins Reg </strong>:${pktDetails.lab} ${pktDetails.certId}</div>
		</c:if>	
			<div >${pktDetails.sh}</div>
			<div >${pktDetails.md} - ${pktDetails.xd} - ${pktDetails.d}</div>
			<div ><strong>Carat Weight</strong> :  <fmt:formatNumber type="number" value=" ${pktDetails.cts}"
									pattern="0.00" /> carat</div>
			<div ><strong>Color</strong> : ${pktDetails.c} ${pktDetails.comment2}
			<c:if test="${empty(pktDetails.c)}">
				${pktDetails.fnco} ${pktDetails.fnci} ${pktDetails.fnc} ${pktDetails.comment}
			</c:if>
			</div>
			<div ><strong>Clarity</strong> : ${pktDetails.pu}</div>
			<div ><strong>Cut</strong> : ${pktDetails.ct}</div>
			<table style="margin: 0px;padding: 0px;border-spacing: 0px;">
			<tr style="margin: 0px;padding: 0px;"><th style="margin: 0px;padding: 0px;">TBL:</th><td> ${pktDetails.t}%</td>  <th> TD:</th><td> ${pktDetails.dp}%</td> </tr>
			<tr><th>CA:</th> <td>${pktDetails.ca} &deg;</td>  <th> PA:</th> <td>${pktDetails.pa} &deg;</td> </tr>
			<tr><th>ST:</th><td> ${pktDetails.sl}%</td>   <th>LH:</th><td> ${pktDetails.lh} </td></tr>
			</table>
			<div ><strong>Girdle</strong> : ${pktDetails.gd_per}</div>
			<div ><strong>Culet</strong> : ${pktDetails.cc}</div>
			<div ><strong>Polish</strong> : ${pktDetails.po}</div>
			<div ><strong>Symmetry</strong> : ${pktDetails.sy}</div>
			<div ><strong>Foluorescence</strong> : ${pktDetails.fls}  ${pktDetails.flc}</div>
			<div >${pktDetails.comment1}</div>
		 </c:when> 
		 <c:when test="${pktDetails.lab == 'IGI' }"> 
		<c:if test="${pktDetails.lab != null &&  pktDetails.lab != 'self' && pktDetails.certId != null }">
			<div ><strong><u>${pktDetails.lab} DIAMOND REPORT</u></strong></div>
			<div ><strong>${pktDetails.lab} Number</strong>: ${pktDetails.certId}</div>
		</c:if>	
			<div >${pktDetails.sh}</div>
			<div ><strong>Carat Weight</strong> : <fmt:formatNumber type="number" value=" ${pktDetails.cts}"
									pattern="0.00" />carat</div>
			<div ><strong>Color</strong> : ${pktDetails.c} ${pktDetails.comment2}</div>
			<div ><strong>Clarity</strong> : ${pktDetails.pu}</div>
			<div ><strong>Cut</strong> : ${pktDetails.ct}</div>
			<div ><strong>Polish</strong> : ${pktDetails.po}</div>
			<div ><strong>Symmetry</strong> : ${pktDetails.sy}</div>
			<div >${pktDetails.md} - ${pktDetails.xd} - ${pktDetails.d}</div>
				<div ><strong>TA</strong> : ${pktDetails.t}%</div>
				<div ><strong>CR</strong> : ${pktDetails.ch}% - ${pktDetails.ca}&deg;</div>
				<div ><strong>PV</strong> : ${pktDetails.pd}% -  ${pktDetails.pa}&deg;</div>
			<div ><strong>Culet</strong> : ${pktDetails.cc}</div>
			<div ><strong>TD</strong> : ${pktDetails.dp}%</div>
			<div ><strong>Foluorescence</strong> : ${pktDetails.fls} ${pktDetails.flc}</div>
			<div >Comments: ${pktDetails.comment1}</div>
			<c:if test="${pktDetails.li == 'Y'}">
				<div >Laserscribe on Girdle:<br/>
					IGI -  ${pktDetails.certId}</div>
			</c:if>	
		 </c:when> 
		  <c:otherwise> 
			<c:if test="${pktDetails.lab != null &&  pktDetails.lab != 'self' && pktDetails.certId != null }">
				<div ><strong>${pktDetails.lab} Report</strong>: ${pktDetails.certId}</div>
			</c:if>	
				<div >${pktDetails.sh}</div>
				<div >${pktDetails.md} - ${pktDetails.xd} - ${pktDetails.d}</div>
				<div ><strong>Carat Weight</strong> : ${pktDetails.cts} carat</div>
				<div ><strong>Color</strong> : ${pktDetails.c} ${pktDetails.comment2}</div>
				<div ><strong>Clarity</strong> : ${pktDetails.pu}</div>
				<div ><strong>Cut</strong> : ${pktDetails.ct}</div>
					<table style="margin: 0px;padding: 0px;border-spacing: 0px;">
					<tr style="margin: 0px;padding: 0px;"><th style="margin: 0px;padding: 0px;">TBL:</th><td> ${pktDetails.t}%</td>  <th> TD:</th><td> ${pktDetails.dp}%</td> </tr>
					<tr><th>CA:</th> <td>${pktDetails.ca}&deg;</td>  <th> PA:</th> <td>${pktDetails.pa}&deg;</td> </tr>
					<tr><th>ST:</th><td> ${pktDetails.sl}%</td>   <th>LH:</th><td> ${pktDetails.lh} </td></tr>
					</table>
				<div ><strong>Girdle</strong> : ${pktDetails.gd_per}</div>
				<div ><strong>Culet</strong> : ${pktDetails.cc}</div>
				<div ><strong>Polish</strong> : ${pktDetails.po}</div>
				<div ><strong>Symmetry</strong> : ${pktDetails.sy}</div>
				<div ><strong>Foluorescence</strong> : ${pktDetails.fls} ${pktDetails.flc}</div>
				<div >${pktDetails.comment1}</div>
				
		 </c:otherwise> 
		 </c:choose>
	</div>
	<div  class="rotate" style="width: 8cm; height: 3.8cm; text-align:center;padding-bottom: 20px;padding-top: 20px;">
		<img src="getBarcode.html?pktCode=${param.pktCode}" 
				alt="barcode" style="height: 35px; width: 225px;padding-top: 40px;"/>
				<div align="center" style="font-size: 12px;font-weight: bold;">${param.pktCode}</div>
	</div>
	<div style="width: 8cm; height: 3.8cm; text-align:center;">
		<table style="width: 8cm; height: 3.8cm; text-align:center;">
			<tr>
				<td colspan="2" rowspan="2" style="height:60px;"> </td>
				<th colspan="2" align="center">
				<c:choose>
				   <c:when test='${fn:toUpperCase(pktDetails.sh) == "ASSCHER"}'>
						<img src="images/AS.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "CUSION BRILLANT"}'>
						<img src="images/CB.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "CUSHION"}'>
						<img src="images/CU.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "EMERALD"}'>
						<img src="images/EM.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "HEART"}'>
						<img src="images/HT.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				     <c:when test='${fn:toUpperCase(pktDetails.sh) == "MARQUISE"}'>
						<img src="images/MQ.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "OVAL"}'>
						<img src="images/OV.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				    <c:when test='${fn:toUpperCase(pktDetails.sh) == "PEAR"}'>
						<img src="images/PR.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				     <c:when test='${fn:toUpperCase(pktDetails.sh) == "PRINCESS"}'>
						<img src="images/PC.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				     <c:when test='${fn:toUpperCase(pktDetails.sh) == "RADIANT"}'>
						<img src="images/RD.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				     <c:when test='${fn:toUpperCase(pktDetails.sh) == "ROUND"}'>
						<img src="images/RO.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
				     <c:when test='${fn:toUpperCase(pktDetails.sh) == "SQUARE RADIENT" || fn:toUpperCase(pktDetails.sh)== "SQUARERADIENT"}'>
						<img src="images/SR.gif" alt="${pktDetails.sh}"/>				        
				    </c:when>
  				    <c:otherwise>
				      	${pktDetails.sh}
				    </c:otherwise>
				</c:choose>
				</th>
				<th>Stock ID</th> <td>${pktDetails.pktCode}</td>
			</tr>
			<tr><td colspan="4"></td></tr>
			<tr>
			</tr>
			<tr>
				<th>Color</th> <td>${pktDetails.c}</td>
				<td colspan="2" align="center"><c:if test="${fn:contains(pktDetails.remark,'H&A')}"><b>${pktDetails.remark}</b></c:if></td>
				<th>Size</th> <td><fmt:formatNumber type="number" value=" ${pktDetails.cts}"
									pattern="0.00" /></td>
			</tr>
			<tr>
				<th>Clarity</th><td>${pktDetails.pu}</td>
				<td colspan="2"></td>
				<th>Lab</th><td>${pktDetails.lab}</td>
			</tr>
			<tr>
				<th>Cut</th><td>${pktDetails.ct}</td>
				<td colspan="2"></td>
				<th>Shade</th><td>${pktDetails.sd}</td>
			</tr>
			<tr>
				<th>Flour</th><td>${pktDetails.fls}</td>
				<td colspan="2"></td>
				<th><c:if test="${pktDetails.rap != 0 && pktDetails.askingPriceDisc != 0}">Disc</c:if> 
<c:if test="${ pktDetails.askingPriceDisc == 0 && pktDetails.rate!= null}">Price/Cts</c:if></th><td><c:if test="${pktDetails.rap != 0 && pktDetails.askingPriceDisc != 0}">
				<fmt:formatNumber type="number" value="${pktDetails.rap+5}" pattern="0" />%</c:if>
				<c:if test="${ pktDetails.askingPriceDisc == 0 && pktDetails.rate!= null}">
				<fmt:formatNumber type="number" value="${pktDetails.rate}" pattern="0" />$</c:if>
				</td>
			</tr>
		</table>
	</div>
	</div>
	</div>
</body>
</html>
					
																			
        
		
							
							
							
							
				
					
							
										
				
			
			    
				
				
				
				
				
				
				
				