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
        /*$(function() {
            $("#login").click(function() {
                var accountname = $("#accountname").val();
                var password = $("#password").val();
                var account = {accountname:accountname, password:password};
                $.ajax({
                    url:"/loginControl",
                    type:"post",
                    data:account
                    success:function(data) {
                        alert(data.result);
                        if(data.result="Success") window.location.href="/home";
                    }
                });
            });

            $("#register").click(function() {
               window.location.href="/register";
            });
        });*/
    </script>
    <title>Login</title>
</head>
<body>
    <h1>欢迎来到登陆页面</h1>
    <form action="/loginControl" method="post">
        Email:<input type="email" id="accountname" name="accountname"/><br/>
        Password:<input type="password" id="password" name="password"/><br/>
        <input type="submit" id="login" value="login"/>
        <a href="/register">
            <input type="button" value="register"/>
        </a>
    </form>
</body>
</html>
