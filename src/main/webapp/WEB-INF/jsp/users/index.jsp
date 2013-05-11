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
		<th>Name</th>
		<th>Email</th>
		<th>Phone</th>
		<th>Actions</th>
	</tr>
	<c:forEach var="user" items="${it.users}">
		<tr>
			<td>${user.email}</td>
			<td>${user.name}</td>
			<td>${user.phone}</td>
			<td>
				<ul class="inline">
					<li>
						<form action="/users/edit" method="get"><button name="key" type="submit" value="${user.email}" class="btn btn-primary">Edit</button></form>
					</li>
					<li>
						<form action="/users/delete" method="post"><button name="key" type="submit" value="${user.email}" class="btn btn-danger">Edit</button></form>
					</li>
				</ul>
			</td>
		</tr>
	</c:forEach>
</table>