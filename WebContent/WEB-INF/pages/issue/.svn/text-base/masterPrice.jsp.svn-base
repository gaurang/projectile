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
	  var mStkId = true;
		html += '<tr id="row_ZZZ">';
		html += '<td>ZZ</td>';
		 
		<c:forEach var="lst" items="${prpDataList}">
		if(mStkId == true){
			html +='<td><input type="text" name="masterStkPktId_ZZZ" id="masterStkPktId_ZZZ" size="4"  /></td>';
			mStkId = false; }
		<c:if test="${lst.prp != null}">
				var onchange = '';
				if('${lst.prp}' == 'SH'|| '${lst.prp}' == 'PU'|| '${lst.prp}' == 'CTS'){
					onchange = 'onchange="getNewRap(ZZZ);"';
				}
				if('${lst.prp}' == 'C'){
					onchange += 'onchange="rapAllowed(ZZZ);getNewRap(ZZZ);"';
				}
				if('${lst.prp}' == 'RATE'){
					onchange = 'onchange="rapAllowed(ZZZ);rapAllowedRate(ZZZ)"';
					html += '<td id="rapPrice_ZZZ"></td>';
				}
				if('${lst.prp}' == 'RAP'){
					onchange = 'onchange="rapChange(ZZZ)"';
				}
			<c:choose>
				<c:when test="${!empty PRP_LOV[lst.prp] && lst.prp != 'FNCO' && lst.prp != 'FNC'}">
				html +='<td><select name="${ lst.prp}_ZZZ" id="${lst.prp}_ZZZ" class="select" style="width:60px;" '+onchange+'>'+
							'<option value="">All</option>'+
							<c:forEach var="prpLov" items="${PRP_LOV[lst.prp]}">
							 	'<option value="${prpLov.id}">${prpLov.description}</option>'+
							</c:forEach>
						'</select></td>';					
				</c:when>
		 <c:otherwise>
		       
		         	html +='<td><input type="text" name="${lst.prp}_ZZZ" id="${lst.prp}_ZZZ" size="4" '+onchange+' /></td>';
					
					
				
				</c:otherwise> 
			</c:choose>
			colList[index]= '${lst.prp}';
		
			index++;
		</c:if>
	</c:forEach>
	html +='<td><img src="images/Close-16.png" alt="Delete" id="delete_ZZZ"  style="cursor:pointer;display:none;" onclick="deletePkt(this);"/></td>';
	html += '</tr>';
      updateMap();
  
    
      
      
    /*  var partyData = [
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
  	});*/
  	
  	
  	/* no  $("#fileUpload #vendorName").result(function(event, data, formatted) {
  		$("#fileUpload #vendorId").val(!data ? "-1" : data.id);
  		alert($("#fileUpload #vendorId").val());
  	});
  /*no	$("#mannualUpload #vendorName").result(function(event, data, formatted) {
  		var selId= !data ? "-1" : data.id;
  		$("#mannualUpload vendorId").val(selId);
  		$('input[name*="vendorId_"]').val(selId);
  		alert(selId);
  	}); no*/
 		
	  


      $('input[name*="PKTMSTID_"]').change(function(){
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
	  		$('input[id*="PKTMSTID_"]').each(function() {
	  				if(thisId != this.id && PKTMSTID == this.value){
	  					$('#'+thisId).val('');
	  					alert('Duplicate Stock Id');
	  				}
  			});
	  	$.getJSON('priceMasterSearchAJAX.html',{PKTMSTID: this.value}, function(data) {
	  			if(data == null){
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
	  					
	  					if((eval('data.'+ (colList[b].replace(/-/g,'')))) == null && eval('data.'+ (colList[b].replace(/-/g,''))+'_val') != null){
	  						$('#'+colList[b]+'_'+count).addClass('error');
	  					} 
	  				}
	  			$('#delete_'+count).show();
	  			$('#span_'+count).remove();
	  			$($('#pktCode_'+count)).parents('td:first').append('<span id="span_'+count+'"><br/><a style="display:block;" href="javascript:void(0);" onclick="window.open(\'labLoadRep.html?pktCode='+pktCode+'\',\'_blank\', \'resizable=1,width=800,height=400\');">LAB</a></span>');
	  			}
	  			rapAllowed(count);
	  		});
	  	});
		
	  //	$('#fileUpload').hide();
		/* $( "#date" ).DatePicker({
			format:'d-m-Y',
			current: '${CURR_DATE}',
			calendars: 1,
			date: '${CURR_DATE}',
			onChange: function(formated, dates){
				$('#date').val(formated);
				$('#date').DatePickerHide();
			}
		});
		
		$( "#date" ).val('${CURR_DATE}');
		$("#exRateTD").hide();
		*/
		var count = $('#pktCount').val();
  });
  	function getNewRap(count){
  		var c= $('#C_'+count).val();
  		var sh= $('#SH_'+count).val();
  		var cts= $('#CTS_'+count).val();
  		var pu= $('#PU_'+count).val();
  		
  		if(c != '' || sh!= ''|| cts !=''||pu!= ''){
	  		$.getJSON('stockRapPriceAJAX.html',{c:c,sh:sh,cts:cts,pu:pu}, function(data) {
	  			if(data && data!=null){
	  				$("#rapPrice_"+count).html(data);
	  				var rate = $('#RATE_'+count).val();
	  				if(rate != ''){
	  					if(rapFormat == 100){
	  						$('#RAP_'+count).val(parseFloat(rate/parseFloat(data)-1).toFixed(2));
	  					}else{
	  						$('#RAP_'+count).val(parseFloat(rate*100/parseFloat(data)-100).toFixed(2));
	  					}
	  					
	  				}
	  			}
	  		});
  		}
  	}
  
     function rapAllowed(count) {
		if($('#C_'+count).val() == '' ){
			$('#RAP_'+count).val('');
			$('#RAP_'+count).attr("disabled", true);
		}else{
			$('#RAP_'+count).attr("disabled", false);
		}
     }
     function rapAllowedRate(count) {
    	 if($('#RATE_'+count).val() != ''){
 			//$('#rap_'+count).val('');
 			$('#RAP_'+count).attr("disabled", true);
 			var rapPrice = $("#rapPrice_"+count).html() ;  
	    	if(rapPrice != '' && rapPrice!=null){
	    			if(rapFormat == 100){
	    				$('#RAP_'+count).val(parseFloat($('#RATE_'+count).val()/parseFloat(rapPrice)-1).toFixed(2));
					}else{
						$('#RAP_'+count).val(parseFloat($('#RATE_'+count).val()*100/parseFloat(rapPrice)-100).toFixed(2));
					}
	    	 }
    	 }else{
    		 $('#RAP_'+count).attr("disabled", false);
    	 }
      }
     function rapChange(count) {
    	 if($('#RAP_'+count).val() != ''){
			//$('#rate_'+count).val('');
			$('#RATE_'+count).attr("disabled", true);
    	 		var rapPrice = $("#rapPrice_"+count).html() ;  
		    	if(rapPrice != '' ){
		    		 	if(rapFormat == 100){
    			 			$('#RATE_'+count).val(parseFloat(rapPrice *(1+ parseFloat($('#RAP_'+count).val()))).toFixed(2));
						}else{
							 $('#RATE_'+count).val(parseFloat(rapPrice *(1+ parseFloat($('#RAP_'+count).val()/100))).toFixed(2));
						}
		    	 }
    	 }else{
    		 $('#RATE_'+count).attr("disabled", false);
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
  	}
  	
  	function hideDiv(fld1,fld2){
  		$('#'+fld1).hide();
  		$('#'+fld2).show();
  	}
  	
  	var minReqPrp =new Array("PKTMSTID","SH","CTS","C","PU","RAP","CT","PO","SY","FL");
  	
//var minReqPrp =new Array();
  //var minReqPrp =new Array("PKTMSTID","SH");
 	
  
  
  	function validateForm(){
		var pktCnt = $('#pktCount').val(); 
		var count = 0;
  		for(var a =0 ;a<pktCnt ;a++){
  			if(!$('#masterStkPktId_'+a) || $('#masterStkPktId_'+a).val() == ''){
				continue;	
  			}else{
  				count++;
  				var PKTMSTID = $('#masterStkPktId_'+a).val();
  				for(var b =1 ;b<minReqPrp.length ;b++){
  					if(minReqPrp[b] == 'RAP'){
  						
  						var rate =$('#RATE_'+a).val(); alert('minReqPrp if RAP'+ rate);
  						var rap =$('#RAP_'+a).val();   alert('minReqPrp if RAP'+ rap);
  						if(rate=='' && rap==''){
  							alert('Enter Rate or Rap for Stock ID '+PKTMSTID);
  							return false;
  						}
  						else if(isNaN(rate)  || isNaN(rap)){
  							alert('Enter Numeric Rate or Rap for Stock ID '+PKTMSTID);
  							return false;
  						}
  						continue;
  					}
  				if($('#'+ minReqPrp[b] +'_'+a).val()=='' ){
  						alert('Enter minimum required details for Stock ID '+PKTMSTID);	
  						return false;
  					} 
  				}
  			}
  		}
  		if(count == 0){
  			alert('Enter stock Id');
  			return false;
  		}
  		return true;
  	}
  	
  	function submitForm(frmId){
	  		$('#ajax').show();
	  		$('#errorMsg').html('Loading....');
	  		
  		    var formOptions = {
  		    		dataType: 'json',
  		    		data : {vendorId:$('#vendorId').val(),billNo:$('#billNo').val(),date:$('#date').val(),comments:$('#comments').val()},
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
	  		        	  alert(textStatus);
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
  	/*function deletePkt(elem){
  		var thisId = elem.id;
  		var count = thisId.substring(thisId.indexOf('_')+1);
  		var pktCode = $('#pktCode_'+count).val();
  		$.getJSON('deletePkt.html',{ pktCode : pktCode}, function(data) {
  			$('#errorMsg').html(data);
  		});
  		$('#pktCode_'+count).trigger('change');
  	}*/
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
  </script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="purchase" />
	</jsp:include>
	<div>
		<br />
		<br />

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>




		<div id="mannualUpload">

			<form method="POST" action="addMasterPrice.html" id="manUploadForm">

				<label for="pktCount">Pkts Insert:</label> <input type="text"
					name="pktCount" id="pktCount" size="4" value="10" class="number"
					onchange="updateMap()" />

				<table id="greenTable">
					<thead>
						<tr>
							<th>Sr No.</th>
							<c:if test="${pageScope.mStkId != false}">
								<th id>Master Stock ID</th>
								<c:set var="mStkId" value="false" scope="page"></c:set>
							</c:if>
							<c:forEach var="lst" items="${prpDataList}">
								<c:if test="${lst.prpDesc!= null}">
									<c:if test="${lst.prpDesc == 'Rate'}">
										<th>Rap Price</th>

									</c:if>
									<th title="${lst.prpDesc}">${lst.prpDesc}</th>
									<!--  <c:if test="${lst.prpDesc == 'pktCode'}">
								<c:if test="${pageScope.editPkt != false}"> -->
									<!--	<th>Stone ID (Edit)</th>		
							 		<c:set var="editPkt" value="false" scope="page" ></c:set>
								</c:if>
							</c:if>	-->
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
		<div id="response"></div>
	</div>
</body>
</html>



