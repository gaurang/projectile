<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="party" />
		<jsp:param name="subPage" value="webUser" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<c:if test="${msg != null}">
				<div id="msg">${msg}</div>
			</c:if>

			<form method="POST" action="users.html">

				<br />
				<div style="font-size: 18px;" id="content">Web Users</div>
				<br />
				<table cellpadding="3" cellspacing="2" border="1" id="greenTable"
					width="100%">
					<tr>
						<th>Sr No.</th>
						<th>Company Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Mobile</th>
						<th>Contact Person</th>
						<th>Country</th>
						<th>User Name</th>
						<th>Term</th>
						<th>Web Acc</th>
					</tr>
					<c:forEach items="${pageList.results}" var="l" varStatus="cnt">

						<tr>
							<td>${cnt.index+1}</a>
							</td>
							<td><a href="javascript:void(0);" onclick="addTab('View WebReg','webRegistrationView.html?uID=${l.userId}');"
								>${l.companyName}</a>
							</td>
							<td>${l.cEmail}</td>
							<td>${l.phoneNo1}</td>
							<td>${l.cMobile}</td>
							<td>${l.cName}</td>
							<td>${l.country}</td>
							<td>${l.loginName}</td>
							<td>${l.termCode}</td>
							<td><a href="javascript:void(0);" onclick="addTab('Web UserEdit','webUserEdit.html?userId=${l.userId}');"><img
									src="images/Edit-32.png" width=18 border="0">
							</a>
							</td>
						</tr>

					</c:forEach>

				</table>

				<br />
			</form>
		</div>
	</div>
</body>
</html>