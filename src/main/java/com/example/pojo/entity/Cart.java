package com.example.pojo.entity;

import java.util.Date;
import java.util.List;

public class Cart {

    private String id;                  // 购物车ID
    private String userId;              // 用戶ID
    private Date createDate;            // 創建時間
    private Date updateDate;            // 更新時間
    private List<CartItem> cartItems;   // 购物车中的商品條目

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
