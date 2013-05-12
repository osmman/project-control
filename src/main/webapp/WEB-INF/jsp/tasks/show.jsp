<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Tasks #${it.task.key}</h1>
	</div>
	<c:if test="${it.posible == true}">
		<div class="span6 text-right">
			<ul class="inline">
			<li><a class="btn btn-primary" href="/tasks/new?id=${it.task.key}">Add Subtask</a></li>
			<c:if test="${(!empty(it.task.assigned)) && it.task.fixed == null}">
				<li><form action="/tasks/fix" method="post"><button name="key" type="submit" value="${it.task.key}" class="btn btn-success">Fix task</button></form></li>
			</c:if>
			<li><a class="btn btn-primary" href="/tasks/${it.task.key}/edit">Edit task</a></li>
			<li><form action="/tasks/delete" method="post"><button name="key" type="submit" value="${it.task.key}" class="btn btn-danger">Delete task</button></form></li>
			</ul>
		</div>
	</c:if>
</div>
<table class="table table-bordered">
	<tr>
		<th>Title:</th>
		<td>${it.task.title}</td>
	</tr>
	
	<tr>
		<th>Created by:</th>
		<td>${it.task.created}</td>
	</tr>
	
	<tr>
		<th>Assigned to:</th>
		<td>${it.task.assigned}</td>
	</tr>
	
	<tr>
		<th>Fixed by:</th>
		<td>${it.task.fixed}</td>
	</tr>
	
	<tr>
		<th>Created at:</th>
		<td><fmt:formatDate value="${it.task.createdAt}" pattern="MM.dd.yy" /></td>
	</tr>
	
	<tr>
		<th>Start at:</th>
		<td><fmt:formatDate value="${it.task.startAt}" pattern="MM.dd.yy" /></td>
	</tr>
	
	
	<tr>
		<th>Deadline:</th>
		<td><fmt:formatDate value="${it.task.deadLineAt}" pattern="MM.dd.yy" /></td>
	</tr>
	
	<tr>
		<th>Subtasks:</th>
		<td>
			<ul>
			<c:forEach var="subtask" items="${it.subtasks}">
				<li><a href="/tasks/${subtask.key}">#${subtask.key} - ${subtask.title}</a></li>
			</c:forEach>
			</ul>
		</td>
	</tr>
</table>
<pre>
	${it.task.text}
</pre>