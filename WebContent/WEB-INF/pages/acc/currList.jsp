<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script>
var curr =[
<c:forEach var='a' items='${currencyList}' varStatus='count'>
	{currAbrev:'${a.currAbrev}', currency:'${a.currency}', currSymbol:'${a.currSymbol}',
		hundredsName:'${a.hundredsName}', country:'${a.country}', autoUpdate:'${a.autoUpdate}', status:'${a.status}'}
	<c:if test="${!count.last}">,</c:if>
</c:forEach>
]
	function deleteAcc(id){
		var con = confirm('Are you sure you want to delete the Currency ? ');
		if(con){
			document.getElementById('currAbrev').value = id;
			document.curr.action ='deleteCurrency.html';
			document.curr.method='post';
			document.curr.submit();
		}
	}	
	function getCurr(currAbrev){
		if(!currAbrev){
			document.getElementById('currAbrev').disabled = false;
			document.getElementById('currAbrev').value = '';
			document.getElementById('currency').value = '';
			document.getElementById('currSymbol').value = '';
			document.getElementById('hundredsName').value = '';
			document.getElementById('country').value = '';
			document.getElementById('autoUpdate').check = true ;
			document.getElementById('currUpdate').value = -1 ;
			
		}
		else{
			for(var i=0 ; i<=curr.length; i++){
				var a = curr[i];
				if(currAbrev == a.currAbrev){
					document.getElementById('currAbrev').disabled = true;
					document.getElementById('currAbrev').value = a.currAbrev;
					document.getElementById('currency').value = a.currency;
					document.getElementById('currSymbol').value = a.currSymbol;
					document.getElementById('hundredsName').value = a.hundredsName;
					document.getElementById('country').value = a.country;
					if(a.autoUpdate == 1 )	{
						document.getElementById('autoUpdate').check = true ;
					}else{
						document.getElementById('autoUpdate').check = false ;
					}
					document.getElementById('currUpdate').value = 1 ;
				}
			}
		}
	}
	function submit(){
		if(document.getElementById('currAbrev').value ==''
					||document.getElementById('currency').value == ''||document.getElementById('hundredsName').value ==''
						||document.getElementById('country').value ==''){
			alert("Pleasse enter all feilds except Currency symbol.");
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="curr" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<br />
			<c:if test="${msg != null}">
				<div id="msg">Successfully done.</div>
			</c:if>
			<table id="greenTable">
				<tr>
					<th>Abbreviation</th>
					<th>Symbol</th>
					<th>Currency Name</th>
					<th>Hundredths name</th>
					<th>Country</th>
					<th>Auto update</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="a" items='${currencyList}'>
					<tr>
						<td>${a.currAbrev}</td>
						<td>${a.currSymbol}</td>
						<td>${a.currency}</td>
						<td>${a.hundredsName}</td>
						<td>${a.country}</td>
						<td>${a.autoUpdate}</td>
						<td><a href="javascript:void(0);"
							onclick="getCurr('${a.currAbrev}')">Edit</a></td>
						<td><a href="javascript:void(0);"
							onclick="deleteAcc('${a.currAbrev}')">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<form name="curr" id="curr" method="post" action="addCurr.html"
				onsubmit="return submit();">
				<input type="hidden" id="currUpdate" name="currUpdate" value="-1">
				<div align="center">
					<br />
					<div style="width: 40%;" align="center">
						<div class="row">
							<div class="element">Currency Abbreviation:</div>
							<div class="element">
								<input type="text" name="currAbrev" value="" id="currAbrev">
							</div>
						</div>
						<div class="row">
							<div class="element">Currency Symbol:</div>
							<div class="element">
								<input type="text" name="currSymbol" value="" id="currSymbol">
							</div>
						</div>
						<div class="row">
							<div class="element">Currency Name:</div>
							<div class="element">
								<input type="text" name="currency" value="" id="currency">
							</div>
						</div>
						<div class="row">
							<div class="element">Hundredths Name:</div>
							<div class="element">
								<input type="text" name="hundredsName" value=""
									id="hundredsName">
							</div>
						</div>
						<div class="row">
							<div class="element">Country:</div>
							<div class="element">
								<input type="text" name="country" value="" id="country">
							</div>
						</div>
						<div class="row">
							<div class="element">Auto Update:</div>
							<div class="element">
								<input type="checkbox" name="autoUpdate" value="1"
									id="autoUpdate" checked="checked">
							</div>
						</div>
						<div class="row">
							<input type="submit" name="Submit" id="Submit" value="submit">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>