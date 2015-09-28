<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<link href="dash/inettuts.css" rel="stylesheet" type="text/css" />
<link href="dash/facebook.alert.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="dash/jquery_facebook.alert.js"></script>
<script type="text/javascript" src="dash/jquery.alerts.js"></script>
<title>Projectile: Dashboard</title>
</head>
<body>
	<div id="head">
		<h1>Dashboard</h1>
	</div>
	<div id="columns">
		<ul id="column1" class="column">
			<c:forEach items="${PARAM}" var="pl" varStatus="status">
				<c:choose>
					<c:when
						test="${DASHDATA[status.index].type == 'QueryCode' && DASHDATA[status.index].divPosition == 'Due_Payments' 
		        			}">
						<li class="widget color-green" id="intro">
							<div class="widget-head">
								<h3>Due Payments(Partywise)</h3>
							</div>
							<div class="widget-content">
								<table id="greenTable">
									<thead>
										<tr>
											<th>id</th>
											<th>Desc</th>
										</tr>
										<c:forEach items="${pl}" var="i" varStatus="cnt">
											<tr>
												<td>${i.id}</td>
												<td>${i.description}</td>
											</tr>
										</c:forEach>
									</thead>
								</table>
							</div></li>
					</c:when>
				</c:choose>
			</c:forEach>
			</li>
			<c:forEach items="${PARAM}" var="pl" varStatus="status">
				<c:choose>
					<c:when
						test="${DASHDATA[status.index].type == 'QueryCode' && DASHDATA[status.index].divPosition == 'Cheque_Due_Partywise' 
		        			}">
						<li class="widget color-green">
							<div class="widget-head">
								<h3>Due Cheques</h3>
							</div>
							<div class="widget-content">
								The Due Cheques are ${pl[status.index].id} and money is Rs.
								<c:if test="${pl[status.index].description == null}">0</c:if>
								<c:if test="${pl[status.index].description != null}">${pl[status.index].description}</c:if>
								.
							</div></li>
					</c:when>
				</c:choose>
			</c:forEach>
			<li class="widget color-red">
				<div class="widget-head">
					<h3>Due Payables</h3>
				</div>
				<div class="widget-content"></div></li>
		</ul>

		<ul id="column2" class="column">
			<li class="widget color-blue"><c:forEach items="${PARAM}"
					var="pl" varStatus="status">
					<c:choose>
						<c:when
							test="${DASHDATA[status.index].type == 'Integer' && DASHDATA[status.index].divPosition == 'OverDue_Web_Memos'}">
							<div class="widget-head">
								<h3>Overdue Web Memos</h3>
							</div>
							<div class="widget-content">
								There are ${pl} Overdue Web Memos. Check them <a
									target="_newtab" href="memoReport.html?web=1">here</a>
							</div>
						</c:when>
					</c:choose>
				</c:forEach></li>
			<li class="widget color-yellow"><c:forEach items="${PARAM}"
					var="pl" varStatus="status">
					<c:choose>
						<c:when
							test="${DASHDATA[status.index].type == 'Integer' && DASHDATA[status.index].divPosition == 'Pending_Web_Memos'}">
							<div class="widget-head">
								<h3>Web Registrations</h3>
							</div>
							<div class="widget-content">
								There are ${pl} Pending/New Web Memos. Check them <a
									target="_newtab" href="memoReport.html?web=1">here</a>
							</div>
						</c:when>
					</c:choose>
				</c:forEach></li>
		</ul>

		<ul id="column3" class="column">
			<li class="widget color-orange"><c:forEach items="${PARAM}"
					var="pl" varStatus="status">
					<c:choose>
						<c:when
							test="${DASHDATA[status.index].type == 'Integer' && DASHDATA[status.index].divPosition == 'Parcel_Dist'}">
							<div class="widget-head">
								<h3>Parcels to Distribute</h3>
							</div>
							<div class="widget-content">
								There are ${pl} Parcels to Distribute. Distribute them <a
									target="_newtab" href="parcelDistribute.html">here</a>
							</div>
						</c:when>
					</c:choose>
				</c:forEach></li>
			<li class="widget color-white"><c:forEach items="${PARAM}"
					var="pl" varStatus="status">
					<c:choose>
						<c:when
							test="${DASHDATA[status.index].type == 'Integer' && DASHDATA[status.index].divPosition == 'Invalid_Stock_count'}">
							<div class="widget-head">
								<h3>Invalid Stock</h3>
							</div>
							<div class="widget-content">
								The Invalid Stock Count is ${pl} Click <a
									href="invalidStock.html" target="_newtab">Here.</a>
							</div>
						</c:when>
					</c:choose>
				</c:forEach></li>
		</ul>

	</div>

	<script type="text/javascript" src="js/jquery/jquery.js"></script>
	<script type="text/javascript"
		src="dash/jquery-ui-personalized-1.6rc2.min.js"></script>
	<script type="text/javascript" src="dash/inettuts.js"></script>
</body>
</html>