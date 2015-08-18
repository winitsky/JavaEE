<%@ include file="include.jsp"%>
<body>
    <h3>Welcome</h3>
    <p>${username}  ${surname}</p>
    <p>${account}  ${balance}</p>
     <table border="1" style="width:100%">
        <thead>
            <th><fmt:message key="list.accounts.account"/></th>
            <th><fmt:message key="list.accounts.balance"/></th>
        </thead>

        <c:forEach items="${accounts}" var="account">
            <tr>
                <td>${account.account}</td>
                <td>${account.balance}</td>
            </tr>
        </c:forEach>

    </table>
    <hr/>
    <table border="1" style="width:100%">
        <thead>
            <th><fmt:message key="list.archive.nameOpertion"/></th>
            <th><fmt:message key="list.archive.sum"/></th>
            <th><fmt:message key="list.archive.date"/></th>
        </thead>

        <c:forEach items="${archive}" var="record">
            <tr>
                <td>${record.nameOperaion}</td>
                <td>${record.sum}</td>
                <td>${record.date}</td>
            </tr>
        </c:forEach>

    </table>
    <p><a href="ServletController?command=payment">New payment</a></p>
    <hr/>
    <p><a href="ServletController?command=logout">Logout</a></p>
</body></html>