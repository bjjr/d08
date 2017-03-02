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
	name="books" requestURI="book/tenant/list.do" id="row">
	<!-- Attributes -->
	
	<acme:column code="book.status" property="status.name"/>
	<acme:column code="book.checkInDate" property="checkInDate"/>
	<acme:column code="book.checkOutDate" property="checkOutDate"/>
	<acme:column code="book.smoker" property="smoker"/>
	
</display:table>