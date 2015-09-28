<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
<script type="text/javascript">

function editLink(cellvalue, options, rowObject){
	return '<a href="javascript:void(0);" onclick="addTab(\'EditTerm\',\'termEdit.html?id='+rowObject.id+'\');">Edit</a>';
}
$(document).ready(function(){
	jQuery("#termList").jqGrid({
			sortable: true, 
			 url: 'termListGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['Term Id', 'Term Name', 'Term Code', 'Description', 'Credit Days',  'Interest Days', 'Factor','Edit'],
			 colModel:[
						{name:'id', index:'id', width:30},
						{name:'termName', index:'termName', width:100 },
						{name:'termCode', index:'termCode', width:65 },
						{name:'termDesc', index:'termDesc', width:65 },
						{name:'creditDays', index:'creditDays', width:65, sortable:false},
						{name:'interestDays', index:'interestDays', width:65, sortable:false},
						{name:'factor', index:'factor', width:65, sortable:false},
						{name:'Edit', index:'Edit', width:65, sortable:false,search:false,formatter:editLink}
			           ], 
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#ptermList',
				sortname: 'id',
				sortorder: "desc", 
				width: "800", 
				height: "350",
				caption:"Term" ,
  			    jsonReader:{
  			    	 root: "results",
  					  page: "pageNo",
  					  total: "pageAmount" ,
  					  records: "recordSize",
  					  repeatitems: false,
  					  id: "0"
				} 
		}); 
	jQuery("#termList").jqGrid('navGrid','#ptermList',{edit:false,add:false,del:false,search:false});
	});	
	

</script>
</head>
<body >
<jsp:include page="../inc/inc_header.jsp">
	<jsp:param name="page" value="utility"/>
	<jsp:param name="subPage" value="terms"/>
</jsp:include>

<div class="container">	
	<div class="content" align="center" >	
	<br/>

		<table id="termList" ></table> 
		<div id="ptermList"></div>
		
		<input type="button" value="Add New Term" onclick="func('termEdit.html');" id="newUSerBtn"/>
	</div>
</div>	
		
</body>
</html>