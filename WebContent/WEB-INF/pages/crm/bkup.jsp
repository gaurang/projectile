<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--   <script src="js/jquery/jquery.js" type="text/javascript"></script>
  <script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
  <script src="js/jquery/jquery.form.js" type="text/javascript"></script>
   -->
  <link rel="stylesheet" type="text/css" href="css/crm/style.css" />
</head>
<body>


<jsp:include page="../inc/inc_header.jsp">
	<jsp:param name="page" value="utility"/>
</jsp:include>      	
    <!-- Main Content here -->
<div class="container">
	<div align="center" class="content">
	<div id="msg"> ${msg}</div>
			<a href="bkupDB.html">Back Up Database</a><br/>
			<a href="bkupDel.html">Back Up-Delete</a><br/>
	</div>
</div>
</body>
</html>