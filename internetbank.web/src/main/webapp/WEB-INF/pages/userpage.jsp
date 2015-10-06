<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.WebResources"/>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<title><fmt:message key="main.title" /></title>
</head>
<body>
<p>Hello<p>
  <h3>
  <fmt:message key="main.title" />
 </h3>
 <hr />
 <sec:authorize access="isAuthenticated()">
  <p>
   <fmt:message key="main.greeting" /> <sec:authentication property="principal.username" />
  </p>
 </sec:authorize>
 
 <hr />
 <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button"><fmt:message key="main.href.logout" /></a>
 
</body>
</html>