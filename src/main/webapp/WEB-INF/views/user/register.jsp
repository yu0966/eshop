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

.error-message {
    color: red;
    font-size: 12px;
    margin-top: -15px;
    margin-bottom: 10px;
    display: none; /* 初始隱藏所有錯誤訊息 */
}

.messages {
    color: green;
    margin-bottom: 15px;
    text-align: center;
}

a {
    display: block;
    margin-top: 20px;
    text-align: center;
}

.has-error {
    border: 1px solid red;
}

/* 表單樣式調整 */
.s-form-table {
    width: 100%;
    border-collapse: collapse;
}
.s-form-table td {
    padding: 5px;
}
.s-form-table .tdLabel {
    width: 30%;
    text-align: right;
    padding-right: 10px;
}
</style>
</head>
<body>

    <div class="form-container">
        <h2>註冊新帳號</h2>

        <!-- 只顯示成功訊息 -->
        <div class="messages">
            <s:actionmessage />
        </div>

        <s:form id="registerForm" cssClass="s-form" action="register" method="post" onsubmit="return validateForm()">
            <table class="s-form-table">
                <tr>
                    <td class="tdLabel"><label for="name">姓名:</label></td>
                    <td>
                        <s:textfield id="name" name="name" theme="simple" />
                        <div id="nameError" class="error-message"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tdLabel"><label for="tel">電話:</label></td>
                    <td>
                        <s:textfield id="tel" name="tel" theme="simple" />
                        <div id="telError" class="error-message"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tdLabel"><label for="loginId">帳號:</label></td>
                    <td>
                        <s:textfield id="loginId" name="loginId" theme="simple" />
                        <div id="loginIdError" class="error-message"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tdLabel"><label for="password">密碼:</label></td>
                    <td>
                        <s:password id="password" name="password" theme="simple" />
                        <div id="passwordError" class="error-message"></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <s:submit value="註冊" theme="simple" />
                    </td>
                </tr>
            </table>
        </s:form>

        <a href="<s:url value='/login.jsp' />">返回首頁</a>
    </div>

    <script>
    // 完全移除服務器返回的錯誤訊息顯示
    document.addEventListener('DOMContentLoaded', function() {
        // 清除所有錯誤訊息元素內容
        document.getElementById('nameError').textContent = '';
        document.getElementById('telError').textContent = '';
        document.getElementById('loginIdError').textContent = '';
        document.getElementById('passwordError').textContent = '';
    });

    // 表單驗證函數
    function validateForm() {
        let isValid = true;
        
        // 驗證姓名
        const name = document.getElementById('name').value.trim();
        const nameError = document.getElementById('nameError');
        if (name === '') {
            nameError.textContent = '姓名不能為空';
            nameError.style.display = 'block';
            document.getElementById('name').classList.add('has-error');
            isValid = false;
        } else {
            nameError.style.display = 'none';
            document.getElementById('name').classList.remove('has-error');
        }
        
        // 驗證電話
        const tel = document.getElementById('tel').value.trim();
        const telError = document.getElementById('telError');
        if (tel === '') {
            telError.textContent = '電話不能為空';
            telError.style.display = 'block';
            document.getElementById('tel').classList.add('has-error');
            isValid = false;
        } else {
            telError.style.display = 'none';
            document.getElementById('tel').classList.remove('has-error');
        }
        
        // 驗證帳號
        const loginId = document.getElementById('loginId').value.trim();
        const loginIdError = document.getElementById('loginIdError');
        if (loginId === '') {
            loginIdError.textContent = '使用者名稱不能為空';
            loginIdError.style.display = 'block';
            document.getElementById('loginId').classList.add('has-error');
            isValid = false;
        } else {
            loginIdError.style.display = 'none';
            document.getElementById('loginId').classList.remove('has-error');
        }
        
        // 驗證密碼
        const password = document.getElementById('password').value.trim();
        const passwordError = document.getElementById('passwordError');
        if (password === '') {
            passwordError.textContent = '密碼不能為空';
            passwordError.style.display = 'block';
            document.getElementById('password').classList.add('has-error');
            isValid = false;
        } else {
            passwordError.style.display = 'none';
            document.getElementById('password').classList.remove('has-error');
        }
        
        return isValid;
    }
    </script>
</body>
</html>