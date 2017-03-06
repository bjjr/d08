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

<form:form action="administrator/administrator/edit.do" modelAttribute="administrator">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="socialIdentities"/>
	<form:hidden path="comments"/>
	<form:hidden path="userAccount"/>
	
	<acme:textbox code="administrator.name" path="name" />
	<acme:textbox code="administrator.surname" path="surname" />
	<acme:textbox code="administrator.email" path="email" />
	<acme:textbox code="administrator.phone" path="phone" />
	<acme:textbox code="administrator.picture" path="picture" />
	<br /> 
		
	<!-- Action buttons -->
	<div>
	<acme:submit name="save" code="administrator.save"/>
	<acme:cancel url="welcome/index.do" code="administrator.cancel"/>
	</div>
		
</form:form>