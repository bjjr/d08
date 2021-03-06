<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="property/edit.do" modelAttribute="property" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="property.name" path="name" />
	<acme:textarea code="property.description" path="description" />
	<acme:textbox code="property.rate" path="rate"/>
	<acme:textbox code="property.address" path="address"/>
	
	<acme:submit name="save" code="property.save"/>
	<acme:delete confirmationCode="property.confirm.delete" buttonCode="property.delete" id="${property.id}"/>
	<acme:cancel url="property/ownList.do" code="property.cancel"/>
		
</form:form>

