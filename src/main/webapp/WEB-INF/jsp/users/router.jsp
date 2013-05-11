<jsp:include page="../template/application.jsp">
	<jsp:param name="title" value="${it.title}"/>  
	<jsp:param name="activePage" value="${it.activePage}"/> 
	<jsp:param name="body" value="/WEB-INF/jsp/${it.page}" />  
</jsp:include> 