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
</style>
</head>
<body>
	<div class="container mt-4">
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
						<img src="<s:property value='imageUrl'/>" class="card-img-top"
							alt="<s:property value='name'/>">
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
							<a
								href="<s:url action='add' namespace='/cart'>
    <s:param name='productId' value='id'/>
    <s:param name='quantity' value='1'/>
    <s:param name='price' value='price'/>
</s:url>"
								class="btn btn-primary">加入購物車</a> <a
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
</body>
</html>