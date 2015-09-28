<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<title>H.Riddhesh &amp; Co.</title>
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
		
		/**<c:if test="${param.orderId == null}">
		$(document).ready(function() { 
		    $.growlUI('Notification', 'Thank you, we will get back to you in 24 hours.'); 
		}); 
		</c:if>	*/
});

</script>


</head>
<body style="background-color: #ffffff;" >
	<table width="100%" >
	<tr><td align="center">Invoice</td></tr>
	<tr align="center">
	<td>
				<table width="80%" style="background-color: #cccccc;">
				<tr align="center">
					<td>
					<table>
						<tr>
						<td>
							<img src="images/l.png" alt="logo" />
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
						<table>
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 32px;">H. RIDDHESH &amp; CO.
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #cccccc;font-size: 15px;">PERFECTION IS OUR PASSION
								</span>
								</td>
							</tr>
							<tr>
								<td>
								<span style="color: #3d555e;font-size: 15px;">
									DIAMOND MERCHANTS, IMPORTERS * EXPORTERS
								</span>
								</td>
							</tr>
						</table>
						</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr><td ><hr/></td></tr>
                  <tr align="center">
                    <td >
							<br/>
							<table width="80%">
								<tr>
									<td></td>
									<td align="right">Invoice Date : ${im.invoiceDate}</td>
								</tr>	
								<tr>
									<td>To,</td>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">${invAddData["companyName"]}</td>
								</tr>
								<tr>
									<td>Ph:- ______________________________________ </td>
									<td align="center">Mobile:-________________________</td>
								</tr>
							</table>
						<br/>
						
						<span>Please receive the following goods on approval for Export /Assortment /Mfg.</span>
						<table cellpadding="3" cellspacing="3" width="80%" border="1px;">
							<tr class="ui-widget-header" align="center">
								<th >Srl No.</th>
								<th>Description</th>
								<th>Pcs</th>
								<th>Carats</th>
								<th>Price US$</th>
								<th>Remarks</th>
								<!-- <th>Shape</th> -->
							</tr>
							<c:forEach items="${inDetails}" var="list" varStatus="status" >
							<tr>
								<td> ${status.count}</td>
								<td> ${list.pktCode}</td>
								<td>1</td>
								<td>${list.cts}</td>
								<td>${list.rate}</td>
								<td></td>
								<!--<td>${list.sh}</td> -->
							</tr>
							</c:forEach>
						
						<tr>
							<th colspan="3">Total Carats : ${orderMasterData.totalCts}</th>
							<th colspan="2">Total : ${im.totalAmount}</th>
						</tr>
						
						</table>
					</td>
				</tr>
				<tr align="center">
                    <td >
                    	<table width="80%">
                    		<tr>
                    			<td colspan="2">
                    			<!-- Receive the goods as condition given back:-->
                    			</td>
                    			
                    		</tr>
                    		<tr>
                    			<td align="right" colspan="2">
                    			H.RIDDHESH &amp; CO.
                    			</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">
                    			&nbsp;
                    			</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">
                    			&nbsp;
                    			</td>
                    		</tr>
                    		<tr>
                    			<td >
                    			Receiver's Signature
                    			</td>
                    			<td align="right">
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
</body>
</html>