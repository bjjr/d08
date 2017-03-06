<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2><spring:message code="audit.auditOf"/> <jstl:out value="${audit.property.name}" /></h2>
	
<jstl:if test="${!audit.draft}">
	<acme:display code="audit.momentWritten" property="${audit.momentWritten}"/>
</jstl:if>
	
<acme:display code="audit.text" property="${audit.text}"/>

<h3><spring:message code="audit.attachments"/></h3>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="attachments" requestURI="${requestURI}" id="at">
	
	<!-- Attributes -->
	
	<acme:column code="attachment.path" property="path"/>

</display:table>
