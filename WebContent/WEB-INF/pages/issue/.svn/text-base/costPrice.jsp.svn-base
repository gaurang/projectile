<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
	$(document).ready(function() {
		$('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});
	});

	function getDetails(val) {
		var arr = new Array();
		arr = val.split('|');
		var id = arr[0];
		$
				.getJSON(
						'costPriceAJAX.html?purchaseId=' + id,
						function(json) {
							if (id == -1) {
								$('#tableData').html("");
								return;
							}
							if (json != null && json.error != '') {
								var html = '';
								var count = 0;
								var rate = 0.0;
								$
										.each(
												json,
												function(i) {
													rate = (json[i].rate)
															.toFixed(2);
													html += '<tr><td><input type="text" id="pktCode_'+i+'" name="pktCode_'+i+'" value="'+json[i].pktCode+'"/></td>';
													html += '<td><label id="wt_'+i+'" name="wt_'+i+'">'
															+ json[i].wt
															+ '</label></td>';
													html += '<td><input type="text" id="rate_'
															+ i
															+ '" name="rate_'
															+ i
															+ '" value="'
															+ rate
															+ '" onchange="getTotal(this,'
															+ i + ')"/></td>';
													html += '<td><input type="text" id="total_'
															+ i
															+ '" name="total_'
															+ i
															+ '" value="'
															+ ((json[i].wt) * rate)
																	.toFixed(2)
															+ '"/></td>';
													count += 1;
												});
								$('#count').val(count);
								$('#tableData').html(html);
							} else
								alert('Could not load details');
						});
		var curr = arr[2];
		changeCurr(curr);
		var glTransNo = arr[1];
		$('#glTransNo').val(glTransNo);
		var selectedVal = $("#purchaseId option:selected").text();
		arr = selectedVal.split('|');
		$('#billNo').val(arr[1]);
		$('#purchaseDate').val(arr[2]);
	}
	function getTotal(fld, i) {
		var rate = $(fld).val();
		var wt = $('#wt_' + i).text();
		$('#total_' + i).val((wt * rate).toFixed(2));
	}
	function changeCurr(curr) {
		$('#Total').html('Total' + '(' + curr + ')');
		$('#CostPriceCts').html('Cost Price/Cts' + '(' + curr + ')');
		$('#currency').val(curr);
		//get Exrate
		var arr = new Array();
		arr = $('#purchaseId').val().split('|');
		$('#exRate').val(arr[4]);
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="costPrice" />
	</jsp:include>

	<br />
	<br />
	<div id="content" align="center">
		<form action="costPriceSubmit.html" id="costPriceForm"
			name="costPriceForm" onsubmit="CPSubmit()">
			<div align="center">
				<label style="text-align: center;"></label> <input type="hidden"
					id="count" name="count" /> <input type="hidden" id="glTransNo"
					name="glTransNo" /> <input type="hidden" id="billNo" name="billNo" />
				<input type="hidden" id="purchaseDate" name="purchaseDate" />
				<table align="center" id="greenTable">
					<th>companyName/billNo/purchaseDate</th>
					<th>Currrency</th>
					<th>Exrate</th>
					<tr>
						<td><select id="purchaseId" name="purchaseId"
							style="width: 250px" onchange="getDetails(this.value)">
								<option value="-1">Select</option>
								<c:forEach var="l" items="${list}">
									<option
										value="${l.id}|${l.glTransNo}|${l.currency}|${l.vendorId}|${l.exRate}">${l.companyName}|${l.billNo}|${l.purchaseDate}</option>
								</c:forEach>
						</select></td>
						<td><select id="currency" name="currency"
							style="width: 100px" onchange="changeCurr(this.value)">
								<option value="-1">Select</option>
								<c:forEach var="a" items="${currency}">
									<option value="${a.id}">${a.description}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" id="exRate" name="exRate"
							class="numbersOnly" width="10px" /></td>
					</tr>
				</table>
			</div>
			<table align="center" id="greenTable">
				<thead>
					<tr>
						<th>Packet Code</th>
						<th>Cts</th>
						<th><label id="CostPriceCts">Cost Price/Cts</label>
						</th>
						<th><label id="Total">Total</label>
						</th>
					</tr>
				</thead>
				<tbody id="tableData"></tbody>
			</table>
			<div align="center">
				<input type="submit" value="Submit" id="Submit" />
			</div>
		</form>
	</div>
</body>
</html>