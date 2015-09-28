<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
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
	$(document)
			.ready(
					function() {
						/**
						$.validator.addMethod("noSpecialChars", function(value, element) {
						    return this.optional(element) || /^[a-z0-9\_]+$/i.test(value);
						}, "Element must contain only letters, numbers, or underscore.");
						$.validator.addMethod("nameOfPerson", function(value, element) {
						    return this.optional(element) || /^[a-z\ ]+$/i.test(value);
						}, "Element must contain only letters");
						 */

						$('#saveMaster')
								.click(
										function() {

											$('#ajax').show();
											var formOptions2 = {
												dataType : 'json',
												beforeSubmit : function() {
													return $('#pAForm')
															.validate().form();
												},
												success : function(jsonObj) {
													<c:set var="errorMsgStr" value="Basic Party Details has been Successfully Saved" scope="request"></c:set>
													window.opener.location = 'partyActionController.html?partyId='
															+ $("#partyId")
																	.val();
													window.close();
												},
												target : '#response'
											};
											$('#pAForm').ajaxForm(formOptions2);
											$('#ajax').hide();
										});

					});
	function resetFrm() {
		$('#pAForm').resetForm();
	}
	function shipAdd(fld) {
		if (fld.checked) {

			$("#sAdd1").val($("#add1").val());
			$("#sAdd2").val($("#add2").val());
			$("#sAdd3").val($("#add3").val());
			$("#sCountry").val($("#country").val());
			$("#sState").val($("#state").val());
			$("#sPin").val($("#pin").val());
			$("#sCity").val($("#city").val());
			$("#sConPer").val($("#conPer").val());

		} else {

			$("#sAdd1").val("");
			$("#sAdd2").val("");
			$("#sAdd3").val("");
			$("#sCountry").val("");
			$("#sState").val("");
			$("#sPin").val("");
			$("#sCity").val("");
			$("#sConPer").val("");
		}

	}
</script>

</head>
<body>
	<div id="ajax">
		<img src="images/ajax-loader.gif" alt="Loading..."
			style="display: none;" />
	</div>

	<h1>Add Branch to Party for - ${partyMasterData.companyName}</h1>
	<form:form method="post" cssClass="cmxform"
		action="partyAddAccController.html" commandName="partyAddMaster"
		name="pAForm" id="pAForm">

		<fieldset id="branch">
			<legend id="branch">Branch :</legend>
			<div align="center" style="height: 280px;">
				<div style="float: left; width: 50%;">
					<form:hidden path="id" id="partyAddId" />
					<form:hidden path="partyId" id="partyId" />
					<fieldset>
						<legend>Address :</legend>
						<table>
							<tr>
								<td class="ttl_names">Address Line 1 <span class="astrix">*</span>
								</td>
								<td><form:input path="address1" cssClass="required"
										size="15" id="add1" maxlength="100" />
								</td>
								<td class="ttl_names">City</td>
								<td><form:input path="city" cssClass="" size="15" id="city"
										maxlength="30" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Address Line 2</td>
								<td><form:input path="address2" cssClass="" size="15"
										id="add2" maxlength="100" />
								</td>
								<td class="ttl_names">Country</td>
								<td><form:input path="country" cssClass="" size="15"
										id="country" maxlength="25" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Address Line 3</td>
								<td><form:input path="address3" size="15" id="add3"
										maxlength="100" />
								</td>
								<td class="ttl_names">State</td>
								<td><form:input path="state" size="15" id="state" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Pin</td>
								<td><form:input path="pin" cssClass="" size="15" id="pin"
										maxlength="15" />
								</td>
								<td class="ttl_names">fax</td>
								<td><form:input path="fax" cssClass="number" size="15"
										id="fax" maxlength="16" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Country code</td>
								<td><form:input path="countryPhoneCode" cssClass="number"
										size="15" id="countryCode" maxlength="6" />
								</td>
								<td class="ttl_names">Phone No.</td>
								<td><form:input path="phone" cssClass="number" size="15"
										id="city" maxlength="100" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Contact Person</td>
								<td><form:input path="contactPerson"
										cssClass="nameOfPerson" size="15" id="conPer" maxlength="50" />
								</td>
								<td class="ttl_names">Designation</td>
								<td><form:input path="designation" cssClass="" size="15"
										id="designation noSpecialChars" maxlength="50" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Cell No.</td>
								<td><form:input path="cellNo" cssClass="number" size="15"
										id="cellNo" maxlength="16" />
								</td>
								<td class="ttl_names">Email</td>
								<td><form:input path="email" cssClass="email" size="15"
										id="email" maxlength="50" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Branch Code <span class="astrix">*</span>
								</td>
								<td><form:input path="branchCode"
										cssClass="required noSpecialChars" size="15" id="branch"
										maxlength="25" />
								</td>
								<td colspan=2 align="center"><c:if
										test="${status.count ==1}">
										<input type="button" value="Add More Branch" id="addBranch" />
									</c:if> <c:if test="${status.count !=1}">
										<input type="button" value="Remove" id="rmvBr"
											onclick="rmvBranch('${status.count - 1}');" />
									</c:if></td>
							</tr>

						</table>

					</fieldset>
					<fieldset>
						<legend>Bank Details :</legend>
						<table>
							<tr>
								<td class="ttl_names">Bank Name</td>
								<td><form:input path="bank" cssClass="" size="15"
										id="bank nameOfPerson" maxlength="100" />
								</td>
								<td class="ttl_names">Acc No.</td>
								<td><form:input path="bankAccNo" cssClass="" size="15"
										id="bankAcc noSpecialChars" maxlength="100" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Address</td>
								<td><form:input path="bankAdd" size="15" id="bankAdd"
										maxlength="250" />
								</td>
								<td class="ttl_names">Bank Branch</td>
								<td><form:input path="bankBranch" size="15" id="bankBranch"
										maxlength="100" cssClass="noSpecialChars" />
								</td>
							</tr>

						</table>
					</fieldset>

				</div>

				<div style="float: left; width: 50%;" align="center">
					<fieldset>
						<form:hidden path="partyShipAdd.partyAddId" id="sPartyAddId" />
						<legend>Shipping Address :</legend>
						<table>
							<tr>
								<td class="ttl_names" colspan="4">Copy Branch Address <input
									type="checkbox" name="shipAddr" id="shipAddr" value="1"
									onclick="shipAdd(this);" style="vertical-align: middle;" /></td>
							</tr>
							<tr>
								<td class="ttl_names">Address Line 1 <span class="astrix">*</span>
								</td>
								<td><form:input path="partyShipAdd.address1"
										cssClass="required" size="15" id="sAdd1" maxlength="100" />
								</td>
								<td class="ttl_names">City</td>
								<td><form:input path="partyShipAdd.city" cssClass=""
										size="15" id="sCity" maxlength="30" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Address Line 2</td>
								<td><form:input path="partyShipAdd.address2" cssClass=""
										size="15" id="sAdd2" maxlength="100" />
								</td>
								<td class="ttl_names">Country</td>
								<td><form:input path="partyShipAdd.country" cssClass=""
										size="15" id="sCountry" maxlength="25" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Address Line 3</td>
								<td><form:input path="partyShipAdd.address3" size="15"
										id="sAdd3" maxlength="100" />
								</td>
								<td class="ttl_names">State</td>
								<td><form:input path="partyShipAdd.state" size="15"
										id="sState" maxlength="50" />
								</td>
							</tr>
							<tr>
								<td class="ttl_names">Pin</td>
								<td><form:input path="partyShipAdd.pin" cssClass=""
										size="15" id="sPin" maxlength="15" />
								</td>
								<td class="ttl_names">Contact Person</td>
								<td><form:input path="partyShipAdd.contactPerson"
										cssClass="" size="15" id="sConPer" maxlength="50" />
								</td>
							</tr>

						</table>

					</fieldset>
					<fieldset>
						<form:hidden path="partyAccs[0].partyAddId" id="aPartyAddId" />
						<form:hidden path="partyAccs[0].id" id="partyAccId" />
						<legend>Party Account Type :</legend>
						<table>
							<tr>
								<td class="ttl_names">Term</td>
								<td class="ttl_names" colspan=2><form:select
										path="partyAccs[0].termId" cssClass="required">
										<c:forEach var="t" items='${TERMS_LIST}'>
											<form:option value='${t.id}'>${t.description}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td class="ttl_names">Account Type</td>
								<td class="ttl_names" valign="middle"><form:radiobutton
										path="partyAccs[0].accType" cssClass="required radio"
										id="export" value="E" /> <label for="local">Export</label></td>
								<td class="ttl_names" valign="middle"><form:radiobutton
										path="partyAccs[0].accType" cssClass="required radio"
										id="local" value="L" /> <label for="export">Local</label></td>
							</tr>
							<tr>
								<td class="ttl_names">Currency</td>
								<td class="ttl_names" colspan=2><form:select
										path="partyAccs[0].currency" cssClass="required">
										<c:forEach var="c" items='${currencyList}'>
											<option value='${c.currAbrev}'
												<c:if test="${c.currAbrev == 'USD'}">selected</c:if>>${c.currency}</option>
										</c:forEach>
									</form:select></td>
							</tr>
						</table>

					</fieldset>
				</div>
			</div>
			<div align="center">
				<input type="submit" value="Save" id="saveMaster" /> <input
					type="button" value="Reset" onclick="resetFrm();" /> <input
					type="button" value="Delete" />
			</div>
		</fieldset>
	</form:form>
</body>
</html>