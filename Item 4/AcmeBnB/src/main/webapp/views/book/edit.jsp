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
	<form:hidden path="tenant"/>
	<form:hidden path="property"/>
	<form:hidden path="status"/>
	
	
	<acme:datebox code="book.checkInDate" path="checkInDate" />
	<acme:datebox code="book.checkOutDate" path="checkOutDate" />
	<acme:textbox code="book.smoker" path="smoker" />
	
	<br />
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:delete confirmationCode="book.delete.conf" buttonCode="misc.delete" id="${book.id}"/>
		<acme:cancel url="book/tenant/list.do" code="misc.cancel"/>
	</div>
</form:form>
