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
	});
	function getParcelData(id, count) {
		$.getJSON('parcelDetailAJAX.html', {
			id : id
		}, function(data) {
			if (data != null) {
				for ( var b = 0; b < colListParcel.length; b++) {
					$('#' + colListParcel[b] + '_' + count).val(
							eval('data.' + (colListParcel[b])));
					if ($('#' + colListParcel[b] + '_' + count).val() == -1) {
						$('#' + colListParcel[b] + '_' + count).val(
								eval('data.' + (colListParcel[b]) + '_so'));
					}
				}
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>
	<div id="FixedPacket" align="center">
		<br />
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('parcelUpload.html');">Parcel Upload</a> 
		<a href="javascript:void(0);" class="buttontab" id="" onclick="func('packetUpload.html');">Packet Upload</a> 
		<a href="javascript:void(0);" class="buttontab" id="active" onclick="func('fixedPacket.html');">Fixed Packet</a> <br />
		<br />
		<br />
	
		<div id="errorMsg">${mailMsg}</div>
		<form id="FixedPacketForm" action="fixedPacketUpload.html"
			method="post">
			<br />
			<div class="Fixed">
				<a href="javascript:void(0);" onclick="addTab('Add SMPList','stockMixList.html?fixedFlag=1');"><h6>Small
					Size packet List (Fixed)</h6></a>
			</div>
			<table align="center" id="greenTable" width="100%">
				<tr id=""
					class="ui-widget-content jqgrow ui-row-ltr selected-row ui-state-hover ui-state-highlight"
					role="row" area-selected="true">
					<th>Pkt</th>
					<th>ParcelNum</th>
					<th>Parcel Type</th>
					<th>Sieve</th>
					<th colspan="2">sh</th>
					<th colspan="2">Clarity</th>
					<th colspan="2">Color</th>
					<th>Cost Price/Cts</th>
				</tr>
				<tr>
					<td colspan="4"></td>
					<th>From</th>
					<th>To</th>
					<th>From</th>
					<th>To</th>
					<th>From</th>
					<th>To</th>
					<td></td>
					<td></td>
				</tr>
				<c:forEach begin="0" end="9" var="count">
					<tr>
						<td><input type="text" name="Pkt_${count}" id="Pkt_${count}"
							size="8"></td>
						<td><select id="parcelNum_${count}" name="parcelNum_${count}"
							onchange="getParcelData(this.value, '${count}');">
								<option value="-1">All</option>
								<c:forEach var="parcel" items='${parcelList}'>
									<option value='${parcel.id}'>${parcel.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="pTyp_${count}" name="pTyp_${count}"
							style="width: 75px;">
								<c:forEach var="p" items='${PRP_LOV["PTYP"]}'>
									<option value='${p.id}'>${p.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="sieve_${count}" name="sieve_${count}"
							style="width: 75px;">
								<c:forEach var="p" items='${PRP_LOV["SIEVE"]}'>
									<option value='${p.id}'>${p.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="shFr_${count}" name="shFr_${count}"
							style="width: 75px;">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="shTo_${count}" name="shTo_${count}"
							style="width: 75px;">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="puFr_${count }" name="puFr_${count }">

								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="puTo_${count }" name="puTo_${count }">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="cFr_${count}" name="cFr_${count}">
								<option value="-1">All</option>

								<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><select id="cTo_${count}" name="cTo_${count}">
								<option value="-1">All</option>
								<c:forEach var="prpLov" items='${PRP_LOV["COL"]}'>
									<option value='${prpLov.id}'>${prpLov.description}</option>
								</c:forEach>
						</select>
						</td>
						<td><input type="text" name="baseRate_${count }"
							id="baseRate_${count }" size="8">
						</td>
						<%--  <td><input type="button" id="splitpkt_${count}" onclick="show_prompt();" value="split" />--%>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" id="submit" value="Submit" />
		</form>
	</div>
</body>
</html>