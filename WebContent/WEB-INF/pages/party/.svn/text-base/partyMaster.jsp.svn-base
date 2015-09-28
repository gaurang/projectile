<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
<script type="text/javascript">
/* function editLink(cellvalue, options, rowObject){
		return	'<a href="javascript:void(0);" onclick="func(\'partyActionController.html?partyId='+rowObject.id+'\');">'+rowObject.id+'</a>';
} */

function editLink(cellvalue, options, rowObject){
    return  '<a href="javascript:void(0);" onclick="addTab(\'Add Party\',\'partyActionController.html?partyId='+rowObject.id+'\');">'+rowObject.id+'</a>';
}

function getTypeOfParty(cellvalue, options, rowObject){
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
	jQuery("#PartyList").jqGrid({
			sortable: true, 
			 url: 'partyListGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Party Id', 'Company Name', 'Party Type', 'Broker Party', 'Business Type',  'CompanyType', 'Status'],
			 colModel:[
						{name:'id', index:'id', width:30, formatter:editLink},
						{name:'companyName', index:'companyName', width:100 },
						{name:'typeOfParty', index:'typeOfParty', width:65,formatter:getTypeOfParty},
						{name:'brokerPartyName', index:'brokerPartyName', width:65 },
						{name:'businessType', index:'businessType', width:65, sortable:true},
						{name:'companyType', index:'companyType', width:65, sortable:true},
						{name:'activeFlag', index:'activeFlag', width:65, sortable:true, formatter:status}
			           ], 
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#pPartyList',
				sortname: 'id',
				sortorder: "desc", 
				width: "900", 
				height: "350",
				caption:"Party List" ,
  			    jsonReader:{
  			    	 root: "results",
  			    	 page: "pageNo",
  					  total: "pageAmount" ,
  					  records: "recordSize",
  					  repeatitems: false,
  					  id: "0"
				} 
		}); 
	jQuery("#PartyList").jqGrid('navGrid','#pPartyList',{edit:false,add:false,del:false,search:false});
	$("#search").click(function(){
		$('#PartyList').setGridParam({url:'partyListGrid.html?from=1&'+$('#partySearch').serialize(), datatype:'json' ,postData:{}}).trigger("reloadGrid");
	});
	
	$("#companyName").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#search").click();
	    }
	});
	$("#brokerName").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#search").click();
	    }
	});	
	
});	
</script>
</head>
<body >
<jsp:include page="../inc/inc_header.jsp">
	<jsp:param name="page" value="party"/>
	<jsp:param name="subPage" value="party"/>
</jsp:include>
<div class="container">	
<div class="content">	
	<form action="" method="post" name="partySearch" id="partySearch">
	<div id="searchPanel">
		<div class="row">
				<label for="businesstype">Business Type</label>
				<div class="element">		
				      <select id="businessType" name="businessType" class="list_box">
			            <option value="">----Select Business Type----</option>
						<option value="Jewellery Manufacturer ">Jewellery Manufacturer </option>
						<option value="Jewellery Wholeseller">Jewellery Wholeseller</option>
						<option value="Jewellery Retailer">Jewellery Retailer</option>
						<option value="Jewellery Importer/Exporter">Jewellery Importer/Exporter</option>
						<option value="Diamond Manufacturer ">Diamond Manufacturer </option>
						<option value="Diamond Wholeseller">Diamond Wholeseller</option>
						<option value="Diamond Retailer">Diamond Retailer</option>
						<option value="Diamond Importer/Exporter">Diamond Importer/Exporter</option>
			          </select>
				</div>
		</div>
		<div class="row">
				<label for="typeOfParty">Party Type</label>
				<div class="element">		
				     <select name="typeOfParty" id="typeOfParty" >
				     	<option value="">----Select Party Type----</option>
		         			<option value="BYR">Buyer</option>
		         			<option value="BKR">Broker</option>
		         			<option value="SBKR">Sub Broker</option>
		         			<option value="VDR">Vendor</option>
		         			<option value="BYVD">Buyer/Vendor</option>
		         			<option value="SLF">Self</option>
		         		</select>	
				</div>
		</div>
		<div class="row">
				<label for="status">Status</label>
				<div class="element">		
				     <select name="status" id="status" >
		         			<option value="1">Active</option>
		         			<option value="0">Inactive</option>
		         		</select>	
				</div>
		</div>
	</div>	
	<div id="searchPanel">
		<div class="row">
				<label for="companyName">Company Name</label>
				<div class="element">	
				<input type="text" name="companyName" id="companyName" size="10"  maxlength="50"/>	
				</div>
		</div>
		
		<div class="row">
				<label for="brokerName">Broker Name</label>
				<div class="element">	
				<input type="text" name="brokerName" id="brokerName" size="10"  maxlength="50"/>	
				</div>
		</div>
		<div class="row" align="center">
				<input type="button" value="search" id="search"/>
		</div>
	</div>
	<br/><br/><br/><br/><br/><br/>

	<table id="PartyList" ></table> 
	<div id="pPartyList"></div>
	</form>
</div>		
</div>			
</body>
</html>