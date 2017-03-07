<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="book/tenant/edit.do" modelAttribute="book" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<acme:datebox code="book.checkInDate" path="checkInDate" />
	<acme:datebox code="book.checkOutDate" path="checkOutDate" />
		
	<jstl:choose>
		<jstl:when test="${book.smoker == true}">
			<spring:message code="book.smoker"/><input name="smoker" type="checkbox" value="true" checked="checked"/>
		</jstl:when>
		<jstl:otherwise>
			<spring:message code="book.smoker"/><input name="smoker" type="checkbox" value="true"/>
		</jstl:otherwise>
	</jstl:choose>
	
	
	<br />
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:delete confirmationCode="book.delete.conf" buttonCode="misc.delete" id="${book.id}"/>
		<acme:cancel url="book/tenant/list.do" code="misc.cancel"/>
	</div>
</form:form>
