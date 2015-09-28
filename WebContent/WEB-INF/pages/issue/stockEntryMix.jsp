<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.buttontab {
	padding: 5px 10px;
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#CACDA2) );
	background: -moz-linear-gradient(top, #fff, #CACDA2);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	text-shadow: 1px 1px #666;
	color: #000;
	text-decoration: none;
}

.buttontab:hover {
	background: #fff;
}

#active {
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#fff) );
	background: -moz-linear-gradient(top, #fff, #fff);
}
</style>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="css/datepicker.css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery/jquery-ui-1.7.2.custom.min.js"
	type="text/javascript"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/colorbox/jquery.colorbox.js" type="text/javascript"></script>
<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="js/datepicker.js"></script>

<script type="text/javascript">
	// var colList = new Array();
	var colListHTML = new Array();
	var html = '';
	// var rapFormat = 0;

	var str = "pufrom" + '-' + "puto";
	var pu = str.split("-");

	colListHTML = [ "parcelNum", "parcelType", "shFr", "shTr", "pcs", "rate",
			"rootPkt", "cFr", "cTo", "puFr", "puTo", "baseRate", "cts" ];
	$(document)
			.ready(
					function() {
						$('input[name*="Pkt_"]')
								.change(
										function() {
											var thisId = this.id;
											var pktCode = this.value;
											var count = thisId.substring(thisId
													.indexOf('_') + 1);

											$('#totcts_' + count).removeAttr(
													'readonly');
											$('#baseRate_' + count).removeAttr(
													'readonly');

											if (this.value == '') {
												for ( var b = 0; b < colListHTML.length; b++) {
													$(
															'#'
																	+ colListHTML[b]
																	+ '_'
																	+ count)
															.val('');
												}
												return;
											}
											$('input[id*="Pkt_"]')
													.each(
															function() {
																if (thisId != this.id
																		&& pktCode == this.value) {
																	$(
																			'#'
																					+ thisId)
																			.val(
																					'');
																	alert('Duplicate Stone Id');
																}
															});

											$
													.getJSON(
															'stockEntryMixAJAX.html',
															{
																pktCode : this.value
															},
															function(data) {
																if (data == null) {
																	for ( var b = 0; b < colListHTML.length; b++) {
																		$(
																				'#'
																						+ colListHTML[b]
																						+ '_'
																						+ count)
																				.val(
																						'');
																	}
																} else {
																	for ( var b = 0; b < colListHTML.length; b++) {
																		//$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))));
																		$(
																				'#'
																						+ colListHTML[b]
																						+ '_'
																						+ count)
																				.val(
																						eval('data.'
																								+ (colListHTML[b])));
																	}
																	$(
																			'#totcts_'
																					+ count)
																			.attr(
																					'readonly',
																					true);
																	$(
																			'#baseRate_'
																					+ count)
																			.attr(
																					'readonly',
																					true);

																	//$($('#pktCode_'+count)).parents('td:first').append('<span id="span_'+count+'"><br/><a style="display:block;" href="javascript:void(0);" onclick="window.open(\'labLoadRep.html?pktCode='+pktCode+'\',\'_blank\', \'resizable=1,width=800,height=400\');">LAAAAAB</a></span>');
																}
															});
										});

						$('#parcelDueDate').DatePicker({
							format : 'd-m-Y',
							calendars : 1,
							date : $('#parcelDueDate').val(),
							onChange : function(formated, dates) {
								$('#parcelDueDate').val(formated);
								$('#parcelDueDate').DatePickerHide();
							}
						});

						$('#parcelDate').DatePicker({
							format : 'd-m-Y',
							calendars : 1,
							date : $('#parcelDate').val(),
							onChange : function(formated, dates) {
								$('#parcelDate').val(formated);
								$('#parcelDate').DatePickerHide();
							}
						});

						$('#packetDueDate').DatePicker({
							format : 'd-m-Y',
							calendars : 1,
							date : $('#packetDueDate').val(),
							onChange : function(formated, dates) {
								$('#packetDueDate').val(formated);
								$('#packetDueDate').DatePickerHide();
							}
						});

						$('#packetDate').DatePicker({
							format : 'd-m-Y',
							calendars : 1,
							date : $('#packetDate').val(),
							onChange : function(formated, dates) {
								$('#packetDate').val(formated);
								$('#packetDate').DatePickerHide();
							}
						});

						$('#ParcelUploadForm').validate();
						$('#PacketUploadForm').validate();
						$('#ParcelUpload').show();
						$('#PacketUpload').hide();
						$('#FixedPacket').hide();
						$('select[name*="Fr"]').bind("change", checkMinMax);
						$('#selectPacket').hide();

						/*$(".numbersonly-format").keydown(function (event) {
						    // Prevent shift key since its not needed
						    if (event.shiftKey == true) {
						        event.preventDefault();
						    }
						    // Allow Only: keyboard 0-9, numpad 0-9, backspace, tab, left arrow, right arrow, delete
						    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46) {
						        // Allow normal operation
						    } else {
						        // Prevent the rest
						        event.preventDefault();
						    }
						});*/
						<c:if test="${edit == 2}">
						hideDiv('FixedPacket', 'PacketUpload', 'ParcelUpload');
						activate(this);
						</c:if>
					});
	colListParcel = [ "parcelType", "shFr", "shTr", "pcs", "rate", "rootPkt",
			"cFr", "cTo", "puFr", "puTo", "baseRate", "cts" ];

	function ShowSelect() {
		$('#selectPacket').show();
		calc();
	}

	function checkMinMax(elem, prp) {
		var elemName;
		if (this.id) {
			elemName = this.id.replace("Fr", "To");
		} else {
			elemName = elem.replace("Fr", "To");
		}
		if ($('#' + elemName) != null && $('#' + elemName).val() == -1) {
			if (this.id) {
				$('#' + elemName).val(this.value);
			} else {
				$('#' + elemName).val($('#' + elem).val());
			}
		} else {
			prpOrder(elem.id, elemName);
		}
	}
	function prpOrder(from, to) {
		if ($('#' + from).val() > $('#' + to).val()) {
			var swap = $('#' + to).val();
			$('#' + to).val($('#' + from).val());
			$('#' + from).val(swap);
		}
	}
	function getParcelData(id, count) {
		$.getJSON('parcelDetailAJAX.html', {
			id : id
		}, function(data) {
			if (data != null) {
				for ( var b = 0; b < colListParcel.length; b++) {
					$('#' + colListParcel[b] + '_' + count).val(
							eval('data.' + (colListParcel[b])));
					if ($('#' + colListParcel[b] + '_' + count).val() == -1) {
						$('#' + colListParcel[b] + '_' + count).val(
								eval('data.' + (colListParcel[b]) + '_so'));
					}
				}

			}

		});
	}

	function show_prompt() {
		var count = prompt("Enter Number of Packets", "1");

		if (count != null && count != "") {
			window.location = "stockSplit.html";
			var load = window
					.open(
							'stockSplit.html?pktCode=' + pktCode + '&count='
									+ count,
							' ',
							'scrollbars=no,menubar=no,height=600,width=1400,resizable=yes,toolbar=no,location=no,status=no');
		}
	}
	var minReqPrp = new Array("Pkt", "totcts", "rate");

	function validateForm() {
		if ($('#vendorId').val() > -1) {
			var purVar = new Array("date", "dueDate", "paymentTerm", "exRate");
			var flag = true;
			for ( var a = 0; a < purVar.length; a++) {
				if (purVar[a] == 'exRate') {
					if ($('#currency').val() != 'USD') {
						$('#' + purVar[a]).addClass('Error');
						flag = false;
					}
				} else if ($('#' + purVar[a]).val() == '') {
					$('#' + purVar[a]).addClass('Error');
					flag = false;
				}
			}
			if (!flag) {
				alert('Enter required fields');
				return false;
			}
		}
		var pktCnt = 10;
		var count = 0;
		if ($('#countEdit').val() == 1) {
			pktCnt = 1;
		}
		for ( var a = 0; a < pktCnt; a++) {
			if (!$('#Pkt_' + a) || $('#Pkt_' + a).val() == '') {
				continue;
			} else {
				count++;
				var pktCode = $('#Pkt_' + a).val();
				for ( var b = 1; b < minReqPrp.length; b++) {
					var rate = $('#rate_' + a).val();
					if (isNaN(rate)) {
						alert('Enter Numeric Rate for Stone ID ' + pktCode);
						return false;
					}
					continue;
				}
				if ($('#' + minReqPrp[b] + '_' + a).val() == '') {
					alert('Enter minimum required details for Stone ID '
							+ pktCode);
					return false;
				}
			}
			var cts = $('#cts_' + a).val();
			var pcs = $('#pcs_' + a).val();
			if (cts == '' && pcs == '') {
				alert('Enter atleast pcs or carats for Stone ID ' + pktCode);
				return false;
			}
			if (isNaN(cts) && isNaN(pcs)) {
				alert('Enter correct pcs or carats for Stone ID ' + pktCode);
				return false;
			}
			checkMinMax('shFr_' + a, 'sh');
			checkMinMax('puFr_' + a, 'pu');
			checkMinMax('cFr_' + a, 'c');
		}
		if (count == 0) {
			alert('Enter Stone Id');
			return false;
		}
		return true;
	}

	function hideDiv(fld1, fld2, fld3) {
		$('#' + fld1).show();
		$('#' + fld2).hide();
		$('#' + fld3).hide();
		if (fld1 == 'PacketUpload') {
			$('#fileUpload').hide();
		}
	}
	function activate(fld) {
		$('.buttontab').attr('id', '');
		$(fld).attr('id', 'active');
	}
	function hideTwoDivs(fld1, fld2) {
		$('#' + fld1).hide();
		$('#' + fld2).show();
	}

	function addParcelForm() {
		var count = $('#curCount').val();
		count++;
		var html = '';
		html += '<tr><td>'
				+ '<select style="width:100px;" name="codes" id="codes_'+count+'">'
				+ '<option value="">Select</option>'
				+ '<c:forEach var="x" items='${PACKET_LIST}'>'
				+ '<option value="${x}">${x}</option>' + '</c:forEach>'
				+ '</select>' + '</td>'
				+ '<td><input type="text" name="Packet_'+count+'"></td>'
				+ '</tr>'
		$('#parcelTable').html($('#parcelTable').html() + html);
		$('#curCount').val(count);
	}

	function showTotalCts() {
		var cts_ID = $('#ctsList').val();
		var array = cts_ID.split("|");
		var cts = array[0];
		var ID = array[1];
		var purchaseId = array[2];
		var text = '';
		$('#curID').val(ID);
		$('#curPurchaseId').val(purchaseId);
		if (cts > 0)
			text = 'The total carats in this parcel are ' + cts + '.';
		$('#totalCarats').html(text);
	}

	function loadFileData(fld) {
		window.location = 'stockEntryMix.html?fileId=' + $('#' + fld).val();
	}
	function calc() {
		var cts = $('#ctsList').val();
		var array = cts.split("|");
		cts = array[0];
		var totalcts = cts;
		if (cts == '') {
			$('#parcelMsg')
					.html(
							'<p style="color:red;">Select a value from the above dropdown</p>');
			return;
		}
		cts = parseFloat(cts);
		var textval = '';
		$('#submit').show();
		$('.textbox')
				.each(
						function(i) {
							textval = $(this).val();
							if (textval == '') {
								$('#parcelMsg').html(
										'<p style="color:red;">Available carats : '
												+ cts + ' (Total: ' + totalcts
												+ ')</p>');
							} else {
								textval = parseFloat(textval);
								if (isNaN(textval)) {
									$('#parcelMsg')
											.html(
													'<p style="color:red;">There is an error in the entered values</p>');
									$('#submit').hide();
								}
							}
							if (textval > 0) {
								cts -= textval;
								if (cts < 0) {
									var rem = 0.0;
									rem = textval + cts;
									$(this).val(rem);
									cts = 0.0;
									alert('The values cannot exceed '
											+ totalcts);
									//$('#parcelMsg').html('<p style="color:red;">Available carats : '+cts+' (Total: '+totalcts+')</p>');	
									//return;
								}
							}
							$('#curRemCts').val(cts);
							$('#parcelMsg').html(
									'<p style="color:red;">Available carats : '
											+ cts + ' (Total: ' + totalcts
											+ ')</p>');
						});
	}

	/*function checkPackets(name){
		var count=$('#curCount').val();
		//alert(count);
		for(i=1;i<count;i++){
			if($("#codes_"+i).val()!=-1 ) {
	  			if($("#Packet_"+(--i)).val()==""|| $("#Packet_"+(--i)).val()==null){
				alert("Please Enter the value." +$("#Packet_"+(--i)).val());
				$("#Packet_"+(--i)).focus();
	  			}
			}
		}
	}*/
</script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>

	<div id="content">

		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<c:if test="${edit == 1}">
			<div id="errorMsg">${mailMsg}</div>
		</c:if>
		<br>
		<br>
		<c:if test="${edit != 1}">
			<a href="#" class="buttontab"
				onclick="hideDiv('ParcelUpload','PacketUpload','FixedPacket');activate(this);"
				id="active">Parcel Upload</a>
			<a href="#" class="buttontab"
				onclick="hideDiv('PacketUpload','ParcelUpload','FixedPacket');activate(this);"
				id="">Packet Upload</a>
			<a href="#" class="buttontab"
				onclick="hideDiv('FixedPacket', 'PacketUpload','ParcelUpload');activate(this);"
				id="">Fixed Packet</a>
			<hr style="margin-top: 5px;">
			<div id="PacketUpload">
				<form id="PacketUploadForm" name="PacketUploadForm"
					action="stockEntryMixSubmit.html" method="post">
					<br />
					<div class="Fixed">
						<a href="stockMixList.html">Small Size packet List (Purchased)</a>
					</div>
					<br />
					<div id="Table">
						<table>
							<tr valign="top">
								<td>
									<table width="100%" align="center" style="vertical-align: top;">
										<tr>
											<th><label for="vendorId">Vendor Id</label>
											</th>
											<td><select name="vendorId" id="vendorId"
												style="width: 145px;">
													<option value="-1">Select Vendor</option>
													<c:forEach var="v" items='${VENDOR_LIST}' varStatus="cnt">
														<option value='${v.id}'>${v.description}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<th><label for="billNo">Bill No:</label>
											</th>
											<td><input type="text" name="billNo" id="billNo"
												class="required" size="20" />
											</td>
										</tr>
										<tr>
											<th><label for="comments">Comments:</label>
											</th>
											<td><input type="text" name="comments" id="comments"
												size="20" />
											</td>
										</tr>
									</table></td>
								<td>
									<table width="100%" align="center" style="vertical-align: top;">
										<tr>
											<th><label for="packetDate">Date:</label></th>
											<td><input type="text" name="packetDate" id="packetDate"
												class="required" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="packetDueDate">Due Date:</label>
											</th>
											<td><input type="text" name="packetDueDate"
												id="packetDueDate" class="required" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="paymentTerm">Payment Term</label>
											</th>
											<td><select name="paymentTerm" id="paymentTerm"
												style="width: 145px;">
													<option value='CHEQUE'>CHEQUE</option>
													<option value='CASH'>CASH</option>
											</select></td>
										</tr>
									</table></td>
								<td>
									<table width="100%" align="center">
										<tr>
											<th><label for="currency">Currency</label>
											</th>
											<td><select name="currency" id="currency"
												style="width: 145px;">
													<c:forEach var="v" items='${currencyList}' varStatus="cnt">
														<option value='${v.currAbrev}'
															<c:if test="${v.currAbrev == 'USD'}">selected</c:if>>${v.currency}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<th><label for="exRate">Ex Rate:</label></th>
											<td><input type="text" name="exRate" id="exRate"
												class="number" size=10 value="1" />
											</td>
										</tr>
										<tr>
											<th><label for="tax">Tax:</label>
											</th>
											<td><input type="text" name="tax" id="tax"
												class="number" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="expenses">Expenses:</label>
											</th>
											<td><input type="text" name="expenses" id="expenses"
												class="number" size=10 />
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div>
					<div id="fileUpload" class="mainBody">
						<input type="button" value="Manual Mode"
							onclick="hideTwoDivs('fileUpload','manualUpload');">
						<%-- 				<form method="POST" action="upload.html" enctype="multipart/form-data" id="fileUploadForm"> --%>
						<br /> <label for="fileId1">Select File Format</label> <select
							id="fileId1" name="fileId1" onchange="loadFileData(this.id);">
							<c:forEach items="${fileList}" var="f" varStatus="cnt">
								<option value="${f.id}"
									<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
							</c:forEach>
						</select> <br />
						<br /> <input type="file" name="file" id="file" /> <br />
						<!-- 					<input type="submit" value="Submit" id="fileSubmit" onclick="submitForm('#fileUploadForm');"/> -->
					</div>
					<div id="manualUpload">
						<%-- 				<form method="POST" action="uploadStockManual.html" id="manUploadForm"> --%>
						<input type="button" value="File Upload Mode"
							onclick="hideTwoDivs('manualUpload','fileUpload');"> <label
							for="pktCount">Pkts Insert:</label> <input type="text"
							name="pktCount" id="pktCount" size="4" value="10" class="number"
							onchange="updateMap()" /> <label for="fileId2">Select
							File Format</label> <select id="fileId2" name="fileId2"
							onchange="loadFileData(this.id);">
							<c:forEach items="${fileList}" var="f" varStatus="cnt">
								<option value="${f.id}"
									<c:if test='${f.id == param.fileId}'>selected</c:if>>${f.fileName}</option>
							</c:forEach>
						</select>
						<div class="ttl_names">
							Notes : <span class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							These shows User mistake in uploading values Kindly correct as it
							may not work in some section. <br /> DISCOUNT will be disabled as
							not required.<br /> GIA-IGI corrections for Properties like
							"Fluor" will be made automatically by system. <br /> Changing
							CTS/Color/Purity/Shape will show you rapPrice and new Discount
							will be automatically calculated; <br />Total Value will change
							once submited.
						</div>
						<table id="greenTable">
							<thead>
								<tr>
									<th>Sr No.</th>
									<c:forEach var="lst" items="${fileMappingList}">
										<c:if test="${lst.columnName != null}">
											<c:if test="${lst.columnName == 'rate'}">
												<th>Rap Price</th>
											</c:if>
											<th title="${lst.columnName}">${lst.excelColumnName}</th>
											<c:if test="${lst.columnName == 'pktCode'}">
												<c:if test="${pageScope.editPkt != false}">
													<th>Stone ID (Edit)</th>
													<c:set var="editPkt" value="false" scope="page"></c:set>
												</c:if>
											</c:if>
										</c:if>
									</c:forEach>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody id="addStock">
							</tbody>
						</table>
						<!-- 					<input type="submit" value="Submit" id="manSubmit" onclick="submitForm('#manUploadForm');" onKeyPress="return disableEnterKey(event)"/> -->

						<!-- <table  align="center" id="greenTable" width="100%">
			              
			              <tr id="" class="ui-widget-content jqgrow ui-row-ltr selected-row ui-state-hover ui-state-highlight" role="row" area-selected="true">
			                <th><input type="checkbox" /></th>
							<th>Pkt</th>
							<th>ParcelNum</th>
							<th>Parcel Type</th>
							<th>Sieve</th>
							<th colspan="2">sh</th>
							<th >Cts(Avg)</th>
							<th >Total Cts</th>
							<th >Pcs</th>
							<th >Rate</th>
							<th >Root</th>
							<th colspan="2">Clarity</th>
							<th colspan="2">Color</th>
							<th>Cost Price/Cts</th>
					
						   </tr>
						   <tr>
						   <td colspan="5"></td><th>From</th><th>To</th><td colspan="5" class="Fixed"></td><th>From</th><th>To</th><th>From</th><th>To</th><td></td><td></td>
						   </tr>
						   
						   <c:forEach begin="0" end="9" var="count">
						   <tr>
						    <td  style="text-align:center;width: 20px;" role="gridcell">
								<input id="splitcheck" class="cbox" type="checkbox"   name="splitcheck" role="checkbox">
							</td>
							 <td><input type="text" name="Pkt_${count}" id="Pkt_${count}" size="8" ></td>
							  <td>
							  	 <select id="parcelNum_${count}" name="parcelNum_${count}" onchange="getParcelData(this.value, '${count}');">
						           <option value="-1">All</option>
						          <c:forEach var="parcel" items='${parcelList}'>
							          <option value='${parcel.id}'>${parcel.description}</option>
						          </c:forEach>
					             </select> 
							  </td>
							  <td>
							    <select id="pTyp_${count}" name="pTyp_${count}" style="width: 75px;">
						          <c:forEach var="p" items='${PRP_LOV["PTYP"]}'>
							          <option value='${p.id}'>${p.description}</option>
						          </c:forEach>
					             </select> 
					         </td>
					          <td>
							    <select id="sieve_${count}" name="sieve_${count}" style="width: 75px;">
						          <c:forEach var="p" items='${PRP_LOV["SIEVE"]}'>
							          <option value='${p.id}'>${p.description}</option>
						          </c:forEach>
					             </select> 
					         </td>
							 <td>
							    <select id="shFr_${count}" name="shFr_${count}" style="width: 75px;">
						          <option value="-1">All</option>
						          <c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
							          <option value='${prpLov.id}'>${prpLov.description}</option>
						          </c:forEach>
					             </select> 
					         </td>
					          <td>
							    <select id="shTo_${count}" name="shTo_${count}" style="width: 75px;">
						          <option value="-1">All</option>
						          <c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
							          <option value='${prpLov.id}'>${prpLov.description}</option>
						          </c:forEach>
					             </select> 
					         </td>
					         <td ><input type="text" id="cts_${count }" 	name="cts_${count }"  size="8" onchange="chkNumVal(this, this.value, 0,10000000);"> </td>
							 <td ><input type="text" id="totcts_${count }" name="totcts_${count }"  size="8" onchange="chkNumVal(this, this.value, 0,10000000);"> </td>
							 <td ><input type="text" name="pcs_${count}" 	id="pcs_${count }" size="8" onchange="chkNumVal(this, this.value, 0,10000000);"></td>
							 <td ><input type="text" name="rate_${count }" id="rate_${count }" size="8" onchange="chkNumVal(this, this.value, 0,10000000);"> </td>
							 <td ><input type="text" name="rootpkt_${count }" id="rootpkt_${count }" size="8"> </td>
							 <td>
					            <select id="puFr_${count }" name="puFr_${count }">
				              
				                 <option value="-1">All</option> 
				                  <c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
					                <option value='${prpLov.id}'>${prpLov.description}</option>
				                  </c:forEach>
			                     </select>
			                 </td>
			                 <td> 
					            <select id="puTo_${count }" name="puTo_${count }">
				                  <option value="-1">All</option>
				                  <c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
					                <option value='${prpLov.id}'>${prpLov.description}</option>
				                  </c:forEach>
			                     </select>
			                 </td>		
					   		 <td>	 		 
							   <select id="cFr_${count}" name="cFr_${count}">
						              <option value="-1">All</option> 
						             
				                  <c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
					                <option value='${prpLov.id}'>${prpLov.description}</option>
				                  </c:forEach>
					            </select> 
	                        </td>
	                        <td>  
							   <select id="cTo_${count}" name="cTo_${count}">
						            <option value="-1">All</option> 
				                     <c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
					                <option value='${prpLov.id}'>${prpLov.description}</option>
				                  </c:forEach>
					            </select> 
					        </td>
							<td><input type="text" name="baseRate_${count }" id="baseRate_${count }" size="8"> </td>
							<%--  <td><input type="button" id="splitpkt_${count}" onclick="show_prompt();" value="split" />--%>
							</tr>
							</c:forEach>
					
	               </table> -->
						<input type="submit" id="submit" value="Submit" />
					</div>
				</form>
			</div>
			<div id="ParcelUpload">
				<form id="ParcelUploadForm" name="stockEnM2"
					action="ParcelDistribute.html" method="post">
					<!--<br/>
						<div class="Fixed"><a href="stockMixList.html">Small Size packet List</a></div>
					<br/>				
				-->
					<div id="Table">
						<table>
							<tr valign="top">
								<td>
									<table width="100%" align="center" style="vertical-align: top;">
										<tr>
											<th><label for="vendorId">Vendor Id</label>
											</th>
											<td><select name="vendorId" id="vendorId"
												style="width: 145px;">
													<option value="-1">Select Vendor</option>
													<c:forEach var="v" items='${VENDOR_LIST}' varStatus="cnt">
														<option value='${v.id}'>${v.description}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<th><label for="billNo">Bill No:</label>
											</th>
											<td><input type="text" name="billNo" id="billNo"
												class="required" size="20" />
											</td>
										</tr>
										<tr>
											<th><label for="comments">Comments:</label>
											</th>
											<td><input type="text" name="comments" id="comments"
												size="20" />
											</td>
										</tr>
									</table></td>
								<td>
									<table width="100%" align="center" style="vertical-align: top;">
										<tr>
											<th><label for="parcelDate">Date:</label></th>
											<td><input type="text" name="parcelDate" id="parcelDate"
												class="required" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="parcelDueDate">Due Date:</label>
											</th>
											<td><input type="text" name="parcelDueDate"
												id="parcelDueDate" class="required" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="paymentTerm">Payment Term</label>
											</th>
											<td><select name="paymentTerm" id="paymentTerm"
												style="width: 145px;">
													<option value='CHAQUE'>CHEQUE</option>
													<option value='CASH'>CASH</option>
											</select></td>
										</tr>
									</table></td>
								<td>
									<table width="100%" align="center">
										<tr>
											<th><label for="currency">Currency</label>
											</th>
											<td><select name="currency" id="currency"
												style="width: 145px;">
													<c:forEach var="v" items='${currencyList}' varStatus="cnt">
														<option value='${v.currAbrev}'
															<c:if test="${v.currAbrev == 'USD'}">selected</c:if>>${v.currency}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<th><label for="exRate">Ex Rate:</label></th>
											<td><input type="text" name="exRate" id="exRate"
												class="number" size=10 value="1" />
											</td>
										</tr>
										<tr>
											<th><label for="tax">Tax:</label>
											</th>
											<td><input type="text" name="tax" id="tax"
												class="number" size=10 />
											</td>
										</tr>
										<tr>
											<th><label for="expenses">Expenses:</label>
											</th>
											<td><input type="text" name="expenses" id="expenses"
												class="number" size=10 />
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div>
					<table>
						<tr valign="top">
							<td>
								<table width="100%" align="center" style="vertical-align: top;">
									<tr>
										<th><label for="parcel_No">Parcel NO</label>
										</th>
										<td><input type="text" name="ParcelNo" id="ParcelNo"
											size="20" />
										</td>
									</tr>
									<tr>
										<th><label for="TotCts">Total Cts</label>
										</th>
										<td><input type="text" name="TotCts" id="TotCts"
											class="required number" size="20" />
										</td>
									</tr>
									<tr>
										<th><label for="TotRate">Total Rate</label>
										</th>
										<td><input type="text" name="TotRate" id="TotRate"
											class="required number" size="20" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<input type="submit" name="submit" id="submit" value="submit" /> <b>OR
					</b><a href="ParcelDistribute.html" class="buttontab">Distribute
						Directly</a>
				</form>
			</div>
		</c:if>
		<c:if test="${edit == 1}">
			<div id="ParcelDetails">
				<form id="stockErMx" name="stockEnM" action="packetSubmit.html"
					method="post">
					<div id="totalCarats"></div>
					<br> <input type="hidden" value="10" id="curCount"
						name="curCount" /> <input type="hidden" value="" id="curID"
						name="curID" /> <input type="hidden" value="" id="curPurchaseId"
						name="curPurchaseId" /> <input type="hidden" value=""
						id="curRemCts" name="curRemCts" />
					<table class="greenTable" style="text-align: center;">
						<tr>
							<th>Vendor / BillNo / Date / TotalCts</th>
						</tr>
						<tr>
							<td><select style="width: 400px;" name="ctsList"
								onchange="showTotalCts()" id="ctsList">
									<option value="">Select</option>
									<c:forEach var="s" items='${PARCEL_LIST}'>
										<option value="${s.totalcts}|${s.ID}|${s.purchaseId}">
											${s.companyname} /
											<c:if test="${s.billno == ''}">-</c:if>
											<c:if test="${s.billno != ''}">${s.billno}</c:if>
											/ ${s.purchaseDate} / ${s.totalcts}
										</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<input type="button" value="Distribute" id="Dist"
						onclick="ShowSelect()">
					<div id="parcelMsg">${PARCEL_MSG}</div>
					<div id="selectPacket">
						<table id="parcelTable" class="greenTable"
							style="text-align: center;">
							<tr>
								<th>Parcel No</th>
								<th>Total Cts</th>
							</tr>
							<c:forEach begin="0" end="9" step="1" varStatus="index">
								<tr>
									<td><select style="width: 100px;"
										name="codes_${index.count}" id="codes_${index.count}"
										class="packetNo">
											<option value="-1">Select</option>
											<c:forEach var="x" items='${PACKET_LIST}'>
												<option value="${x}">${x}</option>
											</c:forEach>
									</select></td>
									<td><input type="text" name="cts_${index.count}"
										id="cts_${index.count}" onblur="calc()" class="textbox">
									</td>
								</tr>
							</c:forEach>
						</table>
						<input type="button" name="addNew" id="addNew" value="Add New"
							onclick="addParcelForm()" /> <input type="submit" name="submit"
							id="submit" value="submit" />
					</div>
				</form>
			</div>
		</c:if>
	</div>
	<c:if test="${edit != 1}">
		<div id="FixedPacket">
			<form id="FixedPacketForm" action="fixedPacketUpload.html"
				method="post">
				<br />
				<div class="Fixed">
					<a href="stockMixList.html?fixedFlag=1">Small Size packet List
						(Fixed)</a>
				</div>
				<br />
				<table align="center" id="greenTable" width="100%">
					<tr id=""
						class="ui-widget-content jqgrow ui-row-ltr selected-row ui-state-hover ui-state-highlight"
						role="row" area-selected="true">
						<th>Pkt</th>
						<th>ParcelNum</th>
						<th>Parcel Type</th>
						<th>Sieve</th>
						<th colspan="2">sh</th>
						<th colspan="2">Clarity</th>
						<th colspan="2">Color</th>
						<th>Cost Price/Cts</th>
					</tr>
					<tr>
						<td colspan="4"></td>
						<th>From</th>
						<th>To</th>
						<th>From</th>
						<th>To</th>
						<th>From</th>
						<th>To</th>
						<td></td>
						<td></td>
					</tr>
					<c:forEach begin="0" end="9" var="count">
						<tr>
							<td><input type="text" name="Pkt_${count}" id="Pkt_${count}"
								size="8">
							</td>
							<td><select id="parcelNum_${count}"
								name="parcelNum_${count}"
								onchange="getParcelData(this.value, '${count}');">
									<option value="-1">All</option>
									<c:forEach var="parcel" items='${parcelList}'>
										<option value='${parcel.id}'>${parcel.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="pTyp_${count}" name="pTyp_${count}"
								style="width: 75px;">
									<c:forEach var="p" items='${PRP_LOV["PTYP"]}'>
										<option value='${p.id}'>${p.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="sieve_${count}" name="sieve_${count}"
								style="width: 75px;">
									<c:forEach var="p" items='${PRP_LOV["SIEVE"]}'>
										<option value='${p.id}'>${p.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="shFr_${count}" name="shFr_${count}"
								style="width: 75px;">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="shTo_${count}" name="shTo_${count}"
								style="width: 75px;">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="puFr_${count }" name="puFr_${count }">

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

									<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><select id="cTo_${count}" name="cTo_${count}">
									<option value="-1">All</option>
									<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
										<option value='${prpLov.id}'>${prpLov.description}</option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="baseRate_${count }"
								id="baseRate_${count }" size="8"></td>
							<%--  <td><input type="button" id="splitpkt_${count}" onclick="show_prompt();" value="split" />--%>
						</tr>
					</c:forEach>

				</table>
				<input type="submit" id="submit" value="Submit" />
			</form>

		</div>
	</c:if>
	</div>
</body>
</html>








