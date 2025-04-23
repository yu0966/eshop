package com.example.dao;

import com.example.pojo.entity.Product;
import java.util.List;

/**
 * 商品資料存取介面
 * 定義所有與商品資料庫操作相關的方法
 */
public interface ProductDAO {
    /**
     * 查詢商品列表（分頁）
     * @param offset 起始位置 (從0開始)
     * @param limit 每頁數量
     * @return 商品列表
     */
    List<Product> findAll(int offset, int limit);
    
    /**
     * 計算商品總數量
     * @return 商品總數
     */
    int count();
    
    /**
     * 根據商品ID查詢單一商品
     * @param id 商品ID
     * @return 商品物件，若找不到則返回null
     */
    Product findById(int id);
}