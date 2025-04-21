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
    <!-- 表單會送到 login.action，用 POST 方式 -->
    <s:form action="login" method="post" theme="simple" id="loginForm">
        <label>帳號：</label>
            <!-- 使用者輸入帳號（對應 Java 裡的 loginId 屬性） -->
        <s:textfield name="loginId" required="true" /><br />
        <label>密碼：</label>
            <!-- 密碼欄位（對應 password 屬性），瀏覽器層級驗證，不填會提示 -->
        <s:password name="password" required="true" /><br />
        <button type="submit">登入</button>
    </s:form>
            <!-- 連結，點下去可以到「註冊頁」，到設定檔input（像是 /register/input.action） -->
    <a href="<s:url action='input' namespace='/register'/>">註冊新帳號</a>
</div>

</body>
</html>