<%@ page isELIgnored="false" %>
<%@ page language="java" session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cache" uri="http://www.opensymphony.com/oscache" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<c:set var="tagTilesStyleTheme">tag.style.<spring:theme code="name"/></c:set>
<c:set var="tagStyleThemePath">styles/<spring:theme code="name"/></c:set>