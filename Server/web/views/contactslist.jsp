<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tangfei
  Date: 14-8-7
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title></title>
</head>
<body>
    <div align="center">
        <h1>Contacts List</h1>
        <table border="1">
            <th>No</th>
            <th>UserName</th>
            <th>phone_1</th>
            <th>phone_2</th>
            <th>phone_3</th>
            <th>phone_4</th>
            <th>email_1</th>
            <th>email_2</th>
            <th>address_1</th>
            <th>address_2</th>
            <th>birthday</th>
            <th>organization</th>

            <c:forEach var="contacts" items="${contacts}" varStatus="status">
                <tr>
                    <%--<td>${status.index + 1}</td>--%>
                    <td>${contacts.userid}</td>
                    <td>${contacts.name}</td>
                    <td>${contacts.phone_1}</td>
                    <td>${contacts.phone_2}</td>
                    <td>${contacts.phone_3}</td>
                    <td>${contacts.phone_4}</td>
                    <td>${contacts.email_1}</td>
                    <td>${contacts.email_2}</td>
                    <td>${contacts.address_1}</td>
                    <td>${contacts.address_2}</td>
                    <td>${contacts.birthday}</td>
                    <td>${contacts.organization}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
