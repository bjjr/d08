<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="comment/create.do" modelAttribute="comment">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	<form:hidden path="momentPosted"/>
	
	<form:label path="title">
		<spring:message code="comment.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="text">
		<spring:message code="comment.text"/>
	</form:label>
	<form:textarea path="text"/>
	<form:errors cssClass="error" path="text"/>
	<br />
	
	<form:label path="stars">
		<spring:message code="comment.stars"/>
	</form:label>
	<form:input path="stars"/>
	<form:errors cssClass="error" path="stars"/>
	<br />
	
	<form:label path="commentableEntity">
		<spring:message code="comment.commentableEntity" />:
	</form:label>
	<form:select id="commentableEntities" path="commentableEntity">
		<form:options items="${commentableEntities}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="commentableEntity" />
	<br />
		
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="comment.save"/>"/>&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />"
		onclick="window.location='comment/list.do'" />
	<br />
		
</form:form>