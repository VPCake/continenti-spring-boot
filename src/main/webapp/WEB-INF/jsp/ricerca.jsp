<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca</title>
</head>
<body>
	<p>${conferma}</p>
	<form action="/risultati-ricerca">
		<input type="text" name="name"> <select name="code">
			<option value="" SELECTED>Nessuna nazione</option>
			<c:forEach items="${nazioni}" var="naz">
				<option value="${naz.code}">${naz.name}</option>
			</c:forEach>
		</select> <input type="submit" value="Cerca">
	</form>

	<div>
		<a href="/modifica-city/0/show"> Inserimento</a>
	</div>
	<c:forEach items="${citta}" var="cit">
		<div>${cit.name}
			<a href="/modifica-city/${cit.id}/show">Modifica</a>
		</div>
	</c:forEach>

</body>
</html>