<%--
  Created by IntelliJ IDEA.
  User: tangfei
  Date: 14-8-8
  Time: 上午10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../resources/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#login").click(function() {
                var accountname = $("#accountname").val();
                var password = $("#password").val();
                var account = {accountname:accountname, password:password};
                $.ajax({
                    url:"/loginControl",
                    type:"post",
                    data:account,
                    success:function(data) {
                        if(data.result == "Fail")
                            alert(data.result);
                    }
                });
            });

            $("#register").click(function() {
               window.location.href="/register";
            });
        });
    </script>
    <title>Login</title>
</head>
<body>
    <h1>欢迎来到登陆页面</h1>
    Email:<input type="email" id="accountname"><br/>
    Password:<input type="password" id="password"><br/>
    <input type="button" id="login" value="login">
    <input type="button" id="register" value="register">
</body>
</html>
