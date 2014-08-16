<%--
  Created by IntelliJ IDEA.
  User: tangfei
  Date: 14-8-7
  Time: 下午12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Register</title>
    <script type="text/javascript" src="../resources/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#submit").click(function() {
                var accountname = $("#accountname").val();
                var password = $("#password").val();
                var password2 = $("#password2").val();
                if(password != password2) {
                    alert("密码不一致！");
                    return;
                }
                var user = {accountname:accountname, password:password};
                $.ajax({
                    url:"/registerControl",
                    type:"post",
                    data:user,
                    success:function(data) {
                        alert(data.result);
                        if(data.result="Success") window.location.href="/login";
                    }
                });
            });
        });
    </script>
</head>
    <h1>Welcome to register page</h1>
<body>
    Pleause input Email:<input type="email" id="accountname"/><br/>
    Pleause input Password:<input type="password" id="password"/><br/>
    Pleause input Password again:<input type="password" id="password2"/><br/>
    <input type="button" value="submit" id="submit"/>
</body>
</html>
