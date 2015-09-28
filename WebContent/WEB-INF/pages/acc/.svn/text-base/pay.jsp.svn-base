<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
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
	var invAmt = new Object();
	var count = 0;

	$(document).ready(function() {
		$("#paymentDate").val('${CURR_DATE}');
		$("#paymentDate").DatePicker({
			format : 'd-m-Y',
			calendars : 1,
			date : $('#paymentDate').val(),
			onChange : function(formated, dates) {
				$('#paymentDate').val(formated);
				$('#paymentDate').DatePickerHide();
			}
		});
		$("#chequeDate").val('${CURR_DATE}');
		$("#chequeDate").DatePicker({
			format : 'd-m-Y',
			calendars : 1,
			date : $('#chequeDate').val(),
			onChange : function(formated, dates) {
				$('#chequeDate').val(formated);
				$('#chequeDate').DatePickerHide();
			}
		});

		//$("#exRateTD").hide();

		$('#party').hide();
		$('#partyName').hide();
		$('#angadiaRow').hide();
		$('#accountCode').val('${accCodeVendor}');
		getBankDet('#fromBankAccId');
		//getBankBal($('#fromBankAccId').val(),$('#fromBankAccId'));
		getAngadiaBal($('#fromAngadiaId').val(), $('#fromAngadiaId'));
		$('#exrateDiv').hide();
		$('#payForm').validate();
		$('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});
		$('.cheque').hide();
	});

	/*function showEx(){
	 if($( "#paymentTerm_L" ).checked)
	 $( "#exRateTD" ).show();
	 else
	 $( "#exRateTD" ).hide();
	 }*/
	function resetFrm() {
		$('#invoices').html('');
		$('#invPay').children().remove();
		$('#payForm').resetForm();
	}
	function validate() {
		if ($('#invPay').val() == -1) {
			var description = $('#dsc').val();
			if (description == '') {
				alert('For direct payments description is compulsory!');
				return false;
			}
			var vendorId = $('#vendorAccId').val();
			if (vendorId == -1) {
				alert('please select vendor');
				return false;
			}
		}
		return $('#payForm').validate().form();

	}
	function toggleElem() {
		if ($('#toPartyType').val() == 0) {
			$('#vendor').show();
			$('#party').hide();
			$('#partyName').hide();
			$('#accountCode').val('${accCodeVendor}');
		} else if ($('#toPartyType').val() == 1) {
			$('#vendor').hide();
			$('#party').show();
			$('#partyName').hide();
			$('#accountCode').val('${accCodeParty}');
		} else {
			$('#vendor').hide();
			$('#party').hide();
			$('#partyName').show();
			$('#accountCode').val('${accCodeDef}');
		}
	}
	function toggleAccElem() {
		if ($('#fromAccType_0').attr('checked') == true) {
			$('#selfAccRow').show();
			$('#angadiaRow').hide();
			$('#mode').val('CASH');
		} else {
			$('#selfAccRow').hide();
			$('#angadiaRow').show();
			$('#mode').val('ADJ');
		}
	}

	function getBankDet(fld) {
		var text = $(fld).val();
		var bankAccId = text.substr(0, text.indexOf('|'));
		var curr = text.substr(text.indexOf('|') + 1);
		getBankBal(bankAccId, $(fld));
		$('.currency').html(curr);
		$('#bankCurr').val(curr);
		payToInv($('#invPay').val());
	}

	function getVendorInv(val) {
		if (val != -1)
			getVendorInvList(val);
	}

	function getVendorInvList(val) {
		$.getJSON('getVendorInvList.html?vendorId=' + val, function(json) {
			if (json != null && json.error != '') {
				invList = new Object();
				$.each(json, function(i, item) {
					invList[item.id] = item.description;
				});
				$('select[id*="invPay"]').each(
						function() {
							$(this).empty();
							this.options[this.options.length] = new Option(
									"Direct", "-1");
							popInvSelect(this, invList);
						});
			} else {
				//alert('Could not load Invoices');
			}
		});
	}
	function popInvSelect(el, items) {
		$.each(items, function(key, value) {
			var arr = new Array();
			arr = value.split('|');
			var balance = arr[0];
			var exrate = arr[1];
			var curr = arr[2];
			if (curr == 'USD')
				value = balance;
			el.options[el.options.length] = new Option(value, key);
		});
	}
	function payToInv(fld) {
		var arr = new Array();
		var val = $("option:selected", fld).text();
		arr = val.split('|');
		var balance = arr[0];
		var exrate = arr[1];
		var curr = arr[2];
		if ($('#invPay').val() != -1)
			$('#amount').val(balance);
		else
			$('#amount').val('');
		$('#actualAmount').val(balance);
		if (curr == undefined || curr == '')
			curr = 'USD';
		var bankCurr = $('#bankCurr').val();
		$('#exrateDiv').hide();
		if (curr != bankCurr) {
			$('#exrateDiv').show();
			$('#exrateDiv').empty();
			if (exrate == undefined || exrate == '')
				getExRate(curr, bankCurr, '#exRate');
			$('#exrateDiv')
					.html(
							'<td>Exrate</td><td>1 '
									+ curr
									+ ' = <input type="text" id="exRate" class="required numbersOnly"/>'
									+ bankCurr + '</td>');
			$('#exRate').val(exrate);
		}
	}
	function getExRate(from, to, fld) {
		$.getJSON('getExRate.html?from=' + from + '&to=' + to, function(json) {
			if (json != null && json.error != '') {
				$(fld).val(parseFloat(json).toFixed(2));
			} else {
				alert('Could not load Ex rate please put mannually');
			}
		});
	}
	function getBankBal(id, fld) {
		$
				.getJSON(
						'getClosingBalBankAJAX.html?toBankAccId=' + id,
						function(json) {
							var txt = ' - USD';
							if (json != null && json.error != '') {
								var txtObj = $(
										'#' + $(fld).attr("id") + ' :selected')
										.text();
								if (txtObj.indexOf('-') > 0) {
									txt = txtObj.substring(txtObj.indexOf('-'));
								}
								$('#bankBal').html(json + txt);
								$('#bankCurr').val(
										jQuery.trim(txt.substring(txt
												.indexOf('-') + 1)));
							} else {
								alert('Could not load Bank details please put mannually');
							}
						});
	}
	function getAngadiaBal(id, fld) {
		$.getJSON('getClosingBalAngadiaAJAX.html?angadiaId=' + id, function(
				json) {
			if (json != null && json.error != '') {
				var txtObj = $('#' + $(fld).attr("id") + ' :selected').text();
				if (txtObj.indexOf('-') > 0) {
					txt = txtObj.substring(txtObj.indexOf('-'));
				}
				$('#angadiaBal').html(json);
				$('#angadiaCurr').val('INR');
			} else {
				alert('Could not load Angadia Balance please put mannually');
			}
		});
	}
	function checkValue() {
		var actualAmt = parseFloat($('#actualAmount').val());
		var enteredAmt = parseFloat($('#amount').val());
		if (enteredAmt > actualAmt && $('#invPay').val() != -1) {
			alert('Entered amount cannot exceed ' + actualAmt);
			$('#amount').val(actualAmt);
		}
	}

	function paymentMode(fld) {
		var val = $(fld).val();
		if (val == 'CHQ')
			$('.cheque').show();
		else
			$('.cheque').hide();
	}
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="pay" />
	</jsp:include>
	<div class="container">
		<div class="content">
			<form method="post" action="paymentAction.html" name="payForm"
				id="payForm" onsubmit="return validate();">
				<div id="ajax">
					<img src="images/ajax-loader.gif" alt="Loading..."
						style="display: none;" />
				</div>
				<div id="errorMsg">${mailMsg}</div>
				<div align="center">
					<table id="greenTable">
						<tr>
							<td width="200px">From Account</td>
							<td><label for="fromAccType_0">Bank Acc.</label> <input
								type="radio" name="fromAccType" id="fromAccType_0" value="0"
								onclick="toggleAccElem();" checked="checked" /> <input
								type="hidden" id="bankCurr" name="bankCurr" /> <label
								for="fromAccType_1">Angadia</label> <input type="radio"
								name="fromAccType" id="fromAccType_1" value="1"
								onclick="toggleAccElem();" /></td>
						</tr>
						<tr id="selfAccRow">
							<td>Company Account</td>
							<td><select id="fromBankAccId" name="fromBankAccId"
								style="width: 150px;" onchange="getBankDet('#fromBankAccId')">
									<c:forEach var="b" items='${BANK_ACC_LIST}'>
										<option value="${b.id}|${b.bankCurrCode}"
											<c:if test="${param.frombankAccId == b.id}">selected </c:if>>
											${b.bankAccountName}
											<c:if test="${b.bankCurrCode != 'USD'}"> - ${b.bankCurrCode}</c:if>
										</option>
									</c:forEach>
							</select>
								<div id="bankBal"></div></td>
						</tr>
						<tr id="angadiaRow">
							<td>Angadia</td>
							<td><select id="fromAngadiaId" name="fromAngadiaId"
								style="width: 150px;"
								onchange="getAngadiaBal(this.value, this);">
									<c:forEach var="a" items='${ANGADIA_LIST}'>
										<option value="${a.id}"
											<c:if test="${param.angadiaId == a.id}">selected </c:if>>${a.angadiaCoName}</option>
									</c:forEach>
							</select>
								<div id="angadiaBal"></div></td>
						</tr>
						<tr>
							<td>To Party Type</td>
							<td><select id="toPartyType" name="toPartyType"
								style="width: 150px;" onchange="toggleElem();">
									<option value="0">Supplier</option>
									<option value="1">Customer</option>
									<option value="2">Others</option>
							</select></td>
						</tr>
						<tr id="party">
							<td>Party Name</td>
							<td><select id="toPartyAccId" name="toPartyAccId"
								style="width: 150px;">
									<c:forEach var="p" items='${PARTY_LIST}'>
										<option value="${p.id}"
											<c:if test="${param.partyAccId == p.id}">selected </c:if>>${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr id="vendor">
							<td>Vendor/Supplier Name</td>
							<td><select id="vendorAccId" name="vendorAccId"
								style="width: 150px;" onchange="getVendorInv(this.value);">
									<option value="-1">select</option>
									<c:forEach var="v" items='${VENDOR_LIST}'>
										<option value="${v.id}"
											<c:if test="${param.vendorAccId == v.id}">selected </c:if>>${v.description}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr id="partyName">
							<td>Pay to</td>
							<td><input name="toPartyName" id="toPartyName" size=20 /></td>
						</tr>
						<tr>
							<td>Account</td>
							<td><select id="accountCode" name="accountCode"
								style="width: 150px;">
									<c:forEach var="gl" items='${GL_ACC}'>
										<optgroup label=""></optgroup>
										<option value="${gl.code}"
											<c:if test="${param.accountCode == v.id}">selected </c:if>>${gl.code}
											- ${gl.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Pay Date</td>
							<td><input type="text" name="paymentDate" id="paymentDate"
								class="required" size=20 /></td>
						</tr>
						<tr>
							<td>Mode of Pay</td>
							<td><select name="mode" id="mode" style="width: 150px;"
								onchange="paymentMode(this)">
									<option value="CASH">Cash</option>
									<option value="CHQ">Cheque</option>
							</select></td>
						</tr>
						<tr>
							<td>Select Invoice</td>
							<td>
								<div>
									<select id="invPay" name="invPay" onchange="payToInv(this);"
										style="width: 125px;">
										<option value="-1">Direct</option>
									</select>
								</div></td>
						</tr>
						<!--cheque payments -->
						<tr class="cheque">
							<td>Bank</td>
							<td><input type="text" name="bank" id="bank" size=10 /></td>
						</tr>
						<tr class="cheque">
							<td>Bank Acc.</td>
							<td><input type="text" name="bankAccNo" id="bankAccNo"
								size=10 /></td>
						</tr>
						<tr class="cheque">
							<td>Cheque Date</td>
							<td><input type="text" name="chequeDate" id="chequeDate"
								size=10 /></td>
						</tr>
						<tr class="cheque">
							<td>Cheque No</td>
							<td><input type="text" name="chequeNo" id="chequeNo" size=10 />
							</td>
						</tr>
						<tr id="exrateDiv"></tr>

						<tr>
							<td>Amount</td>
							<td><input type="text" name="amount" id="amount" size=20
								class="numeric required numbersOnly" onchange="checkValue()" /><span
								class="currency"></span> <input type="hidden"
								name="actualAmount" id="actualAmount" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><textarea rows="2" cols="17" id="dsc" name="dsc"></textarea>
							</td>
						</tr>
					</table>

					<input type="submit" value="Save" id="saveMaster" /> <input
						type="button" value="Reset" onclick="resetFrm();" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>