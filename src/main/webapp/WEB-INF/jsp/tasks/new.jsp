<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
		<div class="controls">
	    	<button type="submit" class="btn btn-primary">Create</button>
	    </div>
	</div>
</form>