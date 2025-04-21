//這是「套件宣告」，意思是這個 Product 類別被放在 com.example.pojo.entity 這個分類（資料夾）底下。
package com.example.pojo.entity;
/*
 * 這是「匯入」功能，把 Java 標準庫中的 BigDecimal（高精度小數）和 Date（日期時間）引進來使用。
 * 我們需要 BigDecimal 來處理價格（避免用 float/double 的誤差），Date 則用來記錄建立時間與更新時間。
 */
import java.math.BigDecimal;
import java.util.Date;

/*
 * 每一行都是「屬性」（也叫欄位）
 * private：這些屬性是「私有的」，只能透過 getXXX() 和 setXXX() 方法來讀取或修改（符合 Java 的封裝原則）。
 */
public class Product {

    private int id;

    private String name;

    private String description;

    private BigDecimal price;

    private Category category;

    private int stockQuantity;

    private String imageUrl;

    private Date createdAt;

    private Date updatedAt;

    /*
     * getId()：這個方法會回傳目前這個商品的 id。
     * setId(int id)：這個方法讓你設定這個商品的 id，括號裡面的 int id 是你要傳進來的值。
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}