<%
	request.getSession().invalidate();
	response.sendRedirect("indexAdmin.html");
%>