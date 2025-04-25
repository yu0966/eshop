package com.example.dao;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll(int offset, int limit);
    List<Product> findByCategory(int categoryId, int offset, int limit);
    int count();
    int countByCategory(int categoryId);
    Product findById(int id);
    List<Category> findAllCategories();
}