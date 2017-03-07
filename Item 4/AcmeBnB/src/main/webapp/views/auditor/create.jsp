<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="auditor/administrator/create.do" modelAttribute="auditorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="auditor.name" path="name" />
	<acme:textbox code="auditor.surname" path="surname" />
	<acme:textbox code="auditor.email" path="email" />
	<acme:textbox code="auditor.phone" path="phone" />
	<acme:textbox code="auditor.picture" path="picture" />
	<acme:textbox code="auditor.companyName" path="companyName"/>
	
	<br />
	<acme:textbox code="auditor.userAccount.username" path="userAccount.username" />
	<acme:password code="auditor.userAccount.password" path="userAccount.password" />
	<acme:password code="auditor.userAccount.confirmPassword" path="confirmPassword" />
	
	<br /><br />
	<acme:submit name="save" code="auditor.save"/>
	<acme:cancel url="welcome/index.do" code="auditor.cancel"/>

</form:form>