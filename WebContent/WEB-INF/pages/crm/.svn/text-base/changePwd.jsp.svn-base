<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<title>Projectile:</title>
<script type="text/javascript">
function validate(){

	if(document.getElementById('oldPwd').value == '' || 
			document.getElementById('newPwd').value == ''||
			document.getElementById('rePwd').value == ''){
			alert('Enter all required fields');
			return false;
	}
	else if(document.getElementById('newPwd').value.length <6 )
	{
		alert('Password should be of min 6 characters ');
		return false;
	}
	else if(document.getElementById('rePwd').value !=document.getElementById('newPwd').value){
			alert('password re-entered does not match your original password ');
			return false;
	}
	else {
		return true;
	}	
}
</script>


</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="pwd" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<div id="errorMsg">
				<c:if test="${RESULT == 1}">Password Updated successfully</c:if>
				<c:if test="${RESULT == 0}">Your old Password didn't was incorrect, try again.</c:if>
			</div>
			<form action="updateCRMPwd.html" method="post" name="pForm"
				id="pForm" onsubmit="return validate();">
				<div class="details" align="center" style="width: 600px;">
					<table>
						<tr>
							<td><label for="oldPwd">Old Password *</label>
							</td>
							<td><input type="password" name="oldPwd" id="oldPwd"
								size="15" />
							</td>
						</tr>
						<tr>
							<td><label for="newPwd">New Password *</label>
							</td>
							<td><input type="password" name="newPwd" id="newPwd"
								size="15" />
							</td>
						</tr>
						<tr>
							<td><label for="rePwd">Re enter Password *</label>
							</td>
							<td><input type="password" name="rePwd" id="rePwd" size="15" />
							</td>
						</tr>

						<tr>
							<td colspan=2 height="10px"></td>
						</tr>
						<tr align="center">
							<td colspan=2 class="button"><input type="submit"
								value="Submit"  style="width: 100px;" /></td>
						</tr>
						<tr>
							<td colspan=2 height="10px"></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>

</body>
</html>