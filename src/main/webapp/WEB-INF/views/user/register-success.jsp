<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>註冊成功</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f9f9f9;
            text-align: center;
        }
        .success-container {
            width: 400px;
            margin: auto;
            background: #fff;
            padding: 30px;
            border-radius: 6px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .messages {
            color: green;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>註冊成功</h2>
        <div class="messages">
            <s:actionmessage />
        </div>
        <p>您的帳號已成功註冊！</p>
        <a href="<s:url value='/login.jsp' />">返回首頁</a>
    </div>
</body>
</html>