<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function editLink(cellvalue, options, rowObject) {
		if (rowObject.statusCode == 'Approved')
			return '<a href="memoPrintPDF.html?orderId=' + rowObject.id
					+ '" target="_blank">' + rowObject.id + '</a>';
		else
			return cellvalue;
	}

	function processInvoice() {
		var data;
		if ($('#accType').val() == 'L') {
			data = $('#invoiceData2').serialize();
		} else {
			data = $('#invoiceData').serialize();
		}
		alert(data);
		$('#ajax').show();
		$.ajax({
			type : 'POST',
			url : 'memoToInvoice.html',
			dataType : 'json',
			data : data,
			success : function(json) {
				if (json != null && json != "") {
					$('#errorMsg').html(json[1]);
					if (!isNaN(json[2]) && parseInt(json[2]) > 0)
						window.open('memoPrintPDF.html?invId=' + json[2],
								'_newtab');
				} else {
					alert(json);
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			}
		});
		$('#memoList').trigger("reloadGrid");
		$('#invoiceDetails').hide();
		$('#invoiceDetailsLC').hide();
		$('#PacketReturn').show();
		$('#ajax').hide();
	}
	function makeInvoiceLc(id, isEdit) {
		$('#ajax').show();
		$('.memoId').val(id);
		$.ajax({
			type : 'POST',
			url : 'invDataAJAX.html',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(json) {
				if (json != null && json != "") {
					var keyValye = new Array("consigneeId", "consigneeName",
							"expRefNo", "othRefNo", "destination",
							"preCarrierPartyId", "placeOfPreCarrier",
							"vesselFlight", "portOfLoad", "portOfDischarge",
							"pan", "cstVat");
					$('#invId').val(json.id);
					for ( var i = 0; i < keyValye.length; i++) {
						var fldval = eval('json.' + keyValye[i]);
						if (fldval) {
							$('#' + keyValye[i]).val(fldval);
						}
					}
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			}
		});
		$('#partyNameLC').html(
				jQuery("#memoList").jqGrid('getCell', id, 'companyName'));
		$('#accType').val("L");
		$('#invoiceDetailsLC').show();
		$('#invoiceDetails').hide();
		$('#PacketReturn').hide();
		$('#ajax').hide();
	}
	function makeInvoiceEx(id, isEdit) {
		$('#ajax').show();
		$('.memoId').val(id);
		alert("MemoId in EX: "+id);
		$.ajax({
			type : 'POST',
			url : 'invDataAJAX.html',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(json) {
				if (json != null && json != "") {
					var keyValye = new Array("consigneeId", "consigneeName",
							"expRefNo", "othRefNo", "destination",
							"preCarrierPartyId", "placeOfPreCarrier",
							"vesselFlight", "portOfLoad", "portOfDischarge");
					$('#invId').val(json.id);
					for ( var i = 0; i < keyValye.length; i++) {
						var fldval = eval('json.' + keyValye[i]);
						if (fldval) {
							$('#' + keyValye[i]).val(fldval);
						}
					}
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			}
		});
		if (isEdit != 1) {
			$('#destination').val(
					jQuery("#memoList").jqGrid('getCell', id, 'country'));
			$('#portOfDischarge').val(
					jQuery("#memoList").jqGrid('getCell', id, 'city'));
		}
		$('#invoiceDetails').show();
		$('#invoiceDetailsLC').hide();
		$('#PacketReturn').hide();
		$('#ajax').hide();
	}
	function openMemoEdit(memoId, brokerId, brokrage) {
		$('#editMemoId').val(memoId);
		$('#brokerId').val(brokerId);
		if (brokrage > 0)
			$('#brokrage').val(brokrage);
		else {
			$('#brokrage').val(0);
		}
		$('#memoEditForm').show();
	}
	function validateBrok() {
		if ($('#brokrage').val() > 10) {
			alert('Brokerage too much please verify');
			$('#brokrage').val('');
			return false;
		} else if (parseFloat($('#brokrage').val()) <= 0) {
			alert('Verify Brokerage its incorrect');
			$('#brokrage').val('');
			return false;
		} else {
			return true;
		}
	}
	function editMemoDetails(cellvalue, options, rowObject) {
		if (rowObject.orderType == 'Confirm'
				|| rowObject.orderType == 'Request'
				|| rowObject.orderType == 'INV') {
			return '<a href="javascript:void(0);" onclick="addTab(\'Memo Edit\',\'memoEdit.html?orderId='
					+ rowObject.id+ '\');" title="Click to Edit">Edit</a>';
		}
		return '';
	}
	function editSaveMemo() {
		var chk = validateBrok();
		if (chk && $('#brokerId').val() > 0) {
			$('#ajax').show();
			$.ajax({
				type : 'POST',
				url : 'editMemoDetails.html',
				dataType : 'json',
				data : {
					orderId : $('#editMemoId').val(),
					brokerId : $('#brokerId').val(),
					brokrage : $('#brokrage').val()
				},
				success : function(json) {
					$('#memoList').trigger("reloadGrid");
				},
				error : function(xmlHttpRequest, textStatus, errorThrown) {
					errorFunc('${USER_SESSION.userId}');
				}
			});
			$('#ajax').hide();
		} else {
			alert('Please fill the details');
		}
	}

	function confirmMemo(id) {
		$('#ajax').show();
		$.ajax({
			type : 'POST',
			url : 'updateMemoType.html',
			dataType : 'json',
			data : {
				orderId : id,
				orderType : 'Confirm'
			},
			success : function(json) {
				$('#memoList').trigger("reloadGrid");
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			}
		});
		$('#ajax').hide();
	}
	function approveMemo(id) {
		$('#ajax').show();
		$
				.ajax({
					type : 'POST',
					url : 'approveMemo.html',
					dataType : 'json',
					data : {
						orderId : id
					},
					success : function(json) {
						if (json == '0') {
							alert('No packets can be approved. Either allpackets are SOLD or in memo already. ');
						}
						$('#memoList').trigger("reloadGrid");
					},
					error : function(xmlHttpRequest, textStatus, errorThrown) {
						errorFunc('${USER_SESSION.userId}');
					}
				});
		$('#ajax').hide();
	}
	function getTotal(cellvalue, options, rowObject) {
		return (parseFloat(rowObject.rate) * parseFloat(rowObject.cts))
				.toFixed(2);
	}
	function orderFrom(cellvalue, options, rowObject) {
		if (cellvalue == 1)
			return 'Memo';
		else
			return 'Web memo';
	}
	function memoLink(cellvalue, options, rowObject) {
		if (rowObject.statusCode == 'Approved'
				&& rowObject.orderType == 'Confirm') {
			if (rowObject.accType == 'E') {
				return '<a href="javascript:void(0);" onclick="makeInvoiceEx(\''
						+ rowObject.id + '\', 1);">Make EX Invoice'+rowObject.id+'</a>';
			} else
				return '<a href="javascript:void(0);" onclick="makeInvoiceLc(\''
						+ rowObject.id + '\', 1);">Make LC Invoice</a>';
		} else if (rowObject.statusCode == 'Approved'
				&& rowObject.orderType == 'INV') {
			if (rowObject.accType == 'E') {
				return '<a href="javascript:void(0);" onclick="makeInvoiceEx(\''
						+ rowObject.id + '\', 1);">Edit EX Invoice</a>';
			} else
				return '<a href="javascript:void(0);" onclick="makeInvoiceLc(\''
						+ rowObject.id + '\', 1);">Edit LC Invoice</a>';
		} else if (rowObject.orderType == 'Request' && rowObject.memo == 1
				&& rowObject.statusCode != 'Rejected')
			return '<a href="javascript:void(0);" onclick="confirmMemo(\''
					+ rowObject.id + '\');">Confirm Memo</a>';
		else if (rowObject.statusCode == 'Pending'
				&& rowObject.orderType == 'Confirm/Invoice')
			return '<a href="javascript:void(0);" onclick="approveMemo(\''
					+ rowObject.id + '\');">Approve Memo</a>';//todo approve reject on pending
		else
			return '';
	}
	function fnFilter() {
		$('#overDue').val(0);
		$('#exRate').val(0);
		if ($('#memoType').val() == 'CI' || $('#memoStatus').val() == '1') {
			if ($('#memoType').val() == 'CI') {
				$('#PacketReturn').hide();
				$('#CertOptions').show();
			}
			$('#cb_memoList').attr('disabled', true);
		} else {
			$('#PacketReturn').show();
			$('#CertOptions').hide();
			$('#cb_memoList').attr('disabled', false);
		}

		var data = {};
		$.each($('#gridPanel').serializeArray(), function(i, kv) {
			data[kv.name] = kv.value;
		});
		data["srtIndex"] = $("#memoList").jqGrid('getGridParam', 'sortname');
		data["srtType"] = $("#memoList").jqGrid('getGridParam', 'sortorder');

		$('#memoList')
				.setGridParam(
						{
							url : 'memoReportGrid.html?q=1<c:if test="${param.web == 1}">&web=1</c:if>',
							datatype : 'json',
							postData : data
						}).trigger("reloadGrid");
		//var memoType = $('#memoType').val();
		//if(memoStatus == 1) {
		//};	
	}
	function datePicker(elem) {
		$(elem).datepicker();
	}
	function getPkStatus(cellvalue, options, rowObject) {
		if (cellvalue == 1) {
			return 'Pending';
		} else if (cellvalue == 2) {
			return 'Approved';
		} else {
			return 'Rejected';
		}
	}

	var selection = new Array();
	var pktRates = new Object();
	var pktRaps = new Object();

	function clrSel() {
		selection = new Array();
		pktRates = new Object();
		pktRaps = new Object();
		$('#selCount').html(selection.length);
		$('.cartLk').hide();
		$('.cbox').attr("checked", false);
	}

	$(document)
			.ready(
					function() {
						jQuery("#memoList")
								.jqGrid(
										{
											sortable : true,
											url : 'memoReportGrid.html?q=1<c:if test="${param.web == 1}">&web=1</c:if>',
											datatype : "json",
											colNames : [ 'Memo No.',
													'Party/Vendor', 'Broker',
													'Brokerage', 'Memo Date',
													'Due Date', 'Memo Type',
													'Status', 'Action',
													'Ex rate', 'LC/EX',
													'Order From', 'Edit',
													'partyId', 'Country',
													'City' ,'Add Discount(%)'],
											colModel : [
													{
														name : 'id',
														index : 'om.id',
														width : 30,
														searchoptions : {
															sopt : [ 'eq', 'in' ]
														},
														formatter : editLink
													},
													{
														name : 'companyName',
														index : 'pm.companyName',
														width : 100,
														searchoptions : {
															sopt : [ 'eq',
																	'cn', 'sw' ]
														}
													},
													{
														name : 'brokerName',
														index : 'brokerName',
														width : 65,
														search : false
													},
													{
														name : 'brokrage',
														index : 'brokrage',
														width : 50,
														search : false
													},
													{
														name : 'orderDate',
														index : 'orderDate',
														width : 75,
														searchoptions : {
															sopt : [ 'eq',
																	'lt', 'gt' ],
															dataInit : function(
																	el) {
																$(el)
																		.datepicker(
																				{
																					dateFormat : 'dd-mm-yyyy'
																				});
																jQuery(
																		'.ui-datepicker')
																		.css(
																				{
																					'zIndex' : '1200',
																					'font-size' : '100%'
																				});
															}
														}
													},
													{
														name : 'dueDate',
														index : 'dueDate',
														width : 75,
														search : false
													},
													{
														name : 'orderType',
														index : 'orderType',
														width : 65,
														stype : 'select',
														editoptions : {
															value : "Confirm:Confirm;Request:Request,LI:Laser,CI:Cert,MX:External,INV:Invoiced;"
														},
														searchoptions : {
															sopt : [ 'eq', 'ne' ]
														}
													},
													{
														name : 'statusCode',
														index : 'statusCode',
														width : 65,
														sortable : false,
														editoptions : {
															value : "1:Pending;2:Approved;3:Rejected"
														},
														stype : 'select',
														searchoptions : {
															sopt : [ 'eq', 'ne' ]
														}
													},
													{
														name : 'action',
														index : 'action',
														width : 65,
														sortable : false,
														search : false,
														formatter : memoLink
													},
													{
														name : 'exrate',
														index : 'exrate',
														width : 65,
														sortable : false,
														search : false,
														editable : true,
														editrules : {
															number : true
														}
													},
													{
														name : 'accType',
														index : 'accType',
														width : 65,
														sortable : false,
														search : false,
														editable : true,
														editrules : {
															number : true
														}
													},
													{
														name : 'memo',
														index : 'memo',
														width : 65,
														sortable : false,
														search : false,
														formatter : orderFrom
													},
													{
														name : 'Edit',
														index : 'Edit',
														width : 65,
														sortable : false,
														search : false,
														formatter : editMemoDetails
													}, {
														name : 'partyAccId',
														index : 'partyAccId',
														hidden : true
													}, {
														name : 'country',
														index : 'country',
														hidden : true
													}, {
														name : 'city',
														index : 'city',
														hidden : true
													},{
														name:'discount',
														index:'discount',
														editable : false
													} ],
											postData : {
												selfPartyAccId : $(
														'#selfPartyAccId')
														.val(),
												memoType : $('#memoType').val(),
												memoStatus : $('#memoStatus')
														.val(),
												accType : $('#LC/EX').val()
											},
											rowNum : 50,
											rowList : [ 50, 75, 100 ],
											pager : '#pmemoList',
											sortname : 'om.id',
											sortorder : "desc",
											width : "900",
											height : "250",
											caption : "Memo List",
											multiselect : "true",
											cellEdit : true,
											cellsubmit : 'remote',
											cellurl : 'updateMemoExRate.html',
											editurl : 'memoEdit.html',
											subGrid : true,
											afterInsertRow : function(rowid,
													aData, rowData) {
												if (rowData.statusCode == 'Pending'
														|| rowData.orderType == 'CI') {
													$('#jqg_memoList_' + rowid)
															.attr("disabled",
																	true);
												}
											},
											subGridRowExpanded : function(
													subgrid_id, row_id) {
												// we pass two parameters 
												// subgrid_id is a id of the div tag created whitin a table data 
												// the id of this elemenet is a combination of the "sg_" + id of the row 
												// the row_id is the id of the row 
												// If we wan to pass additinal parameters to the url we can use 
												// a method getRowData(row_id) - which returns associative array in type name-value 
												// here we can easy construct the flowing
												var subgrid_table_id, pager_id;
												subgrid_table_id = subgrid_id
														+ "_t";
												pager_id = "p_"
														+ subgrid_table_id;
												$("#" + subgrid_id)
														.html(
																"<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
												jQuery("#" + subgrid_table_id)
														.jqGrid(
																{
																	url : "memoSubGrid.html?q=2&id="
																			+ row_id,
																	datatype : "json",
																	colNames : [
																			'pktCode',
																			'cts',
																			'sh',
																			'pu',
																			'c',
																			'rate',
																			'Total',
																			'Status' ],
																	colModel : [
																			{
																				name : "pktCode",
																				index : "pktCode",
																				width : 80,
																				key : true,
																				sortable : false
																			},
																			{
																				name : "cts",
																				index : "cts",
																				width : 130,
																				sortable : false
																			},
																			{
																				name : "sh",
																				index : "sh",
																				width : 70,
																				align : "right",
																				sortable : false
																			},
																			{
																				name : "pu",
																				index : "pu",
																				width : 70,
																				align : "right",
																				sortable : false
																			},
																			{
																				name : "c",
																				index : "c",
																				width : 70,
																				align : "right",
																				sortable : false
																			},
																			{
																				name : "rate",
																				index : "rate",
																				width : 70,
																				align : "right",
																				sortable : false
																			},
																			{
																				name : "totrate",
																				index : "totrate",
																				width : 70,
																				align : "right",
																				sortable : false,
																				formatter : getTotal
																			},
																			{
																				name : "status",
																				index : "status",
																				width : 130,
																				sortable : false,
																				formatter : getPkStatus
																			} ],
																	sortname : 'cts',
																	sortorder : "desc",
																	height : '100%',
																	jsonReader : {
																		root : "results",
																		page : "pageNo",
																		total : "pageAmount",
																		records : "recordSize",
																		repeatitems : false,
																		id : "0"
																	}
																});
												jQuery("#" + subgrid_table_id)
														.jqGrid(
																'navGrid',
																"#" + pager_id,
																{
																	edit : false,
																	add : false,
																	del : false
																})
											},
											subGridRowColapsed : function(
													subgrid_id, row_id) {
												// this function is called before removing the data
												//var subgrid_table_id; 
												//subgrid_table_id = subgrid_id+"_t"; 
												//jQuery("#"+subgrid_table_id).remove(); 
											},
											jsonReader : {
												root : "results",
												page : "pageNo",
												total : "pageAmount",
												records : "recordSize",
												repeatitems : false,
												id : "0"
											},
											prmNames : {
												sort : "srtIndex",
												order : "srtType"
											}
										});
						jQuery("#memoList").jqGrid('navGrid', '#pmemoList', {
							edit : false,
							add : false,
							del : false
						}, {}, {}, {}, {
							multipleSearch : true
						});

						$("#invoiceDetails").hide();
						$("#invoiceDetailsLC").hide();
						$('#ajax').hide();

						$("#fromDate").DatePicker({
							format : 'd-m-Y',
							calendars : 1,
							date : $('#fromDate').val(),
							onChange : function(formated, dates) {
								$('#fromDate').val(formated);
								$('#fromDate').DatePickerHide();
							}
						});
						$("#toDate").DatePicker({
							format : 'd-m-Y',
							current : '${CURR_DATE}',
							calendars : 1,
							date : '${CURR_DATE}',
							onChange : function(formated, dates) {
								$('#toDate').val(formated);
								$('#toDate').DatePickerHide();
							}
						});
						$("#Go").click(function() {
							//$('#memoList').setGridParam({url:'memoReportGrid.html?q=1<c:if test="${param.web == 1}">&web=1</c:if>', datatype:'json' , postData:{toDate :$('#toDate').val(), fromDate:$('#fromDate').val(), companyName:$('#companyName').val()}}).trigger("reloadGrid");
							fnFilter();
						});

						$("#dueDate")
								.click(
										function() {
											$('#overDue').val(1);
											$('#exRate').val(0);
											//$(this).css('background-color', '#CCCCCC');
											$('#memoList')
													.setGridParam(
															{
																url : 'memoReportGrid.html?q=1<c:if test="${param.web == 1}">&web=1</c:if>',
																datatype : 'json',
																postData : $(
																		'#gridPanel')
																		.serialize()
															}).trigger(
															"reloadGrid");
										});
						$("#exRateLink")
								.click(
										function() {
											$('#exRate').val(1);
											$('#overDue').val(0);
											$('#memoList')
													.setGridParam(
															{
																url : 'memoReportGrid.html?q=1<c:if test="${param.web == 1}">&web=1</c:if>',
																datatype : 'json',
																postData : $(
																		'#gridPanel')
																		.serialize()
															}).trigger(
															"reloadGrid");
										});

						$("#PacketReturn").click(
								function() {
									var s = jQuery("#memoList").getGridParam(
											'selarrrow');
									var m = $('#memoType').val();
									if (s.length == 0) {
										alert('Please select packets');
										return;
									}
									$.ajax({
										type : 'POST',
										url : 'memoListReturn.html',
										dataType : 'json',
										data : {
											selectedPkts : s,
											memotype : m
										},
										success : function(json) {
											jsonSuccess(json);
											$("#memoList")
													.trigger("reloadGrid");
										},
										error : function(xmlHttpRequest,
												textStatus, errorThrown) {
											errorFunc();
										}
									})
								});
						$("#Lab_Upload").click(function() {
							if ($('#memoType').val() == 'CI')
								$.fn.colorbox({
									width : "95%",
									height : "90%",
									iframe : true,
									href : "stockCertUpload.html"
								});
						});
						$("#Return_With_Lab").click(function() {
							if ($('#memoType').val() == 'CI')
								$.fn.colorbox({
									width : "95%",
									height : "90%",
									iframe : true,
									href : "PacketCert.html"
								});
						});
						$("#Return_Without_Lab").click(function() {
							if ($('#memoType').val() == 'CI')
								$.fn.colorbox({
									width : "95%",
									height : "90%",
									iframe : true,
									href : "PacketCertAllLab.html"
								});
						});

						//$('.iframe').colorbox({iframe:true, width:"80%", height:"90%"});
						$('#CertOptions').hide();
					});
	function callBack() {
		alert('ok');
	}
	//$("#selfPartyStock").hide();
</script>

</head>
<body>
	<c:if test="${param.web == 1}">
		<c:set var="subPage" value="webMemo" scope="page" />
	</c:if>

	<c:if test="${param.web != 1}">
		<c:set var="subPage" value="memoReport" scope="page" />
	</c:if>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="sales" />
		<jsp:param name="subPage" value="${pageScope.subPage}" />
	</jsp:include>
	<div class="container">
		<div class="content">
			<div id="ajax">
				<img src="images/ajax-loader.gif" alt="Loading..." />
			</div>
			<div id="errorMsg"></div>
			<form action="memoReport.html" id="gridPanel">
				<span id="selfPartyStock"> 
				 <label for="selfPartyAccId" class="ttl_names" id="stockAt">Stock At</label>
				 <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 1}"> 
					 <select id="selfPartyAccId" name="selfPartyAccId" style="width: 220px;" onchange="fnFilter()">
							<option value="">Select</option>
							<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
								<option value="${s.id}"
									<c:if test="${USER_SESSION.partyAccId == s.id}">selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
							</c:forEach>
					</select>
				 </c:if>
				  <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 0}"> 
                     <select id="selfPartyAccId" name="selfPartyAccId" style="width: 220px;" onchange="fnFilter()">
                            <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
                                 <c:if test="${USER_SESSION.partyAccId == s.id}" >
                                    <option value="${s.id}" selected>${s.companyName}/${s.branchCode}/${s.termCode}</option>
                                </c:if>
                            </c:forEach>
                    </select>
                 </c:if> 
				<label for="memoStatus" class="ttl_names" id="memoStatusLabel">Memo Status</label> 
				<select id="memoStatus" name="memoStatus" style="width: 100px;" onchange="fnFilter()">
						<c:forEach items="${MEMOSTATUS_LIST}" var="s" varStatus="cnt">
							<option value="${s.id}">${s.description}</option>
						</c:forEach>
				</select> 
				<label for="memoType" class="ttl_names" id="memoTypeLabel">Memo Type</label> 
				<select id="memoType" name="memoType" style="width: 130px;" onchange="fnFilter()">
						<c:forEach items="${MEMOTYPE_LIST}" var="s" varStatus="cnt">
							<option value="${s.id}"
								<c:if test="${'Confirm' == s.id}"> selected</c:if>>${s.description}</option>
						</c:forEach>
				</select> 
				<label for="LC/EX" class="ttl_names" id="LC/EX">LC/EX</label> 
				<select id="LC/EX" name="LC/EX" style="width: 65px;" onchange="fnFilter()">
						<option value="">Both</option>
						<option value="L">Local</option>
						<option value="E">Export</option>
				</select>
				    <div>
						<label class="ttl_names">From Date:</label> 
						<input type="text" name="fromDate" id="fromDate" size="10" /> 
						<label class="ttl_names">To Date:</label> 
						<input type="text" name="toDate" id="toDate" value="${CURR_DATE}" size="10" /> 
						<label class="ttl_names">Search By Party Name: </label> 
						<input type="text" name="companyName" id="companyName" value="" maxlength="50" size="20" onkeydown="if (event.keyCode == 13) document.getElementById('Go').click()" />
						<label class="ttl_names">Memo No:</label>
						<input type="text" name="memoNo" id="memoNo" size="10" /> 
						<input type="button" name="Go" id="Go" value="Go" />
					</div> 
					</span><br /> 
					<a href="javascript:void(0);" id="dueDate">Show Overdues</a> 
					<input type="hidden" name="overDue" id="overDue" value="0" />/ 
					<a href="javascript:void(0);" id="exRateLink">Zero Exrate</a> 
					<input type="hidden" name="exRate" id="exRate" value="0" />
			</form>
			<table id="memoList"></table>
			<div id="pmemoList"></div>				
			<div id="invoiceDetailsLC">	
				<form action="memoToInvoice.html" id="invoiceData2">
			    <div >
			    <div id="title"> Invoice Details</div>
					    <input type="hidden" name="memoId" id="memoId" class="memoId" value=""/>
					     <input type="hidden" name="invId" id="invId" value=""/>
					     <input type="hidden" name="accType" id="accType" value=""/>			     
						<label for="consigneeId" class="ttl_names">Party </label>
				 		<span id="partyNameLC"></span>	
						<br/>				
						<label for="PAN" class="PAN">PAN</label>
						<input type="text" name="PAN" id="PAN" value=""/>
						<br/>
						<label for="vatTIn" class="ttl_names">VAT TIN NO.</label>
						<input type="text" name="cstVat" id="cstVat" value="VAT TIN NO. "/>
						<br/>
						<label for="othRefNo2" class="ttl_names">Other Ref. No.</label>
						<input type="text" name="othRefNo2" id="othRefNo2" value=""/>
					</div>			
				<br/>
				<input type="button" name="Make Invoice" onclick="processInvoice();" value="Process"/>
				</form>	
			</div>
			<c:if test="${USER_SESSION.userActivityMap['Invoice/Memo Return'] == 1 }">
				<div id="PacketReturn">
					<input type="button" value="Return"/>
				</div>
			</c:if>
			<div id="CertOptions">
				<input type="radio" name="Certification" value="Lab_Upload" id="Lab_Upload"/><b>Lab Upload</b><br />
				<input type="radio" name="Certification" value="Return_With_Lab" id="Return_With_Lab" /><b> Return WITH Lab</b><br />
				<input type="radio" name="Certification" value="Return_Without_Lab" id="Return_Without_Lab"/><b> Return WITHOUT Lab</b><br />
			</div>
	       <br/><br/>
	Note : You can reprint Approved Memo by clicking on memo number.	
	<div id="invoiceDetails">
	<form action="memoToInvoice.html" id="invoiceData">
		<div>
			<div id="title">Invoice Details</div>
			<div id="leftDiv">
				<input type="hidden" name="memoId" id="memoId" class="memoId" value="" /> 
				<input type="hidden" name="invId" id="invId" value="" /> 
				<label for="consigneeId" class="ttl_names">Consignee (If other than Buyer)</label> 
				<select id="consigneeId" name="consigneeId" style="width: 130px;">
					<option value="">Select</option>
					<c:forEach items="${PARTY_LIST}" var="s" varStatus="cnt">
						<option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
					</c:forEach>
				</select> <br /> 
				<label for="consigneeName" class="ttl_names">Specify if other</label>
				<textarea name="consigneeName" id="consigneeName" rows="1" cols="19"></textarea> <br />
				<label for="expRefNo" class="ttl_names">Exporters Ref.</label> <input type="text" name="expRefNo" id="expRefNo" value="" /><br /> 
				<label for="othRefNo" class="ttl_names">Other Ref. No.</label> <input type="text" name="othRefNo" id="othRefNo" value="" /><br /> 
				<label for="destination" class="ttl_names">Destination</label>
				<input type="text" name="destination" id="destination" value="" />
			</div>
			<div id="rightDiv">
				<label for="preCarrierPartyId" class="ttl_names">Carrier</label>
				<select id="preCarrierPartyId" name="preCarrierPartyId" style="width: 130px;">			
				    <option value="">Select</option>
					<c:forEach items="${CARRIER_LIST}" var="s" varStatus="cnt">
						<option value="${s.id}">${s.description}</option>
					</c:forEach>
				</select> <br /> 
				<label for="placeOfPreCarrier" class="ttl_names">Place of receipt by Pre-Carrier</label> 
				<input type="text" name="placeOfPreCarrier" id="placeOfPreCarrier" value="N.A." /><br /> 
				<label for="vesselFlight" class="ttl_names">Vessel Flight No</label> 
				<input type="text" name="vesselFlight" id="vesselFlight" value="By Air" /> <br /> 
				<label for="portOfLoad" class="ttl_names">Port Of Loading</label> 
				<input type="text" name="portOfLoad" id="portOfLoad" value="Mumbai" /><br /> 
				<label for="portOfDischarge" class="ttl_names">Port Of Discharge</label> 
				<input type="text" name="portOfDischarge" id="portOfDischarge" value="" /> <br /> 
				<span>&nbsp; </span> <br />
				<span>&nbsp; </span>
			</div>
		</div>
		<br /> <input type="button" name="Make Invoice" onclick="processInvoice();" value="Process" />
	</form>
		</div>
		<div id="memoEditForm" style="display: none;">
			<form action="memoEdit.html" id="invoiceData">
				<div id="title">Memo Details</div>
				<label for="editMemoId"> Memo No.</label> 
				<input type="text" id="editMemoId" name="editMemoId" value="" readonly="readonly">
				<label for="brokerId"> Broker</label> 
				<select id="brokerId" name="brokerId" style="width: 110px;">
					<option value="-1">Select</option>
					<c:forEach items="${BROKER_LIST}" var="s" varStatus="cnt">
						<option value="${s.id}">${s.description}</option>
					</c:forEach>
				</select> 
				<label for="brokrage">Brokerage </label> 
				<input type="text" id="brokrage" name="brokrage" value="" onchange="validateBrok();">
				<input type="button" name="editSave" onclick="editSaveMemo();" value="Save" />
			</form>
		</div>
	</div>
</div>
</body>
</html>