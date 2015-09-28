<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <jsp:include page="../inc/inc_header.jsp">
        <jsp:param name="page" value="purchase" />
        <jsp:param name="subPage" value="invalid" />
    </jsp:include>
	<div>
	<div class="container">
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<table class="greenTable">
			<tr>
				<th>Sr No</th>
				<th>Pkt No</th>
				<th>Shape</th>
				<th>Cts</th>
				<th>Color</th>
				<th>Purity</th>


				<th>Cut</th>
				<th>Polish</th>
				<th>Sym</th>
				<th>Lab</th>
				<th>Flour</th>
				<th>FLS</th>
				<th>Depth</th>
				<th>Table</th>

				<th>RapPrice</th>
				<th>Discount</th>
				<th>Rate</th>
				<th>Base Rate</th>
				<!-- <th>default</th>
				<th>RapStatus</th>-->
				<th>Total Rate</th>
				<th>Measurement</th>



			</tr>
			<c:set var="totalCts" value="0"></c:set>
			<c:set var="avgRate" value="0"></c:set>
			<c:set var="avgRap" value="0"></c:set>
			<c:set var="totalRate" value="0"></c:set>


			<c:forEach var="s" items="${stock}" varStatus="count">
				<c:if test="${s.cts != 0 && s.cts !=null}">
					<c:set var="totalCts" value="${totalCts+s.cts}"></c:set>
				</c:if>
				<c:if
					test="${s.cts != 0 && s.cts !=null && s.rate != 0 && s.rate !=null}">
					<c:set var="totalRate" value="${totalRate+(s.cts*s.totalRate)}"></c:set>
				</c:if>
				<c:if test="${s.rate != 0 && s.rate !=null}">
					<c:set var="avgRate" value="${avgRate+s.rate/2}"></c:set>
				</c:if>
				<c:if test="${s.rap != 0 && s.rap !=null}">
					<c:set var="avgRap" value="${avgRap+s.rap/2}"></c:set>
				</c:if>
				<tr>
					<td>${count.index}</td>
					<td>${s.pktCode}</td>
					<td>${s.sh}</td>
					<td>${s.cts}</td>
					<td>${s.c}</td>
					<td>${s.pu}</td>

					<td>${s.ct}</td>
					<td>${s.po}</td>
					<td>${s.sy}</td>
					<td>${s.lab}</td>

					<td>${s.flc}</td>
					<td>${s.fls}</td>
					<td>${s.dp}</td>
					<td>${s.t}</td>
					<td>${s.rapPrice}</td>
					<td>${s.rap}</td>
					<td>${s.rate}</td>
					<td>${s.baseRate}</td>
					<!--<td>default</td>
				<td>RapStatus</td> -->
					<td>${s.rate*s.cts}</td>
					<td>${s.md}*${s.xd}*${s.d}</td>









				</tr>
			</c:forEach>
			<c:if test="${empty stock}">
				<tr>
					<td colspan="17">No matching pkts found</td>
				</tr>
			</c:if>
			<tr>






				<td></td>
				<td></td>
				<td></td>
				<td>${totalCts}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>${avgRap}</td>
				<td>${avgRate}</td>
				<td></td>
				<td>${totalRate}</td>
			</tr>
		</table>
	</div>
	</div>
</body>
</html>