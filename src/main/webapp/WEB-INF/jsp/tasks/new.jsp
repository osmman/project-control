<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row-fluid">
	<div class="span6">
		<h1>Task - new</h1>
	</div>
</div>

<p class="text-error">
	<c:forEach var="err" items="${error}">
		${err}<br />
	</c:forEach>
</p>
<form action="create" method="post">
	<input type="hidden" value="${it.parent}" name="parent" value="${it.task.parentTask}"/>
	<div class="control-group">
		<label class="control-label" for="inputTitle">Title</label>
	    <div class="controls">
	    	<input type="text" id="inputTitle" name="title" placeholder="Title" value="${it.task.title}">
	    </div>
  	</div>
  	
  	<div class="control-group">
		<label class="control-label" for="inputStartAt">Start at:</label>
	    <div class="controls">
	    	<input type="text" id="inputStartAt" name="startAt" placeholder="Start At" value="<fmt:formatDate value="${it.task.startAt}" pattern="MM.dd.yy" />">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="inputDeadLineAt">DeadLine at:</label>
	    <div class="controls">
	    	<input type="text" id="inputDeadLineAt"  name="deadLineAt" placeholder="DeadLine At" value="<fmt:formatDate value="${it.task.deadLineAt}" pattern="MM.dd.yy" />">
	    </div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="selectAssigned">Assigne to:</label>
		<div class="controls">
			<select id="selectAssigned" name="assigned">
				<c:if test="${it.task.assigned == ''}">
					<option value="" selected="selected"></option>
				</c:if>
				<c:if test="${it.task.assigned != ''}">
					<option value=""></option>
				</c:if>
				<c:forEach var="usera" items="${it.users}">
					<c:if test="${it.task.assigned == usera.email}">
						<option value="${usera.email}" selected="selected">${usera.name}</option>
					</c:if>
					<c:if test="${it.task.assigned != usera.email}">
						<option value="${usera.email}">${usera.name}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="textareaText">Text of task:</label>
	    <div class="controls">
	    	<textarea id="textareaText" name="text" rows="10">${it.task.text}</textarea>
	    </div>
	</div>
	
	<div class="control-group">
		<div class="controls">
	    	<button type="submit" class="btn btn-primary">Create</button>
	    </div>
	</div>
</form>