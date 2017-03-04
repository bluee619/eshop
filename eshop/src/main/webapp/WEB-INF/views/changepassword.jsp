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
					
		<div class="container-fluid text-center bground2">
			<div class="header">change password</div>
				<div class="myform">
					<form:form method="POST" action="" modelAttribute="password">
						<div>
			    			<label class="fontedit3">Old password:</label>
			    			<input class="" type="password" name="oldPassword"/>
			    		</div>
			    		<form:errors path="oldPassword" cssClass="error" element="div" />
		    			
		    			<div>
			    			<label class="fontedit3 sidebar2">New password:</label>
			    			<input class="" type="password" name="password"/>
			    		</div>
			    		<form:errors path="password" cssClass="error" element="div" />
			    		
			    		<div>
			    			<label class="fontedit3 sidebar2">Repeat new password:</label>
			    			<input class="" type="password" name="passwordRepeat"/>
			    		</div>
			    		<form:errors path="passwordRepeat" cssClass="error" element="div" />
					   		
			    		<% request.setAttribute("password.login", "${pageContext.request.userPrincipal.name}"); %>
		    
		    			<div>
		        			<input class="sidebar2 btn btn-default" type="submit" value="Change password"/>
		    			</div>
					</form:form>
				</div>
		</div>	
		
		<jsp:include page="bot.jsp"/>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>