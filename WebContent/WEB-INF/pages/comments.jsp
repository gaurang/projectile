<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script>
	function submit(){
		if($('#comment').val() ==''){
			alert('Please wirte some Text to describe your query');
			return false;
		}
	}
</script>

</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div id="content">
	<form id="form1" name="form1" method="post" action="commentsSend.html" onsubmit="submit();">
      
      <div align="center" class="ref_detail" >
      <h2>Contact For Assistance</h2>
      </div>
      <div>
      	${msg}
      </div>
      <table width="96%" cellpadding="3" cellspacing="3">
        <tr>

          <td width="33%" class="reach_us_text"><span class="required_fields">*</span> <span style="color: red;">Required fields</span></td>
          <td colspan="3">&nbsp;</td>
          <td width="11%">&nbsp;</td>
        </tr>
        <tr>
          <td class="reach_us_text"> Name</td>

          <td colspan="3"><input name="name" type="text" class="reach_us_fields" id="name" size="33" /></td>
          <td>&nbsp;</td>
        </tr>

        <tr>
          <td valign="top"><span class="reach_us_text">Contact details</span></td>
          <td colspan="3"><input name="contact" type="text" class="reach_us_fields" id="contact" size="33" /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td valign="top"><span class="reach_us_text">Your Feedback</span><span class="required_fields">*</span></td>

          <td colspan="3"><textarea name="comment" cols="25" rows="6" class="reach_us_fields" id="comment"></textarea></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td width="14%" class="button"><input type="button" value="submit" onclick="javascript:window.form.submit();" /></td>
          <td width="14%" class="button"><input type="button" onclick="javascript:reset();" value="reset"/></td>
          <td width="28%">&nbsp;</td>

          <td>&nbsp;</td>
        </tr>
      </table>
    </form>
    </div>
<jsp:include page="include/footer.jsp"></jsp:include>
	
	
</body>
</html>