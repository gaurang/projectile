<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

.active {
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
  $(document).ready(function(){
	  <c:if test="${PAGE_VALUE!='buttontab active'}">
	  	$('#purchaseDetailsForm').hide();
	  </c:if>
	  <c:if test="${PAGE_VALUE=='buttontab active'}">
	         activate('#purchase');
      </c:if>
	  	var index = 0;
	  	var editPkt = true;
		html += '<tr id="row_ZZZ">';
		html += '<td>ZZ  <input type="hidden" name="labVal_ZZZ"  id="labVal_ZZZ"/><input type="hidden" name="grpId_ZZZ"  id="grpId_ZZZ"/></td>';
		<c:forEach var="lst" items="${fileMappingList}">
		<c:if test="${lst.columnName != null}">
				var onchange = '';
				if('${lst.columnName}' == 'SH'|| '${lst.columnName}' == 'PU'|| '${lst.columnName}' == 'CTS'){
					onchange = 'onchange="getNewRap(ZZZ);"';
				}
				if('${lst.columnName}' == 'C'){
					onchange += 'onchange="rapAllowed(ZZZ);getNewRap(ZZZ);"';
				}
				if('${lst.columnName}' == 'rate'){
					onchange = 'onchange="rapAllowed(ZZZ);rapAllowedRate(ZZZ)"';
					html += '<td id="rapPrice_ZZZ"></td>';
				}
				if('${lst.columnName}' == 'rap'){
					onchange = 'onchange="rapChange(ZZZ)"';
				}
			<c:choose>
				<c:when test="${!empty PRP_LOV[lst.columnName] && lst.columnName != 'FNCO' && lst.columnName != 'FNC'}">
				html +='<td><select name="${ lst.columnName}_ZZZ" id="${lst.columnName}_ZZZ" class="select" style="width:60px;" '+onchange+'>'+
							'<option value="">All</option>'+
							<c:forEach var="prpLov" items="${PRP_LOV[lst.columnName]}">
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
	html +='<td><img src="images/Close-16.png" alt="Delete" id="delete_ZZZ"  style="cursor:pointer;display:none;" onclick="deletePkt(this);"/></td>';
	html += '</tr>';
      updateMap();
  	var partyData = [
  	           	<c:forEach items="${PARTY_LIST}" var="p" varStatus="count">
  	           		{ id: "${p.id}", description: "${p.companyName}/${p.branchCode}/${p.termCode}" }
  	           		<c:if test="${!count.last}">,</c:if>
  	           	</c:forEach>
  	           	]
  	$('input[id*="vendorName"]').autocomplete(partyData,
  			{
  			minChars: 0,
  			autoFill: true,
  			matchContains: false,
  			selectFirst: true,
  			formatItem: function(row, i, max, searchTerm) {	
  				return row.description;
  			},
  			formatMatch: function(row, i, max) {
  				return row.description;
  			},
  			formatResult: function(row, i, max) {
  					return row.description;
  			}	
  	});
  	$("#fileUpload #vendorName").result(function(event, data, formatted) {
  		$("#fileUpload #vendorId").val(!data ? "-1" : data.id);
  		//alert($("#fileUpload #vendorId").val());
  	});
  	$("#mannualUpload #vendorName").result(function(event, data, formatted) {
  		var selId= !data ? "-1" : data.id;
  		$("#mannualUpload vendorId").val(selId);
  		$('input[name*="vendorId_"]').val(selId);
  		//alert(selId);
  	});
  		
	  	$('input[name*="pktCode_"]').change(function(){
	  		var thisId= this.id;
	  		var pktCode= this.value;
	  		
	  		var count = thisId.substring(thisId.indexOf('_')+1);
	  		if(this.value==''){
	  			
	  			for(var b =1 ;b<colList.length ;b++){
	  				if(colList[b] != 'pktCode'){
	  					$('#'+colList[b]+'_'+count).val('');
	  					$('#rapPrice_'+count).html('');
	  				}
  				}
	  			$('#span_'+count).remove();
				return;
	  		}
	  		$('input[id*="pktCode_"]').each(function() {
	  				if(thisId != this.id && pktCode == this.value){
	  					$('#'+thisId).val('');
	  					alert('Duplicate Stone Id');
	  				}
  			});
	  		loadStock(this.value,$('#fileId2').val(),count, -1, 0);
	  	});
		
	  	$('#fileUpload').hide();
		$( "#date" ).DatePicker({
			format:'d-m-Y',
			current: '${CURR_DATE}',
			calendars: 1,
			date: '${CURR_DATE}',
			onChange: function(formated, dates){
				$('#date').val(formated);
				$('#date').DatePickerHide();
			}
		});
		
		$( "#dueDate" ).DatePicker({
			format:'d-m-Y',
			current: '${CURR_DATE}',
			calendars: 1,
			date: '${CURR_DATE}',
			onChange: function(formated, dates){
				$('#dueDate').val(formated);
				$('#dueDate').DatePickerHide();
			}
		});
		
		$( "#date" ).val('${CURR_DATE}');
		$( "#dueDate" ).val('${CURR_DATE}');
		$("#exRateTD").hide();
		
		var count = $('#pktCount').val();
		$('#fileUploadTab').show();
	
		$("input[id*=editPktCode_]").each(function(){
			$(this).attr('disabled',true);
		});
  });
 //Display data on pkt enter ajax
  	function loadStock(pktCode,fileId,count,pktId,grpId){
  		$.getJSON('stockSearchAJAX.html',{pktCode: pktCode, fileId:fileId,pktId:pktId,grpId:grpId}, function(data) {
  			//alert("Data:"+data+", Data MSG: "+data+", Data Lenght: "+data.length);
  			if(data !=null && data.length > 0){
  				$('#pktCode_'+count).val('');
  				alert(data);
  			}
  			else if(data == null){
  				for(var b =1 ;b<colList.length ;b++){
  					if(colList[b] == 'pktCode'){
						continue;
  	  	  	  		}
  					$('#'+colList[b]+'_'+count).val('');
  					$('#rapPrice_'+count).html('');
  					$('#'+colList[b]+'_'+count).removeClass('error');
  				}
  				$('#delete_'+count).hide();
  				$('#span_'+count).remove();
  			}else{
  			for(var b =1 ;b<colList.length ;b++){
  	  				if(colList[b] == '' || colList[b] == 'pktCode' ){
						continue;
  	  	  	  		}
  					$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))));
  					if(colList[b] =='rate' ){
  						$('#rapPrice_'+count).html(eval('data.rapPrice'));
  					}
  					if(colList[b] == 'rap' && rapFormat == 100){
  						var rapDisc = parseFloat(parseFloat(eval('data.rap'))/rapFormat);
  						$('#rap_'+count).val(rapDisc);
  					}
  					if(colList[b]!= 'FNC' && colList[b]!= 'FNCO'){
	  					if((eval('data.'+ (colList[b].replace(/-/g,'')))) == null && eval('data.'+ (colList[b].replace(/-/g,''))+'_val') != null){
	  						if($('#'+colList[b]+'_'+count).is('input'))
	  							$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))+'_val'));
	  						else{
	  							$('#'+colList[b]+'_'+count).addClass('error');
	  							
	  						}
  						} 
  					}else{
  						$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))+'_val'));
						}
  				}
  			if($('#pktCode_'+count).val()==''){
  				$('#pktCode_'+count).val(data.pktCode);
  			}
	  		$('#labVal_'+count).val(data.LAB_val);
	  		$('#grpId_'+count).val(data.grpId);

	  		$('#delete_'+count).show();
  			$('#span_'+count).remove();
  			$($('#pktCode_'+count)).parents('td:first').append('<span id="span_'+count+'"><br/><a style="display:block;" href="javascript:void(0);" onclick="window.open(\'labLoadRep.html?pktCode='+pktCode+'\',\'_blank\', \'resizable=1,width=800,height=400\');">LAB</a></span>');
  			}
  			rapAllowed(count);
  		});
  	}
  
  	function getNewRap(count){
  		var c= $('#C_'+count).val();
  		var sh= $('#SH_'+count).val();
  		var cts= $('#CTS_'+count).val();
  		var pu= $('#PU_'+count).val();
  		
  		if(c != '' || sh!= ''|| cts !=''||pu!= ''){
	  		$.getJSON('stockRapPriceAJAX.html',{c:c,sh:sh,cts:cts,pu:pu}, function(data) {
	  			if(data && data!=null){
	  				$("#rapPrice_"+count).html(data);
	  				var rate = $('#rate_'+count).val();
	  				if(rate != '' && !isNaN(rate)){
	  					if(rapFormat == 100){
	  						$('#rap_'+count).val(parseFloat(rate/parseFloat(data)-1).toFixed(2));
	  					}else{
	  						$('#rap_'+count).val(parseFloat(rate*100/parseFloat(data)-100).toFixed(2));
	  					}
	  					
	  				}
	  			}
	  		});
  		}
  	}
  
     function rapAllowed(count) {
		if($('#C_'+count).val() == '' ){
			$('#rap_'+count).val('');
			$('#rap_'+count).attr("disabled", true);
		}else{
			$('#rap_'+count).attr("disabled", false);
		}
     }
     function rapAllowedRate(count) {
    	 if($('#rate_'+count).val() != ''){
 			//$('#rap_'+count).val('');
 			$('#rap_'+count).attr("disabled", true);
 			var rapPrice = $("#rapPrice_"+count).html() ;  
	    	if(rapPrice != '' ){
	    			if(rapFormat == 100){
	    				$('#rap_'+count).val(parseFloat($('#rate_'+count).val()/parseFloat(rapPrice)-1).toFixed(2));
					}else{
						$('#rap_'+count).val(parseFloat($('#rate_'+count).val()*100/parseFloat(rapPrice)-100).toFixed(2));
					}
	    	 }
    	 }else{
    		 $('#rap_'+count).attr("disabled", false);
    	 }
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
  			loadStock("",-1,count-1, '${param.pktId}', '${param.grpId}');
  			$('#LAB_0').attr('readonly',true);
  		</c:if>
  	}
  	
  	
  	//var minReqPrp =new Array("pktCode","SH","CTS","C","PU","MD-XD-D","rap","CT","PO","SY","FL");
  	var minReqPrp =new Array("pktCode","SH","CTS","PU");
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
  	function validateForm(){
  		var pktValue='<spring:message code="b2b.pktcode.auto"></spring:message>';
  		if(pktValue!='Y'){
	  		var flag = validatePurDetails();
	  		if(!flag)
	  			return false;
			var pktCnt = $('#pktCount').val(); 
			var count = 0;
	  		for(var a =0 ;a<pktCnt ;a++){
	  			if((!$('#pktCode_'+a) || $('#pktCode_'+a).val() == '')){
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
  			$.getJSON('uploadStk.html', function(data) {
  	  			$('#errorMsg').html(data);
  	  		});
  		};
  	
  	}
  	function cancel(){
  		alert("Please correct the file and upload again");
  		$('#errorMsg').html('');
  	}
  	function submitForm(frmId){
		$('#ajax').show();
	   var formOptions = {
	    		dataType: 'json',
	    		data : {vendorId:$('#vendorId').val(),billNo:$('#billNo').val(),date:$('#date').val(),comments:$('#comments').val(),dueDate:$('#dueDate').val()
	    			,paymentTerm:$('#paymentTerm').val(),currency:$('#currency').val(),exrate:$('#exRate').val(),tax:$('#tax').val(),expenses:$('#expenses').val()},
	            beforeSubmit: function() {
	            	if(frmId != '#fileUploadForm'){
	            		return  validateForm();
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
	            			$('#main').removeClass("overlay");
	            		}else{
	            			$('#errorMsg').html(jsonObj);
	          		};
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
  	function clearMsg(){
  		$('#errorMsg').html('');
  	}
  	function loadFileData(fld){
  		func('stockEntry.html?fileId='+$('#'+fld).val()+'&pageValue='+$('#purchase').attr('class'));
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
  	function disableEnterKey(e)
  	{
  	     var key;

  	     if(window.event)
  	          key = window.event.keyCode;     //IE
  	     else
  	          key = e.which;     //firefox

  	     if(key == 13)
  	          return false;
  	     else
  	          return true;
  	}
  	function hideDiv(fld1,fld2){  	  	
		$('#'+fld1).hide();
		$('#'+fld2).show();
  	}

  	function hideDivPurchase(flag){
  	  	if(flag==1){
  			$('#purchaseDetailsForm').hide();
  			activate();
  			return;
  	  	}else $('#purchaseDetailsForm').show();
  	}

  	 function activate(fld){
 		$('.buttontab').attr('class','buttontab');
 		$(fld).attr('class','buttontab active');
  	 }
  	function getExcel(){
		window.location.href='getExcel.html?fileId='+$("#fileId2").val();
	}
  	function enableEdit(elemId){
  	    $("input[id*="+elemId+"_]").each(function(){
  	        if($("#"+elemId+"").is(':checked')==true){
  	            $(this).attr('disabled',false);
  	        }
  	        else{
  	            $(this).attr('disabled',true);
  	        }
  	    });
  	}
  </script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="purchase" />
	</jsp:include>
	<div id="main">
		<br />
		<br />

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		<br>
		<div id="fileUploadTab" align="center">
			<a href="javascript:void(0);" class="buttontab active"
				onclick="hideDivPurchase(1);activate(this);" id="inward">Inward</a>
			<a href="javascript:void(0);" class="buttontab"
				onclick="hideDivPurchase(0);activate(this);" id="purchase">Purchase</a>
			<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->
		</div>
		<br>
		<c:if test="${editPacket != 1}">
			<div id="purchaseDetailsForm" align="center">
				<form action="" method="post" id="purchaseDetails">
					<table border="1px" bordercolor="black">
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
											size="20" />
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
										<th><label for="date">Date:</label></th>
										<td><input type="text" name="date" id="date"
											class="required" size=10 />
										</td>
									</tr>
									<tr>
										<th><label for="dueDate">Due Date:</label>
										</th>
										<td><input type="text" name="dueDate" id="dueDate"
											class="required" size=10 />
										</td>
									</tr>
									<tr>
										<th><label for="paymentTerm">Payment Term</label>
										</th>
										<td><select name="paymentTerm" id="paymentTerm"
											style="width: 145px;">
												<option value='CHAQUE'>CHEQUE</option>
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
				</form>
			</div>
			<br/>
			<div id="fileUpload" class="mainBody" align="center">
				<input type="button" value="Manual Mode"
					onclick="hideDiv('fileUpload','mannualUpload');">
				<form method="POST" action="upload.html"
					enctype="multipart/form-data" id="fileUploadForm">
					<br /> <input type="hidden" name="pktType" value="single" /> 
					<label for="fileId1">Select File Format</label> 
					<select id="fileId1" name="fileId1" onchange="loadFileData(this.id);">
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
			<div id="mannualUpload">
				<form method="POST" action="uploadStockManual.html"
					id="manUploadForm">
					<div align="center">
						<input type="button" value="File Upload Mode"
						onclick="hideDiv('mannualUpload','fileUpload');"> <label
						for="pktCount">Pkts Insert:</label> <input type="text"
						name="pktCount" id="pktCount" size="4" value="10" class="number"
						onchange="updateMap()" /> <label for="fileId2">Select File
						Format</label> <select id="fileId2" name="fileId2"
						onchange="loadFileData(this.id);">
						<c:forEach items="${fileList}" var="f" varStatus="cnt">
							<option value="${f.id}"
								<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
						</c:forEach>
						</select>
						<div class="ttl_names">
							Notes : <span class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							These shows User mistake in uploading values Kindly correct as it
							may not work in some section. <br /> DISCOUNT will be disabled as
							not required.<br /> GIA-IGI corrections for Properties like
							"Fluor" will be made automatically by system. <br /> Changing
							CTS/Color/Purity/Shape will show you rapPrice and new Discount
							will be automatically calculated; <br />Total Value will change
							once submited.
						</div>
					</div>
					

					<table id="greenTable">
						<thead>
							<tr>
								<th>Sr No.</th>
								<c:forEach var="lst" items="${fileMappingList}">
									<c:if test="${lst.columnName != null}">
										<c:if test="${lst.columnName == 'rate'}">
											<th>Rap Price</th>
										</c:if>
										<th title="${lst.columnName}">${lst.excelColumnName}</th>
										<c:if test="${lst.columnName == 'pktCode'}">
											<c:if test="${pageScope.editPkt != false}">
												<th>Stone ID (Edit) <input type="checkbox" value="1" onchange="enableEdit(this.id);" name="editPktCode" class="editPktCode" id="editPktCode"></th>
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
					<input type="submit" value="Submit" id="manSubmit"
						onclick="submitForm('#manUploadForm');"
						onKeyPress="return disableEnterKey(event)" />
				</form>
			</div>
		</c:if>
		<c:if test="${editPacket == 1}">
			<form method="POST" action="uploadStockManual.html"
				id="manUploadForm">
				<input type="hidden" name="pktCount" id="pktCount" value="1" />
				<table id="greenTable">
					<thead>
						<tr>
							<th>Sr No.</th>
							<c:forEach var="lst" items="${fileMappingList}">
								<c:if test="${lst.columnName != null}">
									<c:if test="${lst.columnName == 'rate'}">
										<th>Rap Price</th>
									</c:if>
									<th title="${lst.columnName}">${lst.excelColumnName}</th>
									<c:if test="${lst.columnName == 'pktCode'}">
										<c:if test="${pageScope.editPkt != false}">
											<th>Stone ID (Edit) <input type="checkbox" value="1" onchange="enableEdit(this.class);" class="editPktCode"> </th>
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
				<input type="submit" value="Submit" id="manSubmit"
					onclick="submitForm('#manUploadForm');"
					onKeyPress="return disableEnterKey(event)" />
			</form>
		</c:if>
		<div id="response"></div>
	</div>
</body>
</html>