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
	name="attributes" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	
	<acme:column code="attribute.name" property="name"/>
	
	
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="attribute/Çedit.do?attributeId=${row.id}"><spring:message code="attribute.edit" /></a>
		</display:column>
	</security:authorize>
</display:table>