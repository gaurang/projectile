<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
  $(document).ready(function(){
	  $('#split').click(function(){
		  $.post('stockSplit.html', { pktCode: "aaa", text2: "bbb" }, function (result) {
	            WinId = window.open('', 'newwin', 'width=400,height=500');
	            WinId.document.open();
	            WinId.document.write(result);
	            WinId.document.close();
	        });
  
		 // var load = window.open('stockSplit.html?pktCode='+pktCode+'&count='+count,' ','scrollbars=no,menubar=no,height=600,width=1400,resizable=yes,toolbar=no,location=no,status=no');

	  });
	  $('#merge').click(submitMerge);
	  $('#createNew').click(submitMerge);
	//  $('.overlay').hide();
      $('.window .close').click(function (e) {
				//Cancel the link behavior
				e.preventDefault();
				$('#mask').hide();
				$('.window').hide();
			});		
			
			//adjustHt();
  });
	function submitMerge(){
  		if($('input[name="cBox"]:checked').length <= 0){
  			alert('Please select packets to merge from' );
  			return false;
  		}
  		if($(this).attr('id') == 'createNew'){
  			$('input[name="rBox"]').val(-1);
  			$('#pktCodeHTML').show();
  		}else{
	  		if($('input[name="rBox"]:checked').length <= 0){
	  			alert('Please select packets to merge into');
	  			return false;
	  		}
	  		$('#pktCodeHTML').hide();
  		}
  		var tot = 0,totcts=0;
  		$('input[name="cBox"]:checked').each(function() {
  			var rate =$('#rate_'+$(this).val()).val();
  			var cts =$('#rate_'+$(this).val()).val();
  			if(rate && rate != ''){
	  			tot +=  parseFloat(rate*cts);
	  			totcts +=  parseFloat(cts);
  			}
  		});
  		var rate= (tot/totcts).toFixed(2);
  		$('#rate').val(rate); 
  		$('#avgRate').html(rate);
  		modalMsg('#dialog');
	}
function callSubmit(){
		if(parseFloat($('#rate').val())==null || $('#rate').val() == '' ){
			alert('Please enter correct Rate');
  			return false;
		}
		document.stockErMx.action="mergeMixSubmit.html";
  		document.stockErMx.submit();
}

 </script>

</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="purchase" />
		<jsp:param name="subPage" value="merge" />
	</jsp:include>
	<div id="content" align="center">
		<br /> <br />
		<div id="ajax">
			<img src="images/ajax-loader.gif" alt="Loading..." />
		</div>
		<div id="errorMsg">${mailMsg}</div>
		<form id="stockErMx" name="stockErMx"
			action="stockEntryMixSubmit.html" method="post"
			onsubmit="return validateForm();">
			<div id="boxes">
				<div id="dialog" class="window">
					<div id="Pagetitle" class="ui-widget-header">
						<h1 align="center">
							Enter Rate <img src="images/Close-16.png" class="close"
								alt="close" />
						</h1>
					</div>
					<br />
					<br />
					<table align="center">
						<tr>
							<td>Avg Rate</td>
							<td id="avgRate"></td>
						</tr>
						<tr>
							<td>Final Rate/Cts</td>
							<td><input type="text" id="rate" name="rate" value=""
								size="6" /></td>
						</tr>
						<tr id="pktCodeHTML">
							<td>pktCode</td>
							<td><input type="text" id="pktCode" name="pktCode" value=""
								size="6" />
							</td>
						</tr>
					</table>
					<br />
					<br />
					<div align="center">
						<input type="button" value="submit" onclick="callSubmit();" />
					</div>
				</div>
			</div>
			<div id="mask"></div>
			<table align="center" width="70%">
				<tr>

					<td width="50%" align="left" valign="top"><span id="title">Select
							Packets to Merge</span><br />
						<table align="left" id="greenTable" width="100%">
							<!--  <tr>  -->
							<tr>
								<th><input type="checkbox" name="chkAll" id="chkAll" />
								</th>
								<th>Pkt</th>
								<th>Total Cts</th>
								<th>Rate</th>
								<th>Shape</th>
								<th>Clarity</th>
								<th>Color</th>

							</tr>
							<c:forEach var="l" items="${list}" varStatus="c">
								<tr>
									<td style="text-align: center; width: 20px;"><input
										id="cBox_${c.index}" class="cbox" type="checkbox" name="cBox"
										value="${l.pktId}"></td>
									<td>${l.pktCode}</td>
									<td>${l.cts} <input type="hidden" id="cts_${l.pktId}"
										value="${l.cts}"></td>
									<td>${l.rate} <input type="hidden" id="rate_${l.pktId}"
										value="${l.rate}"></td>
									<td><span id="shFr_${c.index}">${l.shFr}</span>-<span
										id="">${l.shTo}</span></td>
									<td>${l.puFr}-${l.puTo}</td>
									<td>${l.cFr}-${l.cTo}</td>
								</tr>
							</c:forEach>
						</table></td>
					<td align="left" valign="top"><span id="title">Select
							Packets to Merge</span><br />
						<table align="left" id="greenTable">
							<tr>
								<th>Select</th>
								<th>Pkt</th>
								<th>Total Cts</th>
								<th>Rate</th>

							</tr>
							<c:forEach var="l" items="${list}" varStatus="c">
								<tr>
									<td style="text-align: center; width: 20px;"><input
										id="rBox_${c.index}" class="rbox" type="radio" name="rBox"
										value="${l.pktId}"></td>
									<td>${l.pktCode}</td>
									<td>${l.cts}</td>
									<td>${l.rate}</td>
								</tr>
							</c:forEach>
						</table></td>
				</tr>
			</table>
			<div align="center">
				<input type="button" id="merge" value="Merge" /> <input
					type="button" id="createNew" value="Create New" />
			</div>

		</form>

	</div>
</body>
</html>








