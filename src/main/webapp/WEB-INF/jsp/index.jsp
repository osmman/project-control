<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/assets/css/bootstrap.css" rel="stylesheet" media="screen" />
<link href="/assets/css/bootstrap-responsive.css" rel="stylesheet"
	media="screen" />

<title>Insert title here</title>
</head>
<body>
	
	<h1>Hello, world!${it.calendar}</h1>
	
	<c:choose>
		<c:when test="${user != null}">
			<p>
				Jste přihlášen jako
				<c:out value="${user.nickname}" />
				. <a href="${fn:escapeXml(logoutUrl)}">Odhlásit se.</a>
			</p>
		</c:when>
	</c:choose>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/assets/js/jquery-1.9.1.min.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>