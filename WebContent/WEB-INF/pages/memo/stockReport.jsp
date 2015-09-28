<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">$(document).ready(function(){
	jQuery("#stockReport").jqGrid({
			sortable: true, 
			 url: 'stockReportGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Pkt Id','Pkt Code', 'Shape', 'Cts', 'Color', 'Clarity',  'Disc %', 'Rate/cts ($)', 'Total', 'Base Rate (INR)','Status'],
			 colModel:[
						{name:'pktId', index:'sp.pktId', width:30},
						{name:'pktCode', index:'pktCode', width:30},
						{name:'sh', index:'sh', width:100 },
						{name:'cts', index:'cts', width:65 },
						{name:'c', index:'c', width:65},
						{name:'pu', index:'pu', width:65 },
						{name:'rap', index:'rap', width:65, editable:true},
						{name:'rate', index:'rate', width:65},
						{name:'totalRate', index:'totalRate', width:65},
						{name:'baseRate', index:'baseRate', width:65},
						{name:'statusCode', index:'statusCode', width:65}
			           ], 
			    cellEdit: true, 
				rowNum:100, 
				rowList:[100,200,300], 
				pager: '#pstockReport',
				sortname: 'sh',
				sortorder: "asc", 
				width: "900", 
				height: "550",
				caption:"Stock List" ,
				multiselect:"true",
				footerrow : true,
				userDataOnFooter : true,
				beforeEditCell: function(rowid, cellname, value, iRow, iCol) { 
					if(jQuery("#memoResult").jqGrid('getCell',rowid,'status') != 'AVLB'){
						editCell(iRow, iCol, false); 	
					}
				},
				cellsubmit : 'remote',
				cellurl: 'stockMemoPriceEditAJAX.html',
				  jsonReader:{
	  			    	 root: "results",
	  					  page: "pageNo",
	  					  total: "pageAmount" ,
	  					  records: "recordSize",
	  					  repeatitems: false,
	  					  id: "0"
					}, prmNames: { 
					    sort: "srtIndex", 
					    order: "srtType"
					}
		}); 
	jQuery("#stockReport").jqGrid('navGrid','#pstockReport',{edit:false,add:false,del:false,search:false});
	
	
	$("#status").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
	$("#sh").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
	$("#c").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
	$("#pu").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
	$('#submit').click(submitSearch);
	$('#bulkChange').click(priceChange);
	
	});	
	

function submitSearch(){
    $('#stockReport').setGridParam({url:'stockReportGrid.html?'+$('#searchForm').serialize(), datatype:'json' }).trigger("reloadGrid");
 }

function priceChange(){
	
	if($('#rapChange').val() ==''){
		alert('Please enter change in rap price');
		return;
	}
	if($('#rapChange').val() > 10 ||$('#rapChange').val() <-10){
		alert('Rap change is allowed to change upto max 10% +/-');
		return;
	}
	if($("#sh option:selected").val() == '' ||$("#cts_from").val() == ''||$("#cts_to").val() == ''
			||$("#c option:selected").val() == ''||$("#pu option:selected").val() == ''){
		alert('Select specifically all properties to change price manually');
	}
	
	var input = confirm('Please verify criteria \n Shape :'+ $("#shape .ui-dropdownchecklist-text").html() +'\n'+
			'Cts From : '+$("#cts_from").val() +' | To : '+$("#cts_to").val()+'\n'+
			'Color :'+ $("#color .ui-dropdownchecklist-text").html() +'\n'+
			'Clarity :'+ $("#clarity .ui-dropdownchecklist-text").html() +'\n'+
			'Change in Rap is : '+$('#rapChange').val()+'\n Are you sure you want to continue');
	if(!input){
		return;
	}
	$.ajax({type:'POST',
		url:'stockPriceBulkEditAJAX.html',
		dataType: 'json',
		data: $('#searchForm').serialize() ,
		success: function(json){
			if(json!=null && json !=""){
				errorMsgPr(json[1]);
				$("#stockReport").trigger("reloadGrid");
			}
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
			errorFunc('${USER_SESSION.userId}');
		}
	});
}

-->	
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<div class="container">
		<div class="content" align="center">
			<form id="searchForm" name="searchForm" action="" method="post">
				<span id="shape"> <label for="sh" class="ttl_names">Shape</label>
					<select multiple="multiple" id="sh" name="sh">
						<option value="-1">All</option>
						<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select> </span> <label for="cts" title="Carats">cts</label> <input type="text"
					name="cts_from" id="cts_from" class="inputText"
					onblur="javascript:chkNumVal(this,this.value,'0','50');" size="10" />
				<input type="text" name="cts_to" id="cts_to" class="inputText"
					onblur="javascript:chkNumVal(this,this.value,'0','50');" size="10" />

				<span id="color"> <label for="status" class="ttl_names">Color</label>
					<select multiple="multiple" id="c" name="c">
						<option value="-1">All</option>
						<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select> </span> <span id="clarity"> <label for="status" class="ttl_names">Clarity</label>
					<select multiple="multiple" id="pu" name="pu">
						<option value="-1">All</option>
						<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select> </span> <input type="text" name="rapChange" id="rapChange"
					class="inputText" size="10" /> <input type="button"
					name="bulkChange" id="bulkChange" value="Price Change" /> <br />
				<br /> <span id="selfPartyStock"> <label
					for="selfPartyAccId" class="ttl_names">Stock At</label> <select
					id="selfPartyAccId" name="selfPartyAccId" style="width: 130px;">
						<option value="">Select</option>
						<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
							<option value="${s.id}"
								<c:if test="${USER_SESSION.partyAccId == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
						</c:forEach>
				</select> </span> <span id="statusSpan"> <label for="status"
					class="ttl_names">Status</label> <select multiple="multiple"
					id="status" name="status">
						<option value="-1">All</option>
						<c:forEach items="${STATUS}" var="s" varStatus="cnt">
							<option value="${s.id}" <c:if test="${s.id ==1}"> selected</c:if>>${s.description}</option>
						</c:forEach>
				</select> </span> <input type="button" id="submit" value="Search" /> <br />
				<br />

			</form>

			<table id="stockReport"></table>
			<div id="pstockReport"></div>

		</div>
	</div>

</body>


</html>