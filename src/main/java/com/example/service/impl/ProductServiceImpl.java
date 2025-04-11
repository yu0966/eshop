// ProductServiceImpl.java
package com.example.service.impl;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;
import com.example.pojo.entity.Category;
import com.example.dao.ProductDAO;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productDAO.getProductsByCategory(category);
    }
}
