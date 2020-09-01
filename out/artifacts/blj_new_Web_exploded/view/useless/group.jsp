<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/group/login2.do" method="post">
<label>组名称：</label>
<input type="text" id="txtGroupname" name="groupname" placeholder="请输入组名称" /><br/>
<label>组ID：</label>
<input type="text" id="txtGroupid" name="groupid" placeholder="请输入Id" /><br/>
<input type="submit" value="提交" />
<input type="reset" value="重置" />
</form>
</body>
</html>