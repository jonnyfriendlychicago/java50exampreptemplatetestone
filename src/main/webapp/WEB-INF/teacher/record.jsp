<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Store Management</h2>
	</div>
	
	<div id=main class="card">
		<h3><c:out value="${teacher.teacherName}"></c:out></h3>
		
		<div id=about class="card">
			<%-- <h4>id: <c:out value="${publication.id}"></c:out></h4> --%>
			
			
			<h4>teacherDesc:</h4>
			<pre class="textAreaReadOut"><c:out value="${teacher.teacherDesc}"></c:out></pre>

			<h4>price: $<c:out value="${teacher.price}"></c:out></h4>
<%-- 			
			<p>Here are
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id}">your</c:when>
				<c:otherwise>
				${publication.userMdl.userName}'s
				</c:otherwise>
			</c:choose> 
			thoughtsOnPub: </p>
--%>


			<%-- 
			<h4>publication.dojoMdl.dojoName: <c:out value="${publication.dojoMdl.dojoName}"></c:out></h4> 
			--%> 
			<%-- <a href= "/publication/${publication.id}/edit">OrigEdit</a>  --%>
			
<%-- 
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
					<a href= "/publication/${publication.id}/edit"><button class="btn btn-secondary">Edit</button></a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> 
			
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
					<form action="/publication/${publication.id}" method="post">
					    <input type="hidden" name="_method" value="delete">
					    <button class="btn btn-danger">Delete this publication</button>
					</form>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
						 --%>
		</div>
		
		<div id=list class="card">
			<h3>Student List for this Teacher</h3>
<%-- 			
			<c:choose>
				<c:when test="${mgmtPermissionErrorMsg != null}">
					<p class="errorText">${mgmtPermissionErrorMsg}</p>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> 
--%>
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">studentName</th>
						<th scope="col">actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${assignedCategories}">
						<tr>
							<td>${record.id}</td>
							<%-- <td><a href="/store/student/${record.id}">${record.studentName}</a></td> --%>
							<td><a href="/student/${record.id}">${record.studentName}</a></td>
							<td>
								<%-- <form action="/store/removeTeacherStudentJoin" method="post"> --%>
								<form action="/removeTeacherStudentJoin" method="post">
								    
								    <input type="hidden" name="_method" value="delete">
								    <input type="hidden" name="studentId" value="${record.id}"/>
								    <input type="hidden" name="teacherId" value="${teacher.id}"/>
								    <input type="hidden" name="origin" value="1"/>
								    
								    <button class="btn btn-danger">Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div id=form class="card">
			<h2>Add a Student:</h2>

			<%-- <form action='/store/teacher/${teacher.id}' method='post' > --%>
			<form action='/teacher/${teacher.id}' method='post' >
			<%-- modelAttribute='teacher' --%>
			
				<!-- JRF note to self: I don't think next line is necessry, and cam says yep! -->
				<%-- <input type="hidden" value="${teacher.id}" path="id" /> --%>

				<div class="form-group">
					<select name="studentId"> 
					<!-- above name needs to match the value in the paramvalue in the ctl file -->
					<!-- path="studentMdl" -->
					<!-- JRF: above left out... this is a JSTL form thing -->
					
						<c:forEach var="record" items="${unassignedCategories}">
							<!--- Each option VALUE is the id of the dojo --->
							<option value="${record.id}" >
							<!-- path="studentMdl"> -->
								<!--- This is what shows to the user as the option --->
								<c:out value="${record.studentName}" />
							</option>
						</c:forEach>
					</select>
				</div>
 				
 				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
	
	</div>
 
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>