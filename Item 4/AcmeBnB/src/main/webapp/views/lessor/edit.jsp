<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="lessor/edit.do" modelAttribute="lessor" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="creditCard"/>
	<form:hidden path="properties"/>
	<form:hidden path="socialIdentities"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="comments"/>
	
	<acme:textbox code="lessor.name" path="name" />
	<acme:textbox code="lessor.surname" path="surname" />
	<acme:textbox code="lessor.email" path="email"/>
	<acme:textbox code="lessor.phone" path="phone"/>
	<acme:textbox code="lessor.picture" path="picture"/>
	
	
	<acme:submit name="save" code="lessor.save"/>
	<acme:delete confirmationCode="lessor.confirm.delete" buttonCode="lessor.delete" id="${lessor.id}"/>
	<acme:cancel url="lessor/list.do" code="lessor.cancel"/>
		
</form:form>

