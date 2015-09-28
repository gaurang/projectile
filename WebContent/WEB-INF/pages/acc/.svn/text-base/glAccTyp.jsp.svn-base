<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script>
var glAccTypes =[
<c:forEach var='a' items='${glAccTypes}' varStatus='count'>
	{id:'${a.id}', name:'${a.name}',status:'${a.status}'}
	<c:if test="${!count.last}">,</c:if>
</c:forEach>];

	function getGlAccTypeId(id){
		if(!id){
			document.getElementById('name').value = '';
			document.getElementById('status_0').checked = false;
			document.getElementById('status_1').checked = false;
		}
		else{
			for(var i=0 ; i<=glAccTypes.length; i++){
				var a = glAccTypes[i];
				if(id == a.id){
					document.getElementById('name').value = a.name;
					document.getElementById('status_'+a.status).checked = true;
					break;
				}
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="gltyp" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<c:if test="${msg != null}">
				<div id="msg">Successfully done.</div>
			</c:if>

			<div>
				<label for="glAccTypeId">GL master Types</label> : <select
					id="glAccTypeId" name="glAccTypeId"
					onchange="getGlAccTypeId(this.value);">
					<option value="" selected>New GL Acc Type</option>
					<c:forEach var="a" items='${glAccTypes}'>
						<option value="${a.id}"
							<c:if test="${param.glAccTypeId == a.id}">selected </c:if>>${a.name}</option>

					</c:forEach>
				</select>
			</div>
			<hr />
			<form name="glAccType" id="glAccType" method="post"
				action="addUpdateGlAccType.html">

				<div style="width: 370px;">
					<div class="row">
						<div class="element">Gl Acc Type</div>
						<div class="element">
							<input type="text" name="name" value="" id="name"
								class="inputText1">
						</div>
					</div>
					<div class="row">
						<div class="element">Status</div>
						<div class="element">
							<label for="status_1">Yes</label> <input type="radio"
								name="status" id="status_1" value="1"> <label
								for="status_0">No</label> <input type="radio" name="status"
								id="status_0" value="0">
						</div>
					</div>
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>

			</form>
		</div>
	</div>
</body>
</html>