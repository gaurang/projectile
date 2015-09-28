<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function exportLink(id){
	$("#invoiceDetails").show();
}
function submit(id){
	
}
function fnFilter(){
	$('#invList').setGridParam({url:'invReportGrid.html', datatype:'json' ,postData:{selfPartyAccId :$('#selfPartyAccId').val()}}).trigger("reloadGrid");
	   
}
function getTotal(cellvalue, options, rowObject){
	return	(parseFloat(rowObject.rate)*parseFloat(rowObject.cts)).toFixed(2);
}
function invLink(cellvalue, options, rowObject){
	if(rowObject.exportStatus == 'Pending')
		return	'<a href="javascript:void(0);" onclick="exportLink(\''+cellvalue+'\');">Export</a>';
	else
		return '';
}
$(document).ready(function(){
	jQuery("#invList").jqGrid({
			sortable: true, 
			 url: 'invReportGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Invoice No.', 'Party/Vendor', 'Broker', 'Invoice Date', 'Due Date',  'Inv Status', 'Inv Type', 'Amount','Paid','Tax','Expences','Shiping', 'Consignee', 'Con Code'],
			 colModel:[
						{name:'id', index:'im.id', width:30,searchoptions: { sopt: ['eq', 'in']}},
						{name:'companyName', index:'pm.companyName', width:100, searchoptions: { sopt: ['eq', 'cn','sw']} },
						{name:'brokerName', index:'brokerName', width:65,search:false },
						{name:'invoiceDate', index:'invoiceDate', width:65, searchoptions: { sopt: ['eq', 'lt','gt'], dataInit:function(el){
					          $(el).datepicker({dateFormat:'dd-mm-yy'});
					          jQuery('.ui-datepicker').css({'zIndex':'1200','font-size':'100%'});
					    	}}},
						{name:'dueDate', index:'dueDate', width:65,search:false },
						{name:'invStatus', index:'invStatus', width:65, stype: 'select',editoptions:{value:"PAID:Paid;UNPAID:Unpaid"}, searchoptions: { sopt: ['eq', 'ne']} },
						{name:'invType', index:'invType', width:65, sortable:false, stype: 'select',editoptions:{value:"L:Local;E:Export"}, searchoptions: { sopt: ['eq', 'ne']}},
						{name:'finalAmount', index:'finalAmount', width:65, sortable:false,search:false},
						{name:'paidAmt', index:'paidAmt', width:65, sortable:false,search:false},
						{name:'tax', index:'tax', width:65, sortable:false,search:false},
						{name:'expences', index:'expences', width:65, sortable:false,search:false},
						{name:'shipCharges', index:'shipCharges', width:65, sortable:false,search:false},
						{name:'consingeeName', index:'consingeeName', width:65, sortable:false,search:false},
						{name:'consignmentCode', index:'consignmentCode', width:65, sortable:false,search:false}
						
			           ], 
			    postData: { selfPartyAccId: $('#selfPartyAccId').val() },
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#pinvList',
				sortname: 'id',
				sortorder: "desc", 
				width: "800", 
				height: "250",
				caption:"Invoice List" ,
				multiselect:"true",
				subGrid : true, 	
				subGridRowExpanded: function(subgrid_id, row_id) { 
					var subgrid_table_id, pager_id;
					subgrid_table_id = subgrid_id+"_t";
					pager_id = "p_"+subgrid_table_id;
					$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
					jQuery("#"+subgrid_table_id).jqGrid({
						url:"invSubGrid.html?q=2&id="+row_id, 
						datatype: "json",
						colNames: ['pktCode','cts','sh','pu','c', 'rate','Total'],
						colModel: [ {name:"pktCode",index:"pktCode",width:80,key:true,sortable:false},
						            {name:"cts",index:"cts",width:130,sortable:false}, 
						            {name:"sh",index:"sh",width:70,align:"right",sortable:false}, 
						            {name:"pu",index:"pu",width:70,align:"right",sortable:false}, 
						            {name:"c",index:"c",width:70,align:"right",sortable:false},
						            {name:"rate",index:"rate",width:70,align:"right",sortable:false},
						            {name:"totrate",index:"totrate",width:70,align:"right",sortable:false,formatter:getTotal}
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
					jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false}) }, 
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
		}); 
	jQuery("#invList").jqGrid('navGrid','#pinvList',{edit:false,add:false,del:false},{},{},{},{multipleSearch:true});
		$("#invoiceDetails").hide();
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
			<span id="selfPartyStock"> <label for="selfPartyAccId"
				class="ttl_names">Stock At</label> <select id="selfPartyAccId"
				name="selfPartyAccId" style="width: 130px;">
					<option value="">Select</option>
					<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
						<option value="${s.id}"
							<c:if test="${USER_SESSION.partyAccId == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
					</c:forEach>
			</select> <input type="button" value="Go" onclick="fnFilter();" /> </span>
			<table id="invList"></table>
			<div id="pinvList"></div>
			<div id="invoiceDetails"
				onkeydown="if (event.keyCode == 13)submit();">
				<div>
					<label for="consignee"> Consignee </label> <select id="consignee"
						name="consignee">
						<option value="">Select</option>
						<c:forEach items="${CAR_LIST}" var="c" varStatus="cnt">
							<option value="${c.id}">${c.description}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="consignmentCode">Consignment Code </label> <input
						type="text" id="consignmentCode" name="consignmentCode" size="15">
				</div>
				<div>
					<input type="button" onclick="submit();">
				</div>
			</div>
		</div>
	</div>

</body>


</html>