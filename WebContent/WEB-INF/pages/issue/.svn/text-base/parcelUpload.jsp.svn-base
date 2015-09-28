<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.buttontab {
	padding: 5px 10px;
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#CACDA2) );
	background: -moz-linear-gradient(top, #fff, #CACDA2);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	text-shadow: 1px 1px #666;
	color: #000;
	text-decoration: none;
}

.buttontab:hover {
	background: #fff;
}

#active {
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#fff) );
	background: -moz-linear-gradient(top, #fff, #fff);
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#ParcelUploadForm').validate();
		$('#dialog').hide();

		$("#parcelDate").DatePicker({
			format : 'd-m-Y',
			current : '${CURR_DATE}',
			calendars : 1,
			date : '${CURR_DATE}',
			onChange : function(formated, dates) {
				$('#parcelDate').val(formated);
				$('#parcelDate').DatePickerHide();
			}
		});
		$("#parcelDate").val('${CURR_DATE}');

		$("#parcelDueDate").DatePicker({
			format : 'd-m-Y',
			current : '${CURR_DATE}',
			calendars : 1,
			date : '${CURR_DATE}',
			onChange : function(formated, dates) {
				$('#parcelDueDate').val(formated);
				$('#parcelDueDate').DatePickerHide();
			}
		});
		$("#parcelDueDate").val('${CURR_DATE}');
	});
	
	function showHelp() {
		$("#dialog").dialog();
		$('#dialog').show();
	}
	
	
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>

	<div id="ParcelUpload" align="center">
		<br /> <a href="javascript:void(0);" class="buttontab" id="active" onclick="func('parcelUpload.html');">Parcel Upload</a> <a
			href="javascript:void(0);" class="buttontab" id="" onclick="func('packetUpload.html');">Packet Upload</a> <a
			href="javascript:void(0);" class="buttontab" id="" onclick="func('fixedPacket.html');">Fixed Packet</a> <br />
		<br />
		<div id="errorMsg">${mailMsg}</div>
		<form id="ParcelUploadForm" name="stockEnM2"
			action="parcelSubmit.html" method="post">
			<!--<br/>
					<div class="Fixed"><a href="stockMixList.html">Small Size packet List</a></div>
				<br/>				
			-->
			<div id="Table">
				<table>
					<tr valign="top">
						<td>
							<table width="100%" align="center" style="vertical-align: top;">
								<tr>
									<th><label for="vendorId">Vendor Id</label>
									</th>
									<td><select name="vendorId" id="vendorId"
										style="width: 145px;">
											<option value="-1">Select Vendor</option>
											<c:forEach var="v" items='${VENDOR_LIST}' varStatus="cnt">
												<option value='${v.id}'>${v.description}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th><label for="billNo">Bill No:</label>
									</th>
									<td><input type="text" name="billNo" id="billNo"
										class="required" size="20" />
									</td>
								</tr>
								<tr>
									<th><label for="comments">Comments:</label>
									</th>
									<td><input type="text" name="comments" id="comments"
										size="20" />
									</td>
								</tr>
							</table></td>
						<td>
							<table width="100%" align="center" style="vertical-align: top;">
								<tr>
									<th><label for="parcelDate">Date:</label></th>
									<td><input type="text" name="parcelDate" id="parcelDate"
										class="required" size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="parcelDueDate">Due Date:</label>
									</th>
									<td><input type="text" name="parcelDueDate"
										id="parcelDueDate" class="required" size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="paymentTerm">Payment Term</label>
									</th>
									<td><select name="paymentTerm" id="paymentTerm"
										style="width: 145px;">
											<option value='CHAQUE'>CHEQUE</option>
											<option value='CASH'>CASH</option>
									</select></td>
								</tr>
							</table></td>
						<td>
							<table width="100%" align="center">
								<tr>
									<th><label for="currency">Currency</label>
									</th>
									<td><select name="currency" id="currency"
										style="width: 145px;">
											<c:forEach var="v" items='${currencyList}' varStatus="cnt">
												<option value='${v.currAbrev}'
													<c:if test="${v.currAbrev == 'USD'}">selected</c:if>>${v.currency}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th><label for="exRate">Ex Rate:</label></th>
									<td><input type="text" name="exRate" id="exRate"
										class="number" size=10 value="1" />
									</td>
								</tr>
								<tr>
									<th><label for="tax">Tax:</label>
									</th>
									<td><input type="text" name="tax" id="tax" class="number"
										size=10 />
									</td>
								</tr>
								<tr>
									<th><label for="expenses">Expenses:</label>
									</th>
									<td><input type="text" name="expenses" id="expenses"
										class="number" size=10 />
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<table>
				<tr valign="top">
					<td>
						<table width="100%" align="center" style="vertical-align: top;">
							<tr>
								<th><label for="parcel_No">Parcel NO</label>
								</th>
								<td><input type="text" name="ParcelNo" id="ParcelNo"
									size="20" />
								</td>
							</tr>
							<tr>
								<th><label for="TotCts">Total Cts</label>
								</th>
								<td><input type="text" name="TotCts" id="TotCts"
									class="required number" size="20" />
								</td>
							</tr>
							<tr>
								<th><label for="TotRate">Total Rate</label>
								</th>
								<td><input type="text" name="TotRate" id="TotRate"
									class="required number" size="20" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="submit" name="submit" id="submit" value="submit" /> <b>OR
			</b><a href="javascript:void(0);" class="buttontab" onclick="func('parcelDistribute.html');">Distribute
				Directly</a>
		</form>
	</div>
</body>
</html>