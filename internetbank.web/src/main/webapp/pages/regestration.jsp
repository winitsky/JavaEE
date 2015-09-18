<%@ include file="include.jsp" %>
<html>
<body>
<head>
    <title>MainPage</title>
</head>
<form name="loginForm" method="POST" action="ServletController">
    <input type="hidden" name="command" value="make_registration" />
    <br/>Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>Name:<br/>
    <input type="text" name="name" value=""/>
    <br/>Surname:<br/>
    <input type="text" name="surname" value=""/>
    <br/>Account:<br/>
    <input type="text" name="account" value=""/>
    <br/>Balance:<br/>
    <input type="text" name="balance" value=""/>
    <br/>Choose type of payments:<br/>
    <select name="paymentList">
  		<option value=1>Internal payments</option>
  		<option value=2>Inner payments</option>
  		<option value=3>All payments</option>
	</select>
    <br/>
    ${errorRegistration}
    <br/>
   <input type="submit" value=<fmt:message key="registration.submit.button"/>>
</form>
</body>
</html>