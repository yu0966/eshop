package com.example.pojo.entity;

import java.math.BigDecimal;

public class CartItem {

    private String id;              // CartItem ID
    private String cartId;          // 所屬購物車ID
    private String productId;       // 商品ID
    private int quantity;           // 數量
    private double price;           // 單價
    private BigDecimal totalPrice; // 小計（單價 * 數量）

    // Getter 和 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // 非映射欄位，邏輯計算用
    public double getSubtotal() {
        return this.price * this.quantity;
    }
}
