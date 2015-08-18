<%@ include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Payment</title>
</head>
<body>
	
	<h3>Payment</h3>
	<form action="ServletController" method="post">
		<input type="hidden" name="command" value="MAKE_PAYMENT">
		<table>
			<tr>
				<td><fmt:message key="list.payment.operation" /></td>
				<td><select size="1" name="paymentList">
						<option value=0>Choose payment</option>
						<c:forEach items="${operations}" var="payment">
							<option value="${payment.id}">${payment.name}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td><fmt:message key="list.payment.sum" /></td>
				<td><input type="text" name="sum" ></td>

			</tr>

		</table>
		<p>${errorpayment}</p>
		<input type="submit" value="Payment" >
		
	</form>
	
</body>
</html>