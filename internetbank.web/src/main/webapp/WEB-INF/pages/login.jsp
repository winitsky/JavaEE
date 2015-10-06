<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.WebResources"/>
 
<html>
<body>
<head>
    <title>MainPage</title>
</head>

<form:form  method="POST" modelAttribute="user">
 <!--     <input type="hidden" name="command" value="login" />-->
    Login:<br/>
    <form:input path="login" id="login"/>
    <br/>Password:<br/>
    <form:input path="password" id="password"/>
    <br/>
    ${error}
    <br/>
   <input type="submit" value=<fmt:message key="main.submit.button"/>>
</form:form>
	 <p><a href="<c:url value='/regestration' />">Add new user</a></p>

</body>
</html>