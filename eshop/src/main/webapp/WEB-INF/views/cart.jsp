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
	
	<style>
	#imgcart{
		width:250;
		height:250; 
	}
	
	</style>
	
	<body>
		<jsp:include page="top.jsp"/>
			
	   <div class="well wellcart sidebar1">
			<div class="row">
				<h1>CART</h1>
			</div>
		</div>
		
		<div class="container-fluid">
			<c:forEach items="${sessionScope.cart.itemsInCart}" var="element">
				<div class="row sidebar2">
					<div class="col-md-12">
						<div class="col-md-4">
							<a href="/item/${element.id}"><img class="img-rounded" id="imgcart" alt="${element.name}" src='data:image/jpeg;base64, ${element.encoded}'/></a>
						</div>
						<div class="col-md-8">
		  						<h1>${element.name}</h1>
		  						<h5>Seller: <a href="/user/${element.seller.login}"> ${element.seller.login}</a></h5>
		  						<div id="border" style="border-color:#2A0A12;"></div>
		  						<div class="row">
		  							<div class="col-md-9">
										<h2>Price: <strong>${element.price}</strong> $</h2>
										<h4>Shipping cost: <strong>${element.shipment.shippingCost}</strong> $</h4>
										<a href="/deleteFromCart/${element.id}" class="btn btn-default" role="button">Reject</a>
									</div> 
								</div>
						</div>
					</div>
				</div>   
			</c:forEach>
		</div>
		
		
		<div class="well wellcart sidebar1">
			<div class="row">
				<div class="col-md-12">
					<h2>Items in cart: ${fn:length(sessionScope.cart.itemsInCart)}</h2>
					<c:set var="total" value="${0}"/>
					<c:forEach items="${sessionScope.cart.itemsInCart}" var="item">					    
						<c:set var="total" value="${total + item.price + item.shipment.shippingCost}" />
					</c:forEach>
					<h2>Total price: ${total} $</h2>
					<c:if test="${fn:length(sessionScope.cart.itemsInCart) gt 0}">
						<sec:authorize access="isAuthenticated()">
							<td><h2><a href="/buycart" class="btn btn-large btn-default" role="button">Buy all</a></h2></td>
						</sec:authorize>	
					</c:if>
					<c:if test="${fn:length(sessionScope.cart.itemsInCart) eq 0}">
						<sec:authorize access="isAuthenticated()">
							<h3>Cart is empty</h3>
						</sec:authorize>	
					</c:if>	
					<sec:authorize access="isAnonymous()">
						<h3><strong>Sign in to buy</strong></h3>
					</sec:authorize>
				</div>
			</div>
		</div>		

		<jsp:include page="bot.jsp"/>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
