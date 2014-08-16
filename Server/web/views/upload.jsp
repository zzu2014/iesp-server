<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
    <title>upload</title>
  </head>
  
  <body>
	 <form name="userForm" action="/upload2" method="post" enctype="multipart/form-data">
	     选择文件:<input type="file" name="Json.zip"/>
	     <input type="submit" value="备份" />
	 </form> 
  </body>
</html>
