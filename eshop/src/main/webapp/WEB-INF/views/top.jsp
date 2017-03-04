<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link href="<c:url value="/css/main.css" />" rel="stylesheet">
		
		<nav class="navbar navbar-default navbar-fixed-top opa">
					<div class="container">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span> 
							</button>
							<a class="navbar-brand sidebar3" href="/main"><i class="glyphicon glyphicon-home"></i>Online Shop</a>
						</div>
						<div class="collapse navbar-collapse" id="myNavbar">
						
						
							<sec:authorize access="isAnonymous()">
								<ul class="nav navbar-nav navbar-right">
									<li class="sidebar3 sidebarright">
										<a href="/cart">
											<i class="glyphicon glyphicon-shopping-cart"></i> CART
										</a>
									</li>
									<li>
										<form class="form-inline sidebar2" action="/login" method="post">
											<div>
												<input class="form-control" name="username" placeholder="login"/>
												<input type="password" class="form-control" name="password" placeholder="password"/>
												<form:errors path="*" cssClass="error"></form:errors>
												<button class="btn btn-default" type="submit">Sign in</button>
											</div> 
										</form>	
									</li>
									<li class="sidebar3 sidebarleft"><a href="/register">REGISTER</a></li>
								</ul>
							</sec:authorize>	
							
							<sec:authorize access="isAuthenticated()">
								<ul class="nav navbar-nav navbar-right">
									<li class="sidebar3 sidebarright">
										<a href="/cart">
											<i class="glyphicon glyphicon-shopping-cart"></i> CART
										</a>
									</li>
									<li class="sidebar3"><a href="/additem">SELL</a></li>	
									<li class="sidebar3"><a href="/myitems">MY ITEMS</a></li>
									<sec:authentication property="principal.username" var="username"/>
									<li class="sidebar3 fon"><a href="/user/${username}">Logged as ${username}</a></li>
									<li>
										<form action="/logout">
											<button class="btn btn-default sidebarleft sidebar2" type="submit">Logout</button>
										</form>
									</li>
								</ul>
							</sec:authorize>	
							
							
					</div>
				</div>
		</nav>
		
			<div class="container-fluid text-center">
				<div class="row">
					<div class="jumbotron bground text-center">
						<h1>ONLINE SHOP</h1>
						<form:form class="form-inline sidebar1" method="post" modelAttribute="itemFilter" action="/searcher">
										
								<select class="form-control" name="category">
						   			<option selected disabled>Category</option>
						   			<option value="ALL">All</option>
						   			<option value="MOTO">Moto</option>
						   			<option value="TECHNOLOGY">Technology</option>
						   			<option value="FOOD">Food</option>
						   			<option value="SOMETHING">SomethingElse</option>
						   		</select>
								<input type="text" size="40" name="name" class="form-control"/>
								<button type="submit" class="btn">
									<i class="glyphicon glyphicon-search"></i> Search
								</button>
					
						</form:form>
					</div>
				</div>
			</div>
		