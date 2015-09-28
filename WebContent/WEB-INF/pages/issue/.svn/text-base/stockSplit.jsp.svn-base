<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
 // var colList = new Array();
  var colListHTML = new Array();
  var html= '' ;
 // var rapFormat = 0;

  
  $(document).ready(function(){
	  if('${success}' == 1){
		window.opener.location.reload(true)
	  	window.close();
	  }
	  
  });
	var minReqPrp =new Array("Pkt","totcts","rate");
	function validateForm(){
		var pktCnt = 10; 
		var count = 0;
		var totCts = 0;
		for(var a =0 ;a<pktCnt ;a++){
			if(!$('#Pkt_'+a) || $('#Pkt_'+a).val() == ''){
				continue;	
			}else{
				count++;
				var pktCode = $('#Pkt_'+a).val();
				for(var b =1 ;b<minReqPrp.length ;b++){
						var rate =$('#rate_'+a).val();
						if(isNaN(rate)){
							alert('Enter Numeric Rate for Stone ID '+pktCode);
							return false;
						}
						continue;
					}
					if($('#'+ minReqPrp[b] +'_'+a).val()=='' ){
						alert('Enter minimum required details for Stone ID '+pktCode);	
						return false;
					} 
				}
			var cts =$('#cts_'+a).val();
			totCts += cts;
			var pcs =$('#pcs_'+a).val();
			if(cts == ''&& pcs ==''){
				alert('Enter atleast pcs or carats for Stone ID '+pktCode);
				return false;
			}
			if(isNaN(cts) && isNaN(pcs)){
				alert('Enter correct pcs or carats for Stone ID '+pktCode);
				return false;
			}
			checkMinMax('shFr_'+a, 'sh');
			checkMinMax('puFr_'+a, 'pu');
			checkMinMax('cFr_'+a, 'c');
		}
		if(totCts > '${SPLIT_DATA_LIST.cts}'){
			alert('Sum of Cts exceeds the Total cts ');
			return false;
		}
		if(count == 0){
			alert('Enter Stone Id');
			return false;
		}
		return true;
	}
 </script>

</head>
<body>


	<form id="stockErSplitMx" name="stockErSplitMx"
		action="stockEntrySplitMixSubmit.html" method="post">

		<table align="center" id="greenTable" width="100%">
			<!--  <tr>  -->
			<tr>

				<th>Pkt</th>
				<th>ParcelNum</th>
				<th>Parcel Type</th>
				<th>Sieve</th>
				<th colspan="2">sh</th>
				<th>Cts(Avg)</th>
				<th>Total Cts</th>
				<th>Pcs</th>
				<th>Rate</th>
				<th>Root</th>
				<th colspan="2">Clarity</th>
				<th colspan="2">Color</th>
				<th>Base Price</th>


			</tr>
			<tr>
				<td colspan="4"></td>
				<th>From</th>
				<th>To</th>
				<td colspan="5"></td>
				<th>From</th>
				<th>To</th>
				<th>From</th>
				<th>To</th>
				<td></td>
				<td><input type="hidden" value="${param.count}" name="count"
					id="count"> <input type="hidden" value="${param.pktId}"
					name="pktId" id="pktId"></td>
			</tr>

			<c:forEach begin="1" end="${param.count}" var="count">
				<tr>

					<td><input type="text" name="Pkt_${count}" id="Pkt_${count}"
						size="8">
					</td>
					<td><select id="parcelNum_${count}" name="parcelNum_${count}"
						onchange="getParcelData(this.value, '${count}');">
							<option value="-1">All</option>
							<c:forEach var="parcel" items='${parcelList}'>
								<option value='${parcel.id}'
									<c:if test="${parcel.id == SPLIT_DATA_LIST.parcelNum}">selected</c:if>>${parcel.description}</option>
							</c:forEach>
					</select></td>
					<td><select id="pTyp_${count}" name="pTyp_${count}"
						style="width: 75px;">
							<c:forEach var="p" items='${PRP_LOV["PTYP"]}'>
								<option value='${p.id}'
									<c:if test="${p.id == SPLIT_DATA_LIST.PTYP}">selected</c:if>>${p.description}</option>
							</c:forEach>
					</select></td>
					<td><select id="sieve_${count}" name="sieve_${count}"
						style="width: 75px;">
							<c:forEach var="p" items='${PRP_LOV["SIEVE"]}'>
								<option value='${p.id}'>${p.description}</option>
							</c:forEach>
					</select></td>
					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.shFr_so}"
							type="number" maxFractionDigits="0" var="shFr" /> <select
						id="shFr_${count}" name="shFr_${count}" style="width: 75px;">
							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == shFr}">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>
					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.shTo_so}"
							type="number" maxFractionDigits="0" var="shTo" /> <select
						id="shTo_${count}" name="shTo_${count}" style="width: 75px;">
							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == shTo}">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>
					<td><input type="text" id="cts_${count }"
						name="cts_${count }" size="5"
						onchange="chkNumVal(this, this.value, 0,10000000);"
						value="${SPLIT_DATA_LIST.AVGCTS}"></td>
					<td><input type="text" id="totcts_${count }"
						name="totcts_${count }" size="5"
						onchange="chkNumVal(this, this.value, 0,10000000);"
						value="${SPLIT_DATA_LIST.cts/param.count}"></td>
					<td><input type="text" name="pcs_${count}" id="pcs_${count }"
						size="5" onchange="chkNumVal(this, this.value, 0,10000000);">
					</td>
					<td><input type="text" name="rate_${count }"
						id="rate_${count }" size="5"
						onchange="chkNumVal(this, this.value, 0,10000000);"
						value="${SPLIT_DATA_LIST.rate}"></td>
					<td><input type="text" name="rootpkt_${count }"
						id="rootpkt_${count }" size="5" value="${SPLIT_DATA_LIST.rootPkt}">
					</td>
					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.puFr_so}"
							type="number" maxFractionDigits="0" var="puFr" /> <select
						id="puFr_${count }" name="puFr_${count }">
							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == puFr}">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>
					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.puTo_so}"
							type="number" maxFractionDigits="0" var="puTo" /> <select
						id="puTo_${count }" name="puTo_${count }">

							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == puTo}">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>

					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.cFr_so}"
							type="number" maxFractionDigits="0" var="cFr" /> <select
						id="cFr_${count}" name="cFr_${count}">
							<option value="-1">All</option>

							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == cFr}">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>
					<td><fmt:formatNumber value="${SPLIT_DATA_LIST.cTo_so}"
							type="number" maxFractionDigits="0" var="cTo" /> <select
						id="cTo_${count}" name="cTo_${count}">
							<option value="-1">All</option>
							<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
								<option value='${prpLov.id}'
									<c:if test="${prpLov.id == cTo }">selected</c:if>>${prpLov.description}</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" id="submit" value="Submit" />
	</form>

</body>
</html>




