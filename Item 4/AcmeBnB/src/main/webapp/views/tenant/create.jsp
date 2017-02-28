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

<form:form action="tenant/create.do" modelAttribute="tenantForm">

	<form:hidden path="tenant.id" />
	<form:hidden path="tenant.version" />
	<form:hidden path="tenant.invoices" />
	<form:hidden path="tenant.finder" />
	<form:hidden path="tenant.books" />
	<form:hidden path="tenant.creditCard" />
	
	<acme:textbox code="tenant.name" path="tenant.name" />
	<acme:textbox code="tenant.surname" path="tenant.surname" />
	<acme:textbox code="tenant.email" path="tenant.email" />
	<acme:textbox code="tenant.phone" path="tenant.phone" />
	<acme:textbox code="tenant.picture" path="tenant.picture" />
	
	<br />
	<acme:textbox code="tenant.userAccount.username" path="tenant.userAccount.username" />
	<acme:password code="tenant.userAccount.password" path="tenant.userAccount.password" />
	<acme:password code="tenant.userAccount.confirmPassword" path="confirmPassword" />
	
	<br />
	<form:checkbox path="eula" /><spring:message code="tenant.eula" />
	
	<br /><br />
	<acme:submit name="save" code="tenant.save"/>
	<acme:cancel url="welcome/index.do" code="tenant.cancel"/>

</form:form>