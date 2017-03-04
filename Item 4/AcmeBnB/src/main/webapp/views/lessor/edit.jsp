<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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

<form:form action="lessor/create.do" modelAttribute="lessorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="properties" />
	<form:hidden path="accumulatedCharges" />
	<form:hidden path="creditCard" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="comments" />
	
	<acme:textbox code="lessor.name" path="name" />
	<acme:textbox code="lessor.surname" path="surname" />
	<acme:textbox code="lessor.email" path="email" />
	<acme:textbox code="lessor.phone" path="phone" />
	<acme:textbox code="lessor.picture" path="picture" />
	
	<br />
	<acme:textbox code="lessor.userAccount.username" path="userAccount.username" />
	<acme:password code="lessor.userAccount.password" path="userAccount.password" />
	<acme:password code="lessor.userAccount.confirmPassword" path="confirmPassword" />
	
	<br />
	<form:checkbox path="eula" /><spring:message code="lessor.eula" />
	
	<br /><br />
	<acme:submit name="save" code="lessor.save"/>
	<acme:cancel url="welcome/index.do" code="lessor.cancel"/>

</form:form>