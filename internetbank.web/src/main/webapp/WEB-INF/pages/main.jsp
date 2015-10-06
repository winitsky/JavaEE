<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.WebResources"/>
<body>
    <h3>Welcome</h3>
    <p>${currentUser.name}  ${currentUser.surname}</p>

       <table border="1" style="width:100%">
     <thead>
            <th><fmt:message key="list.accounts.account"/></th>
            <th><fmt:message key="list.accounts.balance"/></th>
        </thead>

        
            <tr>
                <td>${account.account}</td>
                <td>${account.balance}</td>
            </tr>
      

    </table>
    <hr/>
    <table border="1" style="width:100%">
        <thead>
            <th><fmt:message key="list.archive.nameOpertion"/></th>
            <th><fmt:message key="list.archive.sum"/></th>
            <th><fmt:message key="list.archive.date"/></th>
        </thead>

        <c:forEach items="${archives}" var="archive">
            <tr>
                <td>${archive.nameOperaion}</td>
                <td>${archive.sum}</td>
                <td>${archive.date}</td>
            </tr>
        </c:forEach>

    </table>
    <p><a href="<c:url value='/new' />">New payment</a></p>
    <hr/>
    <p><a href="<c:url value='/userlogin' />">Logout</a></p>
</body></html>