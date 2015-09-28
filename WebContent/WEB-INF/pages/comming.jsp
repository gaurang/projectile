<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>

<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.7.2.custom.min.js"  ></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<style type="text/css">

label {  }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top;display: list-item;font-size: 10px; }
p { clear: both; }
.submit { margin-left: 12em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }
 </style>

</head>
<body>
<jsp:include page="inc/inc_header.jsp">
	<jsp:param name="page" value="account"/>
	<jsp:param name="subPage" value="${param.page}"/>
</jsp:include>      	
    <!-- Main Content here -->
<div class="container">
	<div align="center" class="content">
		Comming Soon in next Realease
	</div>
</div>		
</body>
</html>