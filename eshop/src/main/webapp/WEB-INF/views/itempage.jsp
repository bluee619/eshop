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
		<link href="<c:url value="/css/main.css" />" rel="stylesheet">
		
		<title>Online Shop</title>
	</head>
	<style>
		
		#myPicture {
		    border-radius: 5px;
		    height: 300px;
		    width: 300px;
		    cursor: pointer;
		    transition: 0.3s;
		}
		
		#myPicture:hover {opacity: 0.8;}	
		
		.modal {
		    display: none;
		    position: center;
		    z-index: 1;
		    padding-top: 100px; /* Location of the box */
		    left: 0;
		    top: 55px;
		    width: 100%; /* Full width */
		    height: 100%; /* Full height */
		    overflow: auto; /* Enable scroll if needed */
		    background-color: rgb(0,0,0); /* Fallback color */
		    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
		}
		
		.modal-content {
		    margin: auto;
		    display: block;
		    width: 80%;
		    max-width: 700px;
		}
		
		#caption {
		    margin: auto;
		    display: block;
		    width: 80%;
		    max-width: 700px;
		    text-align: center;
		    color: #ccc;
		    padding: 10px 0;
		    height: 150px;
		}
		
		.modal-content, #caption { 
		    -webkit-animation-name: zoom;
		    -webkit-animation-duration: 0.6s;
		    animation-name: zoom;
		    animation-duration: 0.6s;
		}
		
		@-webkit-keyframes zoom {
		    from {-webkit-transform:scale(0)} 
		    to {-webkit-transform:scale(1)}
		}
	
		@keyframes zoom {
		    from {transform:scale(0)} 
		    to {transform:scale(1)}
		}
		
		.close {
		    position: absolute;
		    top: 15px;
		    right: 35px;
		    color: #f1f1f1;
		    font-size: 40px;
		    font-weight: bold;
		    transition: 0.3s;
		}
	
		.close:hover,
		.close:focus {
		    color: #bbb;
		    text-decoration: none;
		    cursor: pointer;
		}
	
		@media only screen and (max-width: 700px){
	    .modal-content {
	        width: 100%;
	    }
	    
	</style>
	<body>
		<jsp:include page="top.jsp"/>
				<div class="container sidebar2">
				
					<div class="row">
  						<div class="col-md-4"><img class="img-rounded" alt="${item.name}" id="myPicture" src='data:image/jpeg;base64, ${item.encoded}'/>
	  						<div id="myModal" class="modal">
								  <span class="close">×</span>
								  <img class="modal-content" id="myImg">
								  <div id="caption"></div>
							</div>
						</div>
	  					<div class="col-md-8">
	  						<h1>${item.name}</h1>
	  						<h5>Seller: <a href="/user/${item.seller.login}"> ${item.seller.login}</a></h5>
	  						<h5>Category: <a href="/search/${item.category}"> ${item.category}</a></h5>
	  						<div id="border" style="border-color:#2A0A12;"></div>
	  						<div class="row">
	  							<div class="col-md-9">
									<h2>Price: <strong>${item.price}</strong> $</h2>
									<h4>Added: ${item.date}</h4>
									<h4>Shipping cost: <strong>${item.shipment.shippingCost}</strong> $</h4>
									<h4>Approx time: ${item.shipment.approxTime.intValue()} days</h4>
								</div>
								<aside class="col-md-3 sidebar2">
									<div class="panel panel-default">
										<div class="panel-heading">Decided to buy?</div>
										<c:if test="${empty item.boughtBy}">
											<div class="panel-body">
												<form action="/addToCart/${item.id}">
													<button class="btn btn-default" type="submit">
														<i class="glyphicon glyphicon-shopping-cart"></i>
														Add to Cart
													</button>
												</form>
												<sec:authorize access="isAuthenticated()">
													<form action="/buyitem/${item.id}">
													<button class="btn btn-default" type="submit">
														Buy now
													</button>
												</form>
												</sec:authorize>
												<sec:authorize access="isAnonymous()">
													<h4> Sign in to buy</h4>
												</sec:authorize>
											</div>
										</c:if>
										<c:if test="${not empty item.boughtBy}">
											<div class="panel-body">
												<h4>Already sold</h4>
											</div>
										</c:if>
									</div>		
								</aside>	
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
						</div>
						<div class="col-md-8" style="word-wrap: break-word;">
							<h3>Description: ${item.description}</h3>
						</div>
					</div>
				</div>
		
	<jsp:include page="bot.jsp"/>
	
	<script>
		var modal = document.getElementById('myModal');

		var img = document.getElementById('myPicture');
		var modalImg = document.getElementById("myImg");
		var captionText = document.getElementById("caption");
		img.onclick = function(){
			modal.style.display = "block";
			modalImg.src = this.src;
			captionText.innerHTML = this.alt;
		}

		var span = document.getElementsByClassName("close")[0];

		span.onclick = function() {
			modal.style.display = "none";
		}
	</script>
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>