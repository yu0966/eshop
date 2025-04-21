/* 
 * package 是套件宣告，代表這個檔案是在 com.example.dao 這個套件（資料夾）裡面。
 * dao 是「Data Access Object」的縮寫，用來放負責跟資料庫互動的程式碼。*/
package com.example.dao;
/* 
 * 匯入 Product 類別，這樣才能在這個檔案裡使用它。
 * Product 是你自己定義的「商品資料模型類別」，裡面有商品名稱、價格、庫存等屬性。
 */
import com.example.pojo.entity.Product;
/* 
 *匯入 List，這是 Java 裡面一種「列表集合」，用來存放很多筆資料。
 *例如：多個商品就是 List<Product>。
 */
import java.util.List;
/* 
 *這裡定義了一個介面叫 ProductDAO。
 *interface 就像是一張合約或功能規格表，定義「有哪些方法一定要實作」。
 */
public interface ProductDAO {
	/* 
	 *定義一個方法 findAll，它的功能是「查詢多筆商品資料」，而且支援分頁（一次只查部分資料）。
	 *List<Product>：這個方法會回傳一堆商品 → 用列表裝起來
	 *offset：從第幾筆資料開始查（例如第 21 筆）
	 *limit：一次查幾筆（例如查 10 筆）
	 *比如：offset = 20, limit = 10 → 表示查「第 21 到 30 筆」
	 */
    List<Product> findAll(int offset, int limit);
    /* 
	 *定義一個方法 count，用來「計算商品的總筆數」
	 *int：回傳一個整數 → 例如總共有 57 筆商品
	 *常用來配合分頁功能，例如算總頁數。
	 */
    int count();
}