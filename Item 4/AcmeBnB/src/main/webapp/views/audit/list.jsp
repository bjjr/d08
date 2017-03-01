<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" uri="/WEB-INF/tags"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="audits" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<display:column>
		<jstl:if test="${isAuditor == true}">
			<spring:message code="audit.auditOf" />
			<jstl:out value=" " />
			<jstl:out value="${row.property.name}" />
		</jstl:if>
		<jstl:if test="${isAuditor == false}">
			<spring:message code="audit.auditOf" />
			<jstl:out value=" " />
			<jstl:out value="${row.auditor}" />
		</jstl:if>
	</display:column>
	
	<security:authorize access="hasRole('AUDITOR')">
		<jstl:if test="${row.draft == false}">
			<display:column>
				<a href="audit/auditor.display.do?auditId=${row.id}">
					<spring:message code="audit.display"/>
				</a>
			</display:column>
		</jstl:if>
	
		<jstl:if test="${row.draft == true}">
			<display:column>
				<a href="audit/auditor.edit.do?auditId=${row.id}">
					<spring:message code="audit.edit"/>
				</a>
			</display:column>
		</jstl:if>
	</security:authorize>
	
</display:table>
