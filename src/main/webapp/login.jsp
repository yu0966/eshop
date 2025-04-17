<%@ page contentType="text/html;charset=utf-8" %>
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
    <script src="public/jquery-3.4.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            width: 320px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
        .form-group {
            margin: 10px 0;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>用戶登入</h2>

    <!-- 顯示錯誤訊息 -->
    <s:if test="hasActionErrors()">
        <div class="error-message">
            <s:actionerror />
        </div>
    </s:if>

    <!-- 使用 Struts2 的表單 -->
    <s:form action="login" method="post" theme="simple" id="loginForm">
        <div class="form-group">
            <label for="loginId">使用者名稱：</label>
            <s:textfield name="loginId" id="loginId" required="true" cssClass="form-control" />
        </div>
        <div class="form-group">
            <label for="password">密碼：</label>
            <s:password name="password" id="password" required="true" cssClass="form-control" />
        </div>
        <div class="form-group">
            <button type="submit">登入</button>
        </div>
    </s:form>

    <a href="/register">註冊新帳號</a>
</div>

<script>
    function validateAndSubmit() {
        let loginId = $("#loginId").val().trim();
        let password = $("#password").val().trim();

        if (!loginId) {
            alert("使用者名稱不能為空");
            return;
        }

        if (!password) {
            alert("密碼不能為空");
            return;
        }

        $("#loginForm").submit();
    }
</script>

</body>
</html>
