<%@include file="/WEB-INF/pages/include/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
	var termsData = new Array();
	$(document).ready(function() {

		$("#memoDate").DatePicker({
			format : 'd-m-Y',
			current : '',
			calendars : 1,
			date : $('#memoDate').val(),
			onChange : function(formated, dates) {
				$('#memoDate').DatePickerHide();
			}
		});
		$("#dueDate").DatePicker({
			format : 'd-m-y',
			calendars : 1,
			date : $('#dueDate').val(),
			onChange : function(formated, dates) {
				$('#dueDate').val(formated);
				$('#dueDate').DatePickerHide();
			}
		});

		<c:forEach items="${TERMS_MASTER}" var="p" varStatus="count">
		termsData["${p.id}"] = "${p.factor}";
		</c:forEach>

	});
	function checkPktSelect(fld) {
		if (fld.checked == false) {
			var flag = confirm('This packet will be returned from Memo and will be available \n Are you sure you want to return ?');
			if (!flag) {
				fld.checked = true;
			}
		}
	}
	function pktDisc(pktId) {
		alert("pktdisc");
		var disc = $('#disc_' + pktId).val();
		var rapPrice = $('#rapPrice_' + pktId).val();
		//var rate = $('#rate_'+pktCode).val();
		var cts = $('#cts_' + pktId).val();
		var rate = (((disc / 100) + 1) * rapPrice).toFixed(2);
		var total = (rate * cts).toFixed(2);
		$('#rate_' + pktId).val(rate);
		$('#total_' + pktId).html(total);
		$('#totalLc_' + pktId).html(total * $('#memoExRate').val());
	}
	function pktRate(pktId) {
		var rate = $('#rate_' + pktId).val();
		var rapPrice = $('#rapPrice_' + pktId).val();
		var addDisc = $('#addDisc_' + pktId).val();
		var cts = $('#cts_' + pktId).val();

		var disc = (((rate / rapPrice) - 1) * 100).toFixed(2);
		$('#disc_' + pktId).val(disc);
		var total = (rate * cts).toFixed(2);
		$('#total_' + pktId).html(total);
		$('#totalLc_' + pktId).html(total * $('#memoExRate').val());

	}
	function pktRej(pktId) {
		//var disc = $('#disc_'+pktCode).val();
		var rej = $('#rej_' + pktId).val();
		var totalCts = $('#totalCts_' + pktId).val();
		var acts = $('#acts_' + pktId).val();
		var cts = (totalCts - ((rej / 100) * totalCts)).toFixed(2);
		if (cts > acts) {
			alert("CTS cannot be increased after issued");
			$('#cts_' + pktId).val(acts);
			$('#rej_' + pktId).val((100 - (acts / totalCts) * 100).toFixed(2));
			return;
		}
		$('#cts_' + pktId).val(cts);

		var rate = $('#rate_' + pktId).val();
		var total = (rate * cts).toFixed(2);
		$('#total_' + pktId).html(total);
		$('#totalLc_' + pktId).html(total * $('#memoExRate').val());
	}
	function pktCts(pktId) {
		//var disc = $('#disc_'+pktCode).val();
		var cts = $('#cts_' + pktId).val();
		var acts = $('#acts_' + pktId).val();
		if (cts > acts) {
			alert("CTS cannot be increased after issued");
			$('#cts_' + pktId).val(acts);
			$('#rej_' + pktId).val((100 - (acts / totalCts) * 100).toFixed(2));
			return;
		}

		var totalCts = $('#totalCts_' + pktId).val();
		var rej = (100 - (cts / totalCts) * 100).toFixed(2);
		$('#rej_' + pktId).val(rej);

		var rate = $('#rate_' + pktId).val();
		var total = (rate * cts).toFixed(2);
		$('#total_' + pktId).html(total);
		$('#totalLc_' + pktId).html(total * $('#memoExRate').val());
	}

	function popUpClose() {
		window.close();
	}
	function onTermChange() {
		var termId = $('#term').val();
		if (termId == -1) {
			$('input[id*="disc_"]').each(function() {
				var thisId = this.id;
				var id = thisId.substring(thisId.indexOf('_') + 1);
				var cshDisc = $('#cshDisc_' + id).val();
				this.value = parseFloat(cshDisc) + 5;
				pktDisc(id);
			});
		} else {
			var factor = termsData[termId];
			$('input[id*="rate_"]').each(
					function() {
						var thisId = this.id;
						var id = thisId.substring(thisId.indexOf('_') + 1);
						var cshRate = $('#cshRate_' + id).val();
						this.value = (parseFloat(factor) * parseFloat(cshRate))
								.toFixed(2);
						pktRate(id);
					});

		}

	}
	function saveMemo(){
		//alert("called");
		document.searchForm.action = 'saveMemoEdit.html';
        document.searchForm.method = 'post';
        document.searchForm.submit();
	}
</script>
</head>
<body>
	<c:remove var="ses_criteria" />
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="sales"/>
		<jsp:param name="subPage" value="memo"/>
	</jsp:include> 
	<div class="container">
		<%-- <c:if test="${param.submit ==1}">
			<script>
				$('.addMsg').html('<p>Process completed successfully.</p>');
				window.opener.location.reload(true);
				window.close();
			</script>
		</c:if> --%>
		<div class="addMsg"></div>
		<form id="searchForm2" name="searchForm2" action="saveMemoEdit.html">
			<div class="content">
				<input type="hidden" name="orderId" id="orderId"
					value="${param.orderId}" />
				<table align="center" width="95%">
					<tr>
						<td class="ttl_names">Buyer :</td>
						<td>${orderMasterData.companyName}</td>

						<td class="ttl_names">Memo Date:</td>
						<td><input type="text" name="memoDate" id="memoDate"
							value="${orderMasterData.orderDate}" /></td>

						<td class="ttl_names">Term :</td>
						<td><select name="term" id="term" onchange="onTermChange();">
								<option value="-1">Asking Price</option>
								<c:forEach var="t" items='${TERMS_LIST}' varStatus="cnt">
									<c:if test="${t.id != 0}">
										<option value='${t.id}'
											<c:if test="${t.id == orderMasterData.termId}">selected</c:if>>${t.description}</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td colspan=2><input type="hidden" name="cashDisc"
							id="cashDisc" value="-5" /></td>
					</tr>
					<tr>
						<td class="ttl_names">Broker :</td>
						<td><select id="brokerId" name="brokerId">
								<option value="-1">---No broker---</option>
								<c:forEach items="${BROKER_LIST}" var="b" varStatus="count">
									<option value='${b.id}'
										<c:if test="${b.id == orderMasterData.brokerId}">selected</c:if>>${b.description}</option>
								</c:forEach>
						</select> <span class="ttl_names">Brokrage :</span> <input type="text"
							name="brokerage" id="brokerage" size=3
							value="${orderMasterData.brokrage}" />
						<td class="ttl_names">Due Date:</td>
						<td><input type="text" name="dueDate" id="dueDate"
							value="${orderMasterData.dueDate}" /></td>

						<td class="ttl_names">EX. Rate :</td>
						<td><input type="text" name="exRate" id="memoExRate"
							value="${orderMasterData.exrate}" size=10 /></td>
						<td class="ttl_names"><label for="accType_L">Local :
						</label><input type="radio" name="accType" id="accType_L"
							<c:if test="${orderMasterData.accType == 'L'}">checked="checked"</c:if>
							value="L" /><label for="accType_E">Export : </label> <input
							type="radio" name="accType" id="accType_E"
							<c:if test="${orderMasterData.accType == 'E'}">checked="checked"</c:if>
							value="E" />
						</td>

					</tr>
				</table>
				<div align="center">
					<div id="errorMsg"></div>
					<table align="center" id="greenTable" width="100%">
						<tr>
							<th>Select</th>
							<th>Packet No</th>
							<th>Shape</th>
							<th>Color</th>
							<th>Clarity</th>
							<th>Cts</th>
							<th>Discount</th>
							<th>Rej</th>
							<!-- <th>Add Disc</th>-->
							<th>Rate</th>
							<th>Total</th>
							<th>Total - (INR)</th>
						</tr>

						<c:forEach items="${orderMasterData.packetList}" var="p">
							<tr>
								<td><input type="checkbox" value="${p.pktId}"
									id="selectedPkts_${p.pktId}" checked="checked"
									name="selectedPkts" onclick="checkPktSelect(this);"> <input
									type="hidden" name="rapPrice_${p.pktId}"
									id="rapPrice_${p.pktId}" value="${p.rapPrice}"></td>
								<td>${p.pktCode}</td>
								<td>${p.sh}</td>
								<td>${p.c}</td>
								<td>${p.pu}</td>
								<td><input type="hidden" name="acts_${p.pktId}"
									id="acts_${p.pktId}" value="${p.cts}"> <c:if
										test="${p.pcs == 1}">
						  		${p.cts}<input type="hidden" name="cts_${p.pktId}"
											id="cts_${p.pktId}" value="${p.cts}">
									</c:if> <c:if test="${p.pcs != 1}">
										<input type="text" name="cts_${p.pktId}" id="cts_${p.pktId}"
											value="${p.cts}" onchange="pktCts('${p.pktId}');" size="7">
									</c:if></td>
								<td><c:if test="${p.pcs == 1}">
										<c:if test="${p.rapPrice != '' && p.rapPrice != 0}">
											<input type="text" name="disc_${p.pktId}"
												id="disc_${p.pktId}" size="7" value="${p.rap}"
												onchange="pktDisc('${p.pktId}');" />
										</c:if>
									</c:if></td>
								<td><c:if test="${p.pcs != 1}">
										<input type="text" name="rej_${p.pktId}" id="rej_${p.pktId}"
											size="7" value="${100-(p.cts/(p.rejCts+p.cts))*100}"
											onchange="pktRej('${p.pktId}');" />
										<input type="hidden" name="totalCts_${p.pktId}"
											id="totalCts_${p.pktId}" value="${(p.rejCts+p.cts)}" />
									</c:if></td>
								<!-- <td><input type="text" name="addDisc_${p.pktCode}" id="addDisc_${p.pktCode}" size="7" value="${p.addDisc}" onchange="addDisc('${p.pktCode}');"/></td> -->
								<td><input type="text" name="rate_${p.pktId}"
									id="rate_${p.pktId}" size="7" value="${p.rate}"
									onchange="pktRate('${p.pktId}');" /> <input type="hidden"
									name="cshDisc_${p.pktId}" id="cshDisc_${p.pktId}"
									value="${p.rap}"> <input type="hidden"
									name="cshRate_${p.pktId}" id="cshRate_${p.pktId}"
									value="${p.rate}"></td>
								<td id="total_${p.pktId}"><fmt:formatNumber type="number"
										value="${p.totalRate}" pattern="0.00" />
								</td>
								<td id="totalLc_${p.pktId}"><fmt:formatNumber type="number"
										value="${p.totalRate* orderMasterData.exrate}" pattern="0.00" />
								</td>
							</tr>
						</c:forEach>

					</table>
					<table>
						<tr>
							<td>Contact Person</td>
							<td><input type="text" name="contactPerson"
								id="contactPerson" value="${orderMasterData.contactPerson}">
							</td>
						</tr>
						<tr>
							<td>Comments:</td>
							<td><textarea name="comments" id="comments">${orderMasterData.comments}</textarea>
							</td>
						</tr>
					</table>
					<input type="submit" id="submit" value="Save" /> 
					<input type="button" id="Cancel" value="Cancel" onclick="popUpClose()" />
				</div>
			</div>
		</form>
		<div class="ttl_names">Note: Values shown here will be as per
			values uploaded by file/form in purchase.</div>
	</div>
</body>
</html>