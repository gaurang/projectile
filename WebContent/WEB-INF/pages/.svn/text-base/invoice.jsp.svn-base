<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<script src="js/jquery/jquery.js" type="text/javascript"></script>

<script src="js/jquery/jquery.blockUI.js" type="text/javascript"></script>

<script type="text/javascript">
jQuery(document).ready(function(){
		$('#hideHdr').bind('click',function(){
				if($('#hideHdr').html() == 'Hide'){
					$('#hd').hide();
					$('#footer_main').hide();
					$('#hideHdr').html('Show'); 
				}else{
					$('#hideHdr').html('Hide'); 
					$('#hd').show();
					$('#footer_main').show();
					
				}
		});
		
		<c:if test="${param.orderId == null}">
		$(document).ready(function() { 
		    $.growlUI('Notification', 'Thank you, we will get back to you in 24 hours.'); 
		}); 
		</c:if>	
});

</script>


</head>
<body >

<table width="900px" align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" 
style="background-image:url('images/shadow_left.png');">
<img src="images/shadow_left.png" width="15" alt="" height="1"/></td>
    <td><table width="918" align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="0">
                  <tbody><tr>
                    <td valign="bottom" 
 height="131">
<c:if test="${param.mail ne 'Y'}">
<div id="hd">
	<jsp:include page="include/header.jsp"></jsp:include>
</div>
</c:if>
</td>
                  </tr>
                  <tr>
                    <td bgcolor="#ffffff">

				
				
					<a href="javascript:void(0);" id="hideHdr">Hide</a>
					<a href="javascript:window.print();" >Print</a>
					
					<div align="center" >
					<div style="width: 70%;" align="left">
					<div id="Pagetitle" style="width: 70%;"><h2> Order Details</h2></div>
						<table class="bluetable" >
							<tr>
								<th>Order Id </th><td> ${orderMasterData.id}</td>
								<td width="5px"></td>
								<th>Order Date </th><td> ${orderMasterData.orderDate}</td>
							</tr>	
							<tr>
								<th>Party Name</th><td> ${orderMasterData.companyName}</td>
								<td width="5px"></td>
								<th>Proforma Type</th><td> ${orderMasterData.orderType}</td>
							</tr>
						</table>
					</div>
					<br/>
					
					<div id="Pagetitle" style="width: 70%;" align="left"><h2> Order Packets</h2></div>
					<table class="bluetable" align="center" cellpadding="3" cellspacing="3" width="70%">
						<tr class="ui-widget-header">
							<th width="">Srl No.</th>
							<th>Packet No.</th>
							<th>Rate</th>
							<th>Carats</th>
							<th>Shape</th>
							<th>Purity</th>
							<th>Color</th>
							<th>Cut</th>
						</tr>
						<c:forEach items="${orderMasterData.packetList}" var="list" varStatus="status" >
						<tr>
							<td> ${status.count}</td>
							<td> ${list.pktCode}</td>
							<td>${list.rate}</td>
							<c:forEach items="${list.prpValue}" var="pktPrp">
								<td>${pktPrp}</td>
							</c:forEach>
						</tr>
						</c:forEach>
					
					<tr>
						<th colspan="2">Total Carats : ${orderMasterData.totalCts}</th>
						<th colspan="2">Total : ${orderMasterData.totalAmount}</th>
					</tr>
					</table>
					</div>
				 </td>
                 </tr>
                 <tr>
                 <td >
	                <c:if test="${param.mail ne 'Y'}">
	                <div id="footer_main">
						<jsp:include page="include/footer.jsp"></jsp:include>
					</div>
					</c:if>
                 </td>
                 </tr>
              </tbody></table></td>
            </tr>
        </tbody></table></td>
      </tr>
    </tbody></table>
      </td>
    <td valign="top" width="15" 
style="background-image: url('images/shadow_right.png');"><img 
src="images/shadow_right.png" width="15" 
height="1"/></td>
  </tr>
</tbody></table>
<map name="Map2" id="Map2"><area shape="rect" coords="710,209,893,246" 
href="index.html"><area 
shape="rect" coords="710,209,893,246" 
href="index.html">
</map>
<input id="gwProxy" type="hidden"/><!--Session data--><input 
onclick="jsCall();" id="jsProxy" type="hidden"/><div id="refHTML"></div>
</body>
</html>