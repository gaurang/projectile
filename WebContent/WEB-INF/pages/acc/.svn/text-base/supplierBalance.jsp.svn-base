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
    <form action="supplierBalanceSubmit.html" method="POST">
    <table id="greenTable" class='initialGreenTable' >
              <tr>
                  <th colspan="4" align="center"><strong><spring:message code="prof.common.Util.suppBal"></spring:message></strong></th>
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
                  <td ><label><spring:message code="prof.common.Util.supplierName"></spring:message></label></td>
                  <td>
                       <select name="suppName" id="suppName">
                        <option value="-1" >--No Customer Filter--</option>        
                            <c:forEach items="${VENDOR_LIST}" var="c" varStatus="count">
                                <option value='${c.id}'<c:if test="${custName == c.id}"> selected</c:if>>${c.description}</option>
                            </c:forEach>
                       </select>
                  </td>
              </tr>
	              <tr>
	                  <td ><label><spring:message code="prof.common.Util.branch"></spring:message></label></td>
	                  <c:if test="${USER_SESSION.userActivityMap['All_Branch'] == 1}">
		                  <td><select name="branch" id="branch">
		                        <option value="-1" >----No Branch----</option>        
		                           <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
		                            <option value="${s.id}"<c:if test="${branch == s.id}"> selected</c:if>>${s.companyName}/${s.branchCode}/${s.termCode}</option>
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
       <div id="customerBalanceSheetData">
           <table id="greenTable" width="100%">
                <tr>
                    <th>Type</th>
                    <th>Date</th>
                    <th>comments</th>
                    <th>ref/InvId</th>
                    <th>Debit(USD)</th>
                    <th>Credit(USD)</th>
                    <th>Balance(USD)</th>
                    <th class="displayInrValue">ex Rate</th>
                    <th class="displayInrValue">Debit (INR)</th>
                    <th class="displayInrValue">Credit(INR)</th>
                    <th class="displayInrValue">Balance(INR)</th>
                </tr>
                <tr></tr>
                <c:set var="compName" value="" scope="request"></c:set>
                <c:if test="${suppList==null}">
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:if test='${suppList==""}'>
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:set  var="partyAccId" value=""></c:set>
                <c:if test="${suppList!=null}">
                    <c:forEach var="a" items='${suppList}' varStatus="count">
	                    <c:if test="${a.custName != compName && count.index>0}">
	                        <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
                            <tr> 
                                   <td align="center"><strong><spring:message code="prof.common.Util.totalBalance"></spring:message></strong></td>
                                   <td></td>
                                   <td></td>
                                   <td></td>
                                   <td align="right"> <strong><fmt:formatNumber type="number" value="${totalBalanceDebit}" pattern="0.00"/></strong></td>
                                   <td align="right"><strong><fmt:formatNumber type="number" value="${totalBalanceCredit}" pattern="0.00"/></strong></td>
                                   <td align="right"><strong><fmt:formatNumber type="number" value="${totalBalance}" pattern="0.00"/></strong></td>
                                   <td></td>
                                   <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceDebitINR}" pattern="0.00"/></strong></td>
                                   <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceCreditINR}" pattern="0.00"/></strong></td>
                                   <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceINR}" pattern="0.00"/></strong></td>
                             </tr>   
                        </c:if>
	                     <c:if test="${a.id != partyAccId}">
	                             <c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
	                                 <c:if test="${a.id == s.id}">
	                                    <tr><td colspan="20">For Branch : <strong>${s.companyName}/${s.branchCode}</strong></td></tr>
	                                 </c:if>
	                            </c:forEach>
	                     </c:if>
	                     <c:if test="${a.custName != compName}">
	                             <tr><td colspan="18"><p></p></td></tr>
	                             <tr><td colspan="18">Supplier Name :-  <strong>${a.custName}</strong></td></tr>
	                             <c:set  var="totalBalance" value="0" scope="request"></c:set>
                                 <c:set  var="totalBalanceDebit" value="0" scope="request"></c:set>
                                 <c:set  var="totalBalanceCredit" value="0" scope="request"></c:set>
                                 <c:set  var="totalBalanceINR" value="0" scope="request"></c:set>
	                             <c:set  var="totalBalanceDebitINR" value="0" scope="request"></c:set>
	                             <c:set  var="totalBalanceCreditINR" value="0" scope="request"></c:set>
	                     </c:if>
	                     <c:set var="balance" value="${(a.debit)-(a.credit)}" />
	                     <c:set var="balanceDebit" value="${a.debit}" />
                         <c:set var="balanceCredit" value="${a.credit}" />
                         <c:set var="balanceINR" value="${((a.debit)-(a.credit))*(a.exRate)}" />
                         <c:set var="balanceDebitINR" value="${(a.debit)*(a.exRate)}" />
                         <c:set var="balanceCreditINR" value="${(a.credit)*(a.exRate)}" />
	                     <tr>
	                        <td align="center">${a.trans_type}</td>
	                        <td align="center">${a.trans_date}</td>
	                        <td align="center">${a.comment}</td>
	                        <td align="center">${a.ref}</td>
	                        <td align="right"><fmt:formatNumber type="number" value="${a.debit}" pattern="0.00"/></td>
                            <td align="right"><fmt:formatNumber type="number" value="${a.credit}" pattern="0.00"/></td>
                            <td align="right"><fmt:formatNumber type="number" value="${(a.debit)-(a.credit)}" pattern="0.00"/></td>
                            <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${a.exRate}" pattern="0.00"/></td>
                            <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${(a.debit)*(a.exRate)}" pattern="0.00"/></td>
                            <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${(a.credit)*(a.exRate)}" pattern="0.00"/></td>
                            <td align="right" class="displayInrValue"><fmt:formatNumber type="number" value="${(((a.debit)-(a.credit))*(a.exRate))}" pattern="0.00"/></td>
	                     </tr>
	                        <c:set  var="totalBalance" value="${totalBalance+balance}" scope="request"></c:set>
	                        <c:set  var="totalBalanceDebit" value="${totalBalanceDebit+balanceDebit}" scope="request"></c:set>
                            <c:set  var="totalBalanceCredit" value="${totalBalanceCredit+balanceCredit}" scope="request"></c:set>
                            <c:set  var="totalBalanceINR" value="${totalBalanceINR+balanceINR}" scope="request"></c:set>
                            <c:set  var="totalBalanceDebitINR" value="${totalBalanceDebitINR+balanceDebitINR}" scope="request"></c:set>
                            <c:set  var="totalBalanceCreditINR" value="${totalBalanceCreditINR+balanceCreditINR}" scope="request"></c:set>
                            <c:set  var="partyAccId" value="${a.id}"></c:set>
                            <c:set  var="compName" value="${a.custName}"></c:set>
                     </c:forEach>
                     <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
                     <tr> 
                            <td align="center"><strong><spring:message code="prof.common.Util.totalBalance"></spring:message></strong></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td align="right"><strong><fmt:formatNumber type="number" value="${totalBalanceDebit}" pattern="0.00"/></strong></td>
	                        <td align="right"><strong><fmt:formatNumber type="number" value="${totalBalanceCredit}" pattern="0.00"/></strong></td>
	                        <td align="right"><strong><fmt:formatNumber type="number" value="${totalBalance}" pattern="0.00"/></strong></td>
	                        <td></td>
	                        <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceDebitINR}" pattern="0.00"/></strong></td>
	                        <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceCreditINR}" pattern="0.00"/></strong></td>
	                        <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${totalBalanceINR}" pattern="0.00"/></strong></td>
                      </tr>  
                   </c:if>
            </table>
       </div>
</body>
</html>