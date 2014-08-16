<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tangfei
  Date: 14-8-6
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="../resources/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">

    </script>
    <title>Welcome to ContactBackup</title>
</head>
<body>
    <%--<div align="center">
        <h1>Contact List</h1>
        <table border="1">
            <th>No</th>
            <th>Username</th>
            <th>Email</th>

            <c:forEach var="user" items="${userList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>--%>

    <div align="center">
        <h1>Contact List</h1>
        <table border="1">
            <th>No</th>
            <th>ContactName</th>
            <th>Phone_1</th>
            <th>Phone_2</th>
            <th>Phone_3</th>
            <th>Phone_4</th>
            <th>Email_1</th>
            <th>Email_2</th>
            <th>Address_1</th>
            <th>Address_2</th>
            <th>Birthday</th>
            <th>Organization</th>
            <th>修改</th>
            <c:forEach var="contacts" items="${contacts}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
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
                    <td><input type="button" value="修改" onclick="alert(${contacts.contactid})" ></td>
                </tr>
            </c:forEach>
        </table>
        <br/>

        <input type="button" value="增加" onclick="window.location.href='/add'" />
        <input type="button" value="退出" />
    </div>

</body>
</html>
