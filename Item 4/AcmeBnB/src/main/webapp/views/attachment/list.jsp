<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="attachments" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<acme:column code="attachment.path" property="path"/>
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:if test="${audit.draft == true}">
				<a href="attachment/auditor/edit.do?attachmentId=${row.id}&auditId=${auditId}">
					<spring:message code="attachment.edit"/>
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<br />

<security:authorize access="hasRole('AUDITOR')" >
	<jstl:if test="${audit.draft == true}">
		<input type="button" name="addAttachment"
			value="<spring:message code="attachment.addAttachment" />"
			onclick="window.location='attachment/auditor/create.do?auditId=${auditId}'" />
	</jstl:if>
</security:authorize>

<input type="button" name="return"
		value="<spring:message code="attachment.return" />"
		onclick="window.location='audit/auditor/list.do'" />
<br />