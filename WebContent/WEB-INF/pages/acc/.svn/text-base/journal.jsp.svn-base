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
	var count = 0;
	var totalDb = 0;
	var totalCr = 0;

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

		$('#accountCode').val('${accCodeVendor}');
		$('#count').val(count);
		$('#accDesc').val($("#accountCode option:selected").text());

		$('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});

	});

	function removeItem(fld) {
		//$('#remove').val(removeId - 1);
		var removeId = $(fld).closest("tr").prevAll("tr").length + 1;
		var trid = $(fld).closest('tr').attr('id');
		$('#remove').val(removeId - 1);
		alert(removeId - 1);
		$
				.ajax({
					type : 'POST',
					url : 'journalAJAX.html',
					dataType : 'html',
					data : $('#payForm').serialize(),
					success : function(text) {
						$('#' + removeId).remove();
						count--;
					},
					error : function(xmlHttpRequest, textStatus, errorThrown) {
						alert('You have logged out of system kindly login again .system will take you to login section');
						window.location = 'indexCrm.html';
					}
				});
	}
	function submitEntry() {
		dbVal = $('#debitAmt').val();
		crVal = $('#creditAmt').val();
		if ((dbVal == '' || dbVal == 0) && (crVal == '' || crVal == 0)) {
			alert('Either of Amount should be filled');
			return false;
		}
		if (dbVal != '' && crVal != '') {
			alert('Only Either of Amount should be filled');
			return false;
		}
		$
				.ajax({
					type : 'POST',
					url : 'journalAJAX.html',
					dataType : 'text',
					data : $('#payForm').serialize(),
					success : function(text) {
						$('#row').append(text);
						count++;
						$('#debitAmt').val('');
						$('#creditAmt').val('');
						$('#count').val(count);
					},
					error : function(xmlHttpRequest, textStatus, errorThrown) {
						alert('You have logged out of system kindly login again .system will take you to login section');
						window.location = 'indexCrm.html';
					}
				});
	}

	function resetFrm() {
		$('#payForm').resetForm();
	}
	function validate() {

		if (count <= 0) {
			alert('You havent added any journal entry to add list.');
			return false;
		}
		var debitAm = 0;
		var creditAm = 0;
		var status = $('#payForm').validate().form();
		$('td[id^="db_"]').each(function() {
			if ($(this).html() != 0)
				debitAm += parseFloat($(this).html());
		});
		$('td[id^="cr_"]').each(function() {
			if ($(this).html() != 0)
				creditAm += parseFloat($(this).html());
		});

		if (creditAm != debitAm) {
			alert('Credit and Debit amount should be same. You must Add Item before you save.');
			status = false;
		}
		return status;
	}
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="journal" />
	</jsp:include>
	<!-- Main Content here -->
		<div align="center" class="content">
			<form method="post" action="journalEntry.html" name="payForm"
				id="payForm" onsubmit="return validate();">
				<div id="ajax">
					<img src="images/ajax-loader.gif" alt="Loading..."
						style="display: none;" />
				</div>
				<div id="errorMsg">${mailMsg}</div>
				<div id="rightPanel">
					<table id="greenTable">
						<tr>
							<td>Date : <input type="text" name="paymentDate"
								id="paymentDate" class="required" size=20 /></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<br />
					<table id="greenTable" class="maintable">
						<tr>
							<th>Account</th>
							<th>Debit(${USER_SESSION.currency})</th>
							<th>Credit(${USER_SESSION.currency})</th>
							<th>Description</th>
							<th></th>
						</tr>
						<tbody id="row">
						</tbody>
						<tfoot>
							<tr>
								<td><input type="hidden" name="remove" id="remove"
									value="0" /> <input type="hidden" name="count" id="count"
									value="0" /> <input type="hidden" name="accDesc" id="accDesc"
									value="" /> <select id="accountCode" name="accountCode"
									style="width: 150px;"
									onchange="$('#accDesc').val(this.options[this.selectedIndex].text);">
										<c:forEach var="gl" items='${GL_ACC}'>
											<optgroup label=""></optgroup>
											<option value="${gl.code}"
												<c:if test="${param.accountCode == v.id}">selected </c:if>>${gl.code}
												- ${gl.name}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="debitAmt" id="debitAmt"
									size=20 dec="2" class="amount numbersOnly" />
								</td>
								<td><input type="text" name="creditAmt" id="creditAmt"
									size=20 dec="2" class="amount numbersOnly" />
								</td>
								<td><input type="text" name="dsc" id="dsc" size=30 />
								</td>
								<td><input type="button" value="Add item"
									onclick="submitEntry();" />
								</td>
							</tr>

							<tr>
								<td>Description</td>
								<td><textarea rows="2" cols="17" id="memo" name="memo"></textarea>
								</td>
							</tr>
						</tfoot>
					</table>
					<div align="center">

						<input type="submit" value="Save" id="saveMaster" /> <input
							type="button" value="Reset" onclick="resetFrm();" />
					</div>
				</div>
			</form>
		</div>
</body>
</html>