// 宣告這支程式碼屬於 com.example.service 這個套件
package com.example.service;
//匯入 Product 類別，因為等一下會用到它（商品資料）

import com.example.pojo.entity.Product;
//匯入 Java 的 List，代表「商品清單」

import java.util.List;
//宣告一個 ProductService 介面，專門負責「商品相關的服務」
//interface 就像是「合約」、「說明書」，別人只要實作這個介面，就要依照裡面的規定來寫方法。
public interface ProductService {
	 /*
     * 取得商品清單（支援分頁）
     * 意思是：只抓出一部分商品，而不是全部一次抓出來（減少資料量，速度更快）
     * 參數：
     *   currentPage：目前是第幾頁（例如：第 1 頁、第 2 頁）
     *   pageSize：一頁要顯示幾筆商品（例如：每頁顯示 10 筆）
     * 回傳：
     *   List<Product>：商品的清單（可能是 10 筆、20 筆等，看 pageSize 而定）
     */
    List<Product> getProducts(int currentPage, int pageSize);
    /*
     * 計算總共會有幾頁（根據資料總筆數和每頁大小）
     * 參數：
     *   pageSize：一頁幾筆商品（例如：每頁 10 筆）
     * 回傳：
     *   int：總頁數（例如：總共 45 筆資料、每頁 10 筆，那總頁數就是 5）
     */
    int getTotalPages(int pageSize);
}