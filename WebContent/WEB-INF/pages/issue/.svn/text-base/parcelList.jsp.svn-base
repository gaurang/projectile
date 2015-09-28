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
				$('#'+colListHTML[b]+'_'+count).val(eval('data.'+ (colListHTML[b])));
			}
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
	  func("stockSplit.html");
	  var load = window.open('stockSplit.html?pktCode='+pktCode+'&count='+count,' ','scrollbars=no,menubar=no,height=600,width=1400,resizable=yes,toolbar=no,location=no,status=no');
    }
  } 
  
  var minReqPrp =new Array("code","cts","rate");
	function validateForm(){
		var pktCnt = 10; 
		var count = 0;
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
		<div align="center" style="font-size: 11px;">
			<a href="javascript:void(0);" onclick="addTab('Add New Parcel','parcel.html');">Add New Mix Group</a>
		</div>
		<form id="parcel" name="parcel" action="addParcel.html" method="post"
			onsubmit="return validateForm();">
			<input type="hidden" value="10" name="count" id="count">
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
					<th>Action</th>
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
				</tr>
				<c:forEach var="l" items="${list}">
					<tr>
						<td>${l.code}</td>
						<td>${l.parcelType}</td>
						<td>${l.shFr}</td>
						<td>${l.shTo}</td>
						<td>${l.puFr}</td>
						<td>${l.puTo}</td>
						<td>${l.colFr}</td>
						<td>${l.colTo}</td>
						<td>${l.cts}</td>
						<td>${l.rate}</td>
						<td>${l.rootPkt}</td>
						<td>${l.baseRate}</td>
						<td>${l.costRate}</td>
						<td><a href="javascript:void(0);" onclick="func('parcel.html?pId=${l.code}')">Edit</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td colspan="14">No parcel created</td>
					</tr>
				</c:if>
			</table>
			<div align="center"></div>
		</form>
	</div>
</body>
</html>








