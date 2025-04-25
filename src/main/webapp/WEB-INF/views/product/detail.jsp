<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>產品資訊</title>
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

.product-detail-container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

.product-image {
	max-width: 100%;
	height: auto;
	margin-bottom: 20px;
}

.action-buttons {
	margin-top: 30px;
	display: flex;
	justify-content: space-between;
}
</style>
</head>
<body>
	<div class="product-detail-container">
		<!-- 購物車圖示 -->
		<a href="<s:url action='view' namespace='/cart'/>" class="cart-icon">
			<i class="fas fa-shopping-cart"></i> <span class="cart-count"><s:property
					value="#session.cartItemCount" /></span>
		</a>

		<!-- 登出按鈕 -->
		<a href="<s:url action='logout' namespace='/'/>"
			class="btn btn-danger logout-btn">登出</a>

		<!-- 顯示登入使用者名稱 -->
		<p class="text-end text-muted">
			歡迎您，
			<s:property value="#session.user.name" />
		</p>

		<h1 class="mb-4">
			<s:property value="product.name" />
		</h1>

		<div class="row">
			<div class="col-md-6">
				<img src="<s:property value='product.imageUrl'/>"
					class="product-image" alt="<s:property value='product.name'/>">
			</div>
			<div class="col-md-6">
				<p class="lead">
					<s:property value="product.description" />
				</p>
				<p class="text-danger fw-bold fs-3">
					¥
					<s:property value="product.price" />
				</p>
				<p class="text-muted">
					庫存:
					<s:property value="product.stockQuantity" />
				</p>

				<div class="mt-4">
					<h4>商品詳情</h4>
					<p>
						分類:
						<s:property value="product.category.name" />
					</p>
					<p>
						上架時間:
						<s:date name="product.createdAt" format="yyyy-MM-dd" />
					</p>
					<p>
						最後更新:
						<s:date name="product.updatedAt" format="yyyy-MM-dd" />
					</p>
				</div>
			</div>
		</div>

		<div class="action-buttons">
			<a
				href="<s:url action='list' namespace='/product'>
                <s:param name='currentPage' value='currentPage'/>
            </s:url>"
				class="btn btn-secondary">返回</a> <a
				href="<s:url action='add' namespace='/cart'>
    <s:param name='productId' value='product.id'/>
    <s:param name='quantity' value='1'/>
    <s:param name='price' value='product.price'/>
</s:url>"
				class="btn btn-primary">加入購物車</a>
		</div>
	</div>
</body>
</html>