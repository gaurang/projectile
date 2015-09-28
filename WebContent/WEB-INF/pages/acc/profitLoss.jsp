<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
var sales ='SA';
var otherSales='OT';
var goodSold='GS';
function salesRevenue(){
	alert("Sales Revenue Called!");
	$.ajax({type:'POST',
        url:'salesRevenue.html',
        dataType: 'json',
        success: function(json){
               data = json;
               printFiles(data,sales);
        },
        error: function(xmlHttpRequest, textStatus, errorThrown) {
            
        }
    });
}
function otherRevenue(){
	alert("other Revenue Called!");
	$.ajax({type:'POST',
        url:'otherRevenue.html',
        dataType: 'json',
        success: function(json){
               data = json;
               printFiles(data,otherSales);
        },
        error: function(xmlHttpRequest, textStatus, errorThrown) {
            
        }
    });
}
function goodSolds(){
	alert("good solds Called!");
	$.ajax({type:'POST',
        url:'goodSolds.html',
        dataType: 'json',
        success: function(json){
               data = json;
               printFiles(data,goodSold);
        },
        error: function(xmlHttpRequest, textStatus, errorThrown) {
            
        }
    });
}
function printFiles(data,value){
	var html = '';
	var otherHtml='';
		otherHtml+="<tr id='valuesInsert'>";
		otherHtml+="</tr>";
		otherHtml+="<tr>";
		otherHtml+="<th><label><spring:message code='prof.common.Util.groupAccountName'></spring:message></label></th>";
		otherHtml+="<th><label><spring:message code='prof.common.Util.period'></spring:message></label></th>";
		otherHtml+="<th><label><spring:message code='prof.common.Util.accumulated'></spring:message></label></th>";
		otherHtml+="<th><label><spring:message code='prof.common.Util.achieved'></spring:message></label></th>";
		otherHtml+="</tr>";
	if(value=='SA'){
		otherHtml+="<td colspan='4' align='center'><strong><spring:message code='prof.common.Util.salesRevenue'></spring:message></strong></td>";
		$('#valueInsert').inserAfter();
	}else if(value=='OT'){
		//otherHtml+="<tr>";
        otherHtml+="<td colspan='4' align='center'><strong><spring:message code='prof.common.Util.otherRevenues'></spring:message></strong></td>";
       // otherHtml+="</tr>";
	}else if(value=='GS'){
		//otherHtml+="<tr>";
        otherHtml+="<td colspan='4' align='center'><strong><spring:message code='prof.common.Util.goodSolds'></spring:message></strong></td>";
       // otherHtml+="</tr>";
	}else{
		alert("No Value to Display");
	}
		
	
}
$(document).ready(function(){
	
	$( "#fromDate").DatePicker({
	    format:'d-m-Y',
	    current: '${CURR_DATE}',
	    calendars: 1,
	    date: '${CURR_DATE}',
	    onChange: function(formated, dates){
	        $('#fromDate').val(formated);
	        $('#fromDate').DatePickerHide();
	    }
	});
	$("#fromDate").val('${CURR_DATE}');

	$("#toDate").DatePicker({
	    format:'d-m-Y',
	    current: '${CURR_DATE}',
	    calendars: 1,
	    date: '${CURR_DATE}',
	    onChange: function(formated, dates){
	        $('#toDate').val(formated);
	        $('#toDate').DatePickerHide();
	    }
	});
	$("#toDate").val('${CURR_DATE}');
	$('#submitProfitLoss').validate();
});
</script>
</head>
<body>
	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="account" />
		<jsp:param name="subPage" value="reports" />
	</jsp:include>
	<div id="profLossCont">
		<p>
			<spring:message code="prof.common.Util.Menu"></spring:message>
		</p>
		<hr></hr>
		<div id="plUpperMenu">
			<form id="submitProfitLoss" action="submitProfitLoss.html"
				method="POST">
				<p>
					<label id=from> <spring:message
							code="prof.common.Util.From"></spring:message> </label> <input
						type="text" id="fromDate" name="fromDate" class="required" /> <label
						id=to> <spring:message code="prof.common.Util.To"></spring:message>
					</label> <input type="text" id="toDate" name="toDate" class="required" />
					<%-- <label id=compareTo>
                  <spring:message code="prof.common.Util.compareTo"></spring:message>
              </label>
              <select name="compareTo">
                <option value="-1" ><spring:message code="prof.common.Util.select"></spring:message></option>
              </select>
               <label id=dimension>
                  <spring:message code="prof.common.Util.dimension"></spring:message>
              </label>
              <select name="dimension" >
                <option value="-1" ><spring:message code="prof.common.Util.select"></spring:message></option>
              </select> --%>
					<input type="submit" id="submit" value="show" name="submit" />
				</p>
			</form>
		</div>
		<!-- PlUpper Menu End Here-->
		<table id="greenTable" class='initialGreenTable' border="1">
			<tr>
				<td colspan="4" align="center"><strong><spring:message
							code="prof.common.Util.income"></spring:message>
				</strong>
				</td>
			</tr>
			<tr>
				<th><label><spring:message
							code="prof.common.Util.groupAccountName"></spring:message>
				</label>
				</th>
				<th><label><spring:message
							code="prof.common.Util.period"></spring:message>
				</label>
				</th>
				<%--  <td><label><spring:message code="prof.common.Util.accumulated"></spring:message></label></td>
			      <td><label><spring:message code="prof.common.Util.achieved"></spring:message></label></td> --%>
			</tr>
			<tr>
				<td><a href="javascript:salesRevenue();"><label><spring:message
								code="prof.common.Util.salesRevenue"></spring:message>
					</label>
				</a>
				</td>
				<td>${AMOUNT}</td>
			</tr>
			<tr>
				<td><a href="javascript:otherRevenue();"><label><spring:message
								code="prof.common.Util.otherRevenues"></spring:message>
					</label>
				</a>
				</td>
				<td></td>
			</tr>
			<tr>
				<td><strong><label><spring:message
								code="prof.common.Util.totalincome"></spring:message>
					</label>
				</strong>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><strong><spring:message
							code="prof.common.Util.cost"></spring:message>
				</strong>
				</td>
			</tr>
			<tr>
				<th><label><spring:message
							code="prof.common.Util.groupAccountName"></spring:message>
				</label>
				</th>
				<th><label><spring:message
							code="prof.common.Util.period"></spring:message>
				</label>
				</th>
				<%-- <td><label><spring:message code="prof.common.Util.accumulated"></spring:message></label></td>
                  <td><label><spring:message code="prof.common.Util.achieved"></spring:message></label></td> --%>
			</tr>
			<tr>
				<td><a href="javascript:goodSolds();"><label><spring:message
								code="prof.common.Util.goodSolds"></spring:message>
					</label>
				</a>
				</td>
				<td>${GOOD_COST.amount}</td>
			</tr>
			<%--   <tr>
                  <td ><a href=""><label><spring:message code="prof.common.Util.generalExpenses"></spring:message></label></a></td>
              </tr> --%>
			<tr>
				<td><strong><label><spring:message
								code="prof.common.Util.totalCost"></spring:message>
					</label>
				</strong>
				</td>
			</tr>
			<tr>
				<td><strong><label><spring:message
								code="prof.common.Util.calculatedReturn"></spring:message>
					</label>
				</strong>
				</td>
			</tr>
		</table>
		<table id="greenTable" class='afterGreenTable' border="1">
		</table>
		<div id="plData"></div>
		<!-- plData Cont End Here -->
	</div>
	<!-- profLoss Cont End Here -->
</body>
</html>