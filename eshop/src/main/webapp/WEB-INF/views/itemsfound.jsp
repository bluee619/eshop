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

		<title>Online Shop</title>
	</head>
	<style>
		#img{
			height:250px;
			width:250px;
		}
	</style>
	<body>
		<jsp:include page="top.jsp"/>
		
		<div class="well wellcart sidebar1">
			<div class="row">
				<h1>Results:</h1>
			</div>
		</div>
		<c:if test="${fn:length(itemsList) eq 0}">
			<div class="sidebar1 text-center">
				<h2>There are no matching items.</h2>
			</div>
		</c:if>
		<div class="container-fluid">
				<c:forEach items="${itemsList}" var="element">
					<div class="row thumbnail sidebar2">				
							<div class="col-md-3">
								<img class="img-rounded" id="img" src='data:image/jpeg;base64, ${element.encoded}'/>
							</div>
							<div class="col-md-9">
								<div class="caption text-center">
							        <h3>${element.name}</h3>
							        <p>Price: ${element.price} $</p>
							        <p><a href="/item/${element.id}" class="btn btn-default" role="button">More details</a></p>
							      </div>
							</div>
					</div>
				</c:forEach>
			</div>
		<jsp:include page="bot.jsp"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>