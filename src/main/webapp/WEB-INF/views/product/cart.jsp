<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>購物車</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .logout-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            z-index: 1000;
        }
        .cart-icon {
            position: absolute;
            top: 10px;
            right: 100px;
            z-index: 1000;
            font-size: 1.5rem;
            color: #333;
        }
        .cart-count {
            position: absolute;
            top: -5px;
            right: -10px;
            background-color: red;
            color: white;
            border-radius: 50%;
            padding: 0 5px;
            font-size: 0.8rem;
        }
        .cart-container {
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
        }
        .cart-actions {
            margin-top: 30px;
            display: flex;
            justify-content: space-between;
        }
        .quantity-input {
            width: 60px;
            text-align: center;
        }
        .product-name-cell {
            max-width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="cart-container">
        <!-- 購物車圖示 -->
        <a href="<s:url action='view' namespace='/cart'/>" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
            <span class="cart-count"><s:property value="#session.cartItemCount"/></span>
        </a>
        
        <!-- 登出按鈕 -->
        <a href="<s:url action='logout' namespace='/'/>" class="btn btn-danger logout-btn">登出</a>
        
        <!-- 顯示登入使用者名稱 -->
        <p class="text-end text-muted">
            歡迎您，<s:property value="#session.user.name"/>
        </p>

        <h1 class="mb-4">購物車</h1>
        
        <s:if test="cart == null || cart.cartItems == null || cart.cartItems.empty">
            <div class="alert alert-info">
                您的購物車是空的
            </div>
        </s:if>
        <s:else>
            <table class="table">
                <thead>
                    <tr>
                        <th>商品</th>
                        <th>單價</th>
                        <th>數量</th>
                        <th>小計</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="cart.cartItems">
                        <tr>
                            <td class="product-name-cell">
                                <!-- 顯示商品ID和名稱（需要確保CartAction中已加載商品信息） -->
                                <s:if test="product != null">
                                    <s:property value="product.name"/>
                                </s:if>
                                <s:else>
                                    商品ID: <s:property value="productId"/>
                                </s:else>
                            </td>
                            <td>¥ <s:property value="price"/></td>
                            <td>
                                <form action="<s:url action='update' namespace='/cart'/>" method="post" style="display: inline;">
                                    <input type="hidden" name="productId" value="<s:property value='productId'/>">
                                    <input type="number" name="quantity" value="<s:property value='quantity'/>" min="1" class="form-control quantity-input">
                                    <button type="submit" class="btn btn-sm btn-outline-secondary">更新</button>
                                </form>
                            </td>
                            <td>¥ <s:property value="totalPrice"/></td>
                            <td>
                                <a href="<s:url action='remove' namespace='/cart'>
                                    <s:param name='productId' value='productId'/>
                                </s:url>" class="btn btn-sm btn-danger">移除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <td colspan="3" class="text-end fw-bold">總計:</td>
                        <td class="fw-bold">¥ 
                            <s:set var="total" value="0"/>
                            <s:iterator value="cart.cartItems">
                                <s:set var="total" value="#total + totalPrice"/>
                            </s:iterator>
                            <s:property value="#total"/>
                        </td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </s:else>
        
        <div class="cart-actions">
            <a href="<s:url action='list' namespace='/product'/>" class="btn btn-secondary">繼續選購</a>
            <s:if test="cart != null && cart.cartItems != null && !cart.cartItems.empty">
                <a href="#" class="btn btn-primary">結帳</a>
            </s:if>
        </div>
    </div>
</body>
</html>