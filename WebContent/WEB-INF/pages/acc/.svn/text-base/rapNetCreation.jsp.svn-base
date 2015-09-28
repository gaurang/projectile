<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
</head>
<body>
<jsp:include page="../inc/inc_header.jsp">
        <jsp:param name="page" value="account"/>
        <jsp:param name="subPage" value="rapUserCreation"/>
</jsp:include>
<br/><br/>
<form action="rapNetCreationSubmit.html" method="POST">

  <table id="greenTable" align="center" style="vertical-align: middle;">
          <tr><td><strong>RapNet User Name</strong></td><td><input type="text" name="rapUsername" size="20"></input> </td></tr>
          <tr><td><strong>RapNet Password</strong></td><td><input type="password" name="rapPassword" size="20"></input></td></tr>
          <tr><td></td><td>${INVALID}&nbsp;</td></tr> 
          <tr><td></td><td><input type="submit" value="Submit">   </td></tr>
  </table> 
</form>
</body>
</html>