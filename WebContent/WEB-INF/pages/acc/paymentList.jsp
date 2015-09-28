<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function() {
	});
	function checkPageSize(id) {
		var field = document.getElementById(id);
		if (isNaN(field.value)) {
			field.value = '';
			alert('Please Enter The Numeric Value');
		}
	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<form action="paymentList.html" method="post">
				<div id="searchPanel">
					<div class="row">
						<label for="mode">Mode</label>
						<div class="element">
							<select name="mode" id="mode">
								<option value="">All</option>
								<option value="CASH"
									<c:if test="${param.mode == 'CASH'}">selected</c:if>>CASH</option>
								<option value="CHQ"
									<c:if test="${param.mode == 'CHQ'}">selected</c:if>>CHEQUE</option>
							</select>
						</div>
					</div>
					<div class="row">
						<label for="partyName">Company Name</label>
						<div class="element">
							<input type="text" name="partyName" id="partyName" size="10"
								maxlength="50" value="${param.partyName}" />
						</div>
					</div>
					<input type="submit" name="Submit" id="Submit" value="submit">
				</div>
			</form>
			<br />
			<br /> <br />
			<br /> <br />
			<br />

			<div>
				<div class="heading">Payment Received</div>
				<c:set var="paymentId" value="0" scope="request"></c:set>
				<input type="hidden" value="50" name="pageSize">
				<display:table name="PAYMENT_LIST"
					pagesize="${param.pageSize!=null?param.pageSize:20}"
					class="greenTable" requestURI="paymentList.html"  export="true"
					id="p">
					<display:column title="PaymentId">
						<c:if test="${p.id != paymentId}">
						${p.id}
					</c:if>
					</display:column>
					<display:column title="Payment Date">
						<c:if test="${p.id != paymentId}">
						${p.paymentDate}
					</c:if>
						<c:set var="paymentId" value="${p.id}" scope="request"></c:set>
					</display:column>
					<display:column property="mode" title="mode" />

					<display:column property="invoiceId" title="Invoice" />
					<display:column property="amt" title="Amount" />
					<display:column property="bankName" title="Bank" />
					<display:column property="bankAcc" title="Bank Account" />
					<display:column property="chequeDate" title="Cheque Date" />
					<display:column property="chequeNo" title="Cheque No." />
					<display:column title="Cleared">
						<%-- <jsp:useBean id="now" class="java.util.Date" />
						<fmt:parseDate var="chequeDate" value="${p.chequeDate}"
							type="DATE" pattern="dd-MM-yyyy" /> --%>
							
						<c:if test="${p.clearStatus == 1}">
						Cleared
					</c:if>
						<c:if test="${p.clearStatus !=1 &&  now <= chequeDate}">
						PDC
					</c:if>
						<c:if test="${p.clearStatus != 1 && now > chequeDate}">
							<a href="clearPayment.html?pdId=${p.paymentDetailId}">Mark
								Clear</a>
						</c:if>
					</display:column>


					<!-- <display:setProperty name="export.pdf" value="true" /> -->
					<display:setProperty name="export.pdf.filename"
						value="saleData.pdf" />

					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.excel" value="true" />
					<display:setProperty name="export.csv" value="true" />
				</display:table>
				<br />
				<br />
				<br />
			</div>
		</div>
	</div>
</body>
</html>
