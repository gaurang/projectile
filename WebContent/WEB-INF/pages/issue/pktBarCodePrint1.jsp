<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile ::</title>

<!--  <script type="text/javascript" src="js/flip.js"></script> -->
<style lang="text/css">
@page land {
	size: landscape;
}

@page port {
	size: portrait;
}

.landscapePage {
	page: land;
}

.portraitPage {
	page: port;
}

body {
	margin: 0;
}

.rotate {
	-webkit-transform: rotate(-180deg);
	-moz-transform: rotate(-180deg);
	/* for ie */
	filter: progid : DXImageTransform.Microsoft.BasicImage ( rotation = 2 );
}
</style>

</head>
<body class="landscapePage">

	<!-- Main Content here -->
	<div align="center" class="rotate"
		style="width: 568.96px; height: 213.36px;">
		www.hrcdiamond.com<br /> <img
			src="getBarcode.html?pktCode=${param.pktCode}" alt="barcode"
			style="height: 84px; width: 225px;">
	</div>

	<div align="left" style="width: 50%">
		<br /> <img src="log.jpg">
	</div>
	<div align="left">
		<br />
		<br />
		<table width="50%" style="width: 568.96px; height: 213.36px;">

			<tr>
				<td colspan="2" rowspan="2"><img src="images/l.png" height="60"></img>
				</td>
				<th>Stock ID</th>
				<td>${pktDetails.pktCode}</td>

			</tr>
			<tr>
				<td colspan="4"></td>
			</tr>
			<tr>

				<th>Shape</th>
				<td>${pktDetails.sh}</td>

			</tr>
			<tr>

				<th>Color</th>
				<td>${pktDetails.c}</td>
				<th>Size</th>
				<td>${pktDetails.cts}</td>
			</tr>
			<tr>
				<th>Clarity</th>
				<td>${pktDetails.pu}</td>
				<th>Lab</th>
				<td>${pktDetails.lab}</td>
			</tr>

			<tr>
				<th>Cut</th>
				<td>${pktDetails.ct}</td>
				<th>Shade</th>
				<td>${pktDetails.sd}</td>
			</tr>
			<tr>
				<th>Flour</th>
				<td>${pktDetails.flc}</td>
				<th>Disc</th>
				<td>${pktDetails.rap}</td>

			</tr>
		</table>
	</div>
</body>
</html>























