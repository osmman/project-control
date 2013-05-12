<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row-fluid">
	<div class="span6">
		<h1>User - new</h1>
	</div>
</div>
<form action="create" method="post"  >
	<div class="control-group">
		<label class="control-label" for="inputEmail">Email</label>
	    <div class="controls">
	    	<input type="text" id="inputEmail" name="email" placeholder="Email" value="${email}">
	    </div>
  	</div>
  	
  	<div class="control-group">
		<label class="control-label" for="inputName">Name</label>
	    <div class="controls">
	    	<input type="text" id="inputName" name="name" placeholder="Name" value="${name}">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="inputPhone">Phone</label>
	    <div class="controls">
	    	<input type="text" id="inputPhone"  name="phone" placeholder="Phone" value="${phone}">
	    </div>
	</div>
	<div class="control-group">
		<div class="controls">
	    	<button type="submit" class="btn btn-primary">Create</button>
	    </div>
	</div>
</form>