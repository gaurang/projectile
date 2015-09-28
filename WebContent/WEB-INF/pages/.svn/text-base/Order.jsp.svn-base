<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.basync.b2b.util.ConnectionUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.basync.b2b.dataobjects.TermMaster"%>
<%@page import="com.basync.b2b.dataobjects.UserDO"%>
<%@page import="com.basync.b2b.dataobjects.UserControlDO"%>
<%@page import="org.springframework.context.ApplicationContext"%><html>
<head>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
</head>
<body>
<h3> User Details</h3>
<%
	List<TermMaster> termList = new ArrayList<TermMaster>();
	TermMaster tm = null;
	PreparedStatement pst = null;
	ResultSet rs =null;
	ConnectionUtil connUtil = new ConnectionUtil();
	connUtil.setDriverClass("com.mysql.jdbc.Driver");
	connUtil.setUserName("root");
	connUtil.setPassword("");
	connUtil.setConnectionUrl("jdbc:mysql:///test");
	Connection conn = connUtil.getConnection();
	pst =conn.prepareStatement("select id,termname from tb_termmaster");
	rs=pst.executeQuery();
	while(rs.next()){
		tm = new TermMaster();
		tm.setId(rs.getLong("ID"));
		tm.setTermName(rs.getString("TERMNAME"));
		termList.add(tm);
	}
UserControlDO uc=(UserControlDO) request.getAttribute("userControlDO");
%>
<form action="UserController.run?mode=process" method="post">
	<table border="1" >
		<tr>
			<td>User Name :</td> 
			<td><input type="text" name="userName" value="<%=uc.getUserName()%>" readonly="readonly"></td>
			<td>Party Name :</td> 
			<td><input type="text" name="partyName" value="<%=uc.getCName()%>" readonly="readonly"></td>
		</tr>
		<tr>	
			<td>Term :</td> 
			<td><select name="termId">
			<% String selected = "";
				for(int i=0 ;i<termList.size();i++){
				tm = termList.get(i);
				%>
				<option value="<%=tm.getId()%>" <%=Integer.parseInt(uc.getTermId()) == i?"selected":""%>> <%=tm.getTermName()%></option>	
			<%}%>
			</select></td>
			<td>Status </td>
			<td><select name="status">
				<%
				String active ="";
				String inActive ="";
				if(Integer.parseInt(uc.getStatus())==0){
					inActive = "selected";
				}
				else if(Integer.parseInt(uc.getStatus())==1){
					active = "selected";
				}
				%>
				<option value="1" <%=active %>>ACTIVE</option>
				<option value="0" <%=inActive %>>INACTIVE</option>
			</select></td>
		</tr>
		<tr>
			<td><input type="submit" value ="Save" ></td>
		</tr>	
	</table>
</form>
</body>
</html>