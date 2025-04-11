// ProductController.java
package com.example.controller;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 顯示所有商品列表
    @RequestMapping("/list")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list"; // 返回商品列表頁面
    }

    // 顯示某個商品的詳細頁面
    @RequestMapping("/detail/{id}")
    public String getProductDetail(@PathVariable("id") int productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product-detail"; // 返回商品詳細頁面
    }

    // 根據分類篩選商品
    @RequestMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        Category category = new Category();  // 獲取分類的物件，通常會從資料庫查詢
        category.setId(categoryId);  // 設定分類ID
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        return "product-list"; // 返回商品列表頁面
    }
}
