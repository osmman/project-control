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
	${it.users.get(1)}
	
	<c:forEach var="user" items="${it.users}">
		<tr>
			<td>${user.key}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td>${user.phone}</td>
			<td><a>Edit</a><a>Delete</a></td>
		</tr>
	</c:forEach>
</table>