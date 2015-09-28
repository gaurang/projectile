<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						jQuery("#purchaseReport")
								.jqGrid(
										{
											sortable : true,
											url : 'purchaseReportGrid.html?q=1',
											datatype : "json",
											colNames : [ 'System Id',
													'Vendor Party', 'Bill NO',
													'Purchase Date',
													'Due Date', 'Comments' ],
											colModel : [ {
												name : 'id',
												index : 'id',
												width : 30
											}, {
												name : 'companyName',
												index : 'companyName',
												width : 30
											}, {
												name : 'billNo',
												index : 'billNo',
												width : 100
											}, {
												name : 'purchaseDate',
												index : 'purchaseDate',
												width : 65
											}, {
												name : 'dueDate',
												index : 'dueDate',
												width : 65
											}, {
												name : 'comments',
												index : 'comments',
												width : 130
											} ],
											rowNum : 100,
											rowList : [ 100, 200, 300 ],
											pager : '#ppurchaseReport',
											sortname : 'compnayName',
											sortorder : "asc",
											width : "900",
											height : "550",
											caption : "Purchase Report",
											footerrow : true,

											subGrid : true,
											subGridRowExpanded : function(
													subgrid_id, row_id) {
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
																	url : "purchaseReportSubGrid.html?q=2&id="
																			+ row_id,
																	datatype : "json",
																	colNames : [
																			'pktCode',
																			'Rate',
																			'Wt' ],
																	colModel : [
																			{
																				name : "pktCode",
																				index : "pktCode",
																				width : 80,
																				key : true,
																				sortable : false
																			},
																			{
																				name : "rate",
																				index : "rate",
																				width : 130,
																				sortable : false
																			},
																			{
																				name : "wt",
																				index : "wt",
																				width : 70,
																				align : "right",
																				sortable : false
																			} ],
																	sortname : 'pktCode',
																	sortorder : "asc",
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
						jQuery("#purchaseReport").jqGrid('navGrid',
								'#ppurchaseReport', {
									edit : false,
									add : false,
									del : false,
									search : false
								});
					});
</script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<div class="container">
		<div class="content" align="center">
			<table id="purchaseReport"></table>
			<div id="ppurchaseReport"></div>
		</div>
	</div>
</body>
</html>