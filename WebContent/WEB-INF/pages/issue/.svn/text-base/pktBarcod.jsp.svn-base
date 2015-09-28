<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Projectile:</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#BarCode").click(function() {
		var Backlen=history.length;   history.go(-Backlen);
		window.open("pktBarcodePrint.html?pktCode="+$('#pktCode').val(),"_blank");
	});
});
$("form").keypress(function(e) {
	    if (e.which == 13) {
	    return false;
	    }
    });
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="sales" />
		<jsp:param name="subPage" value="barcode" />
	</jsp:include>
	<div class="container">
		<div class="content" align="center">
			<div class="heading">Print Barcode</div>
			<form action="pktBarcodePrint.html" method="post" target="_blank">
				<div style="width: 370px;">
					<div class="row">
						<div class="element">
							<label for="PktNo">Packet No</label>
						</div>
						<div class="element">
							<input type="text" name="pktCode" id="pktCode" class="required"/>
						</div>
					</div>
					<div class="row">
						<input type="button" name="BarCode" id="BarCode" value="GoBarCode">
					</div>
				</div>
			</form>

		</div>
	</div>

</body>
</html>




