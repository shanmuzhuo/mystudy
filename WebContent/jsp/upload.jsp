<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	上传文件
	<form action="/mystudy/upload/byCommons" method="post"
		enctype="multipart/form-data">
		<input type="file" name="myfile" /><br> 
		<input type="text" name="title" /><br> 
		<input type="submit" value="提交" />
	</form>
</body>
</html>