<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
  function loadStock(pktCode,fileId,count,pktId,grpId){
		$.getJSON('stockSearchAJAX.html',{pktCode: pktCode, fileId:fileId,pktId:pktId,grpId:grpId}, function(data) {
			if(data !=null && data.statusMsg && data.statusMsg.length > 0){
				$('#pktCode_'+count).val('');
				alert(data.statusMsg);
			}
			else if(data == null){
				for(var b =1 ;b<colList.length ;b++){
					$('#'+colList[b]+'_'+count).val('');
					$('#rapPrice_'+count).html('');
					$('#'+colList[b]+'_'+count).removeClass('error');
				}
				$('#delete_'+count).hide();
				$('#span_'+count).remove();
			}else{
			for(var b =1 ;b<colList.length ;b++){
	  				if(colList[b] == ''){
						continue;
	  	  	  		}
					$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))));
					if(colList[b] =='rate' ){
						$('#rapPrice_'+count).html(eval('data.rapPrice'));
					}
					if(colList[b] == 'rap' && rapFormat == 100){
						var rapDisc = parseFloat(parseFloat(eval('data.rap'))/rapFormat);
						$('#rap_'+count).val(rapDisc);
					}
					if(colList[b]!= 'FNC' && colList[b]!= 'FNCO'){
	  					if((eval('data.'+ (colList[b].replace(/-/g,'')))) == null && eval('data.'+ (colList[b].replace(/-/g,''))+'_val') != null){
	  						$('#'+colList[b]+'_'+count).addClass('error');
						} 
					}else{
						$('#'+colList[b]+'_'+count).val(eval('data.'+ (colList[b].replace(/-/g,''))+'_val'));
						}
				}
			if($('#pktCode_'+count).val()==''){
				$('#pktCode_'+count).val(data.pktCode);
			}
	  		$('#labVal_'+count).val(data.LAB_val);
	  		$('#grpId_'+count).val(data.grpId);

	  		$('#delete_'+count).show();
			$('#span_'+count).remove();
			$($('#pktCode_'+count)).parents('td:first').append('<span id="span_'+count+'"><br/><a style="display:block;" href="javascript:void(0);" onclick="window.open(\'labLoadRep.html?pktCode='+pktCode+'\',\'_blank\', \'resizable=1,width=800,height=400\');">LAB</a></span>');
			}
			
		});
	}

  function updateMap(){
		var count = $('#pktCount').val();
		var addStockHtml= '';
		for(var z =0 ;z<count ;z++){
			var tempHtml = html.replace(/ZZZ/g, z);
			tempHtml =tempHtml.replace(/ZZ/g, z+1);
			addStockHtml += tempHtml;
		}
		$('#addStock').html(addStockHtml);
		<c:if test="${editPacket == 1}">
			loadStock("",$('#fileId2').val(),count-1, '${param.pktId}', '${param.grpId}');
			$('#LAB_0').attr('readonly',true);
		</c:if>
	}
  var colList = new Array();
  var html= '' ;
  $(document).ready(function(){
	  var index = 0;
	  var editPkt = true;
		html += '<tr id="row_ZZZ">';
		html += '<td>ZZ  <input type="hidden" name="labVal_ZZZ"  id="labVal_ZZZ"/><input type="hidden" name="grpId_ZZZ"  id="grpId_ZZZ"/></td>';
		<c:forEach var="lst" items="${fileMappingList}">
		<c:if test="${lst.columnName != null}">
				var onchange = '';
				<c:set var="property" value="${lst.columnName}"/>
				<c:if test="${lst.columnName == 'shFr'}">
					<c:set var="property" value="SH"/>
				</c:if>
				<c:if test="${lst.columnName == 'shTo'}">
					<c:set var="property" value="SH"/>
				</c:if>
				<c:if test="${lst.columnName == 'puTo'}">
					<c:set var="property" value="PU"/>
				</c:if>
				<c:if test="${lst.columnName == 'puFr'}">
					<c:set var="property" value="PU"/>
				</c:if>
				<c:if test="${lst.columnName == 'cTo'}">
					<c:set var="property" value="COL"/>
				</c:if>
				<c:if test="${lst.columnName == 'cFr'}">
					<c:set var="property" value="COL"/>
				</c:if>
				<c:if test="${lst.columnName == 'sieveFr'}">
					<c:set var="property" value="SIEVE"/>
				</c:if>
				<c:if test="${lst.columnName == 'sieveTo'}">
					<c:set var="property" value="SIEVE"/>
				</c:if>
				if('${lst.columnName}' == 'SH'|| '${lst.columnName}' == 'PU'|| '${lst.columnName}' == 'CTS'){
					onchange = 'onchange="getNewRap(ZZZ);"';
				}
				if('${lst.columnName}' == 'C'){
					onchange += 'onchange="rapAllowed(ZZZ);getNewRap(ZZZ);"';
				}
				if('${lst.columnName}' == 'rate'){
					onchange = 'onchange="rapAllowed(ZZZ);rapAllowedRate(ZZZ)"';
					html += '<td id="rapPrice_ZZZ"></td>';
				}
				if('${lst.columnName}' == 'rap'){
					onchange = 'onchange="rapChange(ZZZ)"';
				}
			<c:choose>
				<c:when test="${!empty PRP_LOV[property] && lst.columnName != 'FNCO' && lst.columnName != 'FNC'}">
				html +='<td><select name="${ lst.columnName}_ZZZ" id="${lst.columnName}_ZZZ" class="select" style="width:60px;" '+onchange+'>'+
							'<option value="">All</option>'+
							<c:forEach var="prpLov" items="${PRP_LOV[property]}">
								'<option value="${prpLov.id}">${prpLov.description}</option>'+
							</c:forEach>
						'</select></td>';					
				</c:when>
				<c:otherwise>
					html +='<td><input type="text" name="${lst.columnName}_ZZZ" id="${lst.columnName}_ZZZ" size="4" '+onchange+' /></td>';
					if('${lst.columnName}' == 'pktCode'){
						//pktcode Only be input
						if(editPkt)
							html +='<td><input type="text" name="editPktCode_ZZZ" id="editPktCode_ZZZ" size="4" /></td>';
						editPkt = false; // make it false so not to have this again
					}
				</c:otherwise>
			</c:choose>
			colList[index]= '${lst.columnName}';
			index++;
		</c:if>
	</c:forEach>
	html += '</tr>';
      updateMap();
		
});
  </script>
</head>

<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="stockEntryMix" />
	</jsp:include>
	<c:set var="count" value="0"></c:set>
	<div id="msg">${msg}</div>
	<br></br>
	<form action="uploadStockMixedManual.html" method="post">
		<input type="hidden" id="pktCount" name="pktCount" value="1" /> <input
			type="hidden" id="countEdit" value="1" /> <input type="hidden"
			id="fileId2" name="fileId2" value="${fileMappingList[0].fileId}" />
		<table id="greenTable">
			<thead>
				<tr>
					<th>Sr No.</th>
					<c:forEach var="lst" items="${fileMappingList}">
						<c:if test="${lst.columnName != null}">
							<c:if test="${lst.columnName == 'rate'}">
								<th>Rap Price</th>
							</c:if>
							<th title="${lst.columnName}">${lst.excelColumnName}</th>
							<c:if test="${lst.columnName == 'pktCode'}">
								<c:if test="${pageScope.editPkt != false}">
									<th>Stone ID (Edit)</th>
									<c:set var="editPkt" value="false" scope="page"></c:set>
								</c:if>
							</c:if>
						</c:if>
					</c:forEach>
				</tr>
			</thead>
			<tbody id="addStock">
			</tbody>
		</table>
		<input type="submit" name="submit" value="update">
	</form>
</body>
</html>