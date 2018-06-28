<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1"/>
<title>Register</title>
</head>
<body>
<h3>Register</h3>
        <form action="/arquillian-jpa-drone/register">
        	<label for="username">Username:</label>
        	<input type="text" name="username" id="reg_username" value="${username}"></input><br>
        	<label for="password">Password:</label>
        	 <input type="password" name="password" id="reg_password" value="${password}"></input><br><br>
        	  <input type="submit" id="Register" value="Register" />
        </form>
</body>
</html>