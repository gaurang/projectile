<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function() {
		$("#sh").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#c").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#pu").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#sz").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
		$("#status").dropdownchecklist({
			icon : {},
			firstItemChecksAll : true,
			maxDropHeight : 300,
			width : 60
		});
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
		<div class="heading">Stock Checking Report</div>

		<div align="center" class="content">
			<form action="searchStkChekRep.html" method="post">
				<label for="status" class="ttl_names">Status</label> <select
					multiple="multiple" id="status" name="status">
					<option value="-1">All</option>
					<c:forEach items="${STATUS}" var="s" varStatus="cnt">
						<!--  <option value="${s.id}"  <c:if test="${s.id == 1}">selected</c:if>>${s.description}</option> -->

						<option value="${s.id}"
							<c:if test="${param.status !=0 && s.id == param.status }">selected</c:if>>${s.description}</option>

					</c:forEach>
				</select> <label for="">Shape</label> <select multiple="multiple" id="sh"
					name="sh">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["SH"]}'>
						<option value='${prpLov.id}'
							<c:if test="${param.sh!=0 && param.sh==prpLov.id}">selected</c:if>>${prpLov.description}</option>
					</c:forEach>
				</select> <label for="">Size</label> <select multiple="multiple" id="sz"
					name="sz">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["SZ"]}'>
						<option value='${prpLov.id}'
							<c:if test='${param.sz != 0 && prpLov.id == param.sz}'>selected</c:if>>${prpLov.description}</option>
					</c:forEach>
				</select> <label for="">Clarity</label> <select multiple="multiple" id="pu"
					name="pu">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
						<option value='${prpLov.id }'
							<c:if test="${param.pu != 0 && prpLov.id == param.pu }">selected</c:if>>${prpLov.description
							}</option>
					</c:forEach>
				</select> <label for="" class="ttl_names">Color</label> <select
					multiple="multiple" id="c" name="c">
					<option value="-1">All</option>
					<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
						<option value='${prpLov.id }'
							<c:if test='${param.c != 0 && prpLov.id == param.c}'>selected</c:if>>${prpLov.description}</option>
					</c:forEach>
				</select> <label for="pktCode">Packet Nos/Series</label> <input type="text"
					name="pktCode" id="pktCode"
					<c:if test="${param.pktCode != ''}">value="${param.pktCode}"</c:if> />
				<div class="row">
					<br />
					<label for="selfPartyAccId" class="ttl_names">Stock At</label> 
					<select id="selfPartyAccId" name="selfPartyAccId" style="width: 130px;">
                        <option value="">Select</option>
                        <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
                            <option value="${s.id}"
                                <c:if test="${USER_SESSION.partyAccId == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
                        </c:forEach>
                    </select>
					<label for="PageSize">Page Size</label> <input type="text"
						name="pageSize" id="pageSize"
						<c:if test="${param.pageSize!='' }">value="${param.pageSize}"</c:if>
						onChange="checkPageSize(id)" /> <input type="submit" name="Submit"
						id="Submit" value="submit">
				</div>
			</form>
			<br />
			<br />
			<div>
				<display:table name="STKSRCH_LIST" pagesize="${param.pageSize}"
					class="greenTable" requestURI="searchStkChekRep.html" export="true"
					id="srch">
					<display:column property="pktCode" title="PacketCode" />
					<display:column title="Memo No">
						<c:choose>
							<c:when test='${srch.orderId!=0}'>${srch.orderId}</c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="issueDate" title="IssueDate" />
					<display:column property="accType" title="LC/EX" />
					<display:column property="sh" title="Shape" />
					<display:column property="cts" title="Cts" />
					<display:column property="c" title="Color" />
					<display:column property="pu" title="Clarity" />
					<display:column title="Rap Price">
						<c:choose>
							<c:when test='${srch.rapPrice!=0}'>${srch.rapPrice}</c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</display:column>
					<c:choose>
						<c:when
							test='${srch.statusCode=="SELL" || srch.statusCode=="INV" || srch.statusCode=="EXP" }'>
							<display:column title="Rate">
								<c:choose>
									<c:when test='${srch.sellRate!=0}'>${srch.sellRate}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:when>
						<c:otherwise>
							<display:column title="Rate">
								<c:choose>
									<c:when test='${srch.rate!=0}'>${srch.rate}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test='${srch.statusCode=="SELL" || srch.statusCode=="INV" || srch.statusCode=="EXP" }'>
							<display:column title="Discount">
								<c:choose>
									<c:when test='${srch.rap!=0}'>${srch.rap}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:when>
						<c:otherwise>
							<display:column title="Discount">
								<c:choose>
									<c:when test='${srch.srap!=0}'>${srch.srap}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when
							test='${srch.statusCode=="SELL" || srch.statusCode=="INV" || srch.statusCode=="EXP" }'>
							<display:column title="TotalRate">
								<c:choose>
									<c:when test='${srch.totalRate!=0}'>${srch.totalRate}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:when>
						<c:otherwise>
							<display:column title="TotalRate">
								<c:choose>
									<c:when test='${srch.stocktotalRate!=0}'>${srch.stocktotalRate}</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</display:column>
						</c:otherwise>
					</c:choose>
					<display:column property="statusCode" title="Status" />
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
