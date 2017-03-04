<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="attributeValue/edit.do" modelAttribute="attributeValue" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:select items="${attributes}" itemLabel="name" code="attributeValue.attribute" path="attribute"/>
	<acme:textbox code="attributeValue.value" path="value"/>
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:delete confirmationCode="attributeValue.delete.conf" buttonCode="misc.delete" id="${attributeValue.id}"/>
		<acme:cancel url="property/display.do?propertyId=${attributeValue.property.id}" code="misc.cancel"/>
	</div>
</form:form>
