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
    <br/>Login:<br/>
    <form:input path="login" id="login"/>
    <form:errors path="login" cssClass="error"/>
    <br/>Password:<br/>
    <form:input path="password" id="password"/>
    <form:errors path="password" cssClass="error"/>
    <br/>Name:<br/>
    <form:input path="name" id="name"/>
    <form:errors path="name" cssClass="error"/>
    <br/>Surname:<br/>
    <form:input path="surname" id="surname"/>
    <form:errors path="surname" cssClass="error"/>
    <br/>Account:<br/>
    <form:input path="account" id="account"/>
    <form:errors path="account" cssClass="error"/>
    <br/>Balance:<br/>
    <form:input path="balance" id="balance"/>
    <form:errors path="balance" cssClass="error"/>
    <br/>Choose type of payments:<br/>
    <form:select path="role" >
    	<form:option id="1" value="1">Internal payments</form:option>
    	<form:option id="2" value="2">Inner payments</form:option>
    	<form:option id="3" value="3">All payments</form:option>
   	</form:select>
    <br/>
    ${errorRegistration}
    <br/>
   <input type="submit" value=<fmt:message key="registration.submit.button"/>>
</form:form>
</body>
</html>