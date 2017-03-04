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
		#border{
			border-bottom: 1px solid;
			border-color: #fff;
		}
	</style>
	<body>
		<jsp:include page="top.jsp"/>
		<sec:authentication property="principal.username" var="username"/>			
		<div class="container-fluid text-center bground2">
			<div class="header">change address</div>
				<div class="myform">
					<form:form method="POST" action="/user/edit/personal/address/${username}" modelAttribute="address">
						<div>
					    	<label class="fontedit3">Street:</label>
					    	<input type="text" name="street"/>
					    </div>
					    <form:errors path="street" cssClass="error" element="div" />
						
						<div>
					    	<label class="fontedit3">House number:</label>
					    	<input type="text" name="houseNumber"/>
					    </div>
					    <form:errors path="houseNumber" cssClass="error" element="div" />  
				    
				   	 	<div>
				    		<label class="fontedit3">City:</label>
				    		<input type="text" name="city"/>
				    	</div>
				    	<form:errors path="city" cssClass="error" element="div" />
					
						<div>
				    		<label class="fontedit3">Zip Code:</label>
				    		<input type="text" placeholder="eg. 33-100" name="postalCode"/>
				    	</div>
				    	<form:errors path="postalCode" cssClass="error" element="div" />
					
						<div>
				    		<label class="fontedit3">State:</label>
				    		<input type="text" name="state"/>
				    	</div>
				   	 	<form:errors path="state" cssClass="error" element="div" />
					
		    
		    			<div>
		        			<input class="sidebar2 btn btn-default" type="submit" value="Change address"/>
		    			</div>
					</form:form>
				</div>
		</div>	
		
		<jsp:include page="bot.jsp"/>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>