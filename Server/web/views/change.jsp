<%--
  Created by IntelliJ IDEA.
  User: TangFei
  Date: 2014/8/16
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改联系人</title>
    <script type="text/javascript" src="../resources/js/jquery-1.11.1.min.js"></script>
    <script>
        $(function(){
           $("#submit").click(function() {
               var name = $("#name").val();
               var phone_1 = $("#phone_1").val();
               var phone_2 = $("#phone_2").val();
               var phone_3 = $("#phone_3").val();
               var phone_4 = $("#phone_4").val();
               var email_1 = $("#email_1").val();
               var email_2 = $("#email_2").val();
               var address_1 = $("#address_1").val();
               var address_2 = $("#address_2").val();
               var birthday = $("#birthday").val();
               var organization = $("#organization").val();
               var contacts = {name:name, phone_1:phone_1, phone_2:phone_2, phone_3:phone_3, phone_4:phone_4,
                    email_1:email_1, email_2:email_2, address_1:address_1, address_2:address_2, birthday:birthday,
                    organization:organization};
               alert("send data");
               $.ajax({
                   url:"/chgContact",
                   type:"post",
                   data:contacts,
                   success:function(data) {
                       alert(data.result);
                   }
               });
           });
        });
    </script>
</head>
<body>
    <div align="center">
        <p>修改联系人</p>
        <table border="1">
            <tr>
                <td>姓名:</td>
                <td><input type="text" id="name" value="${contacts.name}" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" id="phone_1" value="${contacts.phone_1}" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" id="phone_2" value="${contacts.phone_2}" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" id="phone_3" value="${contacts.phone_3}" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" id="phone_4" value="${contacts.phone_4}" /></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" id="email_1" value="${contacts.email_1}" /></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" id="email_2" value="${contacts.email_2}" /></td>
            </tr>
            <tr>
                <td>地址:</td>
                <td><input type="text" id="address_1" value="${contacts.address_1}" /></td>
            </tr>
            <tr>
                <td>地址:</td>
                <td><input type="text" id="address_2" value="${contacts.address_2}" /></td>
            </tr>
            <tr>
                <td>生日:</td>
                <td><input type="text" id="birthday" value="${contacts.birthday}" /></td>
            </tr>
            <tr>
                <td>组织:</td>
                <td><input type="text" id="organization" value="${contacts.organization}" /></td>
            </tr>
        </table>
        <input type="button" id="submit" value="确定" />
        <input type="button" value="返回" onclick="history.go(-1)"  />
    </div>
</body>
</html>
