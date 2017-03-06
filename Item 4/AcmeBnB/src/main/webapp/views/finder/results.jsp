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
	name="properties" requestURI="finder/tenant/results.do" id="row">
	<!-- Attributes -->
	
	<acme:column code="finder.property.name" property="name"/>
	<acme:column code="finder.property.rate" property="rate"/>
	<acme:column code="finder.property.description" property="description"/>
	<acme:column code="finder.property.address" property="address"/>
	
</display:table>

<acme:link href="finder/tenant/display.do" code="finder.return"/>