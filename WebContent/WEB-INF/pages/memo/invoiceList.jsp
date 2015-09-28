<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">$(document).ready(function(){
	jQuery("#invoiceList").jqGrid({
			sortable: true, 
			url: link ,
			 datatype: 'local',
			 colNames:col_names,
			 colModel:col_models, 
				rowNum:50, 
				rowList:[50,75,100], 
				pager: '#pinvoiceList',
				sortname: 'invoiceId',
				sortorder: "desc", 
				width: "900", 
				height: "330",
				caption:"Invoice List" ,
				multiselect:"true",
  			    jsonReader:{
		            root: "rows",
		            page: "page",
		            total: "total",
		            records: "records",
		            cell:"cell",
		            certId:"certId",
		            id: "id"
				} 
		}); 
	jQuery("#invoiceList").jqGrid('navGrid','#pinvoiceList',{edit:false,add:false,del:false,search:false});
	});	
-->	
</script>

</head>
<body>


	<table>
		<tr>
			<td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td>
		</tr>
	</table>


	<table id="memoResult"></table>
	<div id="pmemoResult"></div>



</body>


</html>