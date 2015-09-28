    <%@include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
</head>
<body>
<jsp:include page="include/cpHeader.jsp"></jsp:include>


<form method="POST" action="userSubmit.html">
	
	<br/>
	
<c:choose>	
<c:when test="${param.uNm != null}">
	<input type="hidden" name="edit" value="1"/>
 	<div style="font-size: 18px;">
		Buyer Details
	</div>
	
	<table>
	<tr>
		<td>Company </td> 
		<td>   ${param.cNm}</td> 
	</tr>
	<tr>
		<td>User Name  </td> 
		<td> ${param.uNm}<input type="hidden" name="uNm" id="uNm" value="${param.uNm}"/> </td>
	</tr>
	<tr>
		<td> Password </td> 
		<td> <input type="password" name="pwd" id="pwd"/></td>
 	</tr>
 	<tr>
 	<td>Terms </td>
 	<td><select id="trm" name="trm">
 				<c:forEach items="${termsList}" var="t">
 					<option value="${t.id}" <c:if test="${param.trmId == t.id}">selected</c:if>>${t.description}</option>
 				</c:forEach>
 			</select>
 	</td>		
 	</tr>
 	<tr>
 	<td>Status</td>
 	<td> 
		 <select id="status" name="status">
 				<option value="0" <c:if test="${param.status == 0}">selected</c:if>>Inactive</option>
 				<option value="1" <c:if test="${param.status == 1}">selected</c:if>>Active</option>
 		 </select> 	
 	</td>		
 	</tr>
 	</table>
	<input type="submit"  value="update"/>
</c:when>

<c:otherwise>
	<input type="hidden" name="edit" value="0"/>
	<div style="font-size: 18px;">
		Buyer Details
	</div>
	
	<table>
	<tr>
	<td>	Company </td> 
	<td> 
		<select id="trm" name="trm">
			<c:forEach items="${byrList}" var="b">
				<option value="${b.id}" >${b.description}</option>
			</c:forEach>
		</select>
	</td>
	</tr>
	<tr>
		<td>	User Name  </td> 
		<td><input type="text" name="uName" id="uName"/></td> 
 	</tr>
 	<tr>
	 	<td>Password </td> 
	 	<td> <input type="password" name="pwd" id="pwd"/></td> 
 	</tr>
 	<tr>
 		<td>Term</td> 
 		<td> 
 	 		<select id="trm" name="trm">
 				<c:forEach items="${termsList}" var="t">
 					<option value="${t.id}" >${t.description}</option>
 				</c:forEach>
 			</select>
 		</td>	
 	</tr>
 	<tr>
	<td>status</td> 
	<td>
		 <select id="status" name="status">
 				<option value="0">Inactive</option>
 				<option value="1">Active</option>
 		 </select> 	
 	</td>
 	</tr>
 	</table>	 
	<input type="submit"  value="create"/>
</c:otherwise>		
</c:choose>
</form>
</body>
</html>