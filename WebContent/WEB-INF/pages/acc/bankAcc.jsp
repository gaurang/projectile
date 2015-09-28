<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile ::</title>
<script>
var bankAcc =[
<c:forEach var='a' items='${bankAccList}' varStatus='count'>
	{id:'${a.id}', bankAccountName:'${a.bankAccountName}', accountType:'${a.accountType}', bankCurrCode:'${a.bankCurrCode}', accountCode:'${a.accountCode}', dfltCurrAct:'${a.dfltCurrAct}', bankAccountNumber:'${a.bankAccountNumber}',bankAddress:'${a.bankAddress}',bankName:'${a.bankName}'}
	<c:if test="${!count.last}">,</c:if>
</c:forEach>
]
	function deleteAcc(id){
	
		var con = confirm('Are you sure you want to delete the Account ? ');
		if(con){
			document.bankAcc.action='deleteBankAccount.html';
			document.bankAcc.method='post';
			document.bankAcc.submit();
		}
	}
	function getBankAcc(id){
		if(!id){
			document.getElementById('bankAccountName').value = '';
			
			document.getElementById('accountType').disabled = false;
			document.getElementById('accountType').value = '';
			
			document.getElementById('bankCurrCode').disabled = false;
			document.getElementById('bankCurrCode').value = '';
			
			document.getElementById('accountCode').disabled = false;
			document.getElementById('accountCode').value = '';
			document.getElementById('dfltCurrAct').value = '';
			document.getElementById('bankAccountNumber').value = '';
			document.getElementById('bankAddress').value = '';
			document.getElementById('bankName').value = '';
			document.getElementById('bankAccId').value = 0;
			document.getElementById('branchName').value = '';
		}
		else{
			for(var i=0 ; i<=bankAcc.length; i++){
				var a = bankAcc[i];
				if(id == a.id){
					
					document.getElementById('bankAccountName').value = a.bankAccountName;
					
					document.getElementById('accountType').disabled = true;
					document.getElementById('accountType').value = a.accountType;
					
					document.getElementById('bankCurrCode').disabled = true;
					document.getElementById('bankCurrCode').value = a.bankCurrCode;
					
					document.getElementById('accountCode').disabled = true;
					document.getElementById('accountCode').value = a.accountCode;
					document.getElementById('dfltCurrAct').value = a.dfltCurrAct;
					document.getElementById('bankAccountNumber').value = a.bankAccountNumber ;
					document.getElementById('bankAddress').value = a.bankAddress;
					document.getElementById('bankName').value = a.bankName;
					
					document.getElementById('bankAccId').value = a.id;
					document.getElementById('branchName').value = a.branchName;
					break;
				}
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="bank" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<c:if test="${msg != null}">
				<div id="msg">Successfully done.</div>
			</c:if>

			<div>

				<table id="greenTable">
					<tr>
						<th>Account Name</th>
						<th>Type</th>
						<th>Currency</th>
						<th>GL Account</th>
						<th>Bank</th>
						<th>Number</th>
						<th>Bank Address</th>
						<th>Dflt</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="a" items='${bankAccList}'>
						<tr>
							<td>${a.bankAccountName}</td>
							<td>${a.accountType}</td>
							<td>${a.bankCurrCode}</td>
							<td>${a.accountCode}</td>
							<td>${a.bankName}</td>
							<td>${a.bankAccountNumber}</td>
							<td>${a.bankAddress}</td>
							<td>${a.dfltCurrAct}</td>

							<td><a href="javascript:void(0);"
								onclick="getBankAcc('${a.id}')">Edit</a></td>
							<td><a href="javascript:void(0);"
								onclick="deleteAcc('${a.id}')">Delete</a></td>
						</tr>
					</c:forEach>
				</table>

			</div>

			<form name="bankAcc" id="bankAcc" method="post"
				action="addBankAcc.html">
				<input type="hidden" id="bankAccId" name="bankAccId" value="0">
				<div style="width: 370px;">
					<div class="row">
						<div class="element">Bank Account Name:</div>
						<div class="element">
							<input type="text" name="bankAccountName" value=""
								id="bankAccountName" class="inputText1">
						</div>
					</div>
					<div class="row">
						<div class="element">Type</div>
						<div class="element">
							<select id="accountType" name="accountType" class="inputText1">
								<option value="0" selected>Saving Account</option>
								<option value="1">Chequing Account</option>
								<option value="2">Credit Account</option>
								<option value="3">Cash Account</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="element">Currency</div>
						<div class="element">
							<select id="bankCurrCode" name="bankCurrCode" class="inputText1">
								<c:forEach var='c' items='${currency}'>
									<option value="${c.id}">${c.description}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="element">Default Currency Account</div>
						<div class="element">
							<select id=dfltCurrAct name="dfltCurrAct" class="inputText1">
								<option value="0" selected>No</option>
								<option value="1">Yes</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="element">Bank GL account</div>
						<div class="element">
							<select id="accountCode" name="accountCode" class="inputText1">
								<c:forEach var="a" items='${glAcc}'>
									<option value="${a.id}">${a.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="element">Bank Name</div>
						<div class="element">
							<input type="text" name="bankName" value="" id="bankName"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Bank Account Number</div>
						<div class="element">
							<input type="text" name="bankAccountNumber" value=""
								id="bankAccountNumber" class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Bank Address</div>
						<div class="element">
							<input type="text" name="bankAddress" value="" id="bankAddress"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<div class="element">Branch</div>
						<div class="element">
							<input type="text" name="branchName" value="" id="branchName"
								class="inputText1" />
						</div>
					</div>
					<div class="row">
						<input type="submit" name="Submit" id="Submit" value="submit">
					</div>
				</div>

			</form>
		</div>
	</div>
</body>
</html>