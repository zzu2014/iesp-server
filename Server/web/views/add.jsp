<%--
  Created by IntelliJ IDEA.
  User: TangFei
  Date: 2014/8/14
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加联系人</title>
    <script src="../resources/js/jquery-1.11.1.min.js" ></script>
    <script type="text/javascript">
        $(function() {
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
                alert(birthday);
                var organization = $("#organization").val();
                var contacts = {name:name, phone_1:phone_1, phone_2:phone_2, phone_3:phone_3, phone_4:phone_4, email_1:email_1,
                    email_2:email_2, address_1:address_1, address_2:address_2, birthday:birthday, organization:organization};
                $.ajax({
                    url:"/addContact",
                    type:"post",
                    data:contacts,
                    success:function(data) {
                        alert(data.result);
                    }
                });
            });

            $("#reset").click(function() {
                $("#name").val("");
                $("#phone_1").val("");
                $("#phone_2").val("");
                $("#phone_3").val("");
                $("#phone_4").val("");
                $("#email_1").val("");
                $("#email_2").val("");
                $("#address_1").val("");
                $("#address_2").val("");
                $("#birthday").val("");
                $("#organization").val("");
            });
        });
    </script>
</head>
<body>
    <h1 align="center">增加联系人</h1>
    <div align="center">
        姓名<input type="text" id="name" /><br/>
        电话<input type="tel" id="phone_1" /><br/>
        电话<input type="tel" id="phone_2" /><br/>
        电话<input type="tel" id="phone_3" /><br/>
        电话<input type="tel" id="phone_4" /><br/>
        邮箱<input type="email" id="email_1" /><br/>
        邮箱<input type="email" id="email_2" /><br/>
        地址<input type="text" id="address_1" /><br/>
        地址<input type="text" id="address_2" /><br/>
        生日<input type="text" id="birthday" /><br/>
        组织<input type="text" id="organization" /><br/>
        <input type="button" value="添加" id="submit" />
        <input type="reset"  value="重置" id="reset"/>
    </div>
</body>
</html>
