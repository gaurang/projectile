<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
<%--<script src="js/jquery/jquery.maskedinput.js" type="text/javascript"></script>
  <script src="js/valid.js" type="text/javascript"></script> --%>
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
	var branchCount = 0;
	function submitForm() {
		$('#ajax').show();
		var formOptions = {
			dataType : 'json',
			beforeSubmit : function() {
				if ($('#pForm').validate().form()) {
					$('#saveBranch_0').attr("disabled", true);
					$('#saveData').attr("disabled", true);
				} else
					return;
			},
			success : function(jsonObj) {
				$('#partyId').val(jsonObj.id);
				$('#partyAddId').val(jsonObj.partyAddId);
				$('#sPartyAddId').val(jsonObj.partyAddId);
				$('#aPartyAddId').val(jsonObj.partyAddId);
				if (jsonObj.id > 0)
					$('#errorMsg').html(
							'Full Party Details has been Successfully Saved');
				else {
					$('#errorMsg').html('Error occured');
				}
				if ($('#redirect').val() == 1) {
					//window.opener.reloadParty($('#typeOfParty').val());
					//window.opener.partyId = $('#partyId').val();
					window.close();
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc('${USER_SESSION.userId}');
			},
			target : '#response'
		};
		$('#pForm').ajaxForm(formOptions);
		$('#ajax').hide();
	}
	function rmvBranch() {
		$('#branch_' + (branchCount)).hide();
		branchCount--;
	}
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

						// $('#saveBranch_0').click(submitForm);
						$('#saveData').click(submitForm);

						$('#DeleteBuyer')
								.click(
										function() {
											// $('#activeFlag').val(0);
											var val = confirm('Are you sure you want to delete Party details');
											if (val) {
												$
														.ajax({
															type : 'POST',
															url : 'deleteParty.html',
															dataType : 'json',
															data : {
																partyId : $(
																		'#partyId')
																		.val()
															},
															success : function(
																	json) {
																window.location = 'partyList.html';
															},
															error : function(
																	xmlHttpRequest,
																	textStatus,
																	errorThrown) {
																errorFunc('${USER_SESSION.userId}');
															}
														});
											}
										});
						$('#deletePartAdd')
								.click(
										function() {
											var val = confirm('Are you sure you want to delete Party Add details');
											if (val) {
												// $('#activeFlag').val(0);
												$
														.ajax({
															type : 'POST',
															url : 'deleteParty.html',
															dataType : 'json',
															data : {
																partyAddId : $(
																		'#partyAddId')
																		.val()
															},
															success : function(
																	json) {
																window.location = 'partyList.html';
															},
															error : function(
																	xmlHttpRequest,
																	textStatus,
																	errorThrown) {
																errorFunc('${USER_SESSION.userId}');
															}
														});
											}
										});

						$('#saveMaster')
								.click(
										function() {

											$('#ajax').show();
											var formOptions2 = {
												data : {
													partyMaster : '1'
												},
												dataType : 'json',
												beforeSubmit : function() {
													return $(
															'#pDetails .required')
															.valid();
												},
												success : function(jsonObj) {
													$('#partyId').val(
															jsonObj.id);
													$('#partyAddId').val(
															jsonObj.partyAddId);

													if (jsonObj.id > 0) {
														$('#errorMsg')
																.html(
																		'Basic Party Details has been Successfully Saved');
														window.location = 'partyActionController.html?partyId='
																+ jsonObj.id;
													} else {
														$('#errorMsg')
																.html(
																		'Error occured');
													}
													if ($('#redirect').val() == 1) {
														//window.opener.reloadParty($('#typeOfParty').val());
														//window.opener.partyId = $('#partyId').val();
														window.close();
													}
												},
												error : function(
														xmlHttpRequest,
														textStatus, errorThrown) {
													errorFunc('${USER_SESSION.userId}');
												},
												target : '#response'
											};
											$('#pForm').ajaxForm(formOptions2);
											$('#ajax').hide();
										});

						$('#addBranch')
								.click(
										function() {
											/**if(branchCount == 5 )
												alert('Cannot add more branch from here , please go to edit section');
											else{
												$('#branch_'+(branchCount+1)).show();
												branchCount++;
											}*/
											if ($('#partyId').val() > 0) {
												window
														.open(
																'partyAddAccController.html?partyId='
																		+ $(
																				'#partyId')
																				.val(),
																'formresult',
																'scrollbars=yes,menubar=yes,height=600,width=1000,resizable=yes,toolbar=no,status=no');
											} else {
												alert('Save before you procced to add another branch Office');
											}
										});

					});
	function resetFrm() {
		$('#pForm').resetForm();
	}
	function resetDiv(divNm) {
		$('#' + divNm + ' input').clearFields();
		$('#' + divNm + ' textarea').clearFields();
		$('#' + divNm + ' select').each(function() {
			this.selectedIndex = 0;
		});
	}
	function shipAdd(fld) {
		if (fld.checked) {
			$("#sAdd1" + fld.value).val($("#add1" + fld.value).val());
			$("#sAdd2" + fld.value).val($("#add2" + fld.value).val());
			$("#sAdd3" + fld.value).val($("#add3" + fld.value).val());
			$("#sCountry" + fld.value).val($("#country" + fld.value).val());
			$("#sState" + fld.value).val($("#state" + fld.value).val());
			$("#sPin" + fld.value).val($("#pin" + fld.value).val());
			$("#sCity" + fld.value).val($("#city" + fld.value).val());
			$("#sConPer" + fld.value).val($("#conPer" + fld.value).val());
			$('#shipTable .required').valid();
		} else {

			$("#sAdd1" + fld.value).val("");
			$("#sAdd2" + fld.value).val("");
			$("#sAdd3" + fld.value).val("");
			$("#sCountry" + fld.value).val("");
			$("#sState" + fld.value).val("");
			$("#sPin" + fld.value).val("");
			$("#sCity" + fld.value).val("");
			$("#sConPer" + fld.value).val("");
		}

	}
	function chkReqFields() {
		/* if($("#typeOfParty").val()=='VDR'){
			//$('#partyAddDetails').hide();
		}else{
			$('#partyAddDetails').show();
		} */
		/**if($("#typeOfParty").val()=='SLF'){
			$('#tradeRefComp1').removeClass('required');
			$('#tradeRefContact1').removeClass('required');
			$('#tradeRefPhone1').removeClass('required');
			$("#tradeRefTable .astrix").hide();
		}
		else{
			$('#tradeRefComp1').addClass('required');
			$('#tradeRefContact1').addClass('required');
			$('#tradeRefPhone1').addClass('required');
			$("#tradeRefTable .astrix").show();
		}**/
	}
</script>

</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="party" />
		<jsp:param name="subPage" value="addParty" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<div id="ajax">
				<img src="images/ajax-loader.gif" alt="Loading..." />
			</div>
			<div id="errorMsg">
				<c:out value="${errorMsgStr}"></c:out>
			</div>
			<form:form method="post" cssClass="cmxform"
				action="partyActionController.html" commandName="partyMasterData"
				name="pForm" id="pForm">
				<c:if test="${param.redirect == 1}">
					<input type="hidden" id="redirect" value="1" />
				</c:if>
				<div class="error" style="display: none;">
					<img src="images/warning.gif" alt="Warning!" width="24" height="24"
						style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
						clear="all" />
				</div>

				<fieldset id="pDetails">
					<legend>
						Party Details :(<span class="astrix">*</span> Mandatory Fields)
					</legend>
					<table width="85%" border="0" align="center">

						<tr>
							<td width="22%" class="ttl_names">Company Name<span
								class="astrix">*</span> <form:hidden path="id" id="partyId" /></td>
							<td width="28%"><form:input path="companyName"
									id="companyName" cssClass="required nameOfPerson" size="25"
									maxlength="50" onchange="checkCompany(this.value)" /></td>
							<td height="21" width="22%"><span class="ttl_names">Member
									of any association</span>
							</td>
							<td><form:input path="membersOfAccociation" cssClass="input"
									size="25" />
							</td>

						</tr>
						<tr>
							<td class="ttl_names">Owner Name</td>
							<td><form:input path="ownerName" id="ownerName"
									cssClass="input" size="25" maxlength="50" /></td>
							<td class="ttl_names">Mobile No<span class="astrix">*</span>
							</td>
							<td><form:input path="ownerMobNo" id="ownerMobNo"
									maxlength="10" size="25" cssClass="required input"
									onchange="javascript:validateMobileNo(this.value);" /></td>
						</tr>
						<tr>
							<td class="ttl_names">Your Broker (if any)</td>
							<td><form:select path="brokerPartyId" cssClass="required">
									<form:option value='-1'>None</form:option>
									<c:forEach var="b" items='${BROKER_LIST}'>
										<form:option value='${b.id}'>${b.description}</form:option>
									</c:forEach>
								</form:select></td>
							<td class="ttl_names">Telephone 1</td>
							<td><div>
									<form:input cssClass="number" size="25" id="phoneNo1"
										path="phoneNo1" maxlength="16" />
								</div></td>
						</tr>
						<tr>
							<td class="ttl_names">Business Type</td>
							<td><label> <form:select path="businessType"
										cssClass="list_box" id="select">
										<form:option value="">----Select Business Type----</form:option>
										<form:option value="Jewellery Manufacturer ">Jewellery Manufacturer </form:option>
										<form:option value="Jewellery Wholeseller">Jewellery Wholeseller</form:option>
										<form:option value="Jewellery Retailer">Jewellery Retailer</form:option>
										<form:option value="Jewellery Importer/Exporter">Jewellery Importer/Exporter</form:option>
										<form:option value="Diamond Manufacturer ">Diamond Manufacturer </form:option>
										<form:option value="Diamond Wholeseller">Diamond Wholeseller</form:option>
										<form:option value="Diamond Retailer">Diamond Retailer</form:option>
										<form:option value="Diamond Importer/Exporter">Diamond Importer/Exporter</form:option>
									</form:select> </label>
							</td>
							<td class="ttl_names">Telephone 2</td>
							<td><div>
									<form:input path="phoneNo2" id="phoneNo2"
										cssClass="number input" size="25" maxlength="16" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="ttl_names">Email</td>
							<td><form:input path="email" cssClass="email" size="25"
									maxlength="50" />
							</td>
							<td class="ttl_names">Fax</td>
							<td><form:input path="fax" id="fax" cssClass="number input"
									size="25" maxlength="16" />
							</td>
						</tr>
						<tr>
							<td class="ttl_names">Comments</td>
							<td><form:textarea path="comments" cssClass="" />
							</td>
							<td class="ttl_names">Type<span class="astrix">*</span>
							</td>
							<td><form:select path="typeOfParty" cssClass="required"
									id="typeOfParty" onchange="chkReqFields();">
									<form:option value="BYR">Buyer</form:option>
									<form:option value="BKR">Broker</form:option>
									<form:option value="SBKR">Sub Broker</form:option>
									<form:option value="VDR">Vendor</form:option>
									<form:option value="BYVD">Buyer/Vendor</form:option>
									<form:option value="SLF">Self</form:option>
								</form:select></td>
						</tr>
					</table>
					<table width="85%" border="0" align="center" id="tradeRefTable">
						<tr>
							<td colspan="2" class="field_title">Trade Reference 1</td>
							<td colspan="2" class="field_title">Trade Reference 2</td>
						</tr>
						<tr>
							<td class="ttl_names" width="22%">Company Name</td>
							<td width="28%"><form:input path="tradeRefComp1"
									cssClass="nameOfPerson" size="25" id="tradeRefComp1"
									maxlength="100" />
							</td>
							<td class="ttl_names" width="22%">Company Name</td>
							<td><form:input path="tradeRefComp2" id="tradeRefComp2"
									cssClass="nameOfPerson" size="25" maxlength="100" />
							</td>
						</tr>
						<tr>
							<td class="ttl_names">Contact person</td>
							<td><form:input path="tradeRefContact1"
									cssClass="nameOfPerson" size="25" id="tradeRefContact1"
									maxlength="50" />
							</td>
							<td class="ttl_names">Contact person</td>
							<td><form:input path="tradeRefContact2"
									cssClass="nameOfPerson" size="25" maxlength="50" />
							</td>
						</tr>
						<tr>
							<td class="ttl_names">Phone</td>
							<td><form:input path="tradeRefPhone1" cssClass="number"
									size="25" id="tradeRefPhone1" maxlength="16" />
							</td>
							<td class="ttl_names">Phone</td>
							<td><form:input path="tradeRefPhone2" cssClass="number"
									size="25" maxlength="16" />
							</td>
						</tr>
						<tr>
							<td class="ttl_names">Mobile</td>
							<td><form:input path="tradeRefMobile1" cssClass="number"
									size="25" maxlength="16" />
							</td>
							<td class="ttl_names">Mobile</td>
							<td><form:input path="tradeRefMobile2" cssClass="number"
									size="25" maxlength="16" />
							</td>
						</tr>
					</table>

				</fieldset>
				<div>
					<form:hidden path="activeFlag" id="activeFlag" />
					<!-- <input type="submit" value="Save" id="saveMaster"/>  -->
					<input type="button" value="Reset" onclick="resetDiv('pDetails');" />
					<c:if test="${partyMasterData.id != null}">
						<input type="button" value="Delete Buyer" id="DeleteBuyer" />
					</c:if>
					<c:if test="${fn:length(partyMasterData.partyAddMasters) == 0}">
						<input type="button" value="Add Branch" id="addBranch" />
					</c:if>
				</div>
				<%--<c:if test="${partyMasterData.typeOfParty == 'VDR'}">style="display:none;"</c:if> --%>
				<div id="partyAddDetails">
					<c:forEach items="${partyMasterData.partyAddMasters}" var="list"
						varStatus="status">
						<fieldset id="branch_${status.count - 1}">
							<legend id="branch">Main Office Details :</legend>
							<div align="center" style="height: 280px;">
								<div style="float: left; width: 50%;">
									<form:hidden path="partyAddMasters[${status.count - 1}].id"
										id="partyAddId" />
									<fieldset>
										<legend>Address :</legend>
										<table>
											<tr>
												<td class="ttl_names">Address Line 1</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].address1"
														cssClass="" size="15" id="add1${status.count - 1}"
														maxlength="100" />
												</td>
												<td class="ttl_names">City</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].city"
														cssClass="" size="15" id="city${status.count - 1}"
														maxlength="30" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Address Line 2</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].address2"
														cssClass="" size="15" id="add2${status.count - 1}"
														maxlength="100" />
												</td>
												<td class="ttl_names">Country</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].country"
														cssClass="" size="15" id="country${status.count - 1}"
														maxlength="25" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Address Line 3</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].address3"
														size="15" id="add3${status.count - 1}" maxlength="100" />
												</td>
												<td class="ttl_names">State</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].state"
														size="15" id="state${status.count - 1}" maxlength="50" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Pin</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].pin"
														cssClass="" size="15" id="pin${status.count - 1}"
														maxlength="15" />
												</td>
												<td class="ttl_names">fax</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].fax"
														cssClass="number" size="15" id="fax${status.count - 1}"
														maxlength="16" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Country code</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].countryPhoneCode"
														cssClass="number" size="15"
														id="countryCode${status.count - 1}" maxlength="6" />
												</td>
												<td class="ttl_names">Phone No.</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].phone"
														cssClass="number" size="15" id="phone${status.count - 1}"
														maxlength="16" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Contact Person</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].contactPerson"
														cssClass="nameOfPerson" size="15"
														id="conPer${status.count - 1}" maxlength="50" />
												</td>
												<td class="ttl_names">Designation</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].designation"
														cssClass="noSpecialChars" size="15"
														id="designation${status.count - 1}" maxlength="50" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Cell No.</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].cellNo"
														cssClass="number" size="15" id="cellNo${status.count - 1}"
														maxlength="16" />
												</td>
												<td class="ttl_names">Email</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].email"
														cssClass="email" size="15" id="email${status.count - 1}"
														maxlength="50" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Branch Code</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].branchCode"
														cssClass="noSpecialChars" size="15"
														id="branch${status.count - 1}" maxlength="25" />
												</td>
												<td colspan=2 align="center"><c:if
														test="${status.count ==1}">
														<input type="button" value="Add More Branch"
															id="addBranch" />
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
												<td><form:input
														path="partyAddMasters[${status.count - 1}].bank"
														cssClass="nameOfPerson" size="15" id="bank"
														maxlength="100" />
												</td>
												<td class="ttl_names">Acc No.</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].bankAccNo"
														cssClass="noSpecialChars" size="15" id="bankAcc"
														maxlength="50" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Address</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].bankAdd"
														size="15" id="bankAdd" maxlength="250" />
												</td>
												<td class="ttl_names">Bank Branch</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].bankBranch"
														size="15" id="bankBranch" maxlength="100"
														cssClass="noSpecialChars" />
												</td>
											</tr>

										</table>
									</fieldset>

								</div>

								<div style="float: left; width: 50%;" align="center">
									<fieldset>
										<form:hidden
											path="partyAddMasters[${status.count - 1}].partyShipAdd.partyAddId"
											id="sPartyAddId" />
										<legend>Shipping Address :</legend>
										<table id="shipTable">
											<tr>
												<td class="ttl_names" colspan="4">Copy Branch Address <input
													type="checkbox" name="shipAddr"
													id="shipAddr${status.count - 1}"
													value="${status.count - 1}" onclick="shipAdd(this);"
													style="vertical-align: middle;" /></td>
											</tr>
											<tr>
												<td class="ttl_names">Address Line 1</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.address1"
														cssClass="" size="15" id="sAdd1${status.count - 1}"
														maxlength="100" />
												</td>
												<td class="ttl_names">City</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.city"
														cssClass="" size="15" id="sCity${status.count - 1}"
														maxlength="30" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Address Line 2</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.address2"
														cssClass="" size="15" id="sAdd2${status.count - 1}"
														maxlength="100" />
												</td>
												<td class="ttl_names">Country</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.country"
														cssClass="" size="15" id="sCountry${status.count - 1}"
														maxlength="25" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Address Line 3</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.address3"
														size="15" id="sAdd3${status.count - 1}" maxlength="100" />
												</td>
												<td class="ttl_names">State</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.state"
														size="15" id="sState${status.count - 1}" maxlength="50" />
												</td>
											</tr>
											<tr>
												<td class="ttl_names">Pin</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.pin"
														cssClass="" size="15" id="sPin${status.count - 1}"
														maxlength="15" />
												</td>
												<td class="ttl_names">Contact Person</td>
												<td><form:input
														path="partyAddMasters[${status.count - 1}].partyShipAdd.contactPerson"
														cssClass="nameOfPerson" size="15"
														id="sConPer${status.count - 1}" maxlength="50" />
												</td>
											</tr>

										</table>

									</fieldset>
									<fieldset>
										<form:hidden
											path="partyAddMasters[${status.count - 1}].partyAccs[0].partyAddId"
											id="aPartyAddId" />
										<form:hidden
											path="partyAddMasters[${status.count - 1}].partyAccs[0].id"
											id="partyAccId" />
										<legend>Party Account Type :</legend>
										<table>
											<tr>
												<td class="ttl_names">Term</td>
												<td class="ttl_names" colspan=2><form:select
														path="partyAddMasters[${status.count - 1}].partyAccs[0].termId"
														cssClass="required">
														<c:forEach var="t" items='${TERMS_LIST}'>
															<form:option value='${t.id}'>${t.description}</form:option>
														</c:forEach>
													</form:select></td>
											</tr>
											<tr>
												<td class="ttl_names">Account Type</td>
												<td class="ttl_names" valign="middle"><form:radiobutton
														path="partyAddMasters[${status.count - 1}].partyAccs[0].accType"
														cssClass="required radio" id="export" value="E" /> <label
													for="local">Export</label></td>
												<td class="ttl_names" valign="middle"><form:radiobutton
														path="partyAddMasters[${status.count - 1}].partyAccs[0].accType"
														cssClass="required radio" id="local" value="L" /> <label
													for="export">Local</label></td>
											</tr>
											<tr>
												<td class="ttl_names">Currency</td>
												<td class="ttl_names" colspan=2><form:select
														path="partyAddMasters[${status.count - 1}].partyAccs[0].currency"
														cssClass="required">
														<c:forEach var="c" items='${currencyList}'>
															<option value='${c.currAbrev}'
																<c:if test="${c.currAbrev == 'USD'}">selected</c:if>>${c.currency}
															</option>
														</c:forEach>
													</form:select></td>
											</tr>
										</table>

									</fieldset>
								</div>
							</div>
							<div align="center">
								<input type="submit" value="Save" id="saveBranch_0"
									onclick="submitForm();" /> <input type="button" value="Reset"
									onclick="resetDiv('branch_${status.count - 1}');" />
								<c:if
									test="${partyMasterData.partyAddMasters[status.count - 1].id != null}">
									<input type="button" value="Delete" id="deletePartAdd" />
								</c:if>
							</div>
						</fieldset>

					</c:forEach>
				</div>

				<c:if test="${fn:length(partyMasterData.partyAddMasters) gt 0}">
					<input type="submit" value="Save" id="saveData" />
					<input type="button" value="Reset" onclick="reset()" />
				</c:if>
			</form:form>
			<!-- Data ends -->
			<br />
		</div>
		<div id="response"></div>
	</div>
</body>
</html>