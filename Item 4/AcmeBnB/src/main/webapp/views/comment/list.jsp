<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comments" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	
	<spring:message code="comment.momentPosted" var="momentPostedHeader" />
	<display:column property="momentPosted" title="${momentPostedHeader}" sortable="true" />
	
	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" sortable="true" />
	
	<spring:message code="comment.actor" var="actorHeader" />
	<display:column property="actor.name" title="${actorHeader}" sortable="true" />
	
	<spring:message code="comment.commentableEntity" var="commentableEntityHeader" />
	<display:column property="commentableEntity.name" title="${commentableEntityHeader}" sortable="true" />
	
</display:table>

<!-- Action links -->
	<security:authorize access="hasAnyRole('LESSOR, TENANT')">
			<a href="comment/create.do"><spring:message code="comment.create"/></a>
	</security:authorize>