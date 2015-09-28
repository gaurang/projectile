<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<!--

function makeInvoice(id){
	
	$('#ajax').show();
	$.ajax({type:'POST',
		url:'memoToInvoice.html',
		dataType: 'json',
		data: { id: id},
		success: function(json){
			if(json!=null && json !=""){
				$('#errorMsg').html(json[1]);
				if(!isNaN(json[2]) &&  parseInt(json[2]) > 0)
				 	window.open('invPrint.html?invId='+json[2],'_newtab');
			}else {
				alert(json);
			}
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
			if('${USER_SESSION.userId}' == null || '${USER_SESSION.userId}' == '' || '${USER_SESSION.userId}'  < 0){
	            alert('You have logged out of system kindly login again .system will take you to login section');
		   		window.location = 'indexCrm.html';
			}else{
				alert('Some error occured in system kindly contact system admin');
			}
		}
	});
	
	$('#ajax').hide();	
}
function fnFilter(){
	$('#Payment').setGridParam({url:'paymentReportGrid.html', datatype:'json' ,postData:{selfPartyAccId :$('#selfPartyAccId').val()}}).trigger("reloadGrid");
	   
}
function getTotal(cellvalue, options, rowObject){
	return	(parseFloat(rowObject.rate)*parseFloat(rowObject.cts)).toFixed(2);
}
function showPaymentType(cellvalue, options, rowObject){
	if(cellvalue == 1 )
		return	'Inv paid';
	else
		return 'Advance';
}
function datePicker(elem){
	$(elem).datepicker();
}
$(document).ready(function(){
	jQuery("#Payment").jqGrid({
			sortable: true, 
			 url: 'paymentReportGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Payment No.', 'Party/Vendor', 'Payment Date', 'Mode Of pay', 'Amount','Ex. Rate','Invoice Paid'],
			 colModel:[
						{name:'id', index:'p.id', width:30,searchoptions: { sopt: ['eq', 'in']}},
						{name:'companyName', index:'pm.companyName', width:100,searchoptions: { sopt: ['eq', 'cn','sw']} },
						{name:'paymentDate', index:'orderDate', width:65,search:false},
						{name:'mode', index:'mode', width:80, editoptions:{value:"CASH:Cash;CHQ:Cheque,ADJ:Adjustment"}, stype: 'select', searchoptions: { sopt: ['eq', 'ne']}},
						{name:'amount', index:'amount', width:100, searchoptions: { sopt: ['eq', 'lt','gt']}},
						{name:'exRate', index:'exRate', width:65,search:false },
						{name:'invId', index:'invId', width:65, formatter:showPaymentType, editoptions:{value:"1:Invoice Paid;0:Advance"}, stype: 'select', searchoptions: { sopt: ['eq', 'ne']}}
			           ], 
			    postData: { selfPartyAccId: $('#selfPartyAccId').val() },
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#pPayment',
				sortname: 'id',
				sortorder: "desc", 
				width: "800", 
				height: "250",
				caption:"Payment List" ,
				multiselect:"true",
				subGrid : true, 	
				subGridRowExpanded: function(subgrid_id, row_id) { 
					// we pass two parameters 
					// subgrid_id is a id of the div tag created whitin a table data 
					// the id of this elemenet is a combination of the "sg_" + id of the row 
					// the row_id is the id of the row 
					// If we wan to pass additinal parameters to the url we can use 
					// a method getRowData(row_id) - which returns associative array in type name-value 
					// here we can easy construct the flowing
					var subgrid_table_id, pager_id;
					subgrid_table_id = subgrid_id+"_t";
					pager_id = "p_"+subgrid_table_id;
					$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
					jQuery("#"+subgrid_table_id).jqGrid({
						url:"paymentSubGrid.html?q=2&id="+row_id, 
						datatype: "json",
						colNames: ['Invoice Id','Amount'],
						colModel: [ {name:"id",index:"id",width:80,key:true,sortable:false},
						            {name:"description",index:"description",width:130,sortable:false}
						            ],
						 sortname: 'cts',
						 sortorder: "asc", 
						 height: '100%' ,
						 jsonReader:{
		  			    	 root: "results",
		  					  page: "pageNo",
		  					  total: "pageAmount" ,
		  					  records: "recordSize",
		  					  repeatitems: false,
		  					  id: "0"
							}            
						            }); 
					jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false}) 
					}, 
					subGridRowColapsed: function(subgrid_id, row_id) { 
						// this function is called before removing the data
						//var subgrid_table_id; 
						//subgrid_table_id = subgrid_id+"_t"; 
						//jQuery("#"+subgrid_table_id).remove(); 
						} ,
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
						
			/**	grouping: true,
				groupingView : {
								groupField : ['companyName'],
								groupColumnShow : [true],
								groupText : ['<b>{0}</b>'],
								groupCollapse : false, 
								groupOrder: ['asc'], 
								groupSummary : [true], 
								showSummaryOnHide: true, 
								groupDataSorted : true }, 
				footerrow: true,
				userDataOnFooter: true **/
		}); 
	jQuery("#Payment").jqGrid('navGrid','#pPayment',{edit:false,add:false,del:false},{},{},{},{multipleSearch:true});
	//	$("#invoiceDetails").hide();
	$('#ajax').hide();
	});	
	
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
			<div id="ajax">
				<img src="images/ajax-loader.gif" alt="Loading..." />
			</div>
			<div id="errorMsg"></div>
			<span id="selfPartyStock"> <label for="selfPartyAccId"
				class="ttl_names">Stock At</label> <select id="selfPartyAccId"
				name="selfPartyAccId" style="width: 130px;">
					<option value="">Select</option>
					<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
						<option value="${s.id}"
							<c:if test="${USER_SESSION.partyAccId == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
					</c:forEach>
			</select> <input type="button" value="Go" onclick="fnFilter();" /> </span>
			<div id="searchPanel">
				<div class="row">
					<label for="status">Mode</label>
					<div class="element">
						<select name="status" id="status">
							<option value="CASH">CASH</option>
							<option value="CHQ">CHEQUE</option>
						</select>
					</div>
				</div>
				<div class="row">
					<label for="companyName">Company Name</label>
					<div class="element">
						<input type="text" name="companyName" id="companyName" size="10"
							maxlength="50" />
					</div>
				</div>
			</div>


			<table id="Payment"></table>
			<div id="pPayment"></div>
			<div id="invoiceDetails"></div>
		</div>
	</div>

</body>


</html>