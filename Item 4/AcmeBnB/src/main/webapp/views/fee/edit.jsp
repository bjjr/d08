<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="fee/edit.do" modelAttribute="fee">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<div>
		<spring:message code="fee.value" />
		<form:input path="value" />
		<form:errors cssClass="error" path="value" />
		
	</div>
	<div>
		<input type="submit" name="save"
			value="<spring:message code="fee.save" />" />
		<input type="button" name="cancel"
			value="<spring:message code="fee.cancel" />"
			onclick="window.location='/AcmeBnB'" />
	<!-- 	<input type="submit" name="default"
			value="<spring:message code="fee.save" />" /> -->
	</div>

</form:form>