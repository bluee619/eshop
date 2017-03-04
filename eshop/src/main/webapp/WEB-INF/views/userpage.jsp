<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>
		<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css"></link>
		<link href="https://fonts.googleapis.com/css?family=Didot" rel="stylesheet" type="text/css"></link>
		<link href="<c:url value="/css/main.css" />" rel="stylesheet">
		
	<title>Online Shop</title>	
	
	</head>	
	<body>
		<jsp:include page="top.jsp"/>
		<div class="well wellcart">
			<h2>User ${client.login}:</h2>
			<c:if test="${client.login eq null}">
				<h3>There is no such user.</h3>
			</c:if>	
		</div>
		<div class="container-fluid sidebarleft">
			<h2>Firstname: ${client.personalData.firstname}</h2>
			<h2>Surname: ${client.personalData.surname}</h2>
			<h2>Address:</h2>
			<h3>Street: ${client.personalData.address.street}</h3>
			<h3>House number: ${client.personalData.address.houseNumber}</h3>
			<h3>City: ${client.personalData.address.city}</h3>
			<h3>Postal code: ${client.personalData.address.postalCode}</h3>
			<h2>Phone number: ${client.personalData.phoneNumber}</h2>
			<h2>Account number: ${client.personalData.accountNumber}</h2>
			<a href="/itemsfromseller/${client.clientId}" class="btn btn-default sidebar2" role="button">All items from this seller</a>
			<h2>Comments:</h2>
			<c:if test="${fn:length(client.commentsReceived) eq 0}">
				<h4>No comments for this user yet.</h4>
			</c:if>
		</div>
		<div class="sidebarleft sidebar2">
			<sec:authorize access="isAuthenticated()">
				<sec:authentication var="username" property="principal.username"/>
				<c:if test="${username eq client.login}">
					<a href="/user/edit/personal/address/${client.login}" class="btn btn-default sidebarleft" role="button">Edit address</a>
					<a href="/user/edit/password/${client.login}" class="btn btn-default sidebarleft" role="button">Change password</a>
				</c:if>	
			</sec:authorize>
		</div>		
		<jsp:include page="bot.jsp"/>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>