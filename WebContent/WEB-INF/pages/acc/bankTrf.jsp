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
</style>

<script type="text/javascript">
	var invList = new Object();
	var invAmt = new Object();
	var count = 0;

	$(document).ready(function() {
		$("#paymentDate").DatePicker({
			format : 'd-m-Y',
			current : '${CURR_DATE}',
			calendars : 1,
			date : '${CURR_DATE}',
			onChange : function(formated, dates) {
				$('#paymentDate').val(formated);
				$('#paymentDate').DatePickerHide();
			}
		});
		$('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});
		$("#paymentDate").val('${CURR_DATE}');
		getBalance();

	});
	function getBalance(fld1, fld2, fld3) {
		var text = $('#' + fld1).val();
		if (text == undefined || text == -1)
			return;
		var bankAccId = text.substr(0, text.indexOf('|'));
		var curr = text.substr(text.indexOf('|') + 1);
		$.ajax({
			type : 'POST',
			url : 'getBalanceJson.html',
			dataType : 'json',
			data : {
				fromBankAccId : bankAccId
			},
			success : function(json) {
				$('#' + fld3).html('');
				var newRow = $('<tr id="' + fld3 +'"><td>Balance</td><td>('
						+ json + ' ' + curr + ')</td></tr>');
				newRow.insertAfter('#' + fld2);
			}
		});
	}

	function submitForm() {
		if ($('#fromBankAccId').val() == -1 || $('#toBankAccId').val() == -1) {
			if ($('#fromBankAccId').val() == $('#toBankAccId').val())
				alert('To and From Bank account cannot be same');
			return false;
		}

		if ($('#amount').val() <= 0) {
			alert('Amount cannot be zero or negative');
			return false;
		}
		if (parseFloat($('#balance').html()) < $('#amount').val()) {
			alert('Not enough funds in From Account');
			return false;
		}
		return true;
	}

	function getDet(fld1, fld2, fld3) {
		setCurr(fld1);
		getBalance(fld1, fld2, fld3);
	}
	function setCurr(fld) {
		var text = $('#' + fld).val();
		//$('#exRateRow').remove();
		if (text != undefined && text != -1) {
			var curr = text.substr(text.indexOf('|') + 1);
			if (fld == 'fromBankAccId') {
				$('#fromBankCurr').val(curr);
				$('.fromCurr').html(curr);
				//$('#bankCharge').append(curr);
			} else
				$('#toBankCurr').val(curr);
			checkCurrency($('#fromBankCurr').val(), $('#toBankCurr').val());
		} else {
			if (fld == 'fromBankAccId')
				$('#fromBankCurr').val('');
			else
				$('#toBankCurr').val('');
		}

	}

	function checkCurrency(curr1, curr2) {
		if (curr1 == '' | curr2 == '') {
			//getExRate(fld1, fld2, '#exRate');
			return;
		}
		var exrate1 = 0.0;
		var exrate2 = 0.0;
		if (curr1 != 'USD') {
			getExrate('USD', curr1, '#exRate1');
		} else
			exrate1 = 1.0;
		if (curr2 != 'USD') {
			getExrate('USD', curr2, '#exRate2');
		} else
			exrate2 = 1.0;
		showExrate(exrate1, exrate2, curr1, curr2);

	}

	function getExrate(from, to, fld) {
		$.getJSON('getExRate.html?from=' + from + '&to=' + to, function(json) {
			if (json != null && json.error != '') {
				$(fld).val(parseFloat(json).toFixed(2));
			} else {
				alert('Could not load Ex rate please put manually');
			}
		});
	}
	function showExrate(exrate1, exrate2, curr1, curr2) {
		var newrow = '';
		$('.exrateRow').remove();
		if (curr1 == curr2) {
			var newrow = $('<tr class="exrateRow"><td>Both banks have </br>the same Currency('
					+ curr1 + ')</td></tr>');
			newrow.insertAfter('#payDate');
			return;
		}
		if (exrate1 != undefined && exrate1 != 1.0) {
			newrow = $('<tr class="exrateRow"><td>Exchange Rate</td><td> 1 USD = <input type="text" name="exRate1" id="exRate1" class="required" width="10"/> '
					+ curr1 + '</td></tr>');
			newrow.insertAfter('#payDate');
		}
		if (exrate2 != undefined && exrate2 != 1.0) {
			newrow = $('<tr class="exrateRow"><td>Exchange Rate</td><td> 1 USD = <input type="text" name="exRate2" id="exRate2" class="required" width="10"/> '
					+ curr2 + '</td></tr>');
			newrow.insertAfter('#payDate');
		}
	}
// 	function onSuccess(){
// 		alert("Successfully uploaded details.");
// 	}
// 	function onError(){
// 		alert("error");
// 	}
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="bankTrf" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<form method="post" action="bankTrfAction.html" name="payForm"
				id="payForm" onsubmit="return submitForm();">
				<div id="ajax">
					<img src="images/ajax-loader.gif" alt="Loading..."
						style="display: none;" />
				</div>
				<div id="errorMsg">${mailMsg}</div>
				<div align="center">
					<table id="greenTable">
						<tr id="fromBankRow">
							<td>From Bank Account</td>
							<td><select id="fromBankAccId" name="fromBankAccId"
								onchange="getDet('fromBankAccId', 'fromBankRow','fromBankBalance');">
									<option value="-1" selected="selected">select</option>
									<c:forEach var="b" items='${bankAcc}'>
										<option value="${b.id}|${b.bankCurrCode}">${b.bankAccountName}</option>
									</c:forEach>
							</select> <input type="hidden" id="fromBankCurr" name="fromBankCurr" /></td>
						</tr>
						<tr id="toBankRow">
							<td>To Bank Account</td>
							<td><select id="toBankAccId" name="toBankAccId"
								onchange="getDet('toBankAccId','toBankRow','toBankBalance')">
									<option value="-1" selected="selected">select</option>
									<c:forEach var="b" items='${bankAcc}'>
										<option value="${b.id}|${b.bankCurrCode}">${b.bankAccountName}</option>
									</c:forEach>
							</select> <input type="hidden" id="toBankCurr" name="toBankCurr" /></td>
						</tr>

						<tr id="payDate">
							<td>Pay Date</td>
							<td><input type="text" name="paymentDate" id="paymentDate"
								class="required" /></td>
						</tr>
						<tr>
							<td>Amount</td>
							<td><input type="text" name="amount" id="amount"
								class="numeric required numbersOnly" /><span class="fromCurr"></span>
							</td>
						</tr>

						<tr>
							<td>Bank Charge</td>
							<td><input type="text" name="charges" id="charges"
								class="numeric numbersOnly" /><span class="fromCurr"></span></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><textarea rows="2" cols="17" id="dsc" name="dsc"></textarea>
							</td>
						</tr>
						<tr>
							<td>ref</td>
							<td><input type="text" name="ref" id="ref" /></td>
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