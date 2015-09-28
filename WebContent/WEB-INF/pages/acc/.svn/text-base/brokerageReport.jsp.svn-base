<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<script type="text/javascript">
$(document).ready(function(){
    $( "#fromDate").DatePicker({
        format:'d-m-Y',
        current: '${FROM_DATE}',
        calendars: 1,
        date: '${FROM_DATE}',
        onChange: function(formated, dates){
            $('#fromDate').val(formated);
            $('#fromDate').DatePickerHide();
        }
    });
    $("#fromDate").val('${FROM_DATE}');

    $("#toDate").DatePicker({
        format:'d-m-Y',
        current: '${TO_DATE}',
        calendars: 1,
        date: '${TO_DATE}',
        onChange: function(formated, dates){
            $('#toDate').val(formated);
            $('#toDate').DatePickerHide();
        }
    });
    $("#toDate").val('${TO_DATE}');
    $('#submitProfitLoss').validate();
    $(".displayInrValue").hide();
});
</script>
</head>
<body>
<jsp:include page="../inc/inc_header.jsp">
        <jsp:param name="page" value="account"/>
        <jsp:param name="subPage" value="reports"/>
</jsp:include>
<br/>
<div id="customerBalanceSheetBody" align="center">
    <form action="brokerageReportSubmit.html" method="POST">
    <table id="greenTable" class='initialGreenTable'>
              <tr>
                  <th colspan="4" align="center"><strong><spring:message code="prof.common.Util.brokerageReport"></spring:message></strong></th>
              </tr>
              <tr>
                  <td ><label><spring:message code="prof.common.Util.startDate"></spring:message></label></td>
                  <td><input type="text" id="fromDate" name="fromDate" class="required"/></td>
              </tr>
              <tr>
                  <td ><label><spring:message code="prof.common.Util.endDate"></spring:message></label></td>
                  <td><input type="text" id="toDate" name="toDate" class="required"/></td>
              </tr>
               <tr>
                      <td ><label><spring:message code="prof.common.Util.branch"></spring:message></label></td>
                      <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 1}">
                          <td><select name="branch" id="branch">
                                <option value="-1" >----No Branch----</option>        
                                   <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
                                    <option value="${s.id}" <c:if test="${branch == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
                                    </c:forEach>
                              </select>
                          </td>
                     </c:if>
                     <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 0}">
                         <td><select name="branch" id="branch">
                               <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
                                  <c:if test="${USER_SESSION.partyAccId == s.id}" >           
                                      <option value="${s.id}" selected>${s.companyName}/${s.branchCode}/${s.termCode}</option>
                                  </c:if>
                              </c:forEach>
                               </select>
                          </td>
                     </c:if>
                  </tr>
               <tr>
                  <td colspan="2" align="center"><input type="submit" name="submit" value="Display"></td>
              </tr>
              <tr id ="showINRValue">
                  <td colspan="2" align="center" id="showInr"><input type="button" onclick="displayINRValue(this.value);" name="displayInrValue" value="Show INR">
              </tr>
           </table >
          </form>
       </div>
       <br/>
       <br/>  
       <div id="customerBalanceSheetData">
           <table id="greenTable" width="100%">
                <tr>
                    <th>Broker Name</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th class="displayInrValue">exRate</th>
                    <th class="displayInrValue">Amount(INR)</th>
                    <th>Invoice Due Date</th>
                    <th>Invoice Status</th>
                    <th>Brokerage %</th>
                    <th>Brokerage Amount</th>
                </tr>
                <c:if test="${BROKERAGE_LIST==null}">
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td></tr>
                </c:if>
                <c:if test='${BROKERAGE_LIST==""}'>
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td></tr>
                </c:if>
                <c:if test="${BROKERAGE_LIST!=null}">
	                 <c:forEach var="a" items='${BROKERAGE_LIST}' varStatus="count">
		                 <tr>
		                  <td align="center">${a.custName}</td>
		                  <td align="center">${a.date}</td>
		                  <td align="right"><fmt:formatNumber type="number" value="${a.amount}" pattern="0.00"/></td>
                          <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${a.exRate}" pattern="0.00"/></td>
                          <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${(a.amount)*(a.exRate)}" pattern="0.00"/></td>
		                  <td align="center">${a.dueDate}</td>
		                  <td align="center">${a.status}</td>
		                  <td align="center">${a.brokPer}</td>
		                  <td align="center">${a.brokAmount}</td>
		                 </tr>
		                 <c:set  var="total" value="${total+a.amount}" scope="request"></c:set>
                         <c:set  var="totalINR" value="${totalINR+((a.amount)*a.exRate)}" scope="request"></c:set>
	                 </c:forEach>
		                 <tr>
	                        <td><strong><spring:message code="prof.common.Util.total"></spring:message></strong></td>
	                        <td></td>
	                        <td align="right"><strong><fmt:formatNumber type="number" value="${total}" pattern="0.00"/></strong></td>
	                        <td></td>
	                        <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalINR}" pattern="0.00"/></strong></td>
	                     </tr>
                 </c:if>
           </table>
        </div>
</body>
</html>