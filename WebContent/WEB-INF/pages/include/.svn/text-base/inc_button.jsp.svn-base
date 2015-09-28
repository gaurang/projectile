<%@ include file="inc_taglibs.jsp" %>

<c:set var="buttonType" value="button" />
<c:set var="buttonMethod" value="" />
<c:set var="buttonClass" value="buttonClass" />
<c:set var="buttonText" value="Default Text" />     
<c:set var="buttonId" value="myButton" />
<c:set var="buttonDisabled" value="" />
<c:set var="buttonStyle" value="" />
<c:set var="buttonAlign" value="" />

<c:if test="${!empty param.buttonAlign}">
	<c:set var="buttonAlign" value="${param.buttonAlign}" />
</c:if>

<c:if test="${!empty param.buttonStyle}">
	<c:set var="buttonStyle" value="${param.buttonStyle}" />
</c:if>

<c:if test="${!empty param.buttonDisabled && param.buttonDisabled eq true}">
	<c:set var="buttonDisabled" value="disabled='disabled'" />
</c:if>

<c:if test="${!empty param.buttonId}">
	<c:set var="buttonId" value="${param.buttonId}" />
</c:if>

<c:if test="${!empty param.buttonText}">
	<c:set var="buttonText" value="${param.buttonText}" />
</c:if>

<c:if test="${!empty param.buttonMethod}">
	<c:set var="buttonMethod" value="${param.buttonMethod}" />
</c:if>

<c:choose>
	<c:when test="${param.buttonType eq 'merchant'}">
		<c:set var="buttonType" value="${param.buttonType}" />
		<c:set var="buttonClass" value="greenbutton" />
	</c:when>
	<c:otherwise>
		<c:set var="buttonType" value="${param.buttonType}" />
		<c:set var="buttonClass" value="button" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${buttonType ne 'button'}">		
		<table id="${buttonId}" class="${buttonClass}" cellpadding="0" cellspacing="0" style="${buttonStyle}" 
			onclick="${buttonMethod}" <c:out value="${buttonDisabled}" />  <c:if test="${!empty buttonAlign}">align="${buttonAlign}"</c:if>>
			<tr>
				<td class="leftside"></td>
				<td class="text">${buttonText}</td>
				<td class="rightside"></td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<button class="${buttonClass}" onclick="${buttonMethod};">${buttonText}</button>
	</c:otherwise>
</c:choose>
