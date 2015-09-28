<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<style type="text/css">
label {
	
}

label.error {
	float: none;
	color: red;
	padding-left: .5em;
	vertical-align: top;
	display: list-item;
	font-size: 10px;
}

p {
	clear: both;
}

.submit {
	margin-left: 12em;
}

em {
	font-weight: bold;
	padding-right: 1em;
	vertical-align: top;
}

.innerTable th {
	font-weight: bold !important;
	background-color: #F9F9F9 !important;
	background-image: none !important;
	border: none !important;
	color: #000 !important;
}

.infoTable th {
	background-color: #ccc;
}
</style>

<script type="text/javascript">
var invList = new Object();
var invAmt =  new Object();
var count =0;


$(document).ready(function(){
	$('.numbersOnly').keyup(function () { 
	    this.value = this.value.replace(/[^0-9\.]/g,'');
	});
	$( "#paymentDate" ).val('${CURR_DATE}');
	$( "#paymentDate" ).DatePicker({
		format:'d-m-Y',
		calendars: 1,
		date: $('#paymentDate').val(),
		onChange: function(formated, dates){
			$('#paymentDate').val(formated);
			$('#paymentDate').DatePickerHide();
		}
	});
	
	$(".bank").hide();
	$("#mode").change(showBankDet);
	$("#amount").change(chkAmount);
	$("#payOut").hide();
	$("#exRateTD").hide();
	
	//$( "#fromPartyAccId" ).change(loadInvoices);
	$('#vendor').hide();$('#partyName').hide();
	$('#angadiaRow').hide();
	$('select[id*="accountCode_"]').each(function (){
		$(this).val('${accSales}');
	});
	
	$('input[id*="chequeDate_"]').each(function (){
		var curr = $(this);
		$(this).DatePicker({
			format:'d-m-Y',
			calendars: 1,
			date: curr.val(),
			onChange: function(formated, dates){
				curr.val(formated);
				curr.DatePickerHide();
			}
		});
	});

		  
	<c:if test="${param.partyAccId != null}">
		loadInvoices();
	</c:if>
	
	
	$('.cheque').attr('disabled', true);
	
	getBankBal($('#toBankAccId').val(),$('#toBankAccId'));
	getAngadiaBal($('#toAngadiaId').val(),$('#toAngadiaId'));
}); 
	
 
function getBankBal(id, fld){
	$.getJSON('getClosingBalBankAJAX.html?toBankAccId='+id, function(json) {
		var txt = ' - USD';
		if(json!= null && json.error != ''){
			var txtObj = $('#'+$(fld).attr("id")+' :selected').text();
			if(txtObj.indexOf('-')>0){
				txt = txtObj.substring(txtObj.indexOf('-'));
			}
			$('#bankBal').html(json+  txt);
			$('#bankCurr').val(jQuery.trim(txt.substring(txt.indexOf('-')+1)));
			checkCurrParty();
		}else{
			alert('Could not load Bank details please put mannually');
		}
	});
}

function validate(){
	
	if($('#fromPartyAccId').val() == '' && $('#vendorAccId').val() =='' && $('#fromPartyName').val()==''){
		alert('Select from party ');
		return false;
	}
	if($('#toBankAccId').val() == '' && $('#toAngadiaId').val() =='' ){
		alert('Select to Account ');
		return false;
	}
	$('input[id*="amount_"]').each(function() {
		var count = thisId.substring(thisId.indexOf('_')+1);
		if($('#pay_'+count).val() == 1){
			if(this.value == '' || isNaN(this.value) ){
				alert('Amount is invalid or not specified');
				return false;
			}
		}
	});
	return  $('#payForm').validate().form();
}

function payToInv(count){
	var str = $("#fromPartyAccId :selected").text();
	var accType = str.charAt( str.length-1 );
	var text = $('#invPay_'+count+' :selected').text();
	var sAmt =  text.substring(text.indexOf(':')+1);
	if(accType == 'L'){
		sAmt =  text.substring(text.indexOf(':')+1,text.indexOf('-exRate:')+1);
	}
	$('#USD_' + count).val(sAmt);
	if(sAmt != ''){
		var amt = parseFloat(sAmt);
		var exRate =  parseFloat(text.substring(text.indexOf('exRate:')+7));
		$('#exRate_' + count).val(exRate);
		var currency = $('#currency').val();
		if(currency == 'USD') exRate = 1; 
		amt = amt * exRate;
		$('#amount_'+count).val(parseFloat(amt).toFixed(2));
	}else $('#amount_'+count).val('');
}
function validateAmt(id){
	var invId = $('#invPay_'+id).val();
	var text = $('#invPay_'+id+' :selected').text();
	var amt = text.substring(text.indexOf(':')+1);
	var totInvAmt = 0;
	$('select[id*="invPay_"]').each(function (){
		var thisId = $(this).attr("id");
		thisId = thisId.substring(thisId.indexOf('_')+1);
		if(!isNaN($('#amount_'+thisId).val()) && parseInt($(this).val()) == parseInt(invId)){
			totInvAmt += parseFloat($('#amount_'+thisId).val());
		}
	});
	if(totInvAmt > amt){
		var currAmt =$('#amount_'+id).val();
		$('#amount_'+id).val(currAmt -(totInvAmt-amt) );
		alert('Invoice Amount exceeded');
	}
}
function calTotal(){
		var totAmount = 0;
		$('input[id*="amount_"]').each(function() {
			if(!isNaN(this.value))
				totAmount +=this.value;
		});
			$('#amount').val(parseFloat(totAmount));
}
function popInvSelect(el, items){
 	$.each(items, function (key, value) {
        el.options[el.options.length] = new Option(value, key);
    });
}

function showBankDet(){
	if($( "#mode" ).val()=='CHQ')
		$( ".bank" ).show();
	else
		$( ".bank" ).hide();
}
function chkAmount(){
	if($( "#amount" ).val() < 0)
		$( "#payOut" ).show();
	else
		$( "#payOut" ).hide();
}
function bankDtl(id){
	$.getJSON('getPartyBankDetails.html?partyAccId='+id, function(json) {
		if(json!= null && json.error != ''){
			$('input[id*="bank_"]').each(function (){
				 $(this).val(json.bank);
			});
			$('input[id*="bankAccNo_"]').each(function (){
				 $(this).val(json.bankAccNo);
			});
		}else{
			alert('Could not load Bank details please put mannually');
		}
	});
}

function loadInvoices(){
	var str = $("#fromPartyAccId :selected").text();
	var accType = str.charAt( str.length-1 );
	
	$.ajax({type:'POST',
		url:'getPartyInv.html',
		dataType: 'json',
		data: { partyAccId: $( "#fromPartyAccId" ).val(), accType: accType, exRate: $('#exRate').val(), checkBox: $('#currCheckVal').val(), currency: $('#currency').val()},
		success: function(json){
			if(json!=null && json !=""){
				invList = new Object();
				$.each(json, function(i,item){
					invList[item.id] = item.description;
					var txt = item.description;
				});
				$('select[id*="invPay_"]').each(function (){
					$(this).empty();
					this.options[this.options.length] = new Option( "Direct","-1");
					popInvSelect(this, invList );
				});
				$('input[id*="amount_"]').each(function (){
					$(this).val('');
				});
			}else {
				$('select[id*="invPay_"]').each(function (){
					$(this).empty();
				});
			}
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
               alert('You have logged out of system kindly login again .system will take you to login section');
   	   		window.location = 'indexCrm.html';
		}
	});
	bankDtl($("#fromPartyAccId").val());
}
function resetFrm(){
	$('#invoices').html('');
	$('#invPay').children().remove();
	$('#payForm').resetForm();
}
function toggleElem(){
	if($('#fromPartyType').val() == 0){
		$('#vendor').show();partyHide();$('#partyName').hide();
	}else if($('#fromPartyType').val() == 1){
		$('#vendor').hide();partyShow();$('#partyName').hide();
		$('#accountCode').val('${accSales}');
	}else{
		$('#vendor').hide();partyHide();$('#partyName').show();
		$('#accountCode').val('${accSales}');
	}
}
function toggleAccElem(){
	if($('#toAccType_0').attr('checked') == true){
		$('#selfAccRow').show();$('#angadiaRow').hide();$('#mode').val('CASH');
	}else{
		$('#selfAccRow').hide();$('#angadiaRow').show();$('#mode').val('ADJ');
	}
}
function partyHide(){
	$('.invLists').hide();
	$('#party').hide();
}
function partyShow(){
	$('.invLists').show();
	$('#party').show();
}

function addItem(){
	var count = $('#currCount').val();
	$('#rmvBtn').show();
	if(count == 20){
		alert('Maximum 20 payment items can be inserted.');
		return ;
	}
	$('#tr_'+count).show();
	$('#pay_'+count).val(1);
	$('#currCount').val(parseInt(count)+1);
}
 function removeItem(){
	var count =  parseInt($('#currCount').val()) -1;
	 if(count == 1)
		 $('#rmvBtn').hide();
	$('#currCount').val(count);
	$('#tr_'+count).hide();
	$('#pay_'+count).val(0);
} 
 function changeMode(){
	 if($('#mode').val() == 'CHQ'){
		 $('.cheque').attr('disabled', false);
	 }else{
		 $('.cheque').attr('disabled', true);
	 }
 } 
function getOSOnCheckCurrType(){
	var selectVal = $('#fromPartyAccId').val();
	if(selectVal != -1)	{
		getOS(selectVal);
	}
}
 
function loadBankBalance(id, fld){
	getBankBal(id, fld);
	showHideCheckBox();
	getOSOnexRateChange();
}

function showHideCheckBox(){
	if($('#currency').val() == '${USER_SESSION.currency}'){
		$("#currCheck").attr('checked',false);
		$('#exRate').attr('disabled', true);
		$("#currCheck").show();
	}else{
		$("#currCheck").attr('checked',true);
		$('#exRate').attr('disabled', false);
		$("#currCheck").hide();			
	}
}
function changeCurr(){
	loadInvoices();
	showHideCheckBox();
	checkCurrParty(); 
}

function getOSOnPartyChange(id){
	$('#currCheckVal').val(0);
	$.getJSON('getPartyOSAJAX.html?partyAccId='+id+'&currType='+$("#currency").val()+'&exRate='+$("#exRate").val(), function(json) {
		if(json!= null && json.error != ''){
			$('#partyOS').html(json.data);
			$('#exRate').val(parseFloat(json.exRate).toFixed(2));
			var cu = $('#currency').attr('options');
			$('#currency').empty();
			cu[cu.length] = new Option('US Dollar', 'USD');
			if(json.currName != undefined)
			{	if(json.curr != 'USD'){
					cu[cu.length] = new Option(json.currName, json.curr);
					$('#currency').val(json.curr);	
				}
			}
				checkCurrParty();  // 1 if currency is not local not USD
			loadInvoices();
			$("#currCheck").attr('checked',false);
			$('#exRate').attr('disabled', true);
			
			showHideCheckBox();
		}else{
			alert('Sorry, Could not load O/S details.');
		}
	});
}

function getOSOnexRateChange(){
	var partyType = $('#fromPartyType').val();
	if(partyType == 0){//supplier
		id = $('#vendorAccId').val();
	}
	else if(partyType == 1){//Customer
		id = $('#fromPartyAccId').val();
	}
	//TODO for others what will be the partyAccid? ???
	$.getJSON('getPartyOSAJAXWithExrate.html?partyAccId='+id+'&currType='+$("#currency").val()+'&exRate='+$("#exRate").val(), function(json) {
		if(json!= null && json.error != ''){
			$('#partyOS').html(json.data);
			var cu = $('#currency').attr('options');
			$('#currency').empty();
			cu[cu.length] = new Option('US Dollar', 'USD');
			if(json.currName != undefined)
			{	if(json.curr != 'USD'){
					cu[cu.length] = new Option(json.currName, json.curr);
					$('#currency').val(json.curr);	
				}
			}else {
			};
			checkCurrParty();
			loadInvoices();
			if($("#currency").val() != '${USER_SESSION.currency}')showHideCheckBox();
		}else{
			alert('Sorry, Could not load O/S details. ');
		}
	});
}
function changeCheckVal() {
	if ($('#currCheck').is(':checked')) {
	      $('#exRate').removeAttr('disabled');
	      $('#currCheckVal').val(1);
	} else {
		$('#currCheckVal').val(0);
		$('#exRate').attr('disabled', true);	
	}
	loadInvoices();
	getOSOnexRateChange();
}
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="deposit" />
	</jsp:include>
	<div class="container">
		<div align="center" class="content">
			<form method="post" action="depositAction.html" name="payForm"
				id="payForm" onsubmit="return validate();">
				<div id="ajax">
					<img src="images/ajax-loader.gif" alt="Loading..."
						style="display: none;" />
				</div>
				<div id="errorMsg">${msg}</div>
				<div>
					<a href="javascript:void(0);" onclick="addTab('PaymentReport','paymentList.html')">Payment Report</a>
				</div>
				<div align="center">
					<table id="greenTable" width="100%">

						<tr valign="top">
							<td>
								<table class="innerTable">
									<tr>
										<th>Pay Date</th>
										<td><input type="text" name="paymentDate"
											id="paymentDate" class="required" /></td>
									</tr>
								</table></td>
							<td>
								<table class="innerTable">
									<tr>
										<th width="200px">From</th>
										<td><select id="fromPartyType" name="fromPartyType"
											onchange="toggleElem();" style="width: 150px;">
												<option value="0">Supplier</option>
												<option value="1" selected="selected">Customer</option>
												<option value="2">Others</option>
										</select></td>
									</tr>
									<tr id="party">
										<th>From Party Name</th>
										<td><select id="fromPartyAccId" name="fromPartyAccId"
											style="width: 150px;"
											onchange="getOSOnPartyChange(this.value);">
												<option value="-1">Select</option>
												<c:forEach var="p" items='${PARTY_LIST}'>
													<option value="${p.id}"
														<c:if test="${param.partyAccId == p.id}">selected </c:if>>${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr id="vendor">
										<th>From Vendor/Supplier Name</th>
										<td><select id="vendorAccId" name="vendorAccId"
											style="width: 150px;"
											onchange="getOSOnPartyChange(this.value);">
												<option value="-1">Select</option>
												<c:forEach var="v" items='${VENDOR_LIST}'>
													<option value="${v.id}"
														<c:if test="${param.vendorAccId == v.id}">selected </c:if>>${v.description}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr id="partyName">
										<th>From Party</th>
										<td><input type="text" name="fromPartyName"
											id="fromPartyName" size=20 /></td>
									</tr>

								</table> <br />
								<div id="partyOS"></div></td>
							<td>
								<table class="innerTable">
									<tr>
										<th>To Account</th>
										<td><label for="toAccType_1">Bank Account</label> <input
											type="radio" name="toAccType" id="toAccType_0" value="0"
											onclick="toggleAccElem();" checked="checked" /> <label
											for="toAccType_1">Angadia</label> <input type="radio"
											name="toAccType" id="toAccType_1" value="1"
											onclick="toggleAccElem();" /></td>
									</tr>
									<tr id="selfAccRow">
										<th>Company Account</th>
										<td><select id="toBankAccId" name="toBankAccId"
											style="width: 150px;"
											onchange="loadBankBalance(this.value, this);">
												<c:forEach var="b" items='${BANK_ACC_LIST}'>
													<option value="${b.id}"
														<c:if test="${param.frombankAccId == b.id}" >selected </c:if>>
														${b.bankAccountName}
														<c:if test="${b.bankCurrCode != 'USD'}"> - ${b.bankCurrCode}</c:if>
													</option>
												</c:forEach>
										</select><br /> <input type="hidden" id="bankCurr" value="USD">
											<div id="bankBal"></div></td>
									</tr>
									<tr id="angadiaRow">
										<th>Angadia</th>
										<td><select id="toAngadiaId" name="toAngadiaId"
											style="width: 150px;"
											onchange="getAngadiaBal(this.value, this);">
												<c:forEach var="a" items='${ANGADIA_LIST}'>
													<option value="${a.id}"
														<c:if test="${param.angadiaId == a.id}">selected </c:if>>${a.angadiaCoName}</option>
												</c:forEach>
										</select><br /> <input type="hidden" id="angadiaCurr" value="USD">
											<div id="angadiaBal"></div></td>
									</tr>
									<tr>
										<th>Mode of Pay</th>
										<td><select name="mode" id="mode" style="width: 150px;"
											onchange="changeMode();">
												<option value="CASH">Cash</option>
												<option value="CHQ">Cheque</option>
												<!-- 	<option value="ADJ">Adjustment(Angadia)</option> -->
										</select></td>
									</tr>
									<tr id="currencyRow">
										<th>Currency</th>
										<td><select name="currency" id="currency"
											style="width: 145px;" onchange="changeCurr()">
												<c:forEach var="v" items='${currencyList}' varStatus="cnt">
													<option value='${v.currAbrev}'
														<c:if test="${v.currAbrev == 'USD'}">selected</c:if>>${v.currency}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr valign="top">
										<th>Exchange Rate</th>
										<td>
											<div id="toUSDFromLOCAL">
												<span class="from">USD 1 = </span> <input type="text"
													name="exRate" id="exRate" class="numeric numbersOnly"
													value="1" size=10 disabled="disabled"
													onchange="getOSOnexRateChange()" /> <input type="checkbox"
													id="currCheck" onClick="changeCheckVal()" name="currCheck" />
												<input type="hidden" id="currCheckVal" name="currCheckVal" />
												<span class="to"></span>
											</div>
											<div id="fromUSDtoLOCAL">
												<span class="from">USD 1 = </span> <input type="text"
													name="exRate2" id="exRate2" size=10
													class="numeric numbersOnly" value="1" />
												<!--<input type="checkbox" id="currCheck" onClick="toggleStatus()" name="currCheck" value="0"/>-->
												<span class="to"></span>
											</div></td>
									</tr>
								</table></td>
						</tr>
					</table>
					<h3>Payment Items</h3>
					<table id="greenTable" width="100%">
						<tr>
							<th>GL Account</th>
							<th>Bank</th>
							<th>Bank Acc.</th>
							<th>Cheque Date</th>
							<th>Cheque No</th>
							<th>Invoice</th>
							<th id="table_amount">Amount</th>
							<th>Description</th>
							<th>Cleared</th>
						</tr>

						<c:forEach begin="0" end="10" varStatus="count">
							<tr id="tr_${count.index}"
								<c:if test="${count.index != 0}">style="display:none;"</c:if>>
								<td><select id="accountCode_${count.index}"
									name="accountCode_${count.index}" style="width: 125px;">
										<option value="-1">Select</option>
										<c:forEach var="gl" items='${GL_ACC}'>
											<optgroup label=""></optgroup>
											<option value="${gl.code}"
												<c:if test="${param.accountCode == v.id}">selected </c:if>>${gl.code}
												- ${gl.name}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="bank_${count.index}"
									id="bank_${count.index}" size=10 class="cheque" /></td>
								<td><input type="text" name="bankAccNo_${count.index}"
									id="bankAccNo_${count.index}" size=10 class="cheque" /></td>
								<td><input type="text" name="chequeDate_${count.index}"
									id="chequeDate_${count.index}" size=10 class="cheque" /></td>
								<td><input type="text" name="chequeNo_${count.index}"
									id="chequeNo_${count.index}" size=10 class="cheque" />
								<td>
									<div>
										<select id="invPay_${count.index}"
											name="invPay_${count.index}"
											onchange="payToInv('${count.index}');" style="width: 125px;">
											<option value="-1">Direct</option>
										</select>
									</div></td>
								<td><input type="text" name="amount_${count.index}"
									id="amount_${count.index}" size=10 class="numeric numbersOnly"
									onchange="validateAmt('${count.index}');" /> <input
									type="hidden" name="USD_${count.index}" id="USD_${count.index}" />
									<input type="hidden" name="exRate_${count.index}"
									id="exRate_${count.index}" /></td>

								<td><textarea rows="2" cols="15" id="dsc_${count.index}"
										name="dsc_${count.index}"></textarea></td>
								<td><c:if test="${count.index == 0}">
										<input type="hidden" name="pay_${count.index}"
											id="pay_${count.index}" value="1" />
									</c:if> <c:if test="${count.index != 0}">
										<input type="hidden" name="pay_${count.index}"
											id="pay_${count.index}" value="0" />
									</c:if> <input type="checkbox" name="clear_${count.index}"
									id="clear_${count.index}" value="1" /></td>

							</tr>
						</c:forEach>
						<tr align="left">
							<td colspan="8"><input type="hidden" name="currCount"
								id="currCount" value="1" /> <input type="button"
								value="Add Item" onclick="addItem();" /> <input type="button"
								value="Remove Last" onclick="removeItem();" id="rmvBtn"
								style="display: none;" /> <!-- Total : <span id="total"></span> -->

							</td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" value="Save" id="saveMaster" /> <input
							type="button" value="Reset" onclick="resetFrm();" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>