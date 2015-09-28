<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
 // var colList = new Array();
  var colListHTML = new Array();
  var html= '' ;
 // var rapFormat = 0;

 var str="pufrom"+'-'+"puto";
  var pu=str.split("-");
  
  colList = ["parcelNum","sh","pcs","rate","rootPkt","c","pu","baseRate","cts"];
  colListHTML = ["parcelNum","sh","cts","pcs","rate","rootpkt","pufrom","puto","cfrom", "cto","baseRate"];
  $(document).ready(function(){
  $('input[name*="Pkt_"]').change(function(){
		var thisId= this.id;
		var pktCode= this.value;
		
		var count = thisId.substring(thisId.indexOf('_')+1);
		if(this.value==''){
			for(var b =0 ;b<colListHTML.length ;b++){
				$('#'+colListHTML[b]+'_'+count).val('');
			}
			return;
		}
		$('input[id*="Pkt_"]').each(function() {
				if(thisId != this.id && pktCode == this.value){
					$('#'+thisId).val('');
					alert('Duplicate Stone Id');
				}
		});
		
		
	
		
		
		
		
  $.getJSON('stockEntryMixAJAX.html',{pktCode: this.value}, function(data) {
		if(data == null){
			for(var b =0 ;b<colListHTML.length ;b++){
				$('#'+colListHTML[b]+'_'+count).val('');
			}
		}else{
			for(var b =0 ;b<colListHTML.length ;b++){
				//$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))));
				$('#'+colListHTML[b]+'_'+count).val(eval('data.'+ (colListHTML[b])));
			}
			
	
		//$($('#pktCode_'+count)).parents('td:first').append('<span id="span_'+count+'"><br/><a style="display:block;" href="javascript:void(0);" onclick="window.open(\'labLoadRep.html?pktCode='+pktCode+'\',\'_blank\', \'resizable=1,width=800,height=400\');">LAAAAAB</a></span>');
		}
	});
  });

	$( "#date" ).val('${CURR_DATE}');
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
	$('select[name*="Fr"]').bind("change",checkMinMax);
	
  });
  
colListParcel = ["parcelType","shFr","shTr","pcs","rate","rootPkt","cFr","cTo","puFr","puTo","baseRate","cts"];
  
  function checkMinMax(elem,prp){
	  var elemName;
	  if(this.id){
		 elemName = this.id.replace("Fr","To");
	  }
	  else{
		  elemName = elem.replace("Fr","To");
	  }
		if($('#'+elemName)!=null && $('#'+elemName).val() ==-1){
			 if(this.id){
				$('#'+elemName).val( this.value);
			 }else{
				 $('#'+elemName).val($('#'+elem).val());
			 }
		}else{
			prpOrder(elem.id,elemName);
		} 
	}
  	function prpOrder(from, to){
		if($('#'+from).val() > $('#'+to).val()  ){
			var swap = $('#'+to).val() ;
			$('#'+to).val($('#'+from).val());
			$('#'+from).val(swap);
		}
	}
  
  function show_prompt()
  { 
  var count=prompt("Enter Number of Packets","1");

  if (count!=null && count!="")
    { 
	  
	  window.location = "stockSplit.html";
	  
	  var load = window.open('stockSplit.html?pktCode='+pktCode+'&count='+count,' ','scrollbars=no,menubar=no,height=600,width=1400,resizable=yes,toolbar=no,location=no,status=no');
	  
    }
  } 
  
  var minReqPrp =new Array("code","cts","rate");
	function validateForm(){
		var pktCnt = 10; 
		var count = 0;
		if($('#countEdit').val() ==1){
			pktCnt=1;
		}
		for(var a =1 ;a<=pktCnt ;a++){
			if(!$('#code_'+a) || $('#code_'+a).val() == ''){
				continue;	
			}else{
				count++;
				var pktCode = $('#code_'+a).val();
				for(var b =1 ;b<minReqPrp.length ;b++){
						if($('#'+ minReqPrp[b] +'_'+a).val()=='' ){
							alert('Enter minimum required details for Stone ID '+pktCode);	
							return false;
						} 
					}
					var rate =$('#rate_'+a).val();
					if(isNaN(rate)){
						alert('Enter Numeric Rate for Stone ID '+pktCode);
						return false;
					}

				}
			checkMinMax('shFr_'+a, 'sh');
			checkMinMax('puFr_'+a, 'pu');
			checkMinMax('cFr_'+a, 'c');
		}
		if(count == 0){
			alert('Enter Stone Id');
			return false;
		}
		return true;
	}
  
	
 </script>

</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="mixParcel" />
	</jsp:include>
	<div id="content">
		<br /> <br />

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>

		<form id="parcel" name="parcel" action="addParcel.html" method="post"
			onsubmit="return validateForm();">
			<input type="hidden" value="10" name="count" id="count">
			<%--<table width="100%" align="center">
					<tr>
					<td><label for="vendorId">Vendor Id</label></td>	
					<td><select name="vendorId" id="vendorId" style="width: 145px;">
							<option value="-1" >Select Vendor</option>		
							<c:forEach var="v" items='${VENDOR_LIST}' varStatus="cnt">
								<option value='${v.id}'>${v.description}</option>
							</c:forEach>
						</select>
					</td>
					<td><label for="billNo">Bill No:</label></td>
					<td><input type="text" name="billNo" id="billNo" size="20"/></td>
					</tr>	
					<tr>
						<td><label for="date">Date:</label> </td>
						<td> <input type="text" name="date" id="date" class="required" size=20/></td>	
						<td> <label for="comments">Comments:</label></td>	
						<td> <input type="text" name="comments" id="comments" size="20"/></td>	
					</tr>
				
				</table> --%>


			<table align="center" id="greenTable">
				<!--  <tr>  -->
				<tr id=""
					class="ui-widget-content jqgrow ui-row-ltr selected-row ui-state-hover ui-state-highlight"
					role="row" aria-selected="true">
					<th>ParcelNum</th>
					<th>Parcel Type</th>
					<th colspan="2">sh</th>
					<th colspan="2">Clarity</th>
					<th colspan="2">Color</th>
					<th>Avg Cts/Pcs</th>
					<!-- <th>Total Cts</th> -->
					<th>Rate</th>
					<th>Root</th>
					<th>Base Price /cts</th>
					<th>Cost Price /cts</th>
				</tr>
				<tr>
					<td colspan="2"></td>
					<th>From</th>
					<th>To</th>
					<th>From</th>
					<th>To</th>
					<th>From</th>
					<th>To</th>
					<td colspan="6"></td>
					<td></td>
					<td></td>
				</tr>
				<c:if test="${param.pId !=null}">
					<input type="hidden" id="countEdit" value="1" />
					<c:set var="count" value="1"></c:set>
					<tr>
						<td><input type="text" name="code_${count}"
							id="code_${count}" size="10" value="${pMaster.code}">
						</td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.parcelType}" /> <select
							id="parcelType_${count}" name="parcelType_${count}">
								<c:forEach var="prpLov" items='${PRP_LOV["PTYP"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.shFr_so}" /> <select id="shFr_${count}"
							name="shFr_${count}" style="width: 100px;">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.shTo_so}" /> <select id="shTo_${count}"
							name="shTo_${count}" style="width: 100px;">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.puFr_so}" /> <select id="puFr_${count }"
							name="puFr_${count}">

								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.puTo_so}" /> <select id="puTo_${count }"
							name="puTo_${count }">

								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.colFr_so}" /> <select id="cFr_${count}"
							name="cFr_${count}">
								<option value="-1">All</option>

								<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><fmt:parseNumber var="z" integerOnly="true"
								value="${pMaster.colTo_so}" /> <select id="cTo_${count}"
							name="cTo_${count}">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
									<option value='${prpLov.id}'
										<c:if test="${z == prpLov.id}">selected</c:if>>${prpLov.description}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" id="cts_${count }"
							name="cts_${count }" size="8" value="${pMaster.cts}"></td>
						<td><input type="text" name="rate_${count}"
							id="rate_${count}" size="8" value="${pMaster.rate}"></td>
						<td><input type="text" name="rootpkt_${count }"
							id="rootpkt_${count }" size="8" value="${pMaster.rootPkt}">
						</td>
						<td><input type="text" name="baseRate_${count }"
							id="baseRate_${count }" size="8" value="${pMaster.baseRate}">
						</td>
						<td><input type="text" name="costRate_${count }"
							id="costRate_${count }" size="8" value="${pMaster.costRate}">
						</td>
					</tr>
				</c:if>
				<c:if test="${param.pId ==null}">
					<c:forEach begin="1" end="10" var="count">
						<tr>
							<td><input type="text" name="code_${count}"
								id="code_${count}" size="10">
							</td>
							<td><select id="parcelType_${count}"
								name="parcelType_${count}">
									<c:forEach var="prpLov" items='${PRP_LOV["PTYP"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="shFr_${count}" name="shFr_${count}"
								style="width: 100px;">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="shTo_${count}" name="shTo_${count}"
								style="width: 100px;">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="puFr_${count }" name="puFr_${count}">

									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="puTo_${count }" name="puTo_${count }">

									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>

							<td><select id="cFr_${count}" name="cFr_${count}">
									<option value="-1">All</option>

									<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="cTo_${count}" name="cTo_${count}">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>


							<td><input type="text" id="cts_${count }"
								name="cts_${count }" size="8"></td>
							<%--   <td ><input type="text"   id="totCts_${count }" name="totCts_${count }"  size="8"> </td> --%>
							<td><input type="text" name="rate_${count}"
								id="rate_${count}" size="8"></td>
							<td><input type="text" name="rootpkt_${count }"
								id="rootpkt_${count }" size="8"></td>
							<td><input type="text" name="baseRate_${count }"
								id="baseRate_${count }" size="8"></td>
							<td><input type="text" name="costRate_${count }"
								id="costRate_${count }" size="8"></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div align="center">
				<input type="submit" id="submit" value="Submit" />
			</div>
		</form>



	</div>
</body>
</html>








