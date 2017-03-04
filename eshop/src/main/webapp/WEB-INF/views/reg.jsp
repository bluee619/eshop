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
				
		<title>Registration</title>
	
	</head>
	
	<body>	
		<jsp:include page="top.jsp"/>
		
		<div class="container-fluid text-center bground2">
			<div class="header">registration</div>
			<div class="myform">
				<form:form method="POST" action="" modelAttribute="client">
		    
		   		<div>
		    		<label class="fontedit3 sidebar2">Username:</label>
		    		<input class="" type="text" name="login"/>
		    	</div>
		    	<form:errors path="login" cssClass="error" element="div" />
		    
		    	<div>
		    		<label class="fontedit3 sidebar2">Password:</label>
		    		<input type="password" name="password"/>
		    	</div>
		    	<form:errors path="password" cssClass="error" element="div" />
			
			 	<div>
		    		<label class="fontedit3 sidebar2">Email:</label>
		    		<input type="email" name="email"/>
		    	</div>
		    	<form:errors path="email" cssClass="error" element="div" />
			
			 	<div>
		    		<label class="fontedit3 sidebar2">Firstname:</label>
		    		<input type="text" name="personalData.firstname"/>
		    	</div>
		    	<form:errors path="personalData.firstname" cssClass="error" element="div" />
			
			 	<div>
		    		<label class="fontedit3 sidebar2">Surname:</label>
		    		<input type="text" name="personalData.surname"/>
		    	</div>
		    	<form:errors path="personalData.surname" cssClass="error" element="div" />
			
				 <div>
		    		<label class="fontedit3 sidebar2">Phone Number:</label>
		    		<input type="text" name="personalData.phoneNumber"/>
		    	</div>
		    	<form:errors path="personalData.phoneNumber" cssClass="error" element="div" />
			
				 <div>
		    		<label class="fontedit3 sidebar2">Account Number:</label>
		    		<input type="text" name="personalData.accountNumber"/>
		    	</div>
		    	<form:errors path="personalData.accountNumber" cssClass="error" element="div" />
			
			
				<label for="addresid" class="fontedit3 sidebar2">Address</label>
				<div id="addresid">
					<div>
				    	<label class="fontedit3">Street:</label>
				    	<input type="text" name="personalData.address.street"/>
				    </div>
				    <form:errors path="personalData.address.street" cssClass="error" element="div" />
					
					<div>
				    	<label class="fontedit3">House number:</label>
				    	<input type="text" name="personalData.address.houseNumber"/>
				    </div>
				    <form:errors path="personalData.address.houseNumber" cssClass="error" element="div" />  
			    
			   	 	<div>
			    		<label class="fontedit3">City:</label>
			    		<input type="text" name="personalData.address.city"/>
			    	</div>
			    	<form:errors path="personalData.address.city" cssClass="error" element="div" />
				
					<div>
			    		<label class="fontedit3">Zip Code:</label>
			    		<input type="text" placeholder="eg. 33-100" name="personalData.address.postalCode"/>
			    	</div>
			    	<form:errors path="personalData.address.postalCode" cssClass="error" element="div" />
				
					<div>
			    		<label class="fontedit3">State:</label>
			    		<input type="text" name="personalData.address.state"/>
			    	</div>
			   	 	<form:errors path="personalData.address.state" cssClass="error" element="div" />
				
				</div>
			
			
		    	<div id="g-recaptcha"></div>
		    	<form:hidden path="recaptchaResponse"/>
		    	<script type="text/javascript">
		        var onloadCallback = function() {
		            grecaptcha.render('g-recaptcha', {
		                'sitekey' : '<c:out value="${recaptchaSiteKey}" />',
		                'callback' : function(response) {
		                    document.getElementById('recaptchaResponse').value = response;
		                },
		                'theme' : 'light'
		            });
		        }
		    	</script>
		    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
		    <form:errors  path="recaptchaResponse" cssClass="error"/>
		    <div>
		        <input class="sidebar2 btn btn-default" type="submit" value="Register"/>
		    </div>
			</form:form>
		</div>
	</div>
	
	<jsp:include page="bot.jsp"/>
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
		
	</body>
</html>