<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
com.example.pojo.entity.User user = (com.example.pojo.entity.User) session.getAttribute("user");
if (user != null && user.getLoginId() != null && !"".equals(user.getLoginId())) {
	response.sendRedirect("list.jsp");
	return;
}
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>用戶登入</title>
</head>
<body>

	<div class="login-container">
		<h2>用戶登入</h2>



		<!-- 錯誤訊息:帳號密碼錯誤\帳號不存在 -->
		<!-- 如果有錯誤，就執行裡面的內容 -->
		<s:if test="hasActionErrors()">
			<div style="color: red; text-align: left;">
				<!-- 顯示錯誤訊息（紅字） -->
				<s:actionerror />
			</div>
		</s:if>
		<!-- 顯示一般訊息（通常是成功訊息） -->
		<s:actionmessage />
		<!-- 使用者填完按鈕時，資料會送到 login 這個 action 去處理，用 POST 方式傳資料（比較安全） -->
		<s:form action="login" method="post" theme="simple" id="loginForm">
			<label>帳號：</label>
			<s:textfield name="loginId" required="true" />
			<br />
			<label>密碼：</label>
			<s:password name="password" required="true" />
			<br />
			<button type="submit">登入</button>
		</s:form>
		<!-- 超連結「註冊新帳號」，當使用者點擊它時，
            會跳轉到 /register/input.action 這個 URL，也就是負責註冊新帳號的頁面。 -->
		<a href="<s:url action='input' namespace='/register'/>">註冊新帳號</a>
	</div>

</body>
</html>