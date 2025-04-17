package com.example.service.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
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
    public int getTotalPages(int pageSize) {
        int totalCount = productDao.count();
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
