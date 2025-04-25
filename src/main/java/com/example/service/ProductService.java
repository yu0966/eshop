package com.example.service;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import java.util.List;

public interface ProductService {
    List<Product> getProducts(int currentPage, int pageSize);
    List<Product> getProductsByCategory(int categoryId, int currentPage, int pageSize);
    int getTotalPages(int pageSize);
    int getTotalPagesByCategory(int categoryId, int pageSize);
    Product getProductById(int productId);
    List<Category> getAllCategories();
}