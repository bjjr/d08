<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="audits" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<display:column>
		<jstl:if test="${isAuditor == true}">
			<spring:message code="audit.auditOf" />
			<jstl:out value="${row.property.name}" />
			<jstl:if test="${row.draft == true}">
				<spring:message code="audit.draft" />
			</jstl:if>
		</jstl:if>
		<jstl:if test="${isAuditor == false}">
			<spring:message code="audit.auditBy" />
			<jstl:out value="${row.auditor.name}" />
		</jstl:if>
	</display:column>
	
	<display:column>
		<acme:link href="attachment/list.do?auditId=${row.id}" code="audit.attachments"/>
	</display:column>
	
	<security:authorize access="hasAnyRole('LESSOR','TENANT','ADMINISTRATOR')">
		<display:column>
			<jstl:if test="${isAuditor == false}">
				<a href="audit/display.do?auditId=${row.id}">
					<spring:message code="audit.display"/>
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:if test="${row.draft == false}">
				<a href="audit/display.do?auditId=${row.id}">
					<spring:message code="audit.display"/>
				</a>
			</jstl:if>
			<jstl:if test="${row.draft == true}">
				<a href="audit/display.do?auditId=${row.id}">
					<spring:message code="audit.displayPreview"/>
				</a>
			</jstl:if>
		</display:column>
	
		<display:column>
			<jstl:if test="${row.draft == true}">
				<a href="audit/auditor/edit.do?auditId=${row.id}&propertyId=${row.property.id}">
					<spring:message code="audit.edit"/>
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>
