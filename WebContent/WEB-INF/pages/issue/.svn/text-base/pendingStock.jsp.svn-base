<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="css/crm/style.css" /> -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#sh").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#c").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#pu").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#sz").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		<c:forEach var='shVal' items='${paramValues.sh}'>
		$('#sh' + '${shVal}'.replace(" ", "")).attr("checked", true);
		</c:forEach>
		<c:forEach var='puVal' items='${paramValues.pu}'>
		$('#pu' + '${puVal}'.replace(" ", "")).attr("checked", true);
		</c:forEach>
		<c:forEach var='cVal' items='${paramValues.c}'>
		$('#c' + '${cVal}'.replace(" ", "")).attr("checked", true);
		</c:forEach>
	});
	function similarStk(pktCode) {
		var lab = $('input:radio[name=lab_'+pktCode+']:checked').val();
		func('similarStk.html?pktCode=' + pktCode + '&lab=' + lab);
	}
	function similarPriceStk(pktCode) {
		var lab = $('input:radio[name=lab_'+pktCode+']:checked').val();
		addTab('View Similar Packet','similarPriceStk.html?pktCode=' + pktCode + '&lab=' + lab);
	}

	function soldStk(pktCode) {
		var lab = $('input:radio[name=lab_'+pktCode+']:checked').val();
		addTab('View Similar Packet Sold','soldStk.html?pktCode=' + pktCode + '&lab=' + lab);
	}
	function getAbsolutePath() {
		var loc = window.location;
		var pathName = loc.pathname.substring(
				loc.pathname.lastIndexOf('/') + 1, loc.pathname.length);
		return pathName;
	}

	function search() {
		var path = getAbsolutePath();
		document.upload.action = path;
		document.upload.method = 'post';
		document.upload.submit();
	}
	function transferSales() {
		if ($('input:checkbox[name=select]:checked').length == 0) {
			alert('Please select the packet to transfer');
			return false;
		}
		submitFormMan('#upload');
		//$('#upload').trigger('submit');
	}
	function submitFormMan(form){
	    alert($(form).attr('action'));
	    var options = {
	            success: function(response) {
	            alert('ddd');
	                $('#container').html(response);
	                $('#ajaxMessage').show();
	                $('.ajaxMsg').html('<p>Process completed successfully.</p>');//Changed to use CSS effectively TODO
	            },
	            clearForm: true,    
	            iframe: true,
	            error: function(){
	                $('#ajaxMessage').html('<p>Error in submission, please try again.</p>');
	                $('#ajaxMessage').show();
	            },
	            url: $(form).attr('action')
	        };
	        $(form).ajaxSubmit(options);
	        return false;
	}
	function mailSold(pktCode) {
		var lab = $('input:radio[name=lab_' + pktCode + ']:checked').val();
		$.getJSON('emailToByrAJAX.html', {
			pktCode : pktCode,
			lab : lab
		}, function(data) {
			if (data && data != null) {
				alert(data);
			}
		});
	}
    function pktHistoryLink(pktCode) {
	        func('loadPriceHistory.html?pktNo=' + pktCode);
	    }
	function rapChk(pktCode, grp) {
		var rate = $('#rate_' + pktCode + '_' + grp).val();
		if (isNaN(rate) || rate == 0) {
			$('#rate_' + pktCode).val('');
			alert('Invalid Rate');
			return;
		}
		$('input[name*="rapPrice_' + pktCode + '_"]').each(
				function() {
					var name = this.name;
					var value = this.value;
					var grpId = name.substring(name.lastIndexOf('_') + 1);
					if (!isNaN(value) && value > 0) {
						var rap = ((1 - rate / value) * -100);
						$('#rap_' + pktCode + '_' + grpId).val(
								parseFloat(rap).toFixed(2));
					}
				});
	}
	function rateChk(pktCode, grp) {
		var rap = $('#rap_' + pktCode + '_' + grp).val();
		if (isNaN(rap)) {
			$('#rap_' + pktCode + '_' + grp).val('');
			alert('Invalid rap');
			return;
		}
		var rapPrice = $('#rapPrice_' + pktCode + '_' + grp).val()
		if (!isNaN(rapPrice) && rapPrice > 0) {
			var rate = rapPrice * (1 + rap / 100);
			$('#rate_' + pktCode + '_' + grp).val(parseFloat(rate).toFixed(2));
		}
	}
	function checkAll() { // this line makes sure this code runs on page load
		if ($('#select_all').attr("checked") == true) {
			$('input[id*="select_"]').attr('checked', true);
		} else {
			$('input[id*="select_"]').attr('checked', false);
		}
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="${from}" />
	</jsp:include>
	<div class="container" id="container">
		<!-- <div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div> -->
		<div id="errorMsg">

			<c:forEach var="s" items="${eMsg}" varStatus="count">
		          ${s.id} - ${s.description}<br />
			</c:forEach>

			<c:remove var="eMsg" scope="session"/>
		</div>

		<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->
		<form action="transfer.html" method="post"
			name="upload" id="upload">
			<span id="shape"> <label for="sh" class="ttl_names">Shape</label>
				<select multiple="multiple" id="sh" name="sh">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
						<option value='${prpLov.description}'>${prpLov.description}</option>
					</c:forEach>
			</select> </span> <label for="cts" title="Carats">cts</label> <input type="text"
				name="cts_from" id="cts_from" class="inputText"
				onblur="javascript:chkNumVal(this,this.value,'0','50');" size="10"
				value="${param.cts_from}" /> <input type="text" name="cts_to"
				id="cts_to" class="inputText"
				onblur="javascript:chkNumVal(this,this.value,'0','50');" size="10"
				value="${param.cts_to}" /> <span id="color"> <label for="c"
				class="ttl_names">Color</label> <select multiple="multiple" id="c"
				name="c">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
						<option value='${prpLov.description}'>${prpLov.description}</option>
					</c:forEach>
			</select> </span> <span id="clarity"> <label for="pu" class="ttl_names">Clarity</label>
				<select multiple="multiple" id="pu" name="pu">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
						<option value='${prpLov.description}'>${prpLov.description}</option>
					</c:forEach>
			</select> </span> <br /> <input type="text" id="pktCode" name="pktCode"
				value="${param.pktCode}" /> <input type="button" onclick="search();"
				value="search"> <br /> <label for="rapUpload">Also
				Upload in Rap</label> <input type="checkbox" value="1" name="rapUpload"
				id="rapUpload"> 
				<input type="button" onclick="transferSales();" value="Move to Sales">
			<table class="greenTable">
				<tr>
					<td colspan="15">Total Stock in section - <span id="cnt"></span>
					</td>
				</tr>
				<tr>
				<th style="white-space: nowrap;">Sr No <input type="checkbox"
						value="all" name="select_all" id="select_all"
						onclick="checkAll();"></th>
					<th>Pkt No</th>
					<th>Shape</th>
					<th>Cts</th>
					<th>Color</th>
					<th>Purity</th>
					<th>Cut</th>
					<th>Polish</th>
					<th>Sym</th>
					<th>Lab</th>
					<th>Flour</th>
					<th>FL Col.</th>
					<th>Depth</th>
					<th>Table</th>
					<th>RapPrice</th>
					<th>Discount</th>
					<th>Rate</th>
					<th>Base Rate</th>
					<th>default</th>
					<th>RapStatus</th>
					<th>Stock</th>
					<th title="To similar buyers">Send mail</th>
					<th>Measurement</th>
				</tr>
				<c:set var="counter" value="0"></c:set>
				<c:set var="css" value="fff"></c:set>
				<c:forEach var="s" items="${pendingStock}" varStatus="count">
					<c:if test="${s.pktCode != pktNo}">
						<c:choose>
							<c:when test="${css eq 'ccc'}">
								<c:set var="css" value="fff"></c:set>
							</c:when>
							<c:when test="${css eq 'fff'}">
								<c:set var="css" value="ccc"></c:set>
							</c:when>
						</c:choose>
						<c:set var="counter" value="${counter+1}"></c:set>
					</c:if>
					<tr style="background-color: #${css}">
						<td style="text-align: right; white-space: nowrap;">
						<c:if test="${s.pktCode != pktNo}">
							${counter}
							<input type="checkbox" value=${s.pktCode } name="select"
											id="select_${s.pktCode}">
						</c:if>
						<a href="javascript:void(0);" title="Click here to Edit" onclick="addTab('Edit Stock','stockEntry.html?pktId=${s.pktCode}&grpId=${s.grpId}');">Edit</a></td>
						<!--  href="http://localhost:8080/projectileLive/loadPriceHistory.html?pktNo=${s.pktCode}" -->
						<td><c:if test="${s.pktCode != pktNo}">
								<a href="javascript:void(0)"
									onclick="addTab('View Packet History','loadPriceHistory.html?pktNo=${s.pktCode}');"
									title="click here to view Packet History">${s.pktCode}</a>
							</c:if>
						</td>
						<td>${s.sh}</td>
						<td>${s.cts}</td>
						<td>${s.c}</td>
						<td>${s.pu}</td>

						<td>${s.ct}</td>
						<td>${s.po}</td>
						<td>${s.sy}</td>
						<td>${s.lab}</td>
						<td>${s.fls}</td>
						<td>${s.flc}</td>
						<td>${s.dp}</td>
						<td>${s.t}</td>
						<td><input type="text" value="${s.rapPrice}"
							name="rapPrice_${s.pktCode}_${s.grpId}"
							id="rapPrice_${s.pktCode}_${s.grpId}" readonly="readonly"
							size="8" />
						</td>
						<td><input type="text" value="${s.rap}"
							name="rap_${s.pktCode}_${s.grpId}"
							id="rap_${s.pktCode}_${s.grpId}" size="8"
							onchange="rateChk('${s.pktCode}','${s.grpId}');" /></td>
						<td><input type="text" value="${s.rate}"
							name="rate_${s.pktCode}_${s.grpId}"
							id="rate_${s.pktCode}_${s.grpId}" size="8"
							onchange="rapChk('${s.pktCode}', '${s.grpId}');" /></td>
						<td>${s.baseRate}</td>
						<td><input type="hidden" name="grpId_${s.pktCode}"
							value="${s.grpId}" id="grpId__${s.pktCode}_${s.grpId}"> <input
							type="hidden" name="lab_so_${s.pktCode}_${s.grpId}"
							value="${s.lab_so}" id="lab_so_${s.pktCode}_${s.grpId}">
							<input type="hidden" name="lab_${s.pktCode}_${s.grpId}"
							value="${s.lab}" id="lab_${s.pktCode}_${s.grpId}"> <input
							type="radio" value="${s.grpId}" name="default_${s.pktCode}"
							id="default_${s.pktCode}_${s.grpId}"
							<c:if test="${s.grpId == 1}">checked="checked"</c:if>></td>
						<td><c:if test="${s.pktCode != pktNo}">
								<c:if test="${s.rapnetFlag == 1}">Uploaded</c:if>
								<c:if test="${s.rapnetFlag != 1}">New</c:if>
							</c:if></td>
						<td><c:if test="${s.pktCode != pktNo}">
								<a href="javascript:void(0)"
									onclick="similarPriceStk('${s.pktCode}');"
									title="click here view similar packets from stock">similar
								</a>| <a href="javascript:void(0)"
									onclick="soldStk('${s.pktCode}');"
									title="click here view similar packets from Sold items">sold</a>
							</c:if></td>
						<td><a href="javascript:void(0)"
							onclick="mailSold('${s.pktCode}');"
							title="click here to send email to buyers who shown intrest in stone">email</a>
						</td>
						<td>${s.md}*${s.xd}*${s.d}</td>
						<c:set var="pktNo" value="${s.pktCode}"></c:set>
					</tr>
				</c:forEach>
			</table>
			<!-- <script type="text/javascript">
		$('#cnt').html('${counter}');
		</script> -->
		</form>
	</div>
	<div id="response"></div>
</body>
</html>