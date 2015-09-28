<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>H.Riddhesh &amp; Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script src="js/fmt.js" type="text/javascript"></script>
</head>
<body>

<div align="center"><h2 style="color: #ffffff;"> Packet Details</h2></div>
	<table class="bluetable" align="center" cellpadding="3" cellspacing="3" width="98%">
		<tr>
			<td> Packet No.</td><td>${pd.pktCode}</td>
			<td> Weight</td><td>${pd.cts}</td>
		</tr>
		<tr>
			<td> Remark</td><td>${pd.remark}</td>
			<td> Comments</td><td>${pd.comments}</td>
		</tr>
		<tr>
			<td> Lab </td><td>${pd.lab}</td>
			<td> Cert Link</td><td><a href="javascript:callURL('${pd.pktCode}');" class="style3">${pd.pktCode}</a></td>
		</tr>
		<tr>
			<td colspan="6">
				<img src="/cert/img/I${pd.pktCode}.jpg" alt="${pd.pktCode}" width=480 height=340/>
			</td>
		</tr>
	</table>
<div>
</div>

</body>
</html>