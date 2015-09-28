<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>K.B.Gems :</title>
<script type="text/javascript">
	function submit(){
		document.order.submit();
		}

	function submitApprove(){
		document.getElementById('status').value='2';
		document.order.action='OrdersEditSubmit.html';
		document.order.method='post';
		document.order.submit();
		}
	function submitReject(){
		document.getElementById('status').value='3';
		document.order.action='OrdersEditSubmit.html';
		document.order.method='post';
		document.order.submit();
		}

	function submit(){
		document.order.submit();
		}
</script>
</head>
<body>
<jsp:include page="include/cpHeader.jsp"></jsp:include>
<h3> Orders </h3>
<form action="Orders.html" method="post" name="order">
<table>
	
	<tr>
		<td>Status</td>
		<td>
			<select id="status" name="status" onchange="submit();">
			<option value="1" <c:if test="${param.status == 1}">selected</c:if>>Pending</option>
			<option value="2" <c:if test="${param.status == 2}">selected</c:if>>Rejected</option>
			<option value="3" <c:if test="${param.status == 3}">selected</c:if>>Approved</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Last Days</td>
		<td><input type="text" name="days" id="days" ></td>
	</tr>
</table>
<c:forEach items="${msg}" var="msg">
	${msg}
</c:forEach>

<table border="1" width="800px;">

<tr bgcolor="#2E6E9E" style="color:#ffffff;">
	<td>Order Id</td>
	<td>Date</td>
	<td>Status </td>
	<td>Company </td>
</tr>
<c:forEach items="${orderList}" var="list">
		<tr bgcolor="#DFEFFC">
			<td><c:out value="${list.id}"></c:out> </a></td>
			<td><c:out value="${list.orderDate}"></c:out> </td>
			<td>
				<c:choose>
					<c:when test="${list.status == 1}"> Pending</c:when>
					<c:when test="${list.status == 2}"> Rejected</c:when>
					<c:when test="${list.status == 3}"> Approved</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td><c:out value="${list.companyName}"></c:out> </td>
		</tr>
		<tr><td colspan="4" width="100%" id="${list.id}">
			<table border="1" width="90%" align="right">
					<tr bgcolor="#C1CDCD">
						<td>Select</td>
						<td>Ref No.</td>
						<td>Shape</td>
						<td>Carats</td>
						<td>Clarity</td>
						<td>Cut</td>
						<td>Color</td>
						<td>Rate</td>
						<td>Status</td>
						
					</tr>
					
				<c:forEach items="${list.packetList}" var="pList">
					<tr>
						<td><input type="checkbox" value="${list.id}-${pList.pktId}" id="orderPkts_${list.id}-${pList.pktCode}" name="orderPkts" > </td>
						<td>${pList.pktCode}</td>
						<td>${pList.sh}</td>
						<td>${pList.cts}</td>
						<td>${pList.pu}</td>
						<td>${pList.ct}</td>
						<td>${pList.c}</td>
						<td>${pList.rate}</td>
						<td>
						<c:choose>
							<c:when test="${list.status == 1}"> Pending</c:when>
							<c:when test="${list.status == 2}"> Rejected</c:when>
							<c:when test="${list.status == 3}"> Approved</c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</tr>
		<tr style="border: 2px solid #000;"><td colspan="4" > </td></tr>
		
</c:forEach>

		<tr style="border: 2px solid #000;"><td colspan="4" >
			<input type="hidden"  value="" id="status" name="status">
			<input type="button" onclick="javascript:submitApprove();" value="approve">
			<input type="button" onclick="javascript:submitReject();" value="Reject">
		 </td></tr>

</table>
</form>
</body>
</html>