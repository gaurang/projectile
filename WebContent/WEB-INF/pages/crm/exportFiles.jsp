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
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="mapping" />
	</jsp:include>
	<div class="container" align="center">
		<label>Packet Type</label>
		<select id="pktType" name="pktType" onchange="func('exportFilesList.html?pktType='+this.value);">
			<option value="single"
				<c:if test="${pktType == 'single'}">selected="true"</c:if>>Single</option>
			<option value="mixed"
				<c:if test="${pktType == 'mixed'}">selected="true"</c:if>>Mixed</option>
		</select>
		<div align="center" class="content">
			<br />
			<table id="greenTable">
				<tr>
					<th>File Format</th>
					<th>File Type</th>
					<th>Process Type</th>
					<th>Packet Type</th>
					<th>Edit</th>
				</tr>
				<c:forEach items="${FILES_LIST}" var="f">
					<tr>
						<td>${f.fileName}</td>
						<td>${f.type}</td>
						<td>${f.processType}</td>
						<td>${f.pktType}</td>
						<td><a href="javascript:void(0);" onclick="addTab('Edit FileMapping','exportFileMap.html?fileId=${f.id}');">E</a></td>
					</tr>
				</c:forEach>
			</table>

			<input type="button" value="Add New"
				onclick="func('exportFileMap.html');">
		</div>
	</div>
</body>
</html>