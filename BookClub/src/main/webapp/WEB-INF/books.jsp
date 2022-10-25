<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class= "container">
		<h2><c:out value="${book.title}"/></h2>
		<p><a href="/dashboard">Back to the shelf</a> </p>
		<p><c:out value="${book.creator.firstName}"/> read <c:out value="${book.title}"/> by <c:out value="${book.author}"/></p>
		<p>Here are <c:out value="${book.creator.firstName}"/> thoughts:
		<p><c:out value="${book.thoughts}"/></p>
		<p><a href="/books/${book.id} /edit/${recipe.id}">Edit</a></p>
	</div>
</body>
</html>