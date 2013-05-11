<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Users</h1>
	</div>
	<div class="span6 text-right">
		<a class="btn btn-primary" href="/users/new">New</a>
	</div>
</div>
<table class="table table-bordered">
	<tr>
		<th>#</th>
		<th>Name</th>
		<th>Email</th>
		<th>Phone</th>
		<th>Actions</th>
	</tr>
	<c:forEach var="user" items="${it.users}">
		<tr>
			<td>${user.key}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td>${user.phone}</td>
			<td><a href="/users/edit/?key=${user.key}" class="btn btn-primary">Edit</a><a href="/users/delete/?key=${user.key}" class="btn btn-danger">Edit</a></td>
		</tr>
	</c:forEach>
</table>