<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Task - new</h1>
	</div>
</div>
<form action="create" method="post"  >
	<div class="control-group">
		<label class="control-label" for="inputTitle">Title</label>
	    <div class="controls">
	    	<input type="text" id="inputTitle" name="title" placeholder="Title" value="${title}">
	    </div>
  	</div>
  	
  	<div class="control-group">
		<label class="control-label" for="inputStartAt">Start at:</label>
	    <div class="controls">
	    	<input type="text" id="inputStartAt" name="startAt" placeholder="Start At" value="${startAt}">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="inputDeadLineAt">DeadLine at:</label>
	    <div class="controls">
	    	<input type="text" id="inputDeadLineAt"  name="deadLineAt" placeholder="DeadLine At" value="${deadLineAt}">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="selectAssigned">Assigne to:</label>
		<div class="controls">
			<select id="selectAssigned" name="assigned">
				<option value="" selected="selected"></option>
				<c:forEach var="user" items="${it.users}">
					<option value="${user.email}">${user.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="control-group">
		<div class="controls">
	    	<button type="submit" class="btn btn-primary">Create</button>
	    </div>
	</div>
</form>