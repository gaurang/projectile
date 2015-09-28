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
  <div id="customerBalanceSheetData">
           <table id="greenTable" width="100%">
              <tr id ="showINRValue">
                  <td colspan="2" align="center" id="showInr"><input type="button" onclick="displayINRValue(this.value);" name="displayInrValue" value="Show INR">
              </tr>
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
                <c:if test="${BAL_REPORT_OPEN!=null}">
                <c:set  var="totalOpen" value="0" scope="request"></c:set>
                <c:set  var="totalClose" value="0" scope="request"></c:set>
                <c:set  var="totalDiff" value="0" scope="request"></c:set>
                <c:set  var="totalOpenINR" value="0" scope="request"></c:set>
                <c:set  var="totalCloseINR" value="0" scope="request"></c:set>
                <c:set  var="totalDiffINR" value="0" scope="request"></c:set>
                <c:set  var="open" value="0" scope="request"></c:set>
                <c:set  var="close" value="0" scope="request"></c:set>
                <c:set  var="diff" value="0" scope="request"></c:set>
                <c:set  var="openINR" value="0" scope="request"></c:set>
                <c:set  var="closeINR" value="0" scope="request"></c:set>
                <c:set  var="diffINR" value="0" scope="request"></c:set>
                    <c:forEach var="a" items='${BAL_REPORT_OPEN}' varStatus="count">
                         <tr>
                            <td align="center">${a.accountCode}</td>
                            <td align="center">${a.branchCode}</td>
                            <td align="right"> <fmt:formatNumber type="number" value="${a.amount}" pattern="0.00"/></td>
                            <c:forEach var="b" items='${BAL_REPORT_CLOSE}' varStatus="count">
                                <c:if test="${a.branchCode==b.branchCode}">
                                    <td align="right"> <fmt:formatNumber type="number" value="${b.amount}" pattern="0.00"/></td>
                                    <td align="right"> <fmt:formatNumber type="number" value="${(b.amount)-(a.amount)}" pattern="0.00"/></td>
		                            <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${b.exRate}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(a.amount)*(b.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(b.amount)*(b.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(((b.amount)-(a.amount))*(b.exRate))}" pattern="0.00"/></td>
		                            <c:set  var="open" value="${a.amount}" scope="request"></c:set>
					                <c:set  var="close" value="${b.amount}" scope="request"></c:set>
					                <c:set  var="diff" value="${(b.amount)-(a.amount)}" scope="request"></c:set> 
					                <c:set  var="openINR" value="${(a.amount)*(b.exRate)}" scope="request"></c:set>
                                    <c:set  var="closeINR" value="${(b.amount)*(b.exRate)}" scope="request"></c:set>
                                    <c:set  var="diffINR" value="${(((b.amount)-(a.amount))*(b.exRate))}" scope="request"></c:set>   
                                </c:if>
                            </c:forEach>
                            <c:set  var="totalOpen" value="${totalOpen+open}" scope="request"></c:set>
                            <c:set  var="totalClose" value="${totalClose+close}" scope="request"></c:set>
                            <c:set  var="totalDiff" value="${totalDiff+diff}" scope="request"></c:set>
                            <c:set  var="totalOpenINR" value="${totalOpenINR+openINR}" scope="request"></c:set>
                            <c:set  var="totalCloseINR" value="${totalCloseINR+closeINR}" scope="request"></c:set>
                            <c:set  var="totalDiffINR" value="${totalDiffINR+diffINR}" scope="request"></c:set>
                         </tr>
                     </c:forEach>
                     <tr>
                        <td><strong><spring:message code="prof.common.Util.total"></spring:message></strong></td>
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
            </table>
       </div>