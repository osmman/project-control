<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
	<div class="span6">
		<h1>User - new</h1>
	</div>
</div>

<p class="text-error">
	<c:forEach var="err" items="${error}">
		${err}<br />
	</c:forEach>
</p>
<form action="update" method="post">
	<input type="hidden" name="defEmail" value="${it.user.email}" />
	<div class="control-group">
		<label class="control-label" for="inputEmail">Email</label>
	    <div class="controls">
	    	<input type="text" id="inputEmail" name="email" placeholder="Email" value="${it.user.email}" disabled="disabled">
	    </div>
  	</div>
  	
  	<div class="control-group">
		<label class="control-label" for="inputName">Name</label>
	    <div class="controls">
	    	<input type="text" id="inputName" name="name" placeholder="Name" value="${it.user.name}">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="inputPhone">Phone</label>
	    <div class="controls">
	    	<input type="text" id="inputPhone" name="phone" placeholder="Phone" value="${it.user.phone}">
	    </div>
	</div>
	<div class="control-group">
		<div class="controls">
	    	<button type="submit" class="btn btn-primary">Sign in</button>
	    </div>
	</div>
</form>