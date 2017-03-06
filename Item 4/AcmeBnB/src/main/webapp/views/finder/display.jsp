<%--
 * display.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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

<div>
	<spring:message	code="finder.destinationCity" />: <jstl:out value="${finder.destinationCity}"/>
</div>
<div>
	<spring:message	code="finder.minPrice" />: <jstl:out value="${finder.minPrice}"/>
</div>
<div>
	<spring:message	code="finder.maxPrice" />: <jstl:out value="${finder.maxPrice}"/>
</div>
<div>
	<spring:message	code="finder.keyword" />: <jstl:out value="${finder.keyword}"/>
</div>

<br /><acme:link href="finder/tenant/edit.do" code="finder.edit"/>

<jstl:if test="${finder.id != 0}">
	<br /><acme:link href="finder/tenant/results.do" code="finder.results"/>
</jstl:if>