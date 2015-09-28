<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>

<script type="text/javascript">
var invList = new Object();
var invAmt =  new Object();
var count =0;


$(document).ready(function(){
	
	var partyData = [
	<c:forEach items="${PARTY_LIST}" var="p" varStatus="count">
		{ id: "${p.id}", description: "${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}", accType:"${p.accType}" }
		<c:if test="${!count.last}">,</c:if>
	</c:forEach>
	];
	
	$("#buyerName").autocomplete(partyData,
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
	$("#buyerName").result(function(event, data, formatted) {
		$("#partyAccId").val(!data ? "-1" : data.id);
		$("input[name=paymentTerm]").val(data.accType);
		loadInvoices();
	});

	$( "#paymentDate" ).datepicker({
		changeMonth: true,
		changeYear: true,
		currentText: 'Now',
		dateFormat: 'dd-mm-yy' 
	});
	
	$( "#paymentDate" ).val('${CURR_DATE}');
	
	$(".bank").hide();
	$("#mode").change(showBankDet);
	$("#amount").change(chkAmount);
	$("#payOut").hide();
	$("#exRateTD").hide();
	
	//$( "#buyerName" ).change(loadInvoices);
	//$("#payInv").change(payToInv);
	//$("#invPay").select(payToInv);
	
	$('#saveMaster').click(function(){
			$('#ajax').show();
		    var formOptions2 = {
		    		dataType: 'json',
		            beforeSubmit: function() {
		            	return  $('#payForm').validate().form();
		            },
		            success:  function(json) {
		            	$('#errorMsg').html(json[1]);
		            	resetFrm();
		            },
		            error: function(xmlHttpRequest, textStatus, errorThrown) {
		    			if('${USER_SESSION.userId}' == null || '${USER_SESSION.userId}' == '' || '${USER_SESSION.userId}'  < 0){
		    	            alert('You have logged out of system kindly login again .system will take you to login section');
		    		   		window.location = 'indexCrm.html';
		    			}else{
		    				alert('Some error occured in system kindly contact system admin');
		    			}
		    		},
		            target: '#response'
		        };
			$('#payForm').ajaxForm(formOptions2);
			$('#ajax').hide();
		  });
});
	

function payToInv(){
	var html = '';
	var selCount = 0;
	$('#invPay :selected').each(function(i, sel){ 
		selCount++
		 html += '<tr><td>'+(sel.text).substring(0, sel.text.indexOf('-Amt:'))+' </td>'+
			'<td><input type="text" id="amount_'+sel.value+'" name="amount_'+sel.value+'" value="'+(sel.text).substring(sel.text.indexOf(':')+1)+'" onchange="validateAmt(this);"></td></tr>'	;
	});
	if(selCount > 0)
		$('#amount').attr("readonly",true);
	else
		$('#amount').attr("readonly",false);
	$('#invoices').html(html);
	calTotal();
}
function validateAmt(fld){
	var invId = (fld.id).substring(fld.id.indexOf('_')+1);
	if(fld.value == '' || fld.value <= 0 ){
		alert('Enter valid Amount for invoice id '+invId);
		return;
	}else if(parseFloat(fld.value)> parseFloat(invAmt[invId])){
		fld.value = parseFloat(invAmt[invId]);
		alert('Amount cannot be greater then invoice Amount for invoice '+invId);
		return ;
	}else{
		calTotal();
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
/**function payToInv(){
	var id = 'invPay'+count; 
	var html = '<tr><td>Pay	<select id="invPay'+count+'" name="invPay'+count+'" multiselect></select></td>'+
	'<td><input type="text" id="amount'+count+'" name="amount'+count+'"></td></tr>'
	if($( "#payInv" ).checked && invList.length > 0){
		html+=$( "#invoices" ).html();
		$( "#invoices" ).html(html);
		popInvSelect($( '#invPay'+count), invList );
	}else{
		count = 0;
		$( "#invoices" ).html('');
	}
}*/
function popInvSelect(el, items){
		
	 	$.each(items, function (key, value) {
	        el.options[el.options.length] = new Option( value, key);
	    });

}
function showEx(){
	if($( "#paymentTerm_L" ).checked)
		$( "#exRateTD" ).show();
	else
		$( "#exRateTD" ).hide();
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

function loadInvoices(){
	$.ajax({type:'POST',
			url:'getPartyInv.html',
			dataType: 'json',
			data: { partyAccId: $( "#partyAccId" ).val()},
			success: function(json){
				if(json!=null && json !=""){
					$.each(json, function(i,item){
						invList= new Object;
						invList[item.id] = item.description;
						invAmt[item.id] = (item.description).substring(item.description.indexOf(':')+1);
					});
					popInvSelect(document.getElementById('invPay'), invList );
				}else {
					alert(json);
				}
			},
			error: function(xmlHttpRequest, textStatus, errorThrown) {
                alert('You have logged out of system kindly login again .system will take you to login section');
    	   		window.location = 'indexCrm.html';
			}
	});
			

}
function resetFrm(){
	$('#invoices').html('');
	$('#invPay').children().remove();
	$('#payForm').resetForm();
}
</script>

</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td>
		</tr>
	</table>

	<form:form method="post" cssClass="cmxform"
		action="paymentController.html" commandName="payment" name="payForm"
		id="payForm">
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..."
				style="display: none;" />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		<div id="rightPanel">
			<table>
				<tr>
					<td>Party Name</td>
					<td><input type="text" name="buyerName" id="buyerName" size=30 />
						<form:hidden path="partyAccId" id="partyAccId" />
					</td>
				</tr>
				<tr>
					<td>Pay Date</td>
					<td><form:input path="paymentDate" id="paymentDate"
							cssClass="required" /></td>
				</tr>
				<tr>
					<td>Mode of Pay</td>
					<td><form:select path="mode" id="mode">
							<form:option value="CASH">Cash</form:option>
							<form:option value="CHQ">Cheque</form:option>
							<form:option value="ADJ">Adjustment(Angadia)</form:option>

						</form:select></td>
				</tr>
				<tr id="bankN" class="bank">
					<td>Bank</td>
					<td><form:input path="bank" id="bank" />
				</tr>
				<tr id="bankAcc" class="bank">
					<td>Bank Acc.</td>
					<td><form:input path="bankAccNo" id="bankAccNo" /></td>
				</tr>
				<tr style="display: none;">
					<td>Payment Terms</td>
					<td><label for="paymentTerm_L">Local : </label><input
						type="radio" id="paymentTerm_L" name="paymentTerm" /><label
						for="paymentTerm_E">Export : </label><input type="radio"
						id="paymentTerm_E" name="paymentTerm" checked="checked" /></td>

				</tr>
				<tr id="exRateTD" style="display: none;">
					<td>Exchange Rate</td>
					<td><form:input path="exRate" id="exRate" /></td>

				</tr>
				<tr>
					<td>Pay Invoices</td>
					<td><select id="invPay" name="invPay" multiple="multiple"
						onchange="payToInv();" style="width: 150px;">
					</select></td>
				<tr>
					<td colspan=2>
						<table width="100%">
							<tr bgcolor="#cccccc">
								<th width="50%">Inv Id</th>
								<th>Amount</th>
							</tr>
							<tbody id="invoices"></tbody>
						</table></td>
				</tr>
				<tr>
					<td>Amount</td>
					<td><form:input path="amount" id="amount" /></td>
				</tr>
				<tr id="payOut">
					<td>Pay Out party</td>
					<td><form:select id="payFromPartyId" path="payFromPartyId">
							<form:option value="">Select</form:option>
							<c:forEach items="${SELF_PARTY_LIST}" var="s" varStatus="cnt">
								<form:option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</form:option>
							</c:forEach>
						</form:select></td>
				</tr>

			</table>
			<div align="center">

				<input type="submit" value="Save" id="saveMaster" /> <input
					type="button" value="Reset" onclick="resetFrm();" />
			</div>
		</div>
	</form:form>
</body>
</html>