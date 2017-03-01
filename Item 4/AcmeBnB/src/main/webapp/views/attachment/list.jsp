<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" uri="/WEB-INF/tags"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="attachments" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<acme:column code="attachment.path" property="path"/>

</display:table>

<br />

<security:authorize access="hasRole('AUDITOR')" >
	<input type="button" name="addAttachment"
		value="<spring:message code="attachment.addAttachment" />"
		onclick="window.location='attachment/auditor/edit.do'" />
</security:authorize>

<input type="button" name="return"
		value="<spring:message code="attachment.return" />"
		onclick="window.location='property/list.do'" />
<br />