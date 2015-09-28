<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script>
var glAcc =[
<c:forEach var='a' items='${glAcc}' varStatus='count'>
	{id:'${a.code}', name:'${a.name}',status:'${a.status}', glAccGrpId:'${a.typeId}', code:'${a.code}'}
	<c:if test="${!count.last}">,</c:if>	
</c:forEach>
]
	function getGlAccId(id){
	//var id = document.getElementById('glAccId').value;
		if(!id){
			document.getElementById('name').value = '';
			document.getElementById('code').value = '';
			document.getElementById('status_0').checked = false;
			document.getElementById('status_1').checked = false;
			document.getElementById('glAccGrpId').value = '';
		}
		else{
			for(var i=0 ; i<=glAcc.length; i++){
				var a = glAcc[i];
				if(id == a.id){
					document.getElementById('name').value = a.name;
					document.getElementById('code').value = a.code;
					document.getElementById('status_'+a.status).checked = true;
					document.getElementById('glAccGrpId').value = a.typeId;
					break;
				}
			}
		}
	}
function validate(){
	if(document.getElementById('name').value == '' || document.getElementById('code').value == '' ||document.getElementById('glAccGrpId').value==''){
		alert("All values are required.");
		return false;
	}
}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="glacc" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<c:if test="${msg != null}">
				<div id="msg">Successfully done.</div>
			</c:if>

			<div>
				<label for="glAccId">GL Account</label> : <select id="glAccId"
					name="glAccId" onchange="getGlAccId(this.value);"
					style="width: 150px;">
					<option value="-1">New GL Acc</option>
					<c:forEach var="a" items='${glAcc}'>
						<option value="${a.code}">${a.name}</option>
					</c:forEach>
				</select>
			</div>
			<hr />
			<form name="glAcc" id="glAcc" method="post"
				action="addUpdateGlAcc.html" onsubmit="return validate();">
				<div style="width: 370px;">
					<div class="row">
						<div class="element">Gl Account Name</div>
						<div class="element">
							<input type="text" name="name" value="" id="name"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Gl Account Code</div>
						<div class="element">
							<input type="text" name="code" value="" id="code"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Gl Account Group</div>
						<div class="element">
							<select id="glAccGrpId" name="glAccGrpId" class="inputText1">
								<option value="" selected>Select</option>
								<c:forEach var="a" items='${glAccGrp}'>
									<option value="${a.id}"
										<c:if test="${param.glAccGrpId == a.id}">selected </c:if>>${a.name}</option>
								</c:forEach>
							</select>
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