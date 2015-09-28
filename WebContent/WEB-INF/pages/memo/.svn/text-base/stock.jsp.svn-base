<%@include file="/WEB-INF/pages/include/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
var brokerData;
$(document).ready(function(){
	<c:forEach items="${PRP_LIST}" var="pl">
		<c:if test="${pl.fieldDisplayType== 'MULSEL'}">
				<c:if test="${pl.prp == 'C'}">
					$("#${pl.prp}").dropdownchecklist({icon: {}, firstItemChecksAll: true, maxDropHeight: 300, width:60 });  
				</c:if>
				<c:if test="${pl.prp != 'C'}">
					$("#${pl.prp}").dropdownchecklist({icon: {}, firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
				</c:if>
		</c:if>
	</c:forEach>
	
	/* partyData = [
	<c:forEach items="${PARTY_LIST}" var="p" varStatus="count">
		{ id: "${p.id}", description: "${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}", accType : "${p.accType}", termId : "${p.termId}", brokerPartyId: "${p.brokerPartyId}"}
		<c:if test="${!count.last}">,</c:if>
	</c:forEach>
	]*/
	brokerData = [
   	<c:forEach items="${BROKER_LIST}" var="b" varStatus="count">
   		{ id: "${b.id}", description: "${b.description}" }
   		<c:if test="${!count.last}">,</c:if>
   	</c:forEach>
   	] 
   	<c:forEach items="${SELF_LIST}" var="p" varStatus="count">
   	selfParty["${p.id}"] = "${p.branchCode}";
   	</c:forEach>
	
	var link1 = '${url}';
	var col_names1 = [${headers}];
	var col_models1 =  [${colModel}];
	
	createStockGrid = function() {createGrid(link1, col_names1, col_models1);};
	
	var col_names = [${headersMemo}];
	var col_models =  [${colModelMemo}];

	createMemoGrid = function() {createGrid(link1, col_names, col_models);};

	createStockGrid();
	$('#btnSearch').click(submitSearch);
	$('#memoReturn').click(memoReturn);
	$('#createMemo').click(submitOrder);
	$('#trfStock').click(submitOrder);//changed for generating memo in trasnfer
	$('#packetReturn').click(memoReturn);
	$('#exportXl').click(makeExcel);
	$('#exportXl2').click(makeExcel);
	
	$('#btnAdd').click(function(row, i, max) {
		submitSearch(this);
	});
	
	$('#rootPktSrch').attr("checked",false);
	
	autoCompleteByr();
	autoCompleteBkr(brokerData);
	
	/* $("#brokerName").autocomplete(brokerData,
			{
			minChars: 0,
			autoFill: true,
			matchContains: false,
			selectFirst: true,
			max: brokerData.length,
			formatItem: function(row, i, max, searchTerm) {	
				return row.description;
			},
			formatMatch: function(row, i, max) {
				return row.description;
			},
			formatResult: function(row, i, max) {
				return row.description;
			}
	}); */
	
	
	$("#email").autocomplete('partyEmailListAC.html',
			{
            dataType: "json",
			minChars: 2,
			autoFill: true,
			matchContains: false,
			multiple: true, 
			parse: function(json) {	
				var parsed = []; 
				if(json!=null && json.length != 0){
					 $(json).each(function() {
						 parsed[parsed.length] = {
						 data: this,
						 value: this,
						 result: this
					 	};
					 });
				}
			   return parsed; 
			},
			formatItem: function(row, i, max, searchTerm) {	
				return row;
			},
			formatMatch: function(row, i, max) {
				return row;
			},
			formatResult: function(row, i, max) {
				return row;
			}	
	});
	$("#email").result(function(event, data, formatted) {
		var hidden = $(this).parent().next().find(">:input");
		hidden.val( (hidden.val() ? hidden.val() + ";" : hidden.val()) + data[1]);
	});
	$( "#memoDate" ).DatePicker({
		format:'d-m-Y',
		current: '${CURR_DATE}',
		calendars: 1,
		date: '${CURR_DATE}',
		onChange: function(formated, dates){
			$('#memoDate').val(formated);
			$('#memoDate').DatePickerHide();
		}
	});
	$( "#memoDate" ).val('${CURR_DATE}');
	$( "#dueDate" ).DatePicker({
		format:'d-m-Y',
		calendars: 1,
		date: $('#dueDate').val(),
		onChange: function(formated, dates){
			$('#dueDate').val(formated);
			$('#dueDate').DatePickerHide();
		}
	});
	
	 	$('#trfStock').hide();
	    $('#FNC_row').hide();
		$('#FNCI_row').hide();
		$('#FNCO_row').hide();
		
	$(".trigger").click(function(){
		$(".panelBox").toggle("fast");
		$(this).toggleClass("active");
		return false;
		});
	$(".triggerRight").click(function(){
		$(".panelRight").toggle("fast");
		$(this).toggleClass("active");
		return false;
		});
	
	$('#flat').click(function(){
		if($('#flatFile').val()==1){
			$('#flatFormat').hide();
			$('#flatFile').val(-1);
		}
		else{
			$('#flatFormat').show();
			$('#flatFile').val(1);
		}
	});
	$('#flatFormat').hide();
	
});


function submitSearch(frm){
	 
	if($('#rootPktSrch').attr('checked') != true && $('#memoNos').val()!=''){
		$("#memoResult").jqGrid('GridUnload');
		createMemoGrid();
	}else{
		$("#memoResult").jqGrid('GridUnload');
		createStockGrid();
	}
	if(frm.id == 'btnAdd'){
		$('#addCriteria').val(1);
	}else{
		$('#addCriteria').val(0);
	}
	
	if($('#term').val() == ''){
		alert('Select Term to populate Rates');
		return;
 	}
//	if($('#accType_L').attr("checked") && ($('#exRate').val()=='' || isNaN($('#exRate').val()))){
//		alert('Enter Exchange Rate for local types');
//		return;
//}
	var frmId = frm.id;
	if(frm.id == 'btnSearch2'){
		if($('#pktNos').val() != '' )
			frmId = 'pktNos';
		else if($('#memoNos').val() != '')
			frmId = 'memoNos';
	}
	if(!frmId){
		$('#pktNos').val('');
		$('#memoNos').val('');
	}
    $('#memoResult').setGridParam({url:'memoLoadGrid.html?fromSrch='+frmId+'&'+$('#searchForm').serialize(), datatype:'json' ,postData:{ selectedPkts : null}}).trigger("reloadGrid");

    if($('#partyStock').val() != '${USER_SESSION.partyAccId}'){
        $('#actions').hide();
        }else{
        $('#actions').show();
        }
    }

function checkDiscount(value){
	if(value>10 || value< -10){
		alert("Please Insert the value between -10 to 10 only");
		$('#discount').val("");
	}
}
</script>
</head>
<body>
	<c:remove var="ses_criteria" />
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="sales" />
		<jsp:param name="subPage" value="memo" />
	</jsp:include>
	<div class="container">
		<form id="searchForm" name="searchForm">
			<div class="content">
				<table align="center" width="95%">
					<tr>
						<td class="ttl_names">Buyer :</td>
						<td><input type="text" name="buyerName" id="buyerName" size=20 /> <input type="hidden" name="partyAccId" id="partyAccId" />
							<a id="" href="javascript:void(0);" onclick="addTab('New Party','partyActionController.html?typ=BYR');">Add</a>
							<br /> <a href="javascript:void(0);" onclick="partyRep();">Report</a>&nbsp;&nbsp;<a
							href="javascript:void(0);" onclick="deposit();">Deposit</a></td>

						<td class="ttl_names">Memo Date:</td>
						<td><input type="text" name="memoDate" id="memoDate"
							size="10" /></td>

						<td class="ttl_names">Term :</td>
						<td><select name="term" id="term"
							onchange="submitSearch(document.getElementById('btnSearch2'));">
								<option value="-1">Asking Price</option>
								<c:forEach var="t" items='${TERMS_LIST}' varStatus="cnt">
									<c:if test="${t.id != 0}">
										<option value='${t.id}'>${t.description}</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td class="ttl_names">Add Discount:</td>
                        <td><input type="text" name="discount" id="discount" size=4 onchange="checkDiscount(this.value);" />
                        <input type="hidden" name="cashDisc" id="cashDisc" value="-5" /></td>
					</tr>
					<tr>
						<td class="ttl_names">Broker :</td>
						<td><input type="text" name="brokerName" id="brokerName" size="16" /> 
						<a href="javascript:void(0);" onclick="addTab('Add Broker','partyActionController.html?typ=bkr');">Add</a> <span class="ttl_names">Brokrage :</span>
						<input type="text" name="brokerage" id="brokerage" size=3 /> 
						<input type="hidden" name="brokerId" id="brokerId" /></td>
						<td class="ttl_names">Due Date:</td>
						<td><input type="text" name="dueDate" id="dueDate" size="10" />Days
							<input type="text" name="days" id="days" onchange="calDate(this,'dueDate');" size="3" />
						</td>
						<td class="ttl_names">EX. Rate :</td>
						<td><input type="text" name="exRate" id="exRate" size=10 /></td>
						<td class="ttl_names" colspan="2">
							<label for="accType_L">Local : </label>
							<input type="radio" name="accType" id="accType_L" value="L" checked="checked" />
							<label for="accType_E">Export : </label> 
							<input type="radio" name="accType" id="accType_E" value="E" />
						</td>
					</tr>
				</table>
				<div align="center">
					<div id="errorMsg"></div>

					<!--
			<select name="status" id="status" class="select" multiple="multiple" >
					<option value="-1">All</option>
					<c:forEach var="s" items='${STATUS}'>
						<option value='${s.id}'>${s.description}</option>
					</c:forEach>
			</select>
		-->
			<table id="memoResult" ></table> 
			<div id="pmemoResult"></div>
			</div>	
		    <div class="panelRight">
					<div id="actions">
						<c:if test="${USER_SESSION.userActivityMap['memo'] == 1 }">
							<div>
								<label for="orderType"> Select Type</label> <select
									name="orderType" id="orderType" onchange="changeOrderType();"
									style="width: 120px;">
									<option value="MI" selected="selected">Internal View</option>
									<option value="SL">Confirm</option>
									<option value="LI">Laser Insciption</option>
									<option value="CI">Cerification</option>
									<option value="MA">Transfer</option>
								</select>
							</div>
							<div id="vendorParty" style="display: none;">
								<label for="vendor"> Send to Vendor</label> <select id="vendor"
									name="vendor" style="width: 120px;">
									<option value="">Select</option>
									<c:forEach items="${VENDOR_LIST}" var="v" varStatus="cnt">
										<option value="${v.id}">${v.description}</option>
									</c:forEach>
								</select>
							</div>
							<div id="SentToLab" style="display: none;">
								<label for="lab"> Send to Lab</label> <select id="lab"
									name="lab" style="width: 120px;">
									<option value="">Select</option>
									<c:forEach items="${PRP_LOV['LAB']}" var="v" varStatus="cnt">
										<option value="${v.description}">${v.description}</option>
									</c:forEach>
								</select>
							</div>
							<div id="selfParty" style="display: none;">
								<label for="self"> send to Self Party</label> <select id="self"
									name="self" style="width: 120px;">
									<option value="">Select</option>
									<c:forEach items="${SELF_LIST}" var="v" varStatus="cnt">
										<c:if test="${v.id != USER_SESSION.partyAccId}">
											<option value="${v.id}">${v.companyName}/${v.branchCode}/${v.termCode}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<div>
								<label for="contactPerson" class="alignTop">Contact
									Person :</label> <input name="contactPerson" id="contactPerson"
									class="inputText1">
							</div>
							<div>
								<label for="comments" class="alignTop">Comments : </label>
								<textarea cols="15" name="comments" id="comments"
									class="inputText1" rows="1"></textarea>
							</div>
							<div>
								<input type="button" value="Create Memo" name="createMemo"
									id="createMemo">
							</div>
							<div>
								<input type="button" value="Transfer Stock" name="trfStock"
									id="trfStock">
							</div>
							<hr />
							<div>
								<input type="button" name="packetReturn" id="packetReturn"
									value="Packet Return" />
							</div>
						</c:if>
					</div>
					<hr />
					<div>
						<label for="exportFormat"> Export Format</label> <select
							id="exportformat" name="exportformat">
							<c:forEach items="${EXP_FILE_LIST}" var="e" varStatus="cnt">
								<option value="${e.id}"
									<c:if test="${cnt.index == 0}">selected</c:if>>${e.fileName}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<textarea id="email" name="email" class="inputText1" rows="3"
							cols="15"></textarea>
					</div>
					<div>
						<input type="button" value="export Xls" name="exportXl"
							id="exportXl"> <input type="button" name="actionHelp"
							id="actionHelp" value="Help" />
					</div>
					<div>
						<a href="javascript:void(0);" id="flat">Other Format</a>
						<div id="flatFormat">
							<input type="hidden" value="-1" name="flatFile" id="flatFile">
							<label for="flatFileId"> Export Format</label> <select
								id="flatFileId" name="flatFileId">
								<c:forEach items="${FILE_LIST}" var="e" varStatus="cnt">
									<option value="${e.id}"
										<c:if test="${cnt.index == 0}">selected</c:if>>${e.fileName}</option>
								</c:forEach>
							</select> <input type="button" value="export Xls" name="exportXl2"
								id="exportXl2">
						</div>
					</div>
				</div>
				<a class="triggerRight" href="#">Action</a>


			</div>
			<div class="panelBox" id="leftFilter">
				<table>
					<tr>
						<td><label for="partyStock" class="ttl_names">Party</label>
						</td>
						<td><select id="partyStock" name="partyStock"
							style="width: 110px;">
								<option value="">Select</option>
								<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
									<option value="${s.id}"
										<c:if test="${USER_SESSION.partyAccId == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><span class="ttl_names" id="memoSpan">Memo Nos.</span></td>
						<td><input type="text" id="memoNos" name="memoNos"
							onkeydown="if (event.keyCode == 13)submitSearch(this);"
							onfocus="$('#pktNos').val('');" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td><label for="rootPktSrch" class="ttl_names">Rough(Root)</label>
						</td>
						<td><input type="checkbox" name="rootPktSrch"
							id="rootPktSrch" value="1" onchange="searchoption(this);" "/>
						</td>
					</tr>
					<tr>
						<td><span class="ttl_names">Pkt Nos.</span>
						</td>
						<td><input type="text" id="pktNos" name="pktNos"
							onkeydown="if (event.keyCode == 13)submitSearch(this);"
							onfocus="$('#memoNos').val('');" />
						</td>
					</tr>
					<tr>
						<td><input type="button" value="Search" id="btnSearch2"
							onclick="submitSearch(this);">
						</td>
					</tr>

				</table>
				<hr />
				<%--<input type="button" name="memoReturn" id="memoReturn" value="Memo Return"/>  --%>

				<table width="100%">
					<c:forEach items="${PRP_LIST}" var="pl" varStatus="status">
						<c:choose>
							<c:when test="${pl.fieldDisplayType== 'MULSEL'}">
								<div class="row" id="${pl.prp}_row">
									<label for="${pl.prp}" title="${pl.prpDesc}">${pl.prpDesc}</label>
									<div class="element" style="width: 145px;">
										<select name="${pl.prp}" id="${pl.prp}" class="select"
											multiple="multiple">
											<option value="-1">All</option>
											<c:forEach var="prpLov" items='${PRP_LOV[pl.prp]}'>
												<option value='${prpLov.id}'>${prpLov.description}</option>
											</c:forEach>
										</select>
										<c:if test="${pl.prp == 'C'}">
											<span class="ttl_names"> FNC</span>
											<input type="checkbox" name="fancy" id="fancy"
												onclick="chFncy(this);" value="1" />
										</c:if>
									</div>
								</div>
							</c:when>
							<c:when test="${pl.fieldDisplayType == 'RNGSEL'}">
								<div class="row" id="${pl.prp}_row">
									<label for="${pl.prp}" title="${pl.prpDesc}">${pl.prpDesc}</label>
									<div class="element" style="width: 145px;">
										<select name="${pl.prp}_from" id="${pl.prp}_from"
											class="select">
											<option value="-1">All</option>
											<c:forEach var="prpLov" items='${PRP_LOV[pl.prp]}'>
												<option value='${prpLov.id}'>${prpLov.description}</option>
											</c:forEach>
										</select> <select name="${pl.prp}_to" id="${pl.prp}_to" class="select">
											<option value="-1">---All---</option>
											<c:forEach var="prpLov" items='${PRP_LOV[pl.prp]}'>
												<option value='${prpLov.id}'>${prpLov.description}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</c:when>
							<c:when test="${pl.fieldDisplayType == 'INPUT'}">
								<div class="row" id="${pl.prp}_row">
									<label for="${pl.prp}" title="${pl.prpDesc}">${pl.prpDesc}</label>
									<div class="element" style="width: 145px;">
										<input type="text" name='${pl.prp}_from' id='${pl.prp}_from'
											class="inputText"
											onblur="javascript:chkNumVal(this,this.value,'${pl.minValue}','${pl.maxValue}');" />
										<input type="text" name='${pl.prp}_to' id='${pl.prp}_to'
											class="inputText"
											onblur="javascript:chkNumVal(this,this.value,'${pl.minValue}','${pl.maxValue}');" />
									</div>
								</div>
							</c:when>
						</c:choose>
						<%-- <c:if test="${status.count%2 == 0}"></td></tr></c:if>
			<c:if test="${status.count%2 != 0}"></td></c:if> --%>
					</c:forEach>
				</table>
				<label for="srchPair" style="white-space: nowrap;">Only
					Pairs</label><input type="checkbox" name="srchPair" id="srchPair" value="1">
				<input type="button" value="reset" id="reset"
					onclick="javascript:clearForm('#leftFilter');"><br /> <input
					type="hidden" value="" id="addCriteria" name="addCriteria" /> <input
					type="button" value="Search" id="btnSearch"> <input
					type="button" value="Add Criteria" id="btnAdd">
			</div>
			<a class="trigger" href="#">Search</a>
		</form>
		<div class="ttl_names">Note: Values shown here will be as per
			values uploaded by file/form in purchase.</div>

	</div>
</body>
</html>