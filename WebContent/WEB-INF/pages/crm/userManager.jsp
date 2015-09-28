<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<title>Projectile:</title>
<script type="text/javascript">
function editLink(cellvalue, options, rowObject){
		return	'<a href="javascript:void(0);" onclick="addTab(\'Edit User\',\'userManagerEdit.html?id='+rowObject.userId+'\');">Edit</a>';
}
$(document).ready(function(){
	jQuery("#userList").jqGrid({
			sortable: true, 
			 url: 'userListGrid.html?q=1' ,
			 datatype: "json",
			 colNames:['User Name', 'Role Name', 'Active Flag', 'Party Account', 'Action'],
			 colModel:[
						{name:'userName', index:'userName', width:100 },
						{name:'roleName', index:'roleName', width:100 },
						{name:'activeFlag', index:'activeFlag', width:65 },
						{name:'partyAccId', index:'partyAccId', width:65 },
						{name:'action', index:'action', width:65, sortable:false,formatter:editLink}
			           ], 
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#puserList',
				sortname: 'userName',
				sortorder: "desc", 
				width: "800", 
				height: "350",
				caption:"userName" ,
  			    jsonReader:{
  			    	 root: "results",
  					  page: "pageNo",
  					  total: "pageAmount" ,
  					  records: "recordSize",
  					  repeatitems: false,
  					  id: "0"
				} 
		}); 
	jQuery("#userList").jqGrid('navGrid','#puserList',{edit:false,add:false,del:false,search:false});
	});	
	
	//TODO for maximum 2o users from config not allow creat user
-->	
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="user" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<br />
			<table id="userList"></table>
			<div id="puserList"></div>
			<input type="button" value="Add New User"
				onclick="window.location='userManagerEdit.html';" id="newUSerBtn" />
		</div>
	</div>
</body>
</html>