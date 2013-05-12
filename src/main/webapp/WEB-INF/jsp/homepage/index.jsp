<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>Dashboard</h1>
<h2>Signed up as:</h2>
<table class="table table-bordered">
	<tr>
		<th></th>
		<td><img src="${it.files.picture}" /></td>
	</tr>
	<tr>
		<th>Name</th>
		<td>${it.files.given_name}</td>
	</tr>
	
	<tr>
		<th>Gender</th>
		<td>${it.files.gender}</td>
	</tr>
	<tr>
		<th></th>
		<td>
			<c:choose>
				<c:when test="${user != null}">
					<li><a href="${fn:escapeXml(logoutUrl)}">Log out</a></li>
				</c:when>
			</c:choose>
		</td>
	</tr>
</table>