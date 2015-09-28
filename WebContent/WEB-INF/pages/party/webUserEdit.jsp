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
			<form method="POST" action="webUserSubmit.html">
				<input type="hidden" name="userId" id="userId"
					value="${user.userId}" /> <br />
				<c:choose>
					<c:when test="${user.userName != null}">
						<input type="hidden" name="edit" value="1" />
						<div style="font-size: 18px;">Buyer Details</div>

						<table>
							<tr>
								<th>Company</th>
								<td>${user.compnayName}</td>
							</tr>
							<tr>
								<th>User Name</th>
								<td>${user.userName}<input type="hidden" name="uNm"
									id="uNm" value="${user.userName}" /></td>
							</tr>
							<tr>
								<th>Password</th>
								<td><input type="password" name="pwd" id="pwd" />
								</td>
							</tr>
							<tr>
								<th>Terms</th>
								<td><select id="trm" name="trm">
										<c:forEach items="${termsList}" var="t">
											<option value="${t.id}"
												<c:if test="${user.termId == t.id}">selected</c:if>>${t.description}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>Status</th>
								<td><select id="status" name="status">
										<option value="0"
											<c:if test="${user.status == 0}">selected</c:if>>Inactive</option>
										<option value="1"
											<c:if test="${user.status == 1}">selected</c:if>>Active</option>
								</select></td>
							</tr>
							<tr>
								<th>Party Account</th>
								<td><select id="partyAccId" name="partyAccId">
										<option value="-1" selected="selected">Create New
											party</option>
										<c:forEach var="p" items='${PARTY_LIST}'>
											<option value="${p.id}"
												<c:if test="${user.partyAccId == p.id}">selected </c:if>>${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option>
										</c:forEach>
								</select></td>
							</tr>
							<c:if test="${user.partyAccId > 0}">
								<tr>
									<th>Update Web Details</th>
									<td><input type="checkbox" name="webUpdate" id="webUpdate"
										value="1" /></td>
								</tr>
							</c:if>
						</table>
						<input type="submit" value="update" />
					</c:when>

					<c:otherwise>
						<input type="hidden" name="edit" value="0" />
						<div style="font-size: 18px;">Buyer Details</div>

						<table>
							<tr>
								<th>Company</th>
								<td><select id="byr" name="byr">
										<c:forEach items="${byrList}" var="b">
											<option value="${b.id}">${b.description}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>User Name</th>
								<td><input type="text" name="uName" id="uName" />
								</td>
							</tr>
							<tr>
								<th>Password</th>
								<td><input type="password" name="pwd" id="pwd" />
								</td>
							</tr>
							<tr>
								<th>Term</th>
								<td><select id="trm" name="trm">
										<c:forEach items="${termsList}" var="t">
											<option value="${t.id}">${t.description}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>Status</th>
								<td><select id="status" name="status">
										<option value="0">Inactive</option>
										<option value="1">Active</option>
								</select></td>
							</tr>
							<tr>
								<th>Party Acc</th>
								<td><select id="partyAccId" name="partyAccId">
										<option value="-1" selected="selected">Create New
											party</option>
										<c:forEach var="p" items='${PARTY_LIST}'>
											<option value="${p.id}">${p.companyName}/${p.branchCode}/${p.termCode}/${p.accType}</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
						<input type="submit" value="create" />
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</div>
</body>
</html>