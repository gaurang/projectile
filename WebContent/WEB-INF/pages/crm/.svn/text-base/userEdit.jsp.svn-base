<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<title>Projectile:</title>
<script type="text/javascript">
<!--
	function deleteForm() {

	}
	function validate() {

		if (username == '') {
			alert('Please enter user name');
			return false;
		} else if (roleId == -1) {
			alert('Please select Role');
			return false;
		} else if (partyAccId == -1) {
			alert('Please select Party Account');
			return false;
		}
	}
	function checkUser(un) {
		if (un.value.length < 5) {
			un.value = '';
			alert('Please enter minimum of 5 character for Username');
			return;
		}
		$
				.getJSON(
						'checkCRMUserName.html',
						{
							user : un.value
						},
						function(json) {
							if (json != null && json != "") {
								if (json.toString().indexOf("ok") > -1) {
									$('#userMsg')
											.html(
													'<label for=loginName>Available</label>');
								} else {
									un.value = '';
									$('#userMsg')
											.html(
													'<label class=error for=loginName>User name not available</label>');
								}
							}
						});
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="user" />
	</jsp:include>
	<div class="container">
		<div class="content" align="center">
			<form action="userManagerEditSubmit.html" name="userEditForm"
				id="userEditForm" method="post" onsubmit="return validate();">
				<div align="center">
					<c:if test="${USER_DETAILS.userId > -1}">
						<input type="hidden" name="id" id="id"
							value="${USER_DETAILS.userId}" />
					</c:if>
					<table align="center">
						<tr>
							<td>User Name</td>
							<td><input type="text" name="userName" id="userName" size=20
								class="required" value="${USER_DETAILS.userName}"
								onchange="checkUser(this.value);" />
								<div id="userMsg"></div></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" id="password"
								size=20 class="required" value="" /></td>
						</tr>
						<tr>
							<td>Role</td>
							<td><select name="roleId" id="roleId"
								onchange="loadActivity(this.value);">
									<option value="">Select</option>
									<c:forEach items="${USER_ROLES}" var="u" varStatus="cnt">
										<option value="${u.id}"
											<c:if test="${u.id == USER_DETAILS.roleId}">selected</c:if>>${u.description}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Party Account</td>
							<td><select id="partyAccId" name="partyAccId"
								style="width: 130px;">
									<option value="">Select</option>
									<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
										<option value="${s.id}"
											<c:if test="${s.id == USER_DETAILS.partyAccId}">selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
									</c:forEach>
							</select></td>
						</tr>
						<!--<tr>
						<td colspan="2">
							<table>
								<tr>
									<th>Activity Right</th>
									<th></th>
								</tr>
								<tbody id="activity">
									<c:forEach items="${USER_ROLES}" var="u" varStatus="cnt">
											<td></td>
											<td></td>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr> -->
					</table>
					<div align="center">

						<input type="submit" value="Save" id="saveMaster" /> <input
							type="button" value="delete" onclick="deleteForm();" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>