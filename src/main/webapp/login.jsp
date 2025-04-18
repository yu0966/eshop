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

    <!-- ✅ 顯示註冊成功訊息 -->
    <s:if test="hasActionMessages()">
        <div style="color: green; text-align: center; margin-bottom: 10px;">
            <s:actionmessage />
        </div>
    </s:if>

    <!-- 錯誤訊息 -->
    <s:if test="hasActionErrors()">
        <div style="color: red; text-align: left;">
            <s:actionerror />
        </div>
    </s:if>

    <!-- 登入表單 -->
    <s:form action="login" method="post" theme="simple" id="loginForm">
        <label>帳號：</label>
        <s:textfield name="loginId" required="true" /><br />
        <label>密碼：</label>
        <s:password name="password" required="true" /><br />
        <button type="submit">登入</button>
    </s:form>

    <a href="<s:url action='input' namespace='/register'/>">註冊新帳號</a>
</div>

</body>
</html>
