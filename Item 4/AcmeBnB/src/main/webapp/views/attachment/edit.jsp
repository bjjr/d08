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
	<form:hidden path="audit" />
	
	<acme:textbox code="attachment.path" path="path" />
	<br />
	
	<!-- Buttons -->
	
	<acme:submit name="save" code="attachment.save"/>
	
	<jstl:if test="${attachment.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="attachment.delete" />"
			onclick="return confirm('<spring:message code="attachment.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<acme:cancel url="attachment/auditor/list.do" code="attachment.cancel"/>
	<br />

</form:form>


