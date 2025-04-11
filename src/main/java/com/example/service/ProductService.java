// ProductService.java
package com.example.service;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import java.util.List;

public interface ProductService {
    // 查詢所有商品
    List<Product> getAllProducts();

    // 根據商品ID查詢商品
    Product getProductById(int productId);

    // 根據分類查詢商品
    List<Product> getProductsByCategory(Category category);
}
