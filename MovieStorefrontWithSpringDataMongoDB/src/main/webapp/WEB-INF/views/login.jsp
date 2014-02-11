<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/styles/movies.css"/>"
	type="text/css" />
<title>Login</title>
</head>

<body>
	<div id="main_wrapper">
		<h1>Login To Movie Reservation</h1>
		<c:url value="/j_spring_security_check" var="loginUrl"/>
<form:form name="f" action="${loginUrl}" method="post">       
    <c:if test="${param.error != null}">        
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">       
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>	
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>	
    </p>
    
    <button type="submit" class="btn">Log in</button>
</form:form>

<!-- 
		<c:if test="${!empty param.login_error}">
			<h2>
				<font color="red"> <spring:message code="login.invalid" /> </font>
			</h2>
		</c:if>
		<form action="<c:url value='/login'/>" method="post">
			<table>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
		 -->
	</div>
</body>
</html>
