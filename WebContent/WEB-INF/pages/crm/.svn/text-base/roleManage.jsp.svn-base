<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
<script type="text/javascript">
	function loadRoleActivity() {
		func('roleManager.html?roleId='+$('#roleId').val());
	}
</script>
</head>
<body >
<jsp:include page="../inc/inc_header.jsp">
	<jsp:param name="page" value="utility"/>
	<jsp:param name="subPage" value="role"/>
</jsp:include>      	
    <!-- Main Content here -->
<div class="container">
	<div align="center" class="content">
	<br/>
	<form action="roleActivitySubmit.html" method="post" name="roleActivity" id="roleActivity">
		<label for="roleId">Role </label>
		<select id="roleId" name="roleId" onchange="loadRoleActivity();">
			<option value="-1">Select</option>
			<c:forEach items="${ROLE_LIST}" var="r" varStatus="cnt">
				<option value="${r.id}" <c:if test="${r.id == param.roleId}">selected</c:if>>${r.description}</option>
			</c:forEach>
		</select>
		<br/><br/>
		<table id="greenTable" border=0>
			<tr>
				<th>Activity Name</th>
				<th>Activity Flag</th>
			</tr>
			    <c:set var="saleCounter" value="0" scope="request"></c:set>
			    <c:set var="purchaseCounter" value="0" scope="request"></c:set>
			    <c:set var="utilityCounter" value="0" scope="request"></c:set>
			    <c:set var="accountCounter" value="0" scope="request"></c:set>
			    <c:set var="partiesCounter" value="0" scope="request"></c:set>
			    <c:set var="reportsCounter" value="0" scope="request"></c:set>
			    <c:set var="saleCounter" value="0" scope="request"></c:set>
			<c:forEach items="${ROLE_ACTIVITY}" var="a" varStatus="count">
				<c:if test="${a.parentActivityId ==1 && saleCounter==0}">
				    <tr></tr>
				    <tr>
				        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
				    </tr>
				    <c:set var="saleCounter" value="1" scope="request"></c:set>
				</c:if>
				<c:if test="${a.parentActivityId ==2 && purchaseCounter==0}">
                    <tr></tr>
                    <tr>
                        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
                    </tr>
                    <c:set var="purchaseCounter" value="1" scope="request"></c:set>
                </c:if>
                <c:if test="${a.parentActivityId ==3 && partiesCounter==0}">
                    <tr></tr>
                    <tr>
                        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
                    </tr>
                    <c:set var="partiesCounter" value="1" scope="request"></c:set>
                </c:if>
                <c:if test="${a.parentActivityId ==4 && utilityCounter==0}">
                    <tr></tr>
                    <tr>
                        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
                    </tr>
                    <c:set var="utilityCounter" value="1" scope="request"></c:set>
                </c:if>
                <c:if test="${a.parentActivityId ==5 && accountCounter==0}">
                    <tr></tr>
                    <tr>
                        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
                    </tr>
                    <c:set var="accountCounter" value="1" scope="request"></c:set>
                </c:if>
                <c:if test="${a.parentActivityId ==6 && reportsCounter==0}">
                    <tr></tr>
                    <tr>
                        <td colspan="2" align="center"><strong>${a.parentActivityName}</strong></td>
                    </tr>
                    <c:set var="reportsCounter" value="1" scope="request"></c:set>
                </c:if>
				<tr>
					<td>${a.activityName}</td>
					<td><input type="checkbox" value="1" name="act_${a.activityId}"  id="act_${a.activityId}" <c:if test="${a.accessFlag == 1}">checked</c:if>/> </td>
				</tr>
		</c:forEach>
		</table>	
		<input type="submit" value="save Changes"/>
	</form>	
   </div>
 </div>  		
		
</body>
</html>