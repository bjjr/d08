<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h2><spring:message code="administrator.bookAPL" /></h2>

<display:table pagesize="5" class="displaytag" name="bookAPL"
requestURI="administrator/dashboard.do" id="bookAPL">
	
	<spring:message code="user.name" var="name" />
	<spring:message code="user.surname" var="surname" />
	<display:column property="name" title="${name}" />
	<display:column property="surname" title="${surname}" />

</display:table>

<h2><spring:message code="administrator.bookDPL" /></h2>

<display:table pagesize="5" class="displaytag" name="usersOrderPop"
requestURI="administrator/dashboard.do" id="usersOrderPop">
	
	<spring:message code="user.name" var="name" />
	<spring:message code="user.surname" var="surname" />
	<display:column property="name" title="${name}" />
	<display:column property="surname" title="${surname}" />

</display:table>

<h2><spring:message code="administrator.bookAPT" /></h2>

<display:table pagesize="5" class="displaytag" name="usersOrderLike"
requestURI="administrator/dashboard.do" id="usersOrderLike">
	
	<spring:message code="user.name" var="name" />
	<spring:message code="user.surname" var="surname" />
	<display:column property="name" title="${name}" />
	<display:column property="surname" title="${surname}" />

</display:table>

<h2><spring:message code="administrator.bookDPT" /></h2>

<display:table pagesize="5" class="displaytag" name="usersOrderDislike"
requestURI="administrator/dashboard.do" id="usersOrderDislike">
	
	<spring:message code="user.name" var="name" />
	<spring:message code="user.surname" var="surname" />
	<display:column property="name" title="${name}" />
	<display:column property="surname" title="${surname}" />

</display:table>

<h2><spring:message code="administrator.lessorMBA" /></h2>

<display:table pagesize="5" class="displaytag" name="compByCampaigns"
requestURI="administrator/dashboard.do" id="cbc">

	<spring:message code="sponsor.company" var="text" />
	<display:column title="${text}">
		<jstl:out value="${cbc}" />
	</display:column>

</display:table>

<h2><spring:message code="administrator.lessorMBD" /></h2>

<display:table pagesize="5" class="displaytag" name="compByBills"
requestURI="administrator/dashboard.do" id="cbb">

	<spring:message code="sponsor.company" var="text" />
	<display:column title="${text}">
		<jstl:out value="${cbb}" />
	</display:column>

</display:table>

<h2><spring:message code="administrator.lessorMBP" /></h2>

<display:table pagesize="5" class="displaytag" name="inactiveSponsors"
requestURI="administrator/dashboard.do" id="sp">

	<spring:message code="sponsor.name" var="text" />
	<display:column title="${text}">
		<jstl:out value="${sp}" />
	</display:column>

</display:table>

<h2><spring:message code="administrator.tenantMBA" /></h2>

<display:table pagesize="5" class="displaytag" name="compSpentLess"
requestURI="administrator/dashboard.do" id="csl">

	<spring:message code="sponsor.company" var="text" />
	<display:column title="${text}">
		<jstl:out value="${csl}" />
	</display:column>

</display:table>

<h2><spring:message code="administrator.tenantMBD" /></h2>

<display:table pagesize="5" class="displaytag" name="compSpentLeast"
requestURI="administrator/dashboard.do" id="csl90">

	<spring:message code="sponsor.company" var="text" />
	<display:column title="${text}">
		<jstl:out value="${csl90}" />
	</display:column>

</display:table>

<h2><spring:message code="administrator.tenantMBP" /></h2>

<display:table pagesize="5" class="displaytag" name="cooksByProMC"
requestURI="administrator/dashboard.do" id="cookByProMC">
	
	<spring:message code="cook.name" var="name" />
	<spring:message code="cook.surname" var="surname" />
	<display:column property="name" title="${name}" />
	<display:column property="surname" title="${surname}" />

</display:table>

<h2><spring:message code="administrator.lessorMaxABB" /></h2>

<h2><spring:message code="administrator.lessorMinABB" /></h2>

<h2><spring:message code="administrator.tenantMaxABB" /></h2>

<h2><spring:message code="administrator.tenantMinABB" /></h2>

<h2><spring:message code="administrator.maxRPF" /></h2>

<h2><spring:message code="administrator.minRPF" /></h2>

<h2><spring:message code="administrator.avgRPF" /></h2>

<h2><spring:message code="administrator.maxAPP" /></h2>

<h2><spring:message code="administrator.minAPP" /></h2>

<h2><spring:message code="administrator.avgAPP" /></h2>

<h2><spring:message code="administrator.attributeSMTUP" /></h2>

<h2><spring:message code="administrator.maxSIPA" /></h2>

<h2><spring:message code="administrator.minSIPA" /></h2>

<h2><spring:message code="administrator.avgSIPA" /></h2>

<h2><spring:message code="administrator.maxIPT" /></h2>

<h2><spring:message code="administrator.minIPT" /></h2>

<h2><spring:message code="administrator.avgIPT" /></h2>

<h2><spring:message code="administrator.totalMoney" /></h2>

<h2><spring:message code="administrator.avgBPP1A" /></h2>