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
	
<form:form action="attribute/edit.do" modelAttribute="attribute">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="attribute.name" path="name"/>
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:delete confirmationCode="attribute.delete.conf" buttonCode="misc.delete" id="${attribute.id}"/>
		<acme:cancel url="attribute/list.do" code="misc.cancel"/>
	</div>

</form:form>