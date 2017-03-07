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
	name="books" requestURI="${requestUri}" id="row">
	<!-- Attributes -->
	
	<spring:message code="book.property" var="title"/>
	<display:column title="${title}">
		<spring:url var="url_property" value="/property/display.do">
			<spring:param name="propertyId" value="${row.property.id}"/>
		</spring:url>
			
		<a href="${url_property}"><jstl:out value="${row.property.name}"/></a>
	</display:column>
	
	<acme:column code="book.status" property="status.name"/>
	<acme:column code="book.checkInDate" property="checkInDate"/>
	<acme:column code="book.checkOutDate" property="checkOutDate"/>
	<acme:column code="book.smoker" property="smoker"/>
	
	<security:authorize access="hasRole('LESSOR')">
		<acme:column code="book.tenant.name" property="tenant.name"/>
	
		<display:column>
			<jstl:if test="${row.status.name == 'PENDING'}" >
				<spring:url var="url_accept" value="/book/lessor/accept.do">
					<spring:param name="bookId" value="${row.id}"/>
				</spring:url>
			
				<a href="${url_accept}"><spring:message code="book.accept"/></a>
			
			</jstl:if>

		</display:column>
		<display:column>
			<jstl:if test="${row.status.name == 'PENDING'}" >
				<spring:url var="url_deny" value="/book/lessor/deny.do">
					<spring:param name="bookId" value="${row.id}"/>
				</spring:url>
				
				<a href="${url_deny}"><spring:message code="book.deny"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('TENANT')">
		<display:column>
			<spring:url var="url_edit" value="/book/tenant/edit.do">
				<spring:param name="bookId" value="${row.id}"/>
			</spring:url>
			
			<a href="${url_edit}"><spring:message code="book.edit"/></a>
		</display:column>
		
		<display:column>
		<jstl:if test="${row.status.name == 'ACCEPTED'}">
			<spring:url var="url_create_invoice" value="/invoice/tenant/createInvoice.do">
				<spring:param name="bookId" value="${row.id}"/>
			</spring:url>
				
			<a href="${url_create_invoice}"><spring:message code="book.create.invoice"/></a>
		</jstl:if>
			
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('LESSOR')">
	<b><spring:message code="book.fee.topay"/> <jstl:out value="${feeToPay}" />&euro;</b>
</security:authorize>
