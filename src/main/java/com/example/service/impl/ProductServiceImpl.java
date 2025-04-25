package com.example.service.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDao;

    @Autowired
    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProducts(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        return productDao.findAll(offset, pageSize);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId, int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        return productDao.findByCategory(categoryId, offset, pageSize);
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCount = productDao.count();
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    @Override
    public int getTotalPagesByCategory(int categoryId, int pageSize) {
        int totalCount = productDao.countByCategory(categoryId);
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    @Override
    public Product getProductById(int productId) {
        Product product = productDao.findById(productId);
        if (product == null) {
            throw new RuntimeException("找不到ID為 " + productId + " 的商品");
        }
        return product;
    }

    @Override
    public List<Category> getAllCategories() {
        return productDao.findAllCategories();
    }
}