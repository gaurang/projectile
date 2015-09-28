<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
  var colList = new Array();
  var html= '' ;
  var rapFormat = 0;
  
  if('${rapFormat}' == 100){
	  rapFormat =100;
  }
  $(document).ready(function(){
	  var index = 0;
	  var editPkt = 1;
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
	
  	/*$("#fileUpload #vendorName").result(function(event, data, formatted) {
  		$("#fileUpload #vendorId").val(!data ? "-1" : data.id);
  		//alert($("#fileUpload #vendorId").val());
  	});*/
  	
  		
	  	$('input[name*="pktCode_"]').change(function(){
	  		var thisId= this.id;
	  		var pktCode= this.value;
	  		
	  		var count = thisId.substring(thisId.indexOf('_')+1);
	  		if(this.value==''){
	  			
	  			for(var b =1 ;b<colList.length ;b++){
  					$('#'+colList[b]+'_'+count).val('');
  					$('#rapPrice_'+count).html('');
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
		
	  	//$('#fileUpload').hide();
				
		
		
		var count = $('#pktCount').val();

		$('#pktApprove').hide();
	
  });
  	function loadStock(pktCode,fileId,count,pktId,grpId){
  		$.getJSON('stockSearchAJAX.html',{pktCode: this.value, fileId:fileId,pktId:pktId,grpId:grpId}, function(data) {
  			if(data !=null && data.statusMsg.length > 0){
  				$('#pktCode_'+count).val('');
  				alert(data.statusMsg);
  			}
  			else if(data == null){
  				for(var b =1 ;b<colList.length ;b++){
  					$('#'+colList[b]+'_'+count).val('');
  					$('#rapPrice_'+count).html('');
  					$('#'+colList[b]+'_'+count).removeClass('error');
  				}
  				$('#delete_'+count).hide();
  				$('#span_'+count).remove();
  			}else{
  			for(var b =1 ;b<colList.length ;b++){
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
	  						$('#'+colList[b]+'_'+count).addClass('error');
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
	  				if(rate != ''){
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
  		<c:if test="${editPkt == 1}">
  			loadStock("",0,count-1, '${param.pktId}', '${param.grpId}');
  			$('#LAB_0').attr('readonly',true);
  		</c:if>
  	}
  	function hideDiv(fld1,fld2){
  		$('#'+fld1).hide();
  		$('#'+fld2).show();
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
							alert('Enter valid Measurements value for Stone ID '+pktCode);
							return false;
						}else if(isNaN(tempArray[0])||isNaN(tempArray[1])||isNaN(tempArray[2]) ){
							alert('Enter valid Numeric Measurements value for Stone ID '+pktCode);
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
  		var flag = confirm("Are you sure you want to continue with current data, after upload you will have to manually currect entries one by one");
  		if(flag){
  			$('#errorMsg').html('Loading...');
  			$.getJSON('uploadStk.html', function(data) {
  	  			$('#errorMsg').html(data);
  	  			$('#fileUpload').hide();
  	  			$('#pktApprove').show();
  	  		});
  		}
  	
  	}
  	function cancel(){
  		alert("Please correct the file and upload again");
  		$('#errorMsg').html('');
  	}
  	function submitForm(frmId){
	  		$('#ajax').show();
	  		$('#errorMsg').html('Loading....');
  		    var formOptions = {
  		    		dataType: 'json',
  		    		data : {},
  		    		//data : $('#purchaseDetails').serialize() ,
  		            beforeSubmit: function() {
  		            	if(frmId != '#fileUploadForm'){
  		            		return  validateForm();
  		            	}else{
  		            		return validateFileUpload();
  		            	}
  		            },
  		            success:  function(jsonObj) {
  		            	var submit = "<div> <input type='button' value='Submit'  onclick='upload();'> <input type='button' value='cancel' onclick='cancel();'></div>"
  		            		if(frmId == '#fileUploadForm'){
  		            			$('#errorMsg').html(submit+jsonObj);
  		            		}else{
  		            			$('#errorMsg').html(jsonObj);
  		            		}
  		            	//setTimeout (function() {clearMsg();} ,5000);  
  		            },
	  		          error: function(xmlHttpRequest, textStatus, errorThrown) {
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
  		window.location = 'stockEntry.html?fileId='+$('#'+fld).val();
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
  	function labFile(lab)
  	{
		<c:forEach items="${fileList}" var="f" varStatus="cnt">
		 	 if( '${f.lab}'.trim() == lab.trim()){
				$('#fileId1').val('${f.id}');
			 }
		</c:forEach>
  	}
  </script>
</head>
<body>


	<div>
		<br />
		<br />

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>

		<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->


		<div id="fileUpload" class="mainBody">
			<form method="POST" action="upload.html"
				enctype="multipart/form-data" id="fileUploadForm">
				<br /> <label for="LAB">Select LAB</label> <select id="LAB"
					name="LAB" onchange="labFile(this.value)">
					<option value="">Select</option>
					<c:forEach items="${PRP_LOV['LAB']}" var="f" varStatus="cnt">
						<option value="${f.description}"
							<c:if test='${f.id == param.description}'>selected</c:if>>${f.description}</option>
					</c:forEach>
				</select> <label for="fileId1">Select File Format</label> <select
					id="fileId1" name="fileId1">
					<option value="">Select</option>
					<c:forEach items="${fileList}" var="f" varStatus="cnt">
						<option value="${f.id}"
							<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
					</c:forEach>
				</select> <br />
				<br /> <input type="file" name="file" id="file" /> <br /> <input
					type="submit" value="Done" id="fileSubmit"
					onclick="submitForm('#fileUploadForm');" />
			</form>
		</div>
		<div id="pktApprove">
			<input type="button" name="pktsub" id="clear" value="pktsub"
				onclick="window.location = 'PacketCert.html'" />
			<!-- <a href="selectPkt.html"> Select packets</a> -->
		</div>


		<div id="response"></div>
	</div>
</body>
</html>