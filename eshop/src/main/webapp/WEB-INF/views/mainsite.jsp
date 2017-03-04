<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
			witdh:200px; 
			height:200px;
		}	
	
		#border{
			border-bottom: 1px solid;
			border-color: #fff;
		}
	</style>
	<body>
		<jsp:include page="top.jsp"/>
			
					<div class="container-fluid">
						<div class="row">
							<c:forEach items="${itemList}" var="item">	
								<div class="col-md-3">
								    <div class="thumbnail">
								      <img class="img-rounded sidebar2" id="img" src='data:image/jpeg;base64, ${item.encoded}'/>
								      <div class="caption text-center">
								        <h3>${item.name}</h3>
								        <p>Price: ${item.price} $</p>
								        <p><a href="/item/${item.id}" class="btn btn-default" role="button">More details</a></p>
								      </div>
								    </div>
								 </div>
							</c:forEach>
						</div>
					</div>	
		
	<jsp:include page="bot.jsp"/>
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>