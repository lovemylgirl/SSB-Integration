<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<!-- enctype="multipart/form-data" 如果是上传文件需要加上这种类型 -baidu -->
	<form action="/upload/uploadFile" method="post" enctype="multipart/form-data">
		选择文件 : <input type="file" name="file"> 
		<input type="submit" value="上传">
	</form>
	
	<!-- 下载 -->
	<a href="./down/download" >下载</a>  
</body>
</html>