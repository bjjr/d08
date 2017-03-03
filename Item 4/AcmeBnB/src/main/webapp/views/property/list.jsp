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
	name="properties" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	
	<acme:column code="property.name" property="name"/>
	<acme:column code="property.rate" property="rate"/>
	<acme:column code="property.description" property="description"/>
	<acme:column code="property.address" property="address"/>
	
	<display:column>
			<a href="property/display.do?propertyId=${row.id}">
				<spring:message	code="property.display" />
			</a>
	</display:column>
	<jstl:if test="${isLessor == true}">
			<display:column>			
				<a href="property/edit.do?propertyId=${row.id}">
					<spring:message	code="property.edit" />
				</a>
			</display:column>
	</jstl:if>
	
</display:table>