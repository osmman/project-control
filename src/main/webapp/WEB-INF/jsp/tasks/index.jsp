<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Tasks</h1>
	</div>
	<c:if test="${it.posible == true}">
		<div class="span6 text-right">
			<a class="btn btn-primary" href="/tasks/new">New</a>
		</div>
	</c:if>
</div>
<table class="table table-bordered">
	<tr>
		<th>#</th>
		<th>Title</th>
		<th>Created by</th>
		<th>Assigned by</th>
		<th>Fixed by</th>
		<th>Created at</th>
		<th>Start at</th>
		<th>DeadLine at</th>
		<th>Parent task</th>
		<th>Actions</th>
	</tr>
	<c:forEach var="task" items="${it.tasks}">
		<tr>
			<td><a href="/tasks/${task.key}">${task.key}</a></td>
			<td>${task.title}</td>
			<td>${task.created}</td>
			<td>${task.assigned}</td>
			<td>${task.fixed}</td>
			<td><fmt:formatDate value="${task.createdAt}" pattern="dd.MM.yy" /></td>
			<td><fmt:formatDate value="${task.startAt}" pattern="dd.MM.yy" /></td>
			<td><fmt:formatDate value="${task.deadLineAt}" pattern="dd.MM.yy" /></td>
			<td><c:if test="${task.parentTask != null}"><a href="/tasks/${task.parentTask}">#${task.parentTask}</a></c:if></td>
			<td>
				<form action="/tasks/delete" method="post"><button name="key" type="submit" value="${task.key}" class="btn btn-danger">Delete</button></form>
			</td>
		</tr>
	</c:forEach>
</table>