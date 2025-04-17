package com.example.dao;

import com.example.pojo.entity.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll(int offset, int limit);
    int count();
}
