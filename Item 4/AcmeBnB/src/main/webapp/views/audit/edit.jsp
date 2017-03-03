<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="audit/auditor/edit.do" modelAttribute="audit">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="draft"/>
	<form:hidden path="auditor"/>
	<form:hidden path="property"/>
	
	<acme:textbox code="audit.momentWritten" path="momentWritten" readonly="true"/>
	<br />
	
	<acme:textarea code="audit.text" path="text"/>
	<br />
	
	<!-- Buttons -->
	
	<input type="submit" name="publish"
			value="<spring:message code="audit.publish" />"
			onclick="return confirm('<spring:message code="audit.confirm.publish" />')" />&nbsp;
	
	<acme:submit name="saveAsDraft" code="audit.saveAsDraft"/>
		
	<jstl:if test="${audit.id != 0 && audit.draft == true}">
		<input type="submit" name="delete"
			value="<spring:message code="audit.delete" />"
			onclick="return confirm('<spring:message code="audit.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<acme:cancel url="audit/auditor/list.do" code="audit.cancel"/>
	<br />

</form:form>


