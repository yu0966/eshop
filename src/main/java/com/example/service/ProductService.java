package com.example.service;

import com.example.pojo.entity.Product;
import java.util.List;

public interface ProductService {
    /**
     * 取得商品清單（支援分頁）
     * @param currentPage 目前頁碼
     * @param pageSize 每頁顯示數量
     * @return 商品清單
     */
    List<Product> getProducts(int currentPage, int pageSize);
   
    /**
     * 取得總頁數
     * @param pageSize 每頁顯示數量
     * @return 總頁數
     */
    int getTotalPages(int pageSize);
    
    /**
     * 根據商品ID取得單一商品詳細資訊
     * @param productId 商品ID
     * @return 商品物件，若找不到則返回null
     */
    Product getProductById(int productId);
}