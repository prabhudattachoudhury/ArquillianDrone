<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h3>Login</h3>
<form action="/arquillian-jpa-drone/login">
        	<label for="username">Username:</label>
        	<input type="text" name="username" id="log_username" ></input><br>
        	<label for="password">Password:</label>
        	 <input type="password" name="password" id="log_password" ></input><br><br>
        	  <input type="submit" id="Login" value="Login"/>
        </form>
</body>
</html>