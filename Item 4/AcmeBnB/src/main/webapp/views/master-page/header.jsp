<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme BnB Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" />(<security:authentication property="principal.username" />)</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/edit.do"><spring:message code="master.page.profile.edit" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="attribute/list.do"><spring:message code="master.page.administrator.attributes" /></a></li>
					<li><a href="auditor/adminitrator/create.do"><spring:message code="master.page.administrator.auditor" /></a></li>
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.si.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.si.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.lessor" />(<security:authentication property="principal.username" />)</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lessor/edit.do"><spring:message code="master.page.profile.edit" /></a></li>
					<li><a href="comment/list.do"><spring:message code="master.page.comment.list" /></a></li>
					<li><a href="comment/create.do"><spring:message code="master.page.comment.create" /></a></li>
					<li><a href="property/ownList.do"><spring:message code="master.page.lessor.properties.list" /></a></li>
					<li><a href="creditCard/display.do"><spring:message code="master.page.creditCard.display" /></a></li>
					<li><a href="book/lessor/list.do"><spring:message code="master.page.lessor.book" /></a></li>
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.si.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.si.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
			<li><a class="fNiv"><spring:message	code="master.page.tenant" />(<security:authentication property="principal.username" />)</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/edit.do"><spring:message code="master.page.profile.edit" /></a></li>
					<li><a href="comment/list.do"><spring:message code="master.page.comment.list" /></a></li>
					<li><a href="comment/create.do"><spring:message code="master.page.comment.create" /></a></li>
					<li><a href="creditCard/display.do"><spring:message code="master.page.creditCard.display" /></a></li>
					<li><a href="finder/tenant/display.do"><spring:message code="master.page.finder.display" /></a></li>
					<li><a href="book/tenant/list.do"><spring:message code="master.page.tenant.book" /></a></li>
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.si.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.si.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" />(<security:authentication property="principal.username" />)</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="auditor/edit.do"><spring:message code="master.page.profile.edit" /></a></li>
					<li><a href="audit/auditor/list.do"><spring:message code="master.page.auditor.audit.list" /></a></li>
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.si.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.si.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="finder/tenant/display.do"><spring:message code="master.page.tenant.finder" /></a></li>	
					<li><a href="book/tenant/list.do"><spring:message code="master.page.tenant.book" /></a></li>	
					<li><a href="invoice/tenant/list.do"><spring:message code="master.page.tenant.invoice" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message code="master.page.account" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/create.do"><spring:message code="master.page.tenant.create" /></a>
					<li><a href="lessor/create.do"><spring:message code="master.page.lessor.create" /></a>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

