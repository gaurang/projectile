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
    $(".displayInrValue").hide();
    $('#submitProfitLoss').validate();
});
function chooseBalance(accountCode,frDate,tDate,id){
	if(accountCode==1060){
		addTab('Checking Account','checkingAccount.html?accountCode='+accountCode+'&&fromDate='+frDate+'&&toDate='+tDate+'&&branch='+id);
	}
	else if(accountCode==1065){
		addTab('Petty Cash Account',"pettyCashAccount.html?accountCode="+accountCode+"&&fromDate="+frDate+"&&toDate="+tDate+"&&branch="+id);
	}else if(accountCode==1200){
		addTab('Accont Receivables',"accountReceivables.html?fromDate="+frDate+"&&toDate="+tDate+"&&branch="+id);
	}else if(accountCode==1510){
		addTab('Inventory',"searchStkChekRep.html?status=1&&status=2");
	}else if(accountCode==2100){
		addTab('Account Payable',"accountPayable.html?fromDate="+frDate+"&&toDate="+tDate+"&&branch="+id);
	}else if(accountCode==2150){
		addTab('SaleTax Report',"taxReportSubmit.html?fromDate="+frDate+"&&toDate="+tDate+"");
	}else{
		addTab('Sales Report',"saleReportPartyWise.html?fromDate="+frDate+"&&toDate="+tDate+"");
	}
}
</script>
</head>
<body>
<jsp:include page="../inc/inc_header.jsp">
        <jsp:param name="page" value="account"/>
        <jsp:param name="subPage" value="reports"/>
</jsp:include>
<br/>
<div>
<div id="customerBalanceSheetBody" align="center">
    <form action="balanceSheetSubmit.html" method="POST">
    <table id="greenTable" class='initialGreenTable'>
              <tr>
                  <th colspan="4" align="center"><strong><spring:message code="prof.common.Util.balanceSheet"></spring:message></strong></th>
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
                    <th>Account Code</th>
                    <th>Account Name</th>
                    <th>opening Balance(USD)</th>
                    <th>Closing Balance(USD)</th>
                    <th>Difference(USD)</th>
                    <th class="displayInrValue">exRate</th>
                    <th class="displayInrValue">opening Balance(INR)</th>
                    <th class="displayInrValue">Closing Balance(INR)</th>
                    <th class="displayInrValue">Difference(INR)</th>
                </tr>
                <c:if test="${BAL_REPORT_OPEN==null}">
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:if test='${BAL_REPORT_OPEN==""}'>
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:set  var="totalOpen" value="0" scope="request"></c:set>
                <c:set  var="totalClose" value="0" scope="request"></c:set>
                <c:set  var="totalDiff" value="0" scope="request"></c:set>
                <c:set  var="totalOpenINR" value="0" scope="request"></c:set>
                <c:set  var="totalCloseINR" value="0" scope="request"></c:set>
                <c:set  var="totalDiffINR" value="0" scope="request"></c:set>
                <c:if test="${BAL_REPORT_OPEN!=null}">
                    <c:forEach var="a" items='${BAL_REPORT_OPEN}' varStatus="count">
                         <c:if test="${count.index<=0}">
                           <c:set  var="open" value="0" scope="request"></c:set>
                           <c:set  var="close" value="0" scope="request"></c:set>
                           <c:set  var="diff" value="0" scope="request"></c:set>
                           <c:set  var="openINR" value="0" scope="request"></c:set>
                           <c:set  var="closeINR" value="0" scope="request"></c:set>
                           <c:set  var="diffINR" value="0" scope="request"></c:set>
                           <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.assest"></spring:message></strong></td></tr>
                         </c:if>
                         <c:if test="${count.index==4}">
                            <c:set  var="open" value="0" scope="request"></c:set>
                            <c:set  var="close" value="0" scope="request"></c:set>
                            <c:set  var="diff" value="0" scope="request"></c:set>
                            <c:set  var="openINR" value="0" scope="request"></c:set>
                            <c:set  var="closeINR" value="0" scope="request"></c:set>
                            <c:set  var="diffINR" value="0" scope="request"></c:set>
                            <tr>
                                <td><strong><spring:message code="prof.common.Util.totalAssest"></spring:message></strong></td>
                                <td></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalOpen}" pattern="0.00"/></strong></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalClose}" pattern="0.00"/></strong></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalDiff}" pattern="0.00"/></strong></td>
                                <td></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalOpenINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalCloseINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalDiffINR}" pattern="0.00"/></strong></td>
                            </tr>
                            <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.liabilities"></spring:message></strong></td></tr>
	                            <c:set  var="totalOpen" value="0" scope="request"></c:set>
				                <c:set  var="totalClose" value="0" scope="request"></c:set>
				                <c:set  var="totalDiff" value="0" scope="request"></c:set>
				                <c:set  var="totalOpenINR" value="0" scope="request"></c:set>
                                <c:set  var="totalCloseINR" value="0" scope="request"></c:set>
                                <c:set  var="totalDiffINR" value="0" scope="request"></c:set>
                         </c:if>
                          <c:if test="${count.index==6}">
                            <c:set  var="open" value="0" scope="request"></c:set>
                            <c:set  var="close" value="0" scope="request"></c:set>
                            <c:set  var="diff" value="0" scope="request"></c:set>
                            <c:set  var="openINR" value="0" scope="request"></c:set>
                            <c:set  var="closeINR" value="0" scope="request"></c:set>
                            <c:set  var="diffINR" value="0" scope="request"></c:set>
                            <tr>
                                <td><strong><spring:message code="prof.common.Util.totalLiabilities"></spring:message></strong></td>
                                <td></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalOpen}" pattern="0.00"/></strong></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalClose}" pattern="0.00"/></strong></td>
                                <td align="right"> <strong><fmt:formatNumber type="number" value="${totalDiff}" pattern="0.00"/></strong></td>
                                 <td></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalOpenINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalCloseINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalDiffINR}" pattern="0.00"/></strong></td>                            
                            </tr>   
                            <tr><td colspan="18"><strong><spring:message code="prof.common.Util.sale"></spring:message></strong></td></tr>
                                <c:set  var="totalOpen" value="0" scope="request"></c:set>
                                <c:set  var="totalClose" value="0" scope="request"></c:set>
                                <c:set  var="totalDiff" value="0" scope="request"></c:set>
                                <c:set  var="totalOpenINR" value="0" scope="request"></c:set>
                                <c:set  var="totalCloseINR" value="0" scope="request"></c:set>
                                <c:set  var="totalDiffINR" value="0" scope="request"></c:set>
                         </c:if>
                         <tr>
                            <td align="center">${a.accountCode}</td>
                            <td align="center"><a href='javascript:chooseBalance(${a.accountCode},"${FROM_DATE}","${TO_DATE}","${a.id}");'>${a.accountName}</a></td>
                            <td align="right"><fmt:formatNumber type="number" value="${a.description}" pattern="0.00"/></td>
                            <c:forEach var="b" items='${BAL_REPORT_CLOSE}' varStatus="counts">
	                            <c:if test="${a.accountCode==b.accountCode}">
	                                <td align="right"> <fmt:formatNumber type="number" value="${b.description}" pattern="0.00"/></td>
                                    <td align="right"> <fmt:formatNumber type="number" value="${(b.description)-(a.description)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${b.exRate}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(a.description)*(b.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(b.description)*(b.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(((b.description)-(a.description))*(b.exRate))}" pattern="0.00"/></td>
                                    <c:set  var="open" value="${a.description}" scope="request"></c:set>
                                    <c:set  var="close" value="${b.description}" scope="request"></c:set>
                                    <c:set  var="diff" value="${(b.description)-(a.description)}" scope="request"></c:set>
                                    <c:set  var="openINR" value="${(a.description)*(b.exRate)}" scope="request"></c:set>
                                    <c:set  var="closeINR" value="${(b.description)*(b.exRate)}" scope="request"></c:set>
                                    <c:set  var="diffINR" value="${(((b.description)-(a.description))*(b.exRate))}" scope="request"></c:set>
                                    
	                            </c:if>
                            </c:forEach>
                         </tr>
                         <c:set  var="totalOpen" value="${totalOpen+open}" scope="request"></c:set>
                         <c:set  var="totalClose" value="${totalClose+close}" scope="request"></c:set>
                         <c:set  var="totalDiff" value="${totalDiff+diff}" scope="request"></c:set>
                         <c:set  var="totalOpenINR" value="${totalOpenINR+openINR}" scope="request"></c:set>
                         <c:set  var="totalCloseINR" value="${totalCloseINR+closeINR}" scope="request"></c:set>
                         <c:set  var="totalDiffINR" value="${totalDiffINR+diffINR}" scope="request"></c:set>
                           <c:if test="${count.index==6}">
                            <tr>
                               <td><strong><spring:message code="prof.common.Util.totalSale"></spring:message></strong></td>
                               <td></td>
                               <td align="right"> <strong><fmt:formatNumber type="number" value="${totalOpen}" pattern="0.00"/></strong></td>
                               <td align="right"> <strong><fmt:formatNumber type="number" value="${totalClose}" pattern="0.00"/></strong></td>
                               <td align="right"> <strong><fmt:formatNumber type="number" value="${totalDiff}" pattern="0.00"/></strong></td>
                                <td></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalOpenINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalCloseINR}" pattern="0.00"/></strong></td>
                                <td align="right" class="displayInrValue"> <strong><fmt:formatNumber type="number" value="${totalDiffINR}" pattern="0.00"/></strong></td>
                            </tr>
                           </c:if>
                    </c:forEach>
                </c:if>
            </table>
       </div>
  </div>
  </body>
  </html>