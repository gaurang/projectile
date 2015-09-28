<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script>
var angadia =[
<c:forEach var='a' items='${angadiaList}' varStatus='count'>
	{id:'${a.id}', angadiaCoName:'${a.angadiaCoName}',opBalance:'${a.opBalance}',currBalance:'${a.currBalance}',code:'${a.code}',accCode:'${a.accCode}'}
	<c:if test="${!count.last}">,</c:if>
</c:forEach>
]

	function getAngadia(id){
		if(!id){
			document.getElementById('angadiaCoName').value = '';
			document.getElementById('opBalance').value = '';
			document.getElementById('opBalance').disabled = false;
			document.getElementById('code').value = '';
			document.getElementById('accountCode').value = '';
			document.getElementById('currCode').value = '${USER_SESSION.currency}';
		}
		else{
			for(var i=0 ; i<=angadia.length; i++){
				var a = angadia[i];
				if(id == a.id){
					document.getElementById('angadiaCoName').value = a.angadiaCoName;
					document.getElementById('opBalance').value = a.opBalance;
					document.getElementById('opBalance').disabled = true;
					document.getElementById('code').value = a.code;
					document.getElementById('accountCode').value = a.accCode;
					document.getElementById('currCode').value = a.currCode;
					
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
		<jsp:param name="subPage" value="angadia" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<c:if test="${msg != null}">
				<div id="msg">Successfully done.</div>
			</c:if>

			<div>
				<label for="angadiaId">Angadia Name</label> : <select id="angadiaId"
					name="angadiaId" onchange="getAngadia(this.value);">
					<option value="" selected>New Angadia</option>
					<c:forEach var="a" items='${angadiaList}'>
						<option value="${a.id}"
							<c:if test="${param.angadiaId == a.id}">selected </c:if>>${a.angadiaCoName}</option>

					</c:forEach>
				</select>
			</div>
			<hr />
			<form name="angadia" id="angadia" method="post"
				action="addUpdateAngadia.html">
				<div style="width: 370px;">
					<div class="row">
						<div class="element">Angadia Co. Name</div>
						<div class="element">
							<input type="text" name="angadiaCoName" value=""
								id="angadiaCoName" class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Opening Balance</div>
						<div class="element">
							<input type="text" name="opBalance" value="" id="opBalance"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Code</div>
						<div class="element">
							<input type="text" name="code" value="" id="code"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">GL account</div>
						<div class="element">
							<select id="accountCode" name="accountCode" class="inputText1">
								<c:forEach var="a" items='${glAcc}'>
									<option value="${a.code}">${a.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="element">Currency</div>
						<div class="element">
							<select id="currCode" name="currCode" class="inputText1">
								<c:forEach var="a" items='${currency}'>
									<option value="${a.id}"
										<c:if test="${a.id == USER_SESSION.currency}">selected </c:if>>${a.description}</option>
								</c:forEach>
							</select>
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