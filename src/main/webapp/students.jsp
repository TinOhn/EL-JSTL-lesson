<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/common/menu.jsp"></jsp:include>
		<div class="row my-2">
			<div class="col-10">
				<h3>All Students</h3>
			</div>
			<div class="col">
				<c:url value="/add-student" var="addstu"></c:url>
				<a href="${addstu}" class="btn btn-primary">Add Student</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table">
					<tr>
						<th>Student Name</th>
						<th>Email</th>
						<th>Age</th>
						<th>Year</th>
						<th>Address</th>
						<th>DateOfBirth</th>
					</tr>
					<c:forEach items="${studentList }" var="student">
						<tr>
							<td>${student.name }</td>
							<td>${student.email }</td>
							<td>${student.age }</td>
							<td>${student.year }</td>
							<td>${student.address }</td>
							<td>${student.dateOfBirth }</td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
	</div>
</body>
</html>