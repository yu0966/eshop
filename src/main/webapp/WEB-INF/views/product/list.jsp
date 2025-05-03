<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>商品列表</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    rel="stylesheet">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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

/* 新增圖片樣式 */
.card-img-top {
    height: 200px;
    object-fit: contain;
    background-color: #f8f9fa;
}

/* 圖片載入失敗時的替代樣式 */
.img-placeholder {
    background-color: #e9ecef;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #6c757d;
    font-size: 0.9rem;
}
</style>
</head>
<body>
    <div class="container mt-4">
       <!-- 購物車圖示 -->
<a href="<s:url action='view' namespace='/cart'/>" class="cart-icon">
    <i class="fas fa-shopping-cart"></i> 
    <span class="cart-count"><s:property value="#session.cartItemCount" /></span>
</a>

        <!-- 根目錄下執行登出操作 -->
        <a href="<s:url action='logout' namespace='/'/>"
            class="btn btn-danger logout-btn">登出</a>
        <!-- 顯示登入使用者名稱 -->
        <p class="text-end text-muted">
            歡迎您，
            <s:property value="#session.user.name" />
        </p>

        <h2 class="mb-4">商品列表</h2>

        <!-- 商品分類篩選表單 -->
        <form action="<s:url action='list' namespace='/product'/>"
            method="get" class="mb-4">
            <div class="row">
                <div class="col-md-4">
                    <label for="categoryId" class="form-label">商品分類</label> <select
                        name="categoryId" id="categoryId" class="form-select"
                        onchange="this.form.submit()">
                        <option value="">全部商品</option>
                        <s:iterator value="categories">
                            <option value="<s:property value='id'/>"
                                <s:if test="categoryId == id">selected</s:if>>
                                <s:property value="name" />
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <input type="hidden" name="currentPage" value="1">
        </form>

        <div class="row">
            <s:iterator value="products">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <!-- 修改後的圖片標籤 - 添加 contextPath 並處理可能為空的情況 -->
                        <s:if test="imageUrl != null && !imageUrl.isEmpty()">
                            <img src="${pageContext.request.contextPath}<s:property value='imageUrl'/>" 
                                 class="card-img-top" 
                                 alt="<s:property value='name'/>"
                                 onerror="this.onerror=null;this.className='card-img-top img-placeholder';this.innerHTML='圖片無法載入';">
                        </s:if>
                        <s:else>
                            <div class="card-img-top img-placeholder">無商品圖片</div>
                        </s:else>
                        <div class="card-body">
                            <h5 class="card-title">
                                <s:property value="name" />
                            </h5>
                            <p class="card-text">
                                <s:property value="description" />
                            </p>
                            <p class="text-danger fw-bold">
                                ¥
                                <s:property value="price" />
                            </p>
                            <p class="text-muted">
                                庫存:
                                <s:property value="stockQuantity" />
                            </p>
                            <!-- 商品列表中的加入購物車按鈕 -->
<button class="btn btn-primary add-to-cart-btn" 
    data-product-id="<s:property value='id'/>"
    data-price="<s:property value='price'/>">
    加入購物車
</button>
                            <a
                                href="<s:url action='detail' namespace='/product'>
                                    <s:param name='productId' value='id'/>
                                    <s:param name='currentPage' value='currentPage'/>
                                    <s:if test='categoryId != null'>
                                        <s:param name='categoryId' value='categoryId'/>
                                    </s:if>
                                </s:url>"
                                class="btn btn-info">產品資訊</a>
                        </div>
                    </div>
                </div>
            </s:iterator>
        </div>

        <!-- 分頁導航 -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <!-- 上一頁 -->
                <li class="page-item <s:if test="currentPage <= 1">disabled</s:if>">
                    <s:url var="prevUrl" action="list" namespace="/product">
                        <s:param name="currentPage" value="%{currentPage - 1}" />
                        <s:if test="categoryId != null">
                            <s:param name="categoryId" value="categoryId" />
                        </s:if>
                    </s:url> <a class="page-link" href="<s:property value='#prevUrl'/>">上一頁</a>
                </li>

                <!-- 下一頁 -->
                <li
                    class="page-item <s:if test="currentPage >= totalPages">disabled</s:if>">
                    <s:url var="nextUrl" action="list" namespace="/product">
                        <s:param name="currentPage" value="%{currentPage + 1}" />
                        <s:if test="categoryId != null">
                            <s:param name="categoryId" value="categoryId" />
                        </s:if>
                    </s:url> <a class="page-link" href="<s:property value='#nextUrl'/>">下一頁</a>
                </li>
            </ul>
        </nav>
    </div>

    <!-- 成功提示模態框 -->
<div class="modal fade" id="successModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">成功</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                商品已成功加入購物車！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">確定</button>
            </div>
        </div>
    </div>
</div>

    <!-- JavaScript 庫 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    
   <script>
$(document).ready(function() {
    // 加入購物車按鈕點擊事件
    $('.add-to-cart-btn').click(function() {
        var productId = $(this).data('product-id');
        var price = $(this).data('price');
        var $btn = $(this);
        
        $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> 處理中...');
        
        // 使用完整路徑
        var url = '${pageContext.request.contextPath}/cart/add.action';
        
        $.ajax({
            url: url,
            type: 'POST',
            data: {
                productId: productId,
                quantity: 1, // 明確設置數量
                price: price
            },
            dataType: 'json',
            success: function(response) {
                if (response.status === 'success') {
                    var successModal = new bootstrap.Modal(document.getElementById('successModal'));
                    successModal.show();
                    // 更新購物車數量
                    $('.cart-count').text(response.cartItemCount || 0);
                } else {
                    alert('錯誤: ' + (response.message || '未知錯誤'));
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX錯誤:", xhr.responseText);
                var errorMsg = '加入購物車失敗';
                try {
                    var jsonResponse = JSON.parse(xhr.responseText);
                    errorMsg = jsonResponse.message || errorMsg;
                } catch (e) {}
                alert(errorMsg);
            },
            complete: function() {
                $btn.prop('disabled', false).text('加入購物車');
            }
        });
    });
});
</script>
</body>
</html>