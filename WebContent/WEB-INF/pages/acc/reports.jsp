<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
javascript:window.history.forward(1);
</script>
</head>
<body>
<jsp:include page="../inc/inc_header.jsp">
	<jsp:param name="page" value="account"/>
	<jsp:param name="subPage" value="reports"/>
</jsp:include>
<div class="container">	
	<div class="content" align="center">
	<div class="heading">Reports</div>

	<c:if test="${USER_SESSION.userActivityMap['Price_History'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('loadPriceHistory.html');">Price History</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Memo_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('memoReport.html');">Memo Report</a>
		</div>
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Invoice_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('invReport.html');">Invoice Report</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Payment_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('paymentReport.html');">Payment Report</a>
		</div>
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Stock_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('stockReport.html');">Stock Report(Stock Checking)</a>
		</div>
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Stock_Checking_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('stockCheckingReport.html');">Stock Checking(New)</a>
		</div>
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Purchase_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('purchaseReport.html');">Purchase Report</a>
		</div> 
	</c:if>	
	<c:if test="${USER_SESSION.userActivityMap['SaleReportPartyWise'] == 1 }">
		<div>
		  <a href="javascript:void(0);" onclick="func('saleReportPartyWise.html');">Sale Report Party Wise</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Sale_Report'] == 1 }">
		<div>
		  <a href="javascript:void(0);" onclick="func('saleReportMain.html');">Sale Report</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['BankStatement_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('bankStatementReport.html');">Bank Statement Report</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Angadia_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('angadiaReport.html');">Angadia Report</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Profit/Loss_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('profitLoss.html');">Profit/Loss Report</a>
		</div>
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['PurchaseWise_Profit/Loss_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('perReport.html');">PurchaseWise Profit/Loss Report</a>
		</div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Gl_Report'] == 1 }">	
		<div>
		  <a href="javascript:void(0);" onclick="func('glRep.html');">GL Report</a>
		</div> 
	</c:if>  
	<c:if test="${USER_SESSION.userActivityMap['CustomerBalance_Report'] == 1 }"> 
	    <div>
	       <a href="javascript:void(0);" onclick="func('custBalance.html');">Customer Balance Report</a>
	    </div> 
    </c:if>
    <c:if test="${USER_SESSION.userActivityMap['SupplierBalance_Report'] == 1 }">    
        <div>
            <a href="javascript:void(0);" onclick="func('supplierBalance.html');">Supplier Balance Report</a>
        </div> 
    </c:if>
    <c:if test="${USER_SESSION.userActivityMap['Payment_Report'] == 1 }">   
        <div>
            <a href="javascript:void(0);" onclick="func('paymentReportForAccount.html');">Payment Report</a>
        </div> 
    </c:if>
    <c:if test="${USER_SESSION.userActivityMap['Tax_Report'] == 1 }">    
        <div>
            <a href="javascript:void(0);" onclick="func('taxReport.html');">Tax Report</a>
        </div>
    </c:if>    
    <c:if test="${USER_SESSION.userActivityMap['BalanceSheet'] == 1 }">
        <div>
            <a href="javascript:void(0);" onclick="func('balanceSheet.html');">BalanceSheet</a>
        </div> 
	</c:if>
	<c:if test="${USER_SESSION.userActivityMap['Brokerage_Report'] == 1 }">
		<div>
		  <a href="javascript:void(0);" onclick="func('brokerageReport.html');">Brokerage Report</a>
		</div>
	</c:if>
	</div>
</div>	
</body>
</html>