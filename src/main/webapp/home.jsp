<%@ page import="com.example.pojo.entity.User" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>

<%
    // 獲取當前會話中的用戶物件
    User user = (User) session.getAttribute("user");

    // 如果用戶未登入，則重定向回登入頁面
    if (user == null || "".equals(user.getLoginId())) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首頁 - 商品展示</title>
    <script src="public/jquery-3.4.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #333;
            color: white;
            padding: 15px 20px;
            text-align: right;
        }
        .header a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
        }
        .header a:hover {
            text-decoration: underline;
        }
        .product-container {
            padding: 20px;
        }
        .product-table {
            width: 100%;
            border-collapse: collapse;
        }
        .product-table th, .product-table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .product-table th {
            background-color: #f2f2f2;
        }
        .product-table td button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .product-table td button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="header">
        <span>歡迎，${user.loginId}</span>
        <a href="logout.jsp">登出</a>
    </div>

    <div class="product-container">
        <h2>商品列表</h2>
        <table class="product-table">
            <tr>
                <th>商品名稱</th>
                <th>價格</th>
                <th>加入購物車</th>
            </tr>

            <!-- 假設有產品列表 -->
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>
                        <button onclick="addToCart(${product.id})">加入購物車</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <script type="text/javascript">
        function addToCart(productId) {
            // 加入購物車的邏輯
            alert("商品 " + productId + " 已加入購物車");
        }
    </script>

</body>
</html>
