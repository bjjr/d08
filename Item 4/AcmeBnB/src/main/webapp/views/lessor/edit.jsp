<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="lessor/create.do" modelAttribute="lessorForm" >
	
	<form:hidden path="lessor.id"/>
	<form:hidden path="lessor.version"/>
	<form:hidden path="lessor.creditCard"/>
	<form:hidden path="lessor.properties"/>
	<form:hidden path="lessor.socialIdentities"/>
	<form:hidden path="lessor.userAccount"/>
	<form:hidden path="lessor.comments"/>
	
	<acme:textbox code="lessor.name" path="lessor.name" />
	<acme:textbox code="lessor.surname" path="lessor.surname" />
	<acme:textbox code="lessor.email" path="lessor.email"/>
	<acme:textbox code="lessor.phone" path="lessor.phone"/>
	<acme:textbox code="lessor.picture" path="lessor.picture"/>
	
	<br />
	<acme:textbox code="lessor.userAccount.username" path="lessor.userAccount.username" />
	<acme:password code="lessor.userAccount.password" path="lessor.userAccount.password" />
	<acme:password code="lessor.userAccount.confirmPassword" path="confirmPassword" />
	
	<br />
	<form:checkbox path="eula" /><spring:message code="lessor.eula" />
	
	<br /><br />
	<acme:submit name="save" code="lessor.save"/>
	<acme:cancel url="welcome/index.do" code="lessor.cancel"/>
		
</form:form>

