<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco nazioni</title>
</head>
<body>
	<c:forEach items="${nazioni}" var="naz">

		<div>
			<a href="/citta/${naz.code}/show">${naz.name}-${naz.population}</a>
		</div>

	</c:forEach>
</body>
</html>