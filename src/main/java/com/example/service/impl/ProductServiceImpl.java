// 宣告這支程式碼屬於 com.example.service.impl 這個套件
// impl = implementation = 實作，表示這裡放的是介面的實作程式碼
package com.example.service.impl;
//匯入 ProductDAO，因為這裡會使用 DAO 來取得商品資料
import com.example.dao.ProductDAO;
//匯入 Product 類別（商品資料）
import com.example.pojo.entity.Product;
//匯入 ProductService 介面，因為這個類別要實作它

import com.example.service.ProductService;
//匯入 Spring 的 @Autowired（自動注入物件）、@Service（讓 Spring 管理這個服務類別） 和 @Transactional（開啟資料庫交易功能）

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//匯入 Java 的 List，代表「商品清單」

import java.util.List;

//告訴 Spring：這是一個「服務層」的類別（Service Layer）
//告訴 Spring：這裡的方法都需要資料庫交易控制（避免一半成功、一半失敗）
@Service
@Transactional

//ProductServiceImpl 是類別名稱，意思是「ProductService 的實作類別」。
//implements ProductService：表示這個類別要「照著介面的規定」來實作方法。
public class ProductServiceImpl implements ProductService {
	/*
	 *  productDao 就是你用來「跟資料庫打交道」的工具（DAO = Data Access Object）。
	 *  final 表示這個變數一旦指定就不能改（安全、穩定）。
	 */
    private final ProductDAO productDao;
    /*
	 * 這是建構子（constructor）：在建立這個類別物件時，會傳入 ProductDAO。
	 * @Autowired：請 Spring 自動幫我把 DAO 注入進來（不用自己 new，很方便）
	 * 把注入進來的 productDao 存進剛剛宣告的成員變數裡。
	 * 小提醒：這是建構式注入（constructor injection），比起用欄位注入更推薦，因為容易測試、比較安全。
	 */
    @Autowired
    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }
    /*
   	 * 舉例說明：
   	 * 如果 currentPage = 2，pageSize = 10，
   	 * 就是想看第 2 頁的資料（每頁 10 筆）
   	 * 所以 offset 就是從「第 10 筆開始」拿（第 11~20 筆）
   	 * 這樣做就是分頁查詢的原理。
   	 */
    @Override
    public List<Product> getProducts(int currentPage, int pageSize) {
    	// 算出要從第幾筆資料開始抓
        int offset = (currentPage - 1) * pageSize;
     // 呼叫 DAO 去資料庫查指定區間的商品
        return productDao.findAll(offset, pageSize);
    }
    /*
   	 * productDao.count()：回傳總共幾筆商品資料（從資料庫算的）。
   	 * Math.ceil(...)：用來無條件進位，例如：
   	 * 有 27 筆資料，每頁 10 筆，要顯示 3 頁（因為 2.7 頁要進位成 3）
   	 * 這樣可以正確畫出「第幾頁按鈕」
   	 */
    @Override
    public int getTotalPages(int pageSize) {
    	// 找出總共有幾筆商品資料
        int totalCount = productDao.count();
     // 算出總頁數（無條件進位，例如：23 筆 / 每頁 10 筆 = 3 頁）
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}