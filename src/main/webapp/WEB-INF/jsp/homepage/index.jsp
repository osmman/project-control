<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>Dashboard</h1>
<h2>Hello, world! ${it.calendar} ${it.files}</h2>
	
<c:choose>
	<c:when test="${user != null}">
		<p>
			Jste přihlášen jako
			<c:out value="${user.nickname}" />
			. <a href="${fn:escapeXml(logoutUrl)}">Odhlásit se.</a>
		</p>
	</c:when>
</c:choose>