    <%@include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
</head>
<body>
<jsp:include page="include/cpHeader.jsp"></jsp:include>


<form method="POST" action="users.html" >
	
	<br/>
	<div style="font-size: 18px;" id="content">
		All Buyers
	
	</div>
	
	<br/>
	<div>
		${msg}
	</div>
	<table cellpadding="3" cellspacing="2" border="1">
		<tr bgcolor="#cccccc">
			<th>Sr No.</th>
			<th>Company Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Mobile</th>
			<th>Contact Person</th>
			<th>Country</th>
			<th>User Name</th>
			<th>Term</th>
		</tr>
	<c:forEach items="${pageList.results}" var="l" varStatus="cnt">
	
		<tr>
			<td><a href="registrationView.html?uID=${l.userId}" target="_blank">${cnt.index+1}</a></td>
			<td><a href="userEdit.html?uNm=${l.loginName}&cNm=${l.companyName}&trmId=${l.termId}&status=${l.status}">${l.companyName}</a></td>
			<td>${l.cEmail}</td>
			<td>${l.phoneNo1}</td>
			<td>${l.cMobile}</td>
			<td>${l.cName}</td>
			<td>${l.country}</td>
			<td>${l.loginName}</td>
			<td>${l.termCode}</td>
		</tr>
	
	</c:forEach>
	
	</table>

	<br/>
</form>
</body>
</html>