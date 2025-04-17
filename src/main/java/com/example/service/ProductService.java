package com.example.service;

import com.example.pojo.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getProducts(int currentPage, int pageSize);
    int getTotalPages(int pageSize);
}
