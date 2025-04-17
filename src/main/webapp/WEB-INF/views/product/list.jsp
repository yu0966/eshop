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
<style>
.logout-btn {
	position: absolute;
	top: 10px;
	right: 10px;
	z-index: 1000;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<!-- 登出按鈕 -->
		<a href="<s:url action='logout'/>" class="btn btn-danger logout-btn">登出</a>
		<!-- 顯示登入使用者名稱 -->
		<%
		com.example.pojo.entity.User user = (com.example.pojo.entity.User) session.getAttribute("user");
		%>
		<p class="text-end text-muted">
			歡迎您，<%=user != null ? user.getName() : "訪客"%></p>

		<h2 class="mb-4">商品列表</h2>

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
							<a href="#" class="btn btn-primary">加入購物車</a>
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
					<s:url var="prevUrl" action="list">
						<s:param name="currentPage" value="%{currentPage - 1}" />
					</s:url> <a class="page-link" href="<s:property value='#prevUrl'/>">上一頁</a>
				</li>

				<!-- 下一頁 -->
				<li
					class="page-item <s:if test="currentPage >= totalPages">disabled</s:if>">
					<s:url var="nextUrl" action="list">
						<s:param name="currentPage" value="%{currentPage + 1}" />
					</s:url> <a class="page-link" href="<s:property value='#nextUrl'/>">下一頁</a>
				</li>
			</ul>
		</nav>
	</div>
</body>
</html>
