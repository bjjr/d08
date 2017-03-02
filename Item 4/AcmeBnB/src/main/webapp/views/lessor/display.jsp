<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
	
<!-- Listing grid -->
	<acme:display code="lessor.name" property="${lessor.name}"/>
	<acme:display code="lessor.surname" property="${lessor.surname}"/>
	<acme:display code="lessor.email" property="${lessor.email}"/>
	<acme:display code="lessor.phone" property="${lessor.phone}"/>
	<img alt='<spring:message code="lessor.alt" />' src="${lessor.picture }"/>

