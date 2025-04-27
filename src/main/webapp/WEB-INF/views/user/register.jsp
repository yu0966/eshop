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

.messages {
	color: green;
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

		<div class="errors">
			<s:actionerror />
			<!-- 顯示「整體錯誤」 -->
			<s:fielderror />
			<!-- 顯示「欄位錯誤」（像帳號空白） -->
		</div>

		<!-- 顯示成功註冊訊息 -->
		<div class="messages">
			<s:actionmessage />
		</div>
		<!-- 建一個表單（form）。
當使用者填完按「送出」，就會把資料送到 register 這個 action。
method="post" 是說用 POST 的方式送資料（比較安全，不會把資料直接顯示在網址上）。 -->
		<s:form action="register" method="post">
			<s:textfield name="user.name" label="姓名" />
			<s:textfield name="user.tel" label="電話" />
			<s:textfield name="user.loginId" label="帳號" />
			<s:password name="user.password" label="密碼" />
			<s:submit value="註冊" />
		</s:form>

		<a href="<s:url value='/login.jsp' />">返回首頁</a>

	</div>

</body>
</html>