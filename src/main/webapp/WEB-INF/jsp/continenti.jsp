<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco continenti</title>
</head>
<body>
	<c:forEach items="${continents}" var="cont">

		<form action="/nazioni">
			<input type="submit" value="${cont}" />
			<input type="text" id="nascondi" name="nomeContinente" value="${cont}" />
		</form>
	</c:forEach>
</body>
<style>
#nascondi {
	visibility: hidden;
}
</style>
</html>