<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
  var colList = new Array();
  
   function loadLab(pktCode) {
	   	func('labLoad.html?pktCode='+pktCode);
   }
   function addLab(){
	   var html = '<tr id="add">' ;
	   var index = 0;
	   <c:forEach var="lst" items="${prpList}">
			<c:choose>
				<c:when test="${!empty PRP_LOV[lst.prp] && lst.prp != 'FNCO' && lst.prp != 'FNC'}">
				html +='<td><select name="${lst.prp}" id="${lst.prp}" class="select" style="width:50px;">'+
							'<option value="">All</option>'+
							<c:forEach var="prpLov" items="${PRP_LOV[lst.prp]}">
							 	'<option value="${prpLov.id}">${prpLov.description}</option>'+
							</c:forEach>
						'</select></td>';					
				</c:when>
				<c:otherwise>
					html +='<td><input type="text" name="${lst.prp}" id="${lst.prp}" size="3"  /></td>';
				</c:otherwise>
			</c:choose>
			colList[index]= '${lst.prp}';
			index++;
	</c:forEach>
	html +='<td><input type="checkbox" name="default" id="default" value="1"/></td>';
	html +='<td><input type="button" name="add" id="add" value="add" onclick="submit();"/></td>';
	html += '</tr>' ;
	
	
	$('#grpId').val('');
	$('#addStock').html($('#addStock').html()+html);
	$('tr[id*="edit_"]').remove();
   }
   function editLab(grp){
	   var html= '<tr id="edit_'+grp+'">' ;
	   var index = 0;
	   <c:forEach var="lst" items="${prpList}">
			<c:choose>
				<c:when test="${!empty PRP_LOV[lst.prp] && lst.prp != 'FNCO' && lst.prp != 'FNC'}">
				html +='<td><select name="${lst.prp}_'+grp+'" id="${lst.prp}_'+grp+'" class="select" style="width:50px;" >'+
							'<option value="">All</option>'+
							<c:forEach var="prpLov" items="${PRP_LOV[lst.prp]}">
							 	'<option value="${prpLov.id}" >${prpLov.description}</option>'+
							</c:forEach>
						'</select></td>';	
				</c:when>
				<c:otherwise>
					html +='<td><input type="text" name="${lst.prp}_'+grp+'" id="${lst.prp}_'+grp+'" size="3"/></td>';
				</c:otherwise>
			</c:choose>
			colList[index]= '${lst.prp}';
			index++;
	</c:forEach>
	html +='<td><input type="checkbox" name="default_'+grp+'" id="default_'+grp+'" value="1"/></td>';
	html +='<td><input type="button" name="edit" id="edit" value="update" onclick="submit();"/></td>';
	html += '</tr>' ;
	$('#grpId').val(grp);
	$('#row_'+grp).after(html);
	$('tr[id*="add_"]').remove();
	<c:forEach var="lst" items="${prpList}">
		if($('#${lst.prp}_'+grp).is('select')){
			var myVal = $('#${lst.prp}_${pktCode}_'+grp).html().trim();
			$('#${lst.prp}_'+grp+' option')
			   .each(function() { this.selected = (this.text == myVal); });

		}else{
			$('#${lst.prp}_'+grp).val($('#${lst.prp}_${pktCode}_'+grp).html());
		}
	</c:forEach>
   }
      function submit(){
    	  $('#labDtl').submit();
      }
   </script>
</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="lab" />
	</jsp:include>
	<div align="center">
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>

		<!-- <div><input type="button" name="clear" id="clear" value="Clear Current Stock" onclick="window.location = 'clearStock.html';"/></div> -->
		<form action="labSubmit.html" method="post" id="labDtl">
			<br/>
			<label>Enter Packet No. </label>
			<input type="text" name="pktCode" id="pktCode"
				value="${pktCode}" /> <input type="button" value="go"
				onclick="loadLab($('#pktCode').val());";> <input
				type="hidden" name="grpId" id="grpId" value="">
			<table id="greenTable">
				<thead>
					<tr>
						<c:forEach var="lst" items="${prpList}">
							<c:if test="${lst.prpDesc != null}">
								<th title="${lst.prpDesc}">${lst.prpDesc}</th>
							</c:if>
						</c:forEach>
						<th>Default</th>
						<th>Action</th>

					</tr>

				</thead>
				<tbody id="addStock">
					<c:forEach var="pd" items="${pktDetails}">
						<tr id="row_${pd['grpid']}">
							<c:forEach var="lst" items="${prpList}">
								<c:set var="prp" value="${lst.prp}_val" scope="page" />
								<c:if test="${lst.prp == 'CTS' }">
									<c:set var="prp" value="${lst.prp}" scope="page" />
								</c:if>
								<td id="${lst.prp}_${pktCode}_${pd['grpid']}">${pd[prp]}</td>
							</c:forEach>
							<td><c:if test="${pd['grpid'] == 1}">Default</c:if>
							</td>
							<td><input type="button" value="edit" id="manSubmit"
								onclick="editLab('${pd['grpid']}');" />
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<input type="button" value="Add New" onclick="addLab();" />
		</form>
	</div>
	<div id="response"></div>
</body>
</html>