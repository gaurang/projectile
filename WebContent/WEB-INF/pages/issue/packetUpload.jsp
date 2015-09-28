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
		var colList = new Array();
	 	var html= '' ;
	  	var rapFormat = 0;
	  	if('${rapFormat}' == 100){
			rapFormat =100;
	  	}
	  	var minReqPrp = new Array("pktCode","SH","CTS","PU");
	$(document).ready(function(){
		$('#PacketUploadForm').hide();
	    $( "#packetDate").DatePicker({
	        format:'d-m-Y',
	        current: '${CURR_DATE}',
	        calendars: 1,
	        date: '${CURR_DATE}',
	        onChange: function(formated, dates){
	            $('#packetDate').val(formated);
	            $('#packetDate').DatePickerHide();
	        }
	    });
	    $("#packetDate").val('${CURR_DATE}');
	    
	    
	    $( "#packetDueDate").DatePicker({
	        format:'d-m-Y',
	        current: '${CURR_DATE}',
	        calendars: 1,
	        date: '${CURR_DATE}',
	        onChange: function(formated, dates){
	            $('#packetDueDate').val(formated);
	            $('#packetDueDate').DatePickerHide();
	        }
	    });
	    $("#packetDueDate").val('${CURR_DATE}');
	    
		$('#PacketUploadForm').validate();
		var fld1 = '';
		var fld2 = '${mode}';
		if(fld2 == "fileUpload") fld1 = "manualUpload"; 
		else if(fld2 == "manualUpload") fld1 = "fileUpload";
		hideTwoDivs(fld1, fld2);
		
		var index = 0;
		  var editPkt = true;
			html += '<tr id="row_ZZZ">';
			html += '<td>ZZ  <input type="hidden" name="labVal_ZZZ"  id="labVal_ZZZ"/><input type="hidden" name="grpId_ZZZ"  id="grpId_ZZZ"/></td>';
			<c:forEach var="lst" items="${fileMappingList}">
			<c:if test="${lst.columnName != null}">
					var onchange = '';
					<c:set var="property" value="${lst.columnName}"/>
					<c:if test="${lst.columnName == 'shFr'}">
						<c:set var="property" value="SH"/>
					</c:if>
					<c:if test="${lst.columnName == 'shTo'}">
						<c:set var="property" value="SH"/>
					</c:if>
					<c:if test="${lst.columnName == 'puTo'}">
						<c:set var="property" value="PU"/>
					</c:if>
					<c:if test="${lst.columnName == 'puFr'}">
						<c:set var="property" value="PU"/>
					</c:if>
					<c:if test="${lst.columnName == 'cTo'}">
						<c:set var="property" value="COL"/>
					</c:if>
					<c:if test="${lst.columnName == 'cFr'}">
						<c:set var="property" value="COL"/>
					</c:if>
					<c:if test="${lst.columnName == 'sieveFr'}">
						<c:set var="property" value="SIEVE"/>
					</c:if>
					<c:if test="${lst.columnName == 'sieveTo'}">
						<c:set var="property" value="SIEVE"/>
					</c:if>
					/* if('${lst.columnName}' == 'SH'|| '${lst.columnName}' == 'PU'|| '${lst.columnName}' == 'CTS'){
						//onchange = 'onchange="getNewRap(ZZZ);"';
					}
					if('${lst.columnName}' == 'C'){
						onchange += 'onchange="rapAllowed(ZZZ);getNewRap(ZZZ);"';
					}
					if('${lst.columnName}' == 'rate'){
						onchange = 'onchange="rapAllowed(ZZZ);rapAllowedRate(ZZZ)"';
						html += '<td id="rapPrice_ZZZ"></td>';
					}
					if('${lst.columnName}' == 'rap'){
						//onchange = 'onchange="rapChange(ZZZ)"';
					} */
				<c:choose>
					<c:when test="${!empty PRP_LOV[property] && lst.columnName != 'FNCO' && lst.columnName != 'FNC'}">
					html +='<td><select name="${ lst.columnName}_ZZZ" id="${lst.columnName}_ZZZ" class="select" style="width:60px;" '+onchange+'>'+
								'<option value="">All</option>'+
								<c:forEach var="prpLov" items="${PRP_LOV[property]}">
									'<option value="${prpLov.id}">${prpLov.description}</option>'+
								</c:forEach>
							'</select></td>';					
					</c:when>
					<c:otherwise>
						html +='<td><input type="text" name="${lst.columnName}_ZZZ" id="${lst.columnName}_ZZZ" size="4" '+onchange+' /></td>';
						if('${lst.columnName}' == 'pktCode'){
							//pktcode Only be input
							if(editPkt)
								html +='<td><input type="text" name="editPktCode_ZZZ" id="editPktCode_ZZZ" size="4" /></td>';
							editPkt = false; // make it false so not to have this again
						}
					</c:otherwise>
				</c:choose>
				colList[index]= '${lst.columnName}';
				index++;
			</c:if>
		</c:forEach>
		//html +='<td><img src="images/Close-16.png" alt="Delete" id="delete_ZZZ"  style="cursor:pointer;display:none;" onclick="deletePkt(this);"/></td>';
		html += '</tr>';
	      updateMap();
	});
	
	function hideTwoDivs(fld1, fld2){
  		$('#'+fld1).hide();
		$('#'+fld2).show();  		
		$('#mode').val(fld2);
  	}
	
	function loadFileData(fld, mode){
  		func('packetUpload.html?fileId='+$('#'+fld).val()+'&mode='+$('#mode').val());
  	}
	
	function updateMap(){
  		var count = $('#pktCount').val();
  		var addStockHtml= '';
  		for(var z =0 ;z<count ;z++){
  			var tempHtml = html.replace(/ZZZ/g, z);
  			tempHtml =tempHtml.replace(/ZZ/g, z+1);
  			addStockHtml += tempHtml;
  		}
  		$('#addStock').html(addStockHtml);
  		for(var z =0 ;z<count ;z++){
  			rapAllowed(z);
  		}
  		<c:if test="${editPacket == 1}">
  			loadStock("",0,count-1, '${param.pktId}', '${param.grpId}');
  			$('#LAB_0').attr('readonly',true);
  		</c:if>
  	}
	function rapAllowed(count) {
		if($('#C_'+count).val() == '' ){
			$('#rap_'+count).val('');
			$('#rap_'+count).attr("disabled", true);
		}else{
			$('#rap_'+count).attr("disabled", false);
		}
     }

	function submitForm(frmId){
  		$('#ajax').show();
		    var formOptions = {
		    		dataType: 'json',
		    		data : {vendorId:$('#vendorId').val(),billNo:$('#billNo').val(),date:$('#date').val(),comments:$('#comments').val(),dueDate:$('#dueDate').val()
		    			,paymentTerm:$('#paymentTerm').val(),currency:$('#currency').val(),exrate:$('#exRate').val(),tax:$('#tax').val(),expenses:$('#expenses').val()},
		    		//data : $('#purchaseDetails').serialize() ,
		            beforeSubmit: function() {
		            	if(frmId != '#fileUploadForm'){
		            		return  validate();
		            	}else{
		            		return validateFileUpload();
		            	}
		            	$.showprogress("loading..");
		            },
		            success:  function(jsonObj) {
		            	$.hideprogress();
		            	var submit = "<div> <input type='button' value='Submit'  onclick='upload();'> <input type='button' value='cancel' onclick='cancel();'></div>"
		            		if(frmId == '#fileUploadForm'){
		            			$('#errorMsg').html(submit+jsonObj);
		            		}else{
		            			$('#errorMsg').html(jsonObj);
		          		}
		            	//setTimeout (function() {clearMsg();} ,5000);  
		            },
  		          error: function(xmlHttpRequest, textStatus, errorThrown) {
  		        	$.hideprogress();  
  					errorFunc('${USER_SESSION.userId}');
  				},
		            target: '#response'
		        };
			$(frmId).ajaxForm(formOptions);
			$('#ajax').hide();
		}
	
	function submitPacketUploadForm(frmId){
		alert($("#"+frmId).attr('action'));
		var options = {
	            dataType: 'json',
	            //beforeSubmit:  showRequest,
	            success: function(response) {
	                $('#container').html(response);
	                $('#ajaxMessage').show();
	                $('.ajaxMsg').html('<p>Process completed successfully.</p>');//Changed to use CSS effectively TODO
	            },
	            error: function(){
	                $('#ajaxMessage').html('<p>Error in submission, please try again.</p>');
	                $('#ajaxMessage').show();
	            },
	            url: $("#"+frmId).attr('action')
	        };
		   
		
        }
	function validate(){
  		var flag = validatePurDetails();
  		if(!flag)
  			return false;
		var pktCnt = $('#pktCount').val(); 
		var count = 0;
  		for(var a =0 ;a<pktCnt ;a++){
  			if(!$('#pktCode_'+a) || $('#pktCode_'+a).val() == ''){
				continue;	
  			}else{
  				count++;
  				var pktCode = $('#pktCode_'+a).val();
  				for(var b =1 ;b<minReqPrp.length ;b++){
  					if(minReqPrp[b] == 'MD-XD-D'){
  						var msmt =$('#MD-XD-D_'+a).val();
  						msmt = msmt.replace(/[^a-zA-Z 0-9 .]+/g,'-');
						var tempArray = msmt.split("-");	
						if(tempArray.length != 3){
							alert('Enter valid Mesurements value for Stone ID '+pktCode);
							return false;
						}else if(isNaN(tempArray[0])||isNaN(tempArray[1])||isNaN(tempArray[2]) ){
							alert('Enter valid Numeric Mesurements value for Stone ID '+pktCode);
							return false;
						}
  						
  					}else if(minReqPrp[b] == 'rap'){
  						var rate =$('#rate_'+a).val();
  						var rap =$('#rap_'+a).val();
  						if(rate=='' && rap==''){
  							alert('Enter Rate or Rap for Stone ID '+pktCode);
  							return false;
  						}
  						else if(isNaN(rate)  || isNaN(rap)){
  							alert('Enter Numeric Rate or Rap for Stone ID '+pktCode);
  							return false;
  						}
  						continue;
  					}
  					if($('#'+ minReqPrp[b] +'_'+a).val()=='' ){
  						alert('Enter minimum required details for Stone ID '+pktCode);	
  						return false;
  					} 
  				}
  			}
  		}
  		if(count == 0){
  			alert('Enter Stone Id');
  			return false;
  		}
  		return true;
  	}
  	function validateFileUpload(){
  		var flag = validatePurDetails();
  		if(!flag)
  			return false;
  		
  		if($('#file').val() ==''){
  			alert('Please select file');
  			return false;
  		}else{
  			return true;
  		}
  	}
  	
  	function upload(){
  		var flag = confirm("Are you sure you want to continue with current data, after upload you will have to mannualy currect entries one by one");
  		if(flag){
  			$('#errorMsg').html('Loading...');
  			$.getJSON('uploadStock.html', function(data) {
  	  			$('#errorMsg').html(data);
  	  		});
  		}
  	
  	}
  	function cancel(){
  		alert("Please correct the file and upload again");
  		$('#errorMsg').html('');
  	}
  	
  	
  	function validatePurDetails(){
  		if($('#vendorId').val() >-1){
			var purVar =new Array("date","dueDate","paymentTerm","exRate");
			var flag = false;
			for(var a =0 ;a<purVar.length ;a++){
				if(purVar[a] == 'exRate'){
					if($('#currency').val() != 'USD' && $('#exRate').val() == ''){
						$('#'+purVar[a]).addClass('Error');
						flag =true;
					}
				}
				else if($('#'+purVar[a]).val() == ''){
					$('#'+purVar[a]).addClass('Error');
					flag =true;
				}
			}
			if(flag){
				alert('Enter required fields');
				$('#errorMsg').html('');
				return false;
			}
		}
  		return true;
  	}
  	
  	function deletePkt(elem){
  		var thisId = elem.id;
  		var count = thisId.substring(thisId.indexOf('_')+1);
  		var pktCode = $('#pktCode_'+count).val();
  		$.getJSON('deletePkt.html',{ pktCode : pktCode}, function(data) {
  			$('#errorMsg').html(data);
  		});
  		$('#pktCode_'+count).trigger('change');
  	}

     function rapChange(count) {
    	 if($('#rap_'+count).val() != ''){
			//$('#rate_'+count).val('');
			$('#rate_'+count).attr("disabled", true);
    	 		var rapPrice = $("#rapPrice_"+count).html() ;  
		    	if(rapPrice != '' ){
		    		 	if(rapFormat == 100){
    			 			$('#rate_'+count).val(parseFloat(rapPrice *(1+ parseFloat($('#rap_'+count).val()))).toFixed(2));
						}else{
							 $('#rate_'+count).val(parseFloat(rapPrice *(1+ parseFloat($('#rap_'+count).val()/100))).toFixed(2));
						}
		    	 }
    	 }else{
    		 $('#rate_'+count).attr("disabled", false);
    	 }
  	 }
			
    	function hideDivPurchase(flag){
    	  	if(flag==1){
    			$('#PacketUploadForm').hide();
    			return;
    	  	}else $('#PacketUploadForm').show();
    	}

    	 function activate(fld){
	   		$('.buttontab').attr('id','');
	   		$(fld).attr('id','active');
   		}
	</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>
	<div id="PacketUpload" align="center">
		<br />
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('parcelUpload.html');">Parcel Upload</a> 
		<a href="javascript:void(0);" class="buttontab" id="active" onclick="func('packetUpload.html');">Packet Upload</a> 
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('fixedPacket.html');">Fixed Packet</a> <br />
		<br />
		<br />
		<div id="errorMsg">${mailMsg}</div>
		<br />
		<a href="#" class="buttontab"
			onclick="hideDivPurchase(1);activate(this);" id="active">Invard</a> <a
			href="#" class="buttontab"
			onclick="hideDivPurchase(0);activate(this);" id="">Purchase</a> <br />
		<br />

		<form id="PacketUploadForm" name="PacketUploadForm" action=""
			method="post">
			<br /> <br />
			<div class="Fixed">
				<a href="javascript:void(0);" onclick="func('stockMixList.html')">Small Size packet
					List (Purchased)</a>
			</div>
			<br /> <input type="hidden" name="mode" id="mode" />
			<div id="Table">
				<table>
					<tr valign="top">
						<td>
							<table width="100%" align="center" style="vertical-align: top;">
								<tr>
									<th><label for="vendorId">Vendor Id</label>
									</th>
									<td><select name="vendorId" id="vendorId"
										style="width: 145px;">
											<option value="-1">Select Vendor</option>
											<c:forEach var="v" items='${VENDOR_LIST}' varStatus="cnt">
												<option value='${v.id}'>${v.description}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th><label for="billNo">Bill No:</label>
									</th>
									<td><input type="text" name="billNo" id="billNo"
										class="required" size="20" />
									</td>
								</tr>
								<tr>
									<th><label for="comments">Comments:</label>
									</th>
									<td><input type="text" name="comments" id="comments"
										size="20" />
									</td>
								</tr>
							</table></td>
						<td>
							<table width="100%" align="center" style="vertical-align: top;">
								<tr>
									<th><label for="packetDate">Date:</label></th>
									<td><input type="text" name="packetDate" id="packetDate"
										class="required" size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="packetDueDate">Due Date:</label>
									</th>
									<td><input type="text" name="packetDueDate"
										id="packetDueDate" class="required" size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="paymentTerm">Payment Term</label>
									</th>
									<td><select name="paymentTerm" id="paymentTerm"
										style="width: 145px;">
											<option value='CHEQUE'>CHEQUE</option>
											<option value='CASH'>CASH</option>
									</select></td>
								</tr>
							</table></td>
						<td>
							<table width="100%" align="center">
								<tr>
									<th><label for="currency">Currency</label>
									</th>
									<td><select name="currency" id="currency"
										style="width: 145px;">
											<c:forEach var="v" items='${currencyList}' varStatus="cnt">
												<option value='${v.currAbrev}'
													<c:if test="${v.currAbrev == 'USD'}">selected</c:if>>${v.currency}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th><label for="exRate">Ex Rate:</label></th>
									<td><input type="text" name="exRate" id="exRate"
										class="number" size=10 value="1" />
									</td>
								</tr>
								<tr>
									<th><label for="tax">Tax:</label>
									</th>
									<td><input type="text" name="tax" id="tax" class="number"
										size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="expenses">Expenses:</label>
									</th>
									<td><input type="text" name="expenses" id="expenses"
										class="number" size=10 />
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div id="fileUpload" class="mainBody" align="center">
		<input type="button" value="Manual Mode"
			onclick="hideTwoDivs('fileUpload','manualUpload');">
		<form method="POST" action="upload.html" enctype="multipart/form-data"
			id="fileUploadForm">
			<br /> <input type="hidden" name="pktType" value="mixed" /> <label
				for="fileId1">Select File Format</label> <select id="fileId1"
				name="fileId1" onchange="loadFileData(this.id, 'fileUpload');">
				<c:forEach items="${fileList}" var="f" varStatus="cnt">
					<option value="${f.id}"
						<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
				</c:forEach>
			</select> <a href="javascript:void(0);" onclick="getExcel();">Excel</a> <br />
			<br /> <input type="file" name="file" id="file" /> <br /> <input
				type="submit" value="Submit" id="fileSubmit"
				onclick="submitForm('#fileUploadForm');" />
		</form>
	</div>

	<div id="manualUpload" align="center">
		<form id="PacketUploadForm" name="PacketUploadForm"
			action="uploadStockMixedManual.html" onsubmit="return validate();" method="post">
				<input type="button" value="File Upload Mode"
					onclick="hideTwoDivs('manualUpload','fileUpload');"> <label
					for="pktCount">Pkts Insert:</label> <input type="text"
					name="pktCount" id="pktCount" size="4" value="10" class="number"
					onchange="updateMap()" /> <label for="fileId2">Select File
					Format</label> <select id="fileId2" name="fileId2"
					onchange="loadFileData(this.id, 'manualUpload');">
					<c:forEach items="${fileList}" var="f" varStatus="cnt">
						<option value="${f.id}"
							<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
					</c:forEach>
				</select>
				<div class="ttl_names">
					Notes : <span class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					These shows User mistake in uploading values Kindly correct as it
					may not work in some section. <br /> DISCOUNT will be disabled as
					not required.<br /> GIA-IGI corrections for Properties like "Fluor"
					will be made automatically by system. <br /> Changing
					CTS/Color/Purity/Shape will show you rapPrice and new Discount will
					be automatically calculated; <br />Total Value will change once
					submited.
				</div>
				<table id="greenTable">
					<thead>
						<tr>
							<th>Sr No.</th>
							<c:forEach var="lst" items="${fileMappingList}">
								<c:if test="${lst.columnName != null}">
									
									<th title="${lst.columnName}">${lst.excelColumnName}</th>
									<c:if test="${lst.columnName == 'pktCode'}">
										<c:if test="${pageScope.editPkt != false}">
											<th>Stone ID (Edit)</th>
											<c:set var="editPkt" value="false" scope="page"></c:set>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody id="addStock">
					</tbody>
				</table>
			
			<!-- 					<input type="submit" value="Submit" id="manSubmit" onclick="submitForm('#manUploadForm');" onKeyPress="return disableEnterKey(event)"/> -->
			<input type="submit" id="submit" value="Submit"/>
		</form>
	</div>

</body>
</html>