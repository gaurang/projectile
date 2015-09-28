<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>H.Riddhesh & Co. : </title>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery.ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqgrid/css/ui.jqgrid.css" />
   <link rel="stylesheet" type="text/css" href="css/ui.dropdownchecklist.css" />
<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-dropdown/ui.core-min.js"></script>
<script type="text/javascript" src="js/jquery-dropdown/ui.dropdownchecklist-min.js"></script>
  <script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
  <script src="js/jquery/jquery.form.js" type="text/javascript"></script>

<style type="text/css">

label {  }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top;display: list-item;font-size: 10px; }
p { clear: both; }
.submit { margin-left: 12em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }
 </style>



<script type="text/javascript">
$(document).ready(function(){
	
<c:forEach items="${prpList}" var="pl">
	<c:if test="${pl.fieldDisplayType == 'MULSEL'}">
		$("#${pl.prp}").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 300, width:85 });  
	</c:if>
</c:forEach>
$('#saveMaster').click(function(){
	
	$('#ajax').show();
    var formOptions2 = {
    		dataType: 'json',
            beforeSubmit: function() {
            	return  $('#pAForm').validate().form();
            },
            success:  function(jsonObj) {
            	<c:set var="errorMsgStr" value="Basic Party Details has been Successfully Saved" scope="request"></c:set>
            	window.opener.location ='partyActionController.html?partyId='+$("#partyId").val();
            	window.close();
            },
            target: '#response'
        };
	$('#pAForm').ajaxForm(formOptions2);
	$('#ajax').hide();
  });


});
</script>

</head>
<body >
<table><tr><td><jsp:include page="../inc/inc_header.jsp"></jsp:include></td></tr></table>
	
	
	<h1>Demand form </h1>
	<form id="demandForm" name="demandForm" action="demandSave.html" method="post">
	<label for="">Select Party *</label>
	<select id="partyAccId" name="partyAccId" style="width: 130px;" class="required">
				<option value="">Select</option>
				<c:forEach items="${PARTY_LIST}" var="s" varStatus="cnt">
				 	<option value="${s.id}">${s.companyName}/${s.branchCode}/${s.termCode}</option>
				</c:forEach>
	</select>
	<label for="demandName">Demand Name *</label>
	<input type="text" name="demandName" id="demandName" value="" class="required"/>
	<table>
		<tr>
			<td>
			<table class="bluetable">
				<thead>
					<tr>
						<th>Property</th>
						<th>From</th>	
						<th>To</th>	
					</tr>
				</thead>
				<tbody>
				
				<c:forEach var="prpList" items="${prpList}" varStatus="list" begin="0">
				<c:if test="${list.count % 2 != 0 }">
				
				<c:set var="selectCss" value="class='select'"></c:set>
				<c:if test="${prpList.fieldDisplayType == 'MULSEL'}">
				<c:set var="selectCss" value="multiple='multiple' class='select'"></c:set>
				</c:if>
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td><label for="${prpList.prp}" title="${prpList.prpDesc}">${prpList.prpDesc}</label></td>
							<td>
									<c:choose>
										<c:when test="${prpList.fieldDisplayType == 'MULSEL'}" >
											<select name='${prpList.prp}' id='${prpList.prp}' ${selectCss}>
											<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
											
										 </c:otherwise>
									</c:choose>
							</td>
							<td>
								<c:if test="${prpList.fieldDisplayType !=  'MULSEL'}">
								<select name='${prpList.prp}_to' id='${prpList.prp}_to' ${selectCss} >
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpToNum == prpLov.id}">selected</c:if>> ${prpLov.description}</option>
									</c:forEach>
								</select>
								</c:if>
							</td>
							
						</c:when>
						<c:otherwise>
							<td><label for="${prpList.prp}" title="${prpList.prpDesc}">${prpList.prpDesc}</label></td>
							<td><input type="text" name='${prpList.prp}_from' id='${prpList.prp}_from' class="inputText" <c:if test='${SEARCH_PRP_MAP[prpList.prp].prpFromNum > 0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpFromNum}"</c:if>
							onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
							<td><input type="text" name='${prpList.prp}_to' id='${prpList.prp}_to' class="inputText" <c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpToNum}"</c:if>
							onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
				</c:forEach>
				</tbody>
			</table>
			</td>
		 	<td>
			<table class="bluetable">
				<thead>
					<tr>
					<th>Property</th>
					<th>From</th>	
					<th >To</th>	
					
					</tr>
					
				</thead>
				<tbody>
				<c:forEach var="prpList" items="${prpList}" varStatus="list" begin="0">
				<c:if test="${list.count % 2 == 0}">
				<c:set var="selectCss" value="class='select'"></c:set>
				<c:if test="${prpList.fieldDisplayType == 'MULSEL'}">
				<c:set var="selectCss" value="multiple='multiple' class='select'"></c:set>
				</c:if>
				
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td><label for="${prpList.prp}" title="${prpList.prpDesc}">${prpList.prpDesc}</label></td>
							<td>
									<c:choose>
										<c:when test="${prpList.fieldDisplayType == 'MULSEL'}">
											<select name='${prpList.prp}' id='${prpList.prp}' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
										 </c:otherwise>
									</c:choose>
									
							</td>
							<td>
							<c:if test="${prpList.fieldDisplayType !=  'MULSEL'}">
								<select name='${prpList.prp}_to' id='${prpList.prp}_to' ${selectCss}>
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
											<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpToNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
								</c:if>
							</td>
							
						</c:when>
						<c:otherwise>
							<td><label for="${prpList.prp}" title="${prpList.prpDesc}">${prpList.prpDesc}</label></td>
							<td><input type="text" name='${prpList.prp}_from' id='${prpList.prp}_from' class="inputText" 
							<c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpFromNum}"</c:if> onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
							<td><input type="text" name='${prpList.prp}_to' id='${prpList.prp}_to' class="inputText" 
							<c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpToNum}"</c:if> onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
				</c:forEach>
				</tbody>
			</table>
			</td>
			</tr>
			<tr>
			<td colspan="2" style="padding: 4px!important;">
				<input type="checkbox" id="autoMail" value="1" name="autoMail"/> Send Auto Email
				<select name="frequency" id="frequency">
					<option value="1">Daily </option>
					<option value="7">Weekly</option>
					<option value="30">Monthly</option>
				</select>
				
				<input type="checkbox" id="noPrice" value="1" name="noPrice"/> No price
				<input type="checkbox" id="autoMemo" value="1" name="autoMemo"/> Make Auto Memo
			</td>
			</tr>
			<tr>
				<td  style="padding: 4px!important;">
					<label for="toDate">From date</label>
					<input type="text" name="fromDate" id="fromDate"/>
				</td>
				<td  style="padding: 4px!important;">
				<label for="toDate">To date</label>
					<input type="text" name="toDate" id="toDate"/>
				</td>
			
			</tr>
			<tr>
				<td colspan="2"  style="padding: 4px!important;">
					<input type="submit"  value="Save"/>
					<input type="button"  value="reset"/>
					<c:if test="${dmd.id > 0}">
						<input type="button"  value="Add as New"/>
					</c:if>
					
				</td>
			</tr>
		</table>
		</form>
</body>
</html>