<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<title>H.Riddhesh & Co. : </title>
  <link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery.ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqgrid/css/ui.jqgrid.css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.7.2.custom.min.js"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>



<script type="text/javascript">
<!--
function editLink(cellvalue, options, rowObject){
		return	'<a href="partyActionController.html?partyId='+rowObject.id+'">'+rowObject.id+'</a>';
}

function getTipeOfParty(cellvalue, options, rowObject){
	if(cellvalue == 'BKR')
		return 'Broker';
	else if(cellvalue == 'SBKR')
		return 'Sub Broker';
	else if(cellvalue == 'VDR')
		return 'Vendor';
	else if(cellvalue == 'SLF')
		return 'Self';
	else if(cellvalue == 'CAR')
		return 'Carieer';
	else if(cellvalue == 'BYVD')
		return 'Buyer/Vendor';
	else
		return 'Buyer';

}
function status(cellvalue, options, rowObject){
	if(cellvalue == 1){
		return 'Active';
	}
	else{
		return 'In-Active';			
	}	
}

$(document).ready(function(){
	jQuery("#demandList").jqGrid({
			sortable: true, 
			 url: 'demandListGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Demand Id', 'Company Name', 'Demand Name', 'Auto Mail', 'Auto Memo',  'No Price', 'CreateDate'],
			 colModel:[
						{name:'id', index:'id', width:30},
						{name:'companyName', index:'companyName', width:100 },
						{name:'demandName', index:'demandName', width:65 },
						{name:'autoMail', index:'autoMail', width:65 },
						{name:'autoMemo', index:'autoMemo', width:65, sortable:false},
						{name:'noPriceFlag', index:'noPriceFlag', width:65, sortable:false},
						{name:'createDateTime', index:'createDateTime', width:65, sortable:false}
			           ], 
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#pdemandList',
				sortname: 'id',
				sortorder: "desc", 
				width: "800", 
				height: "350",
				caption:"Demand List" ,
  			    jsonReader:{
  			    	 root: "results",
  					  page: "pageNo",
  					  total: "pageAmount" ,
  					  records: "recordSize",
  					  repeatitems: false,
  					  id: "0"
				} 
		}); 
	jQuery("#demandList").jqGrid('navGrid','#pdemandList',{edit:false,add:false,del:false,search:false});
	});	
	
function reloadGrid(){
	  $('#demandList').setGridParam({url:'demandListGrid.html', datatype:'json', postData:{ partyAccId : $('#partyAccId').val()}}).trigger("reloadGrid");
}
	
-->	
</script>
</head>
<body >
<table><tr><td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td></tr></table>
<form id="demandForm" name="demandForm">
	<select id="partyAccId" name="partyAccId" style="width: 130px;" onchange="reloadGrid();">
				<option value="">Select</option>
				<c:forEach items="${PARTY_LIST}" var="s" varStatus="cnt">
				 	<option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
				</c:forEach>
	</select>

	<table id="demandList" ></table> 
	<div id="pdemandList"></div>
</form>	
		
</body>
</html>