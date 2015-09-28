<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.buttontab {
	padding: 5px 10px;
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#CACDA2) );
	background: -moz-linear-gradient(top, #fff, #CACDA2);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	text-shadow: 1px 1px #666;
	color: #000;
	text-decoration: none;
}

.buttontab:hover {
	background: #fff;
}

#active {
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#fff) );
	background: -moz-linear-gradient(top, #fff, #fff);
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#selectPacket').hide();
		var selectedPrcl = $('#ctsList').val();
		if(selectedPrcl != '-1'){
			showTotalCts();
			ShowSelect();
		}
	});
		
	function showTotalCts(){
  	  	var cts_ID = $('#ctsList').val();
  	  	var array = cts_ID.split("|");
  	  	var cts = array[0];
  	  	var ID = array[1];
  	  	var purchaseId =array[2]; 
  	  	var text = '';
  	  	$('#curID').val(ID);
  	  	$('#curPurchaseId').val(purchaseId);
  	  	if(cts > 0) text = 'The total carats in this parcel are ' + cts + '.';
  	  	$('#totalCarats').html(text);  	  	
 	}
	
	function ShowSelect(){
		  $('#selectPacket').show();
		  calc();
	}
	 
	function calc(){
	  	var cts = $('#ctsList').val();
  	  	var array = cts.split("|");
	  	cts = array[0];
	  	var totalcts = cts;
  	  	if(cts ==''){
  	  		$('#parcelMsg').html('<p style="color:red;">Select a value from the above dropdown</p>');
  	  	  	return;
  	  	 }
  	  	cts = parseFloat(cts); 		
 		var textval = '';
 		$('#submit').show();
 		$('.textbox').each(function(i){
 			textval = $(this).val();
 	 		if(textval == ''){ 
 	 			$('#parcelMsg').html('<p style="color:red;">Available carats : '+cts+' (Total: '+totalcts+')</p>'); 	 	 		
 	 	 	}else{
 	 			textval = parseFloat(textval);
 				if (isNaN(textval)){
 					$('#parcelMsg').html('<p style="color:red;">There is an error in the entered values</p>');
 	 				$('#submit').hide();}
	 		}
 			if(textval > 0){
 				cts -= textval;
 				if(cts < 0) {
 	 				var rem = 0.0;
 	 				rem = textval + cts;
 	 				$(this).val(rem);
 	 				cts=0.0;
 			 		alert('The values cannot exceed '+ totalcts); 			 		
 			 		//$('#parcelMsg').html('<p style="color:red;">Available carats : '+cts+' (Total: '+totalcts+')</p>');	
 					//return;
 			 	}
 			} 
 			$('#curRemCts').val(cts); 			
 			$('#parcelMsg').html('<p style="color:red;">Available carats : '+cts+' (Total: '+totalcts+')</p>'); 				
 	 	}); 
	}	
	
	function addParcelForm(){
  	  	var count = $('#curCount').val();
  	  	count++;
		var html ='';
			html += '<tr><td>'+
					'<select style="width:100px;" name="codes" id="codes_'+count+'">'+
						'<option value="">Select</option>'+
						'<c:forEach var="x" items='${PACKET_LIST}'>'+
							'<option value="${x}">${x}</option>'+
						'</c:forEach>'+
					'</select>'+
				'</td>'+
				'<td><input type="text" name="Packet_'+count+'"></td>'+
			'</tr>'	;		
		$('#parcelTable').append(html);	
		$('#curCount').val(count);				
  	}

	</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>
	<br />
	<div id="ParcelDetails" align="center">
		<a href="javascript:void(0);" class="buttontab" id="active" onclick="func('parcelDistribute.html');">Parcel Distribute</a>
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('packetUpload.html');">Packet Upload</a>
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('fixedPacket.html');">Fixed Packet</a>
		<br />
		<br />
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('parcelUpload.html');">Back To Parcel
			Upload</a>
		<br />
		<br />
		<div id="errorMsg">${mailMsg}</div>
	
		<br />
		<form id="stockErMx" name="stockEnM" action="mixedPacketSubmit.html"
			method="post">
			<div id="totalCarats"></div>
			<br> <input type="hidden" value="10" id="curCount"
				name="curCount" /> <input type="hidden" value="" id="curID"
				name="curID" /> <input type="hidden" value="" id="curPurchaseId"
				name="curPurchaseId" /> <input type="hidden" value="" id="curRemCts"
				name="curRemCts" />
			<table class="greenTable" style="text-align: center;">
				<tr>
					<th>Vendor / BillNo / Date / TotalCts</th>
				</tr>
				<tr>
					<td><select style="width: 400px;" name="ctsList"
						onchange="showTotalCts()" id="ctsList">
							<option value="-1">Select</option>
							<c:forEach var="s" items='${PARCEL_LIST}'>
								<option value="${s.totalcts}|${s.ID}|${s.purchaseId}">
									${s.companyname} /
									<c:if test="${s.billno == ''}">-</c:if>
									<c:if test="${s.billno != ''}">${s.billno}</c:if>
									/ ${s.purchaseDate} / ${s.totalcts}
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<input type="button" value="Distribute" id="Dist"
				onclick="ShowSelect()">
			<div id="parcelMsg">${PARCEL_MSG}</div>
			<div id="selectPacket">
				<table id="parcelTable" class="greenTable"
					style="text-align: center;">
					<tr>
						<th>Parcel No</th>
						<th>Total Cts</th>
					</tr>
					<c:forEach begin="0" end="9" step="1" varStatus="index">
						<tr>
							<td><select style="width: 100px;"
								name="codes_${index.count}" id="codes_${index.count}"
								class="packetNo">
									<option value="-1">Select</option>
									<c:forEach var="x" items='${PACKET_LIST}'>
										<option value="${x}">${x}</option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="cts_${index.count}"
								id="cts_${index.count}" onblur="calc()" class="textbox">
							</td>
						</tr>
					</c:forEach>
				</table>
				<input type="button" name="addNew" id="addNew" value="Add New"
					onclick="addParcelForm()" /> <input type="submit" name="submit"
					id="submit" value="submit" />
			</div>
		</form>
	</div>
</body>
</html>