package com.example.pojo.entity;

import java.math.BigDecimal;

public class CartItem {

    private String id;
    private Cart cart;          // 改為直接引用Cart對象
    private String productId;
    private int quantity;
    private double price;
    private BigDecimal totalPrice;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

    // 輔助方法
    public void calculateTotalPrice() {
        if (this.price > 0 && this.quantity > 0) {
            this.totalPrice = BigDecimal.valueOf(this.price).multiply(BigDecimal.valueOf(this.quantity));
        }
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}