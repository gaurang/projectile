<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />

<script type="text/javascript">
	function fnSubmit(fld){
		if(fld.value == -1)
		{
			alert('Select invoice from the list');
		}
		else{
			document.orderNo.submit();
		}
			

	}			

</script>
</head>
<body>

<table  align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" ></td>
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
                    <td id="mainBody">
	<div align="center" style="color: white;margin-bottom: 40px;" >

		
				<h2 class="heading" align="center" >Search Your Request</h2>

			<br/>
		<div class="ttl_names">
		<form action="invoiceReprint.html" name="orderNo" id="orderNo">
		Select Request
		&nbsp;&nbsp;&nbsp;
			<select id="orderId" name="orderId" style="width:150px;" onchange="javascript:fnSubmit(this)">
				<option value="-1">---Select---</option>
				<c:forEach items="${OrderList}" var="o" >
					<option value="${o.id}">${o.id} - ${o.description}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="OrderType" id="OrderType" value="${OrderType}">
		</form>
		</div>
<br/><br/>

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
    <td valign="top" width="15"></td>
  </tr>
</tbody></table>

	
</body>
</html>