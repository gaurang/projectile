<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
label {
	
}

label.error {
	float: none;
	color: red;
	padding-left: .5em;
	vertical-align: top;
	display: list-item;
	font-size: 10px;
}

p {
	clear: both;
}

.submit {
	margin-left: 12em;
}

em {
	font-weight: bold;
	padding-right: 1em;
	vertical-align: top;
}
</style>

<script type="text/javascript">
var invList = new Object();
var invAmt =  new Object();
var count =0;

$(document).ready(function(){

	$( "#paymentDate" ).datepicker({
		changeMonth: true,
		changeYear: true,
		currentText: 'Now',
		dateFormat: 'dd-mm-yy' 
	});
	
	$( "#paymentDate" ).val('${CURR_DATE}');
	
	
	
	$('#saveMaster').click(function(){
});
	

</script>

</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td>
		</tr>
	</table>

	<form method="post" action="paymentAction.html" name="payForm"
		id="payForm">
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..."
				style="display: none;" />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		<div id="rightPanel">
			<table>

				<tr>
					<td>Date : <input type="text" name="paymentDate"
						id="paymentDate" /></td>
					<td>Ref : <input type="text" name="ref" id="ref" /></td>

				</tr>


			</table>
			<table>

				<tr>
					<th>Acc Desscription</th>
					<th>Debit</th>
					<th>Credit</th>
					<th>Desc</th>
					<th></th>
				</tr>
				<tr id="row_1">
					<td><select id="accountCode_1" name="accountCode_1">
							<option value="-1">Select</option>
							<c:forEach var="gl" items='${glAcc}'>
								<option value="${gl.id}"
									<c:if test="${param.accountCode == v.id}">selected </c:if>>${gl.description}</option>
							</c:forEach>
					</select></td>
					<td><input type="text" name="db_1" id="db_1" /></td>
					<td><input type="text" name="cr_1" id="cr_1" /></td>
					<td><input type="text" name="dsc_1" id="dsc_1" /></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td id="Debit"></td>
					<td id="Credit"></td>
					<td></td>
					<td></td>
				</tr>


			</table>
			<div align="center">

				<input type="submit" value="Save" id="saveMaster" /> <input
					type="button" value="Reset" onclick="resetFrm();" />
			</div>
		</div>
	</form>
</body>
</html>