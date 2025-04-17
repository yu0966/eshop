<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>註冊新帳號</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 40px;
	background-color: #f9f9f9;
}

.form-container {
	width: 400px;
	margin: auto;
	background: #fff;
	padding: 30px;
	border-radius: 6px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
}

.errors {
	color: red;
	margin-bottom: 15px;
}

a {
	display: block;
	margin-top: 20px;
	text-align: center;
}
</style>
</head>
<body>

	<div class="form-container">
		<h2>註冊新帳號</h2>

		<!-- 顯示錯誤訊息（如帳號已存在） -->
		<div class="errors">
			<s:actionerror />
			<s:fielderror />
		</div>

		<!-- 表單送出至 /register/register -->
		<s:form action="register" method="post">
			<s:textfield name="user.name" label="姓名" />
			<s:textfield name="user.loginId" label="使用者名稱" />
			<s:password name="user.password" label="密碼" />
			<s:textfield name="user.tel" label="電話" />
			<s:submit value="註冊" />
		</s:form>


		<a href="<s:url value='/login.jsp'/>">返回登入</a>
	</div>

</body>
</html>
