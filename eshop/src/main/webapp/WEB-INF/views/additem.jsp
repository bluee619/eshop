<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>
		<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css"></link>
		<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css"></link>
		<link href="https://fonts.googleapis.com/css?family=Didot" rel="stylesheet" type="text/css"></link>
		<link href="<c:url value="/css/main.css" />" rel="stylesheet">
				
		<title>Sell Item</title>
	</head>
	<body>	
		<jsp:include page="top.jsp"/>
		
		<div class="container-fluid bground2 text-center">
			<div class="header">sell item</div>
			<div class="myform">
			<form:form method="POST" action="" modelAttribute="item" encType="multipart/form-data">
			    
			    <div>
			    	<label for="nameid" class="fontedit1">Title:</label>
			    	<input class="" type="text" name="name" id="nameid"/>
			    </div>
			    <form:errors class="sidebar2" path="name" cssClass="error" element="div" />
			   	
			   	<div class="form-group">
			   		<label class="fontedit1 sidebar2" for="categoryid"> Choose category:</label>
			   		<select class="form-control" name="category" id="categoryid">
			   			<option selected disabled>Category</option>
			   			<option>Moto</option>
			   			<option>Technology</option>
			   			<option>Food</option>
			   			<option>SomethingElse</option>
			   		</select>
			   	</div>
			   	<form:errors class="sidebar2" path="category" cssClass="error" element="div" />
			   	
			    <div class="form-group">
			    	<label class="fontedit1 sidebar2" for="descriptionid" >Description:</label>
			    	<textarea class="form-control" cols="40" rows="5" name="description" id="descriptionid"></textarea>
			    </div>
			    <form:errors class="sidebar2" path="description" cssClass="error" element="div" />
				
				 <div class="form-group myform">
			    	<label class="fontedit1 sidebar2" for="imageid">Upload Image:</label>
			    	<input  type="file" name="file" id="imageid"/>
			    </div>
			    <form:errors class="sidebar2" path="file" cssClass="error" element="div" />
				
				 <div>
			    	<label class="fontedit1" for="priceid">Price:</label>
			    	<input class="sidebar2" type="number" step="0.01" name="price" id="priceid"/>
			    </div>
			    <form:errors class="sidebar2" path="price" cssClass="error" element="div" />
				
				<div>
					<label class="sidebar2 fontedit1 form2" for="shippingid">Shipping</label>
					<div class="input-form" id=shippingid>
						<div class="form2">
							<label for="shipcostid">Cost:</label>
							<input class="" type="number" step="0.01" name="shipment.shippingCost" id="shipcostid"/>
							<form:errors class="sidebar2" path="shipment.shippingCost"/>
						</div>
						<div class="form2">
							<label class="" for="timeid">Time:</label>
							<input class="sidebar2" type="number" step="1" name="shipment.approxTime" id="timeid" placeholder="Days"/>
							<form:errors class="sidebar2" path="shipment.approxTime"/>		
						</div>			
					</div>	
				</div>
			   
			    <input class="btn btn-default sidebar2" type="submit" value="Add Item"/>
			</form:form>
			
			</div>
		</div>
		
		<jsp:include page="bot.jsp"/>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>	