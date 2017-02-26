<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${card == null}">
	<spring:message code="creditcard.warning" var="warning" />
	<h3><jstl:out value="${warning}" /></h3>
	<acme:link code="creditcard.register" href="creditCard/create.do" />
</jstl:if>

<jstl:if test="${card != null}" >
	<acme:display code="creditcard.holder" property="${card.holderName}"/>
	<acme:display code="creditcard.brand" property="${card.brandName}"/>
	<acme:display code="creditcard.number" property="${card.number}"/>
	<acme:display code="creditcard.expirationMonth" property="${card.expirationMonth}"/>
	<acme:display code="creditcard.expirationYear" property="${card.expirationYear}"/>
	<acme:display code="creditcard.cvv" property="${card.cvv}"/>

	<acme:link code="creditcard.edit" href="creditCard/edit.do?creditCardId=${card.id}" />
</jstl:if>

