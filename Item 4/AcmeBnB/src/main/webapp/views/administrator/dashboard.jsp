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
<jstl:out value="${bookAPL}"></jstl:out>

<h2><spring:message code="administrator.bookDPL" /></h2>
<jstl:out value="${bookDPL}"></jstl:out>

<h2><spring:message code="administrator.bookAPT" /></h2>
<jstl:out value="${bookAPT}"></jstl:out>

<h2><spring:message code="administrator.bookDPT" /></h2>
<jstl:out value="${bookDPT}"></jstl:out>

<h2><spring:message code="administrator.lessorMBA" /></h2>

<display:table pagesize="5" class="displaytag" name="lessorMBA"
requestURI="administrator/dashboard.do" id="lessorMBA">

	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.lessorMBD" /></h2>

<display:table pagesize="5" class="displaytag" name="lessorMBD"
requestURI="administrator/dashboard.do" id="lessorMBD">

	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.lessorMBP" /></h2>

<display:table pagesize="5" class="displaytag" name="lessorMBP"
requestURI="administrator/dashboard.do" id="lessorMBP">

	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.tenantMBA" /></h2>

<display:table pagesize="5" class="displaytag" name="tenantMBA"
requestURI="administrator/dashboard.do" id="tenantMBA">

	<acme:column code="tenant.name" property="name"/>
	<acme:column code="tenant.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.tenantMBD" /></h2>

<display:table pagesize="5" class="displaytag" name="tenantMBD"
requestURI="administrator/dashboard.do" id="tenantMBD">

	<acme:column code="tenant.name" property="name"/>
	<acme:column code="tenant.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.tenantMBP" /></h2>

<display:table pagesize="5" class="displaytag" name="tenantMBP"
requestURI="administrator/dashboard.do" id="tenantMBP">
	
	<acme:column code="tenant.name" property="name"/>
	<acme:column code="tenant.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.lessorMaxABB" /></h2>

<display:table pagesize="5" class="displaytag" name="lessorMaxABB"
requestURI="administrator/dashboard.do" id="lessorMaxABB">

	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.lessorMinABB" /></h2>

<display:table pagesize="5" class="displaytag" name="lessorMinABB"
requestURI="administrator/dashboard.do" id="lessorMinABB">

	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.tenantMaxABB" /></h2>

<display:table pagesize="5" class="displaytag" name="tenantMaxABB"
requestURI="administrator/dashboard.do" id="tenantMaxABB">
	
	<acme:column code="tenant.name" property="name"/>
	<acme:column code="tenant.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.tenantMinABB" /></h2>

<display:table pagesize="5" class="displaytag" name="tenantMinABB"
requestURI="administrator/dashboard.do" id="tenantMinABB">
	
	<acme:column code="tenant.name" property="name"/>
	<acme:column code="tenant.surname" property="surname"/>

</display:table>

<h2><spring:message code="administrator.maxRPF" /></h2>
<jstl:out value="${maxRPF}"></jstl:out>

<h2><spring:message code="administrator.minRPF" /></h2>
<jstl:out value="${minRPF}"></jstl:out>

<h2><spring:message code="administrator.avgRPF" /></h2>
<jstl:out value="${avgRPF}"></jstl:out>

<h2><spring:message code="administrator.maxAPP" /></h2>
<jstl:out value="${maxAPP}"></jstl:out>

<h2><spring:message code="administrator.minAPP" /></h2>
<jstl:out value="${minAPP}"></jstl:out>

<h2><spring:message code="administrator.avgAPP" /></h2>
<jstl:out value="${avgAPP}"></jstl:out>

<h2><spring:message code="administrator.attributeSMTUP" /></h2>

<display:table pagesize="5" class="displaytag" name="attributeSMTUP"
requestURI="administrator/dashboard.do" id="attributeSMTUP">

	<acme:column code="attribute.name" property="name"/>

</display:table>

<h2><spring:message code="administrator.maxSIPA" /></h2>
<jstl:out value="${maxSIPA}"></jstl:out>

<h2><spring:message code="administrator.minSIPA" /></h2>
<jstl:out value="${minSIPA}"></jstl:out>

<h2><spring:message code="administrator.avgSIPA" /></h2>
<jstl:out value="${avgSIPA}"></jstl:out>

<h2><spring:message code="administrator.maxIPT" /></h2>
<jstl:out value="${maxIPT}"></jstl:out>

<h2><spring:message code="administrator.minIPT" /></h2>
<jstl:out value="${minIPT}"></jstl:out>

<h2><spring:message code="administrator.avgIPT" /></h2>
<jstl:out value="${avgIPT}"></jstl:out>

<h2><spring:message code="administrator.totalMoney" /></h2>
<jstl:out value="${totalMoney}"></jstl:out>

<h2><spring:message code="administrator.avgBPP1A" /></h2>
<jstl:out value="${avgBPP1A}"></jstl:out>