package com.example.pojo.entity;

public class CartItem {

    private String id;              // CartItem ID
    private String cartId;          // 所屬購物車ID
    private String productId;       // 商品ID
    private int quantity;           // 數量
    private double price;           // 單價

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

    // 可選: 小計（非映射資料庫欄位，只是方便邏輯運算）
    public double getSubtotal() {
        return this.price * this.quantity;
    }
}
