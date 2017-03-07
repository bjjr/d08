<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Listing grid -->
<acme:display code="lessor.name" property="${lessor.name}" />
<acme:display code="lessor.surname" property="${lessor.surname}" />
<acme:display code="lessor.email" property="${lessor.email}" />
<acme:display code="lessor.phone" property="${lessor.phone}" />
<img alt='<spring:message code="lessor.alt" />' src="${lessor.picture }" />

<h2>
			<spring:message code="lessor.social" />
		</h2>
		<display:table pagesize="5" class="displaytag"
			name="lessor.socialIdentities" requestURI="lessor/display.do"
			id="row">
			<acme:column code="socialIdentity.nick" property="nick" />
			<acme:column code="socialIdentity.nameSN" property="nameSN" />
			<acme:column code="socialIdentity.urlSN" property="urlSN" />
		</display:table>


<security:authorize access="hasRole('ADMINISTRATOR')">
	<h2><spring:message code="propertiesSortedByAudits" /></h2>
	<display:table pagesize="5" class="displaytag"
		name="propertiesSortedByAudits" requestURI="lessor/display.do"
		id="row">
		<acme:column code="property.name" property="name" />
	</display:table>
	<h2><spring:message code="propertiesSortedByBooks" /></h2>
	<display:table pagesize="5" class="displaytag"
		name="propertiesSortedByBooks" requestURI="lessor/display.do"
		id="row">
		<acme:column code="property.name" property="name" />
	</display:table>
			<h2><spring:message code="propertiesSortedByAcceptedBooks" /></h2>
		<display:table pagesize="5" class="displaytag"
	name="propertiesSortedByAcceptedBooks" requestURI="lessor/display.do" id="row">
		<acme:column code="property.name" property="name"/>
	</display:table>
			<h2><spring:message code="propertiesSortedByDeniedBooks" /></h2>
		<display:table pagesize="5" class="displaytag"
	name="propertiesSortedByDeniedBooks" requestURI="lessor/display.do" id="row">
		<acme:column code="property.name" property="name"/>
	</display:table>
		<h2><spring:message code="propertiesSortedByPendingBooks" /></h2>
		<display:table pagesize="5" class="displaytag"
	name="propertiesSortedByPendingBooks" requestURI="lessor/display.do" id="row">
		<acme:column code="property.name" property="name"/>
	</display:table>
</security:authorize>