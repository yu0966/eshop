// ProductDAO.java
package com.example.dao;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import java.util.List;

public interface ProductDAO {
    // 查詢所有商品
    List<Product> getAllProducts();

    // 根據商品ID查詢商品
    Product getProductById(int productId);

    // 根據分類ID查詢商品
    List<Product> getProductsByCategory(Category category);
}
