<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var colList = new Array();
	var html = '';
	var rapFormat = 0;

	if ('${rapFormat}' == 100) {
		rapFormat = 100;
	}

	//var minReqPrp =new Array("pktCode","SH","CTS","C","PU","MD-XD-D","rap","CT","PO","SY","FL");
	var minReqPrp = new Array("pktCode", "SH", "CTS", "PU");

	function validateForm() {
		var pktCnt = $('#pktCount').val();
		var count = 0;
		for ( var a = 0; a < pktCnt; a++) {
			if (!$('#pktCode_' + a) || $('#pktCode_' + a).val() == '') {
				continue;
			} else {
				count++;
				var pktCode = $('#pktCode_' + a).val();
				for ( var b = 1; b < minReqPrp.length; b++) {
					if (minReqPrp[b] == 'MD-XD-D') {
						var msmt = $('#MD-XD-D_' + a).val();
						msmt = msmt.replace(/[^a-zA-Z 0-9 .]+/g, '-');
						var tempArray = msmt.split("-");
						if (tempArray.length != 3) {
							alert('Enter valid Mesurements value for Stone ID '
									+ pktCode);
							return false;
						} else if (isNaN(tempArray[0]) || isNaN(tempArray[1])
								|| isNaN(tempArray[2])) {
							alert('Enter valid Numeric Mesurements value for Stone ID '
									+ pktCode);
							return false;
						}

					} else if (minReqPrp[b] == 'rap') {
						var rate = $('#rate_' + a).val();
						var rap = $('#rap_' + a).val();
						if (rate == '' && rap == '') {
							alert('Enter Rate or Rap for Stone ID ' + pktCode);
							return false;
						} else if (isNaN(rate) || isNaN(rap)) {
							alert('Enter Numeric Rate or Rap for Stone ID '
									+ pktCode);
							return false;
						}
						continue;
					} else if (minReqPrp[b] == 'CTS') {
						var cts = $('#CTS_' + a).val();
						if (isNaN(cts) || cts <= 0 || cts == '') {
							alert('Cts cannot be null or less then 0 for Stone ID '
									+ pktCode);
							return false;
						}
					}
					if ($('#' + minReqPrp[b] + '_' + a).val() == '') {
						alert('Enter minimum required details for Stone ID '
								+ pktCode);
						return false;
					}
				}
			}
		}
		if (count == 0) {
			alert('Enter Stone Id');
			return false;
		}
		return true;
	}
	function validateFileUpload() {
		if ($('#file').val() == '') {
			alert('Please select file');
			return false;
		} else {
			return true;
		}
	}
	function submitForm(frmId) {
		$('#ajax').show();
		$('#errorMsg').html('Loading....');
		var formOptions = {
			dataType : 'json',
			data : $('#purchaseDetails').serialize(),
			beforeSubmit : function() {
				if (frmId != '#fileUploadForm') {
					return validateForm();
				} else {
					return validateFileUpload();
				}
			},
			success : function(jsonObj) {
				$('#errorMsg').html(jsonObj);
				//setTimeout (function() {clearMsg();} ,5000);  
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			},
			target : '#response'
		};
		$(frmId).ajaxForm(formOptions);
		$('#ajax').hide();
	}
	function clearMsg() {
		$('#errorMsg').html('');
	}

	function deletePkt(elem) {
		var thisId = elem.id;
		var count = thisId.substring(thisId.indexOf('_') + 1);
		var pktCode = $('#pktCode_' + count).val();
		$.getJSON('deletePkt.html', {
			pktCode : pktCode
		}, function(data) {
			$('#errorMsg').html(data);
		});
		$('#pktCode_' + count).trigger('change');
	}
	function disableEnterKey(e) {
		var key;

		if (window.event)
			key = window.event.keyCode; //IE
		else
			key = e.which; //firefox

		if (key == 13)
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

		<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->

		<div id="mannualUpload">
			<form method="POST" action="uploadStockManual.html"
				id="manUploadForm">
				<div class="ttl_names">
					Notes : <span class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					These shows User mistake in uploading values Kindly correct as it
					may not work in some section. <br /> DISCOUNT will be disabled as
					not required.<br /> GIA-IGI corrections for Properties like "Fluor"
					will be made automatically by system. <br /> Changing
					CTS/Color/Purity/Shape will show you rapPrice and new Discount will
					be automatically calculated; <br />Total Value will change once
					submited.
				</div>
				<table id="greenTable">
					<thead>
						<tr>
							<th>Sr No.</th>
							<c:forEach var="lst" items="${fileMappingList}">
								<c:if test="${lst.columnName != null}">
									<th title="${lst.columnName}">${lst.excelColumnName}</th>
								</c:if>
							</c:forEach>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody id="addStock">
						<c:forEach var="sm" items="${stockMasterList}" varStatus="status">
							<tr id="row_${status.count}">
								<td>${status.count}</td>
								<c:forEach var="lst" items="${fileMappingList}">
									<c:if test="${lst.columnName != null}">
										<td></td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
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