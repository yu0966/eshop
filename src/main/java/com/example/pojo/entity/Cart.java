package com.example.pojo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private String id;
    private String userId;
    private Date createDate;
    private Date updateDate;
    private Set<CartItem> cartItems = new HashSet<>();

    // Getters and Setters
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

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // 輔助方法
    public void addCartItem(CartItem item) {
        item.setCart(this);
        this.cartItems.add(item);
    }

    public void removeCartItem(CartItem item) {
        this.cartItems.remove(item);
        item.setCart(null);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", cartItems=" + cartItems +
                '}';
    }
}