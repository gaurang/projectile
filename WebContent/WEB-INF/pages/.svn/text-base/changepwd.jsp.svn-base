<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>

<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function validateMainForm(){

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
<body >


<table  align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" 
style="background-image:url('images/shadow_left.png');">
<img src="images/shadow_left.png" width="15" alt="" height="1"/></td>
    <td><table  align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="0">
                  <tbody><tr>
                    <td valign="bottom" 
height="131">
<jsp:include page="include/header.jsp"></jsp:include>
</td>
                  </tr>
                  <tr>
                    <td >

   <div id="mainBody">
   	<div align="center" class="heading">Change Password</div>
       <table align="center" >
       <tr>
       	  <td valign="top">
       	  <form action="updatePwd.html" method="post" name="pMainForm" id="pMainForm" onsubmit="validateMainForm();">
       	  	<div class="details" align="center" style="width: 600px;" >
       	  		<table>
       	  		<tr>
       	  			<td><label for="oldPwd">Old Password *</label></td>
       	  			<td><input type="password" name="oldPwd" id="oldPwd" size="15"></td>
       	  		</tr>
       	  		<tr>
       	  		<td><label for="newPwd">New Password *</label></td>
       	  		<td><input type="password" name="newPwd" id="newPwd" size="15"></td>
       	  		</tr>
       	  		<tr>
    	  		<td><label for="rePwd">Re enter Password *</label></td>
       	  		<td><input type="password" name="rePwd" id="rePwd" size="15"></td>
       	  		</tr>
       	  		
       	  		<tr><td colspan=2 height="10px"></td></tr>
       	  		<tr align="center" ><td colspan=2 class="button">
       	  			<input type="submit" value="Submit" style="width:100px;">
       	  		</td></tr>
       	  		<tr><td colspan=2 height="10px"></td></tr>
       	  		</table>
       	  	</div>
       	  </form>
       	  </td>	
       	  </tr>
       	 </table>
</div>
                    </td>
                  </tr>
                  <tr>
                  <td >
                  <jsp:include page="include/footer.jsp"></jsp:include>
                  </td>
                  </tr>
                  
                  
              </tbody></table></td>
            </tr>
        </tbody></table></td>
      </tr>
    </tbody></table>
      </td>
    <td valign="top" width="15" 
background="images/shadow_right.png"><img 
src="images/shadow_right.png" width="15" 
height="1"></td>
  </tr>
</tbody></table>
<map name="Map2" id="Map2"><area shape="rect" coords="710,209,893,246" 
href="index.html"><area 
shape="rect" coords="710,209,893,246" 
href="index.html">
</map>
<input id="gwProxy" type="hidden"><!--Session data--><input 
onclick="jsCall();" id="jsProxy" type="hidden"><div id="refHTML"></div>

</body>
</html>