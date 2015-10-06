<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.WebResources"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
</head>
<body>
<p>User</p>
	<p>${currentUser.name}  ${currentUser.surname}</p>
	<h3>Payment</h3>
	<form:form  method="POST" modelAttribute="archiveNew">
		<input type="hidden" name="command" value="MAKE_PAYMENT">
		<table>
			<tr>
				<td><fmt:message key="list.payment.operation" /></td>
				<td><form:select path="operationID" size="1" >
							<c:forEach items="${operations}" var="operation">
							<!--  <option value="${operation.id}">${operation.name}</option>-->
							<form:option id="${operation.id}" value="${operation.id}">${operation.name}</form:option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr>
				<td><fmt:message key="list.payment.sum" /></td>
				<td><form:input path="sum" id="sum"/></td>
				 <form:errors path="sum" cssClass="error"/>
			</tr>

		</table>
		<p>${errorpayment}</p>
		<input type="submit" value="Payment" >
		
	</form:form>
	
</body>
</html>