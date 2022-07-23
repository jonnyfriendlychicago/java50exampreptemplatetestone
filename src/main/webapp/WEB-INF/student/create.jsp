<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	
	<div id=pageHeader class="container-fluid">
		<h2>Store Management</h2>
	</div>

	<div id=main class="container-fluid">
		<div id=form class="container-fluid">
			<h3>Add a Student</h3>
			<%-- <form:form action='/store/student/new' method='post' modelAttribute='student'> --%>
			<form:form action='/student/new' method='post' modelAttribute='student'>
				
				<!-- use below, in concert with Mdl file, to capture createdBy_id value -->
				<%-- <form:hidden value="${user.id}" path="userMdl" /> --%>
				
				<div class="form-group">
					<form:label path="studentName" for="studentName">studentName</form:label>
					<form:input type="text" class="form-control" path="studentName"/>
					<p class="errorText"><form:errors path="studentName" />
					</p>
				</div>
<%-- 
				<div class="form-group">
					<form:label path="teacherDesc" for="teacherDesc">teacherDesc</form:label>
					<form:textarea type="text" class="form-control" path="teacherDesc" />
					<p class="errorText"><form:errors path="teacherDesc" /></p>
				</div> 				
 --%>
				
<%-- 		
				<div class="form-group">
					<form:select path="dojoMdl">
						<c:forEach var="record" items="${dojoList}">
							<!--- Each option VALUE is the id of the dojo --->
							<form:option value="${record.id}" path="dojoMdl">
								<!--- This is what shows to the user as the option --->
								<c:out value="${record.dojoName}" />
							</form:option>
						</c:forEach>
					</form:select>
				</div>
 --%>				
 				
		
 				
 				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
<%-- 			
			<c:choose>
				<c:when test="${onErrorPath == 'yep' }">
					<a href= "/publication"><button class="btn btn-secondary">Cancel</button></a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> 			
 --%>		
 </div>

	
	</div>

	<jsp:include page="/WEB-INF/include/footer.jsp"/>
	
</body>
</html>