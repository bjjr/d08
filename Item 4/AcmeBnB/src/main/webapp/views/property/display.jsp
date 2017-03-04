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
	<acme:display code="property.name" property="${property.name}"/>
	<acme:display code="property.rate" property="${property.rate}"/>
	<acme:display code="property.description" property="${property.description}"/>
	<acme:display code="property.address" property="${property.address}"/>

<h1><spring:message code="property.attributes"/></h1>
<display:table pagesize="5" class="displaytag"
	name="attributes" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	
	<acme:column code="property.attribute.value" property="value"/>
	<acme:column code="property.attribute.name" property="attribute.name"/>
	
</display:table>