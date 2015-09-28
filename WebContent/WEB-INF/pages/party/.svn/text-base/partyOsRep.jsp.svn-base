<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(
			function() {
				jQuery("#partyOSReport").jqGrid(
						{
							sortable : true,
							url : 'partyOSReportGrid.html?q=1',
							datatype : "json",
							colNames : [ 'Party Name', 'Branch Code',
									'Total Amount', 'INV-Paid Amount',
									'Other Paid', 'Total OS', 'Date' ],
							colModel : [ {
								name : 'companyName',
								index : 'companyName',
								width : 30
							}, {
								name : 'branchCode',
								index : 'branchCode',
								width : 30
							}, {
								name : 'totalAmount',
								index : 'totalAmount',
								width : 100,
								summaryType : 'sum'
							}, {
								name : 'paidAmount',
								index : 'paidAmount',
								width : 65,
								summaryType : 'sum'
							}, {
								name : 'othPaidAmount',
								index : 'othPaidAmount',
								width : 65,
								summaryType : 'sum'
							}, {
								name : 'totalOs',
								index : 'totalOs',
								width : 65,
								summaryType : 'sum'
							}, {
								name : 'lastPaymentDate',
								index : 'lastPaymentDate',
								width : 65
							}, ],
							rowNum : 100,
							rowList : [ 100, 200, 300 ],
							pager : '#ppartyOSReport',
							sortname : 'compnayName',
							sortorder : "asc",
							width : "900",
							height : "550",
							caption : "Party Outstandings",
							grouping : true,
							groupingView : {
								groupField : [ 'companyName' ],
								groupColumnShow : [ true ],
								groupText : [ '<b>{0}</b>' ],
								groupCollapse : false,
								groupOrder : [ 'asc' ],
								groupSummary : [ true ],
								groupDataSorted : true
							},
							footerrow : true,
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
				jQuery("#partyOSReport").jqGrid('navGrid', '#ppartyOSReport', {
					edit : false,
					add : false,
					del : false,
					search : false
				});
			});
</script>
</head>
<body>

	<table>
		<tr>
			<td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td>
		</tr>
	</table>

	<table id="partyOSReport"></table>
	<div id="ppartyOSReport"></div>

</body>
</html>