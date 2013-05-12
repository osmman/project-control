<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<td>${task.key}</td>
			<td>${task.title}</td>
			<td>${task.created}</td>
			<td>${task.assigned}</td>
			<td>${task.fixed}</td>
			<td>${task.createdAt}</td>
			<td>${task.startAt}</td>
			<td>${task.deadLineAt}</td>
			<td><c:if test="${task.parentTask}">${task.parentTask.key}</c:if></td>
			<td>
				<ul class="inline">
					<li>
						<form action="/tasks/edit" method="get"><button name="key" type="submit" value="${task.key}" class="btn btn-primary">Edit</button></form>
					</li>
					<li>
						<form action="/tasks/delete" method="post"><button name="key" type="submit" value="${task.key}" class="btn btn-danger">Delete</button></form>
					</li>
				</ul>
			</td>
		</tr>
	</c:forEach>
</table>