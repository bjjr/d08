<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="attachment/auditor/edit.do" modelAttribute="attachment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="attachment.path" path="path" />
	<br />
	
	<!-- Buttons -->
	
	<acme:submit name="save" code="attachment.save"/>
	
	<acme:delete confirmationCode="attachment.confirm.delete" buttonCode="attachment.delete" id="${attachment.id}"/>
	
	<acme:cancel url="attachment/list.do?auditId=${auditId}" code="attachment.cancel"/>

</form:form>