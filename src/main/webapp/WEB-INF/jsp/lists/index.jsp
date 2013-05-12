<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Lists</h1>
	</div>
</div>
<div class="prettyprint">
	<form action="" method="get" class="form-inline">
		<select name="email">
			<c:forEach var="user" items="${it.users}">
				<c:if test="${it.email == user.email}">
					<option value="${user.email}" selected="selected">${user.name}</option>
				</c:if>
				<c:if test="${it.email != user.email}">
					<option value="${user.email}" >${user.name}</option>
				</c:if>
			</c:forEach>
		</select>
		<select name="type">
			<c:if test='${it.type == "created"}'>
				<option value="created" selected="selected">Creted by</option>
			</c:if>
			<c:if test='${it.type != "created"}'>
				<option value="created">Creted by</option>
			</c:if>
			<c:if test='${it.type == "assigned"}'>
				<option value="assigned" selected="selected">Assigned to</option>
			</c:if>
			<c:if test='${it.type != "assigned"}'>
				<option value="assigned">Assigned to</option>
			</c:if>
			<c:if test='${it.type == "fixed"}'>
				<option value="fixed" selected="selected">Fixed by</option>
			</c:if>
			<c:if test='${it.type != "fixed"}'>
				<option value="fixed">Fixed by</option>
			</c:if>
		</select>
		<button type="submit" class="btn btn-primary">Filter</button>
	</form>
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
			<td>${task.createdAt}</td>
			<td>${task.startAt}</td>
			<td>${task.deadLineAt}</td>
			<td><c:if test="${task.parentTask != null}"><a href="/tasks/${task.parentTask}">#${task.parentTask}</a></c:if></td>
			<td>
				<form action="/tasks/delete" method="post"><button name="key" type="submit" value="${task.key}" class="btn btn-danger">Delete</button></form>
			</td>
		</tr>
	</c:forEach>
</table>