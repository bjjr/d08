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
	
<!-- Listing grid -->
<display:table pagesize="5" class="displaytag"
	name="lessors" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	
	<acme:column code="lessor.name" property="name"/>
	<acme:column code="lessor.surname" property="surname"/>
	<acme:column code="lessor.email" property="email"/>
	<acme:column code="lessor.phone" property="phone"/>

	<!-- Action links -->
	<spring:message code="lessor.properties" var="propertiesHeader" />
	<display:column title="${propertiesHeader}">
		<jstl:forEach items="${row.properties }" var="pro">
			<a href="property/display.do?propertyId=${pro.id}">${pro.name}</a>
			<br />
		</jstl:forEach>
	</display:column> 
	
	<display:column>
			<a href="lessor/display.do?lessorId=${row.id}">
				<spring:message	code="lessor.display" />
			</a>
	</display:column>
	
</display:table>