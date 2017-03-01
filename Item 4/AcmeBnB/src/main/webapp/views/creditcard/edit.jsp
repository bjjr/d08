<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="creditCard/edit.do" modelAttribute="creditCardForm" >
	
	<form:hidden path="creditCard.id"/>
	<form:hidden path="creditCard.version"/>
	
	<acme:textbox code="creditcard.holder" path="creditCard.holderName" />
	<acme:textbox code="creditcard.brand" path="creditCard.brandName" />
	<acme:textbox code="creditcard.number" path="creditCard.number"/>
	<acme:textbox code="creditcard.expiryDate" path="date" id="datepicker" />
	<acme:textbox code="creditcard.cvv" path="creditCard.cvv" />
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:cancel url="creditCard/display.do" code="misc.cancel"/>
	</div>
</form:form>
