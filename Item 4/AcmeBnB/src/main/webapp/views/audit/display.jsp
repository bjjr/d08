<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" uri="/WEB-INF/tags"%>

<form:form action="audit/display.do" modelAttribute="audit">

	<h1><spring:message code="audit.auditOf"/> <jstl:out value="${audit.property.name}" /></h1>
	<br />
	
	<acme:display code="audit.momentWritten" property="${audit.momentWritten}"/>
	<br />
	
	<acme:display code="audit.text" property="${audit.text}"/>
	

</form:form>