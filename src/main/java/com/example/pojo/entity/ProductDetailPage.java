package com.example.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品詳細頁面實體類別，用來封裝商品的詳細資料。
 */
public class ProductDetailPage {

    // 商品的唯一識別碼
    private int id;

    // 商品名稱
    private String name;

    // 商品描述
    private String description;

    // 商品價格
    private BigDecimal price;

    // 商品所屬分類
    private Category category;

    // 商品庫存數量
    private int stockQuantity;

    // 商品圖片URL
    private String imageUrl;

    // 商品創建時間
    private Date createdAt;

    // 商品更新時間
    private Date updatedAt;

    // 取得商品的 ID
    public int getId() {
        return id;
    }

    // 設定商品的 ID
    public void setId(int id) {
        this.id = id;
    }

    // 取得商品名稱
    public String getName() {
        return name;
    }

    // 設定商品名稱
    public void setName(String name) {
        this.name = name;
    }

    // 取得商品描述
    public String getDescription() {
        return description;
    }

    // 設定商品描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 取得商品價格
    public BigDecimal getPrice() {
        return price;
    }

    // 設定商品價格
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // 取得商品所屬的分類
    public Category getCategory() {
        return category;
    }

    // 設定商品所屬的分類
    public void setCategory(Category category) {
        this.category = category;
    }

    // 取得商品庫存數量
    public int getStockQuantity() {
        return stockQuantity;
    }

    // 設定商品庫存數量
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // 取得商品圖片URL
    public String getImageUrl() {
        return imageUrl;
    }

    // 設定商品圖片URL
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 取得商品創建時間
    public Date getCreatedAt() {
        return createdAt;
    }

    // 設定商品創建時間
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // 取得商品更新時間
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // 設定商品更新時間
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}