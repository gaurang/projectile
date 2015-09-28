<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Projectile:</title>
<script type="text/javascript">
<!--
	function deleteForm() {

	}

	function getFactor() {

		var comm1 = $('#comm1').val();
		var comm2 = $('#comm2').val();
		var comm3 = $('#comm3').val();
		var byrComm = $('#byrComm').val();
		var brcomm1 = $('#brokComm1').val();
		var brcomm2 = $('#brokComm2').val();
		var interest = $('#interest').val();
		var crDays = $('#creditDays').val();
		var rap = $('#rap').val();
		//var factor =  $('#factor').val();

		var tmpFact = 0;
		var commulativeComm = 0;
		if (checkInt(comm1) && comm1 != '') {
			tmpFact += parseFloat(comm1) / 100;
			commulativeComm += parseFloat(comm1);
		}
		if (checkInt(comm2) && comm2 != '') {
			tmpFact += parseFloat(comm2) / (100 - commulativeComm);
			commulativeComm += parseFloat(comm2);
		}
		if (checknumber(comm3) && comm3 != '') {
			tmpFact += parseFloat(comm3) / (100 - commulativeComm);
			commulativeComm += parseFloat(comm3);
		}

		if (checknumber(byrComm) && byrComm != '')
			tmpFact += parseFloat(byrComm) / (100 - commulativeComm);

		if (!$('#flat').attr("checked")) {
			if (checkInt(brcomm1) && brcomm1 != '')
				tmpFact += parseFloat(brcomm1) / 100;
			if (ccheckInt(brcomm2) && brcomm2 != '')
				tmpFact += parseFloat(brcomm2) / 100;
		} else {
			if (checkInt(brcomm1) && brcomm1 != '') {
				tmpFact += (1 / ((100 - parseFloat(brcomm1)) / 100) - 1);
			}
			if (checknumber(brcomm2) && brcomm2 != '')
				tmpFact += (1 / ((100 - parseFloat(brcomm1)) / 100) - 1);
		}

		if (checknumber(interest) && (checknumber(crDays) && crDays > 0))
			tmpFact += (parseFloat(interest) / 30) * crDays;

		$('#factor').val(1 + tmpFact);

		//	if(!isNaN(rap))
		//		tmpFact += (parseFloat(rap)) ;	

	}
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="terms" />
	</jsp:include>
	<div class="container">
		<div class="content" align="center">
			<form action="termEditSubmit.html" name="termForm" id="termForm"
				method="post">
				<div align="center">
					<input type="hidden" name="id" id="id" value="${tm.id}" />
					<table align="center">
						<tr>
							<td>Term Name</td>
							<td><input type="text" name="termName" id="termName" size=20
								class="required" value="${tm.termName}" /></td>
						</tr>
						<tr>
							<td>Term Code</td>
							<td><input type="text" name="termCode" id="termCode" size=20
								class="required" value="${tm.termCode}" /></td>
						</tr>
						<tr>
							<td>Term Desc</td>
							<td><input type="text" name="termDesc" id="termDesc" size=20
								value="${tm.termDesc}" /></td>
						</tr>
						<tr>
							<td>Commision 1st</td>
							<td><input type="text" name="comm1" id="comm1" size=20
								class="number" onblur="getFactor();" value="${tm.comm1}" /> %</td>
						</tr>
						<tr>
							<td>Commision 2nd</td>
							<td><input type="text" name="comm2" id="comm2" size=20
								class="number" onblur="getFactor();" value="${tm.comm2}" /> %</td>
						</tr>
						<tr>
							<td>Commision 3rd</td>
							<td><input type="text" name="comm3" id="comm3" size=20
								class="number" onblur="getFactor();" value="${tm.comm3}" /> %</td>
						</tr>
						<tr>
							<td>Buyer Commmision (cut)</td>
							<td><input type="text" name="byrComm" id="byrComm" size=20
								class="number" onblur="getFactor();" value="${tm.byrComm}" /> %
							</td>
						</tr>
						<tr>
							<td>% flat Commission</td>
							<td><input type="checkbox" name="flat" id="flat" size=20
								checked="checked" onchange="getFactor();" /></td>
						</tr>
						<tr>
							<td>Broker Commmision 1st</td>
							<td><input type="text" name="brokComm1" id="brokComm1"
								size=20 class="number" onblur="getFactor();"
								value="${tm.brokComm1}" /> %</td>
						</tr>
						<tr>
							<td>Broker Commmision 2nd</td>
							<td><input type="text" name="brokComm2" id="brokComm2"
								size=20 class="number" onblur="getFactor();"
								value="${tm.brokComm2}" /> %</td>
						</tr>
						<tr>
							<td>Credit Days</td>
							<td><input type="text" name="creditDays" id="creditDays"
								size=20 class="number" onblur="getFactor();"
								value="${tm.creditDays}" /> Days</td>
						</tr>
						<!--<tr>
						<td>Interest days </td>
						<td><input type="text" name="creditDays" id="creditDays" size=20 class="number"/>  
						</td>		
					</tr>
					-->
						<tr>
							<td>Interest</td>
							<td><input type="text" name="interest" id="interest" size=20
								value="1.25" class="number" onblur="getFactor();"
								value="${tm.interest}" /> %/30 Days</td>
						</tr>
						<tr>
							<td>Rap Discount</td>
							<td><input type="text" name="rap" id="rap" size=20
								class="number" onblur="getFactor();" value="${tm.rap}" /> Diff.
								of rap</td>
						</tr>
						<tr>
							<td>Factor</td>
							<td><input type="text" name="factor" id="factor" size=20
								readonly="readonly" value="${tm.factor}" /> Final Multiplication
								Factor</td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" value="Save" id="saveMaster" /> <input
							type="button" value="Reset" onclick="resetFrm();" /> <input
							type="button" value="delete" onclick="deleteForm();" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>