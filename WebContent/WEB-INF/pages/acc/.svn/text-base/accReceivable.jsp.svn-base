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
                    <th>CompanyName</th>
                    <th>Invoice Amount(USD)</th>
                    <th>Payment Amount(USD)</th>
                    <th>OutStanding(USD)</th>
                    <th class="displayInrValue">Invoice exRate</th>
                    <th class="displayInrValue">Payment exRate</th>
                    <th class="displayInrValue">Invoice Amount(INR)</th>
                    <th class="displayInrValue">Payment Amount(INR)</th>
                    <th class="displayInrValue">OutStanding(INR)</th>
                </tr>
                <c:if test="${INVOICE_LIST==null}">
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:if test='${INVOICE_LIST==""}'>
                    <tr><td  colspan="18"><strong><spring:message code="prof.common.Util.noValue"></spring:message></strong></td>
                </tr>
                </c:if>
                <c:if test="${INVOICE_LIST!=null}">
                    <c:set  var="invAmount" value="0" scope="request"></c:set>
                    <c:set  var="payAmount" value="0" scope="request"></c:set>
                    <c:set  var="outAmount" value="0" scope="request"></c:set>
                    <c:set  var="invAmountINR" value="0" scope="request"></c:set>
                    <c:set  var="payAmountINR" value="0" scope="request"></c:set>
                    <c:set  var="outAmountINR" value="0" scope="request"></c:set>
                    <c:forEach var="a" items='${INVOICE_LIST}' varStatus="count">
                    <c:set  var="invAmountTemp" value="0" scope="request"></c:set>
                    <c:set  var="payAmountTemp" value="0" scope="request"></c:set>
                    <c:set  var="outAmountTemp" value="0" scope="request"></c:set>
                    <c:set  var="invAmountTempINR" value="0" scope="request"></c:set>
                    <c:set  var="payAmountTempINR" value="0" scope="request"></c:set>
                    <c:set  var="outAmountTempINR" value="0" scope="request"></c:set>
                    <c:set  var="value" value="false" scope="request"></c:set>
                         <tr>
                            <c:forEach var="b" items='${PAY_LIST}' varStatus="count">
                                <c:if test="${a.id==b.id}">
                                    <td align="center">${a.custName}</td>
                                    <td align="right"> <fmt:formatNumber type="number" value="${a.amount}" pattern="0.00"/></td>
                                    <td align="right"> <fmt:formatNumber type="number" value="${b.amount}" pattern="0.00"/></td>
                                    <td align="right"> <fmt:formatNumber type="number" value="${(a.amount)-(b.amount)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${a.exRate}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${b.exRate}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(a.amount)*(a.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(b.amount)*(b.exRate)}" pattern="0.00"/></td>
                                    <td align="right" class="displayInrValue"> <fmt:formatNumber type="number" value="${(((a.amount)*(a.exRate))-((b.amount)*(b.exRate)))}" pattern="0.00"/></td>
                                    <c:set  var="value" value="true" scope="request"></c:set>
                                    <c:set  var="invAmountTemp" value="${invAmountTemp+a.amount}" scope="request"></c:set>
                                    <c:set  var="payAmountTemp" value="${payAmountTemp+b.amount}" scope="request"></c:set>
                                    <c:set  var="outAmountTemp" value="${outAmountTemp+((a.amount)-(b.amount))}" scope="request"></c:set>
                                    <c:set  var="invAmountTempINR" value="${(invAmountTempINR+(a.amount)*(a.exRate))}" scope="request"></c:set>
                                    <c:set  var="payAmountTempINR" value="${(payAmountTempINR+(b.amount)*(b.exRate))}" scope="request"></c:set>
                                    <c:set  var="outAmountTempINR" value="${(outAmountTempINR+((a.amount)*(a.exRate))-(b.amount)*(b.exRate))}" scope="request"></c:set>
                                </c:if>
                            </c:forEach>
                                <c:set  var="invAmount" value="${invAmount+invAmountTemp}" scope="request"></c:set>
                                <c:set  var="payAmount" value="${payAmount+payAmountTemp}" scope="request"></c:set>
                                <c:set  var="outAmount" value="${outAmount+outAmountTemp}" scope="request"></c:set>
                                <c:set  var="invAmountINR" value="${invAmountINR+invAmountTempINR}" scope="request"></c:set>
                                <c:set  var="payAmountINR" value="${payAmountINR+payAmountTempINR}" scope="request"></c:set>
                                <c:set  var="outAmountINR" value="${outAmountINR+outAmountTempINR}" scope="request"></c:set>
                         </tr>
                     </c:forEach>
                         <tr><tr>
                         <tr>
	                        <td><strong><spring:message code="prof.common.Util.total"></spring:message></strong></td>
	                        <td align="right"><strong> <fmt:formatNumber type="number" value="${invAmount}" pattern="0.00"/></strong></td>
                            <td align="right"><strong> <fmt:formatNumber type="number" value="${payAmount}" pattern="0.00"/></strong></td>
                            <td align="right"><strong> <fmt:formatNumber type="number" value="${outAmount}" pattern="0.00"/></strong></td>
                            <td></td>
                            <td></td>
                            <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${invAmountINR}" pattern="0.00"/></strong></td>
                            <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${payAmountINR}" pattern="0.00"/></strong></td>
                            <td align="right" class="displayInrValue"><strong><fmt:formatNumber type="number" value="${outAmountINR}" pattern="0.00"/></strong></td>
                        </tr>
                </c:if>
            </table>
       </div>