<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica o inserisci</title>
</head>
<body>
	<form action="/salva-city" method="post">
		<div>
			<input type="hidden" name="modificaID" value="${city.id}">
		</div>
		<div>
			<input type="text" name="modificaNome" value="${city.name}">
		</div>
		<div>
			<select name="modificaCodice">
				<c:forEach items="${nazioni}" var="naz">
				<c:choose>
					<c:when test="${naz.code == city.countryCode}"><option value="${naz.code}" selected>${naz.name}</option></c:when>
					<c:otherwise><option value="${naz.code}">${naz.name}</option></c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
		</div>
		<div>
			<input type="submit" value="Applica">
		</div>
	</form>
</body>
</html>