<%@ include file="./pages/include.jsp" %>

<html>
<body>
<head>
    <title>MainPage</title>
</head>
<form name="loginForm" method="POST" action="ServletController">
    <input type="hidden" name="command" value="login" />
    Login:<br/>
    <input type="text" name="email" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${error}
    <br/>
   <input type="submit" value=<fmt:message key="main.submit.button"/>>
</form>
</body>
</html>
