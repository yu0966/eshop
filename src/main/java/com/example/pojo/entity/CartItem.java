package com.example.pojo.entity;

import java.math.BigDecimal;

public class CartItem {

    private String id;                  // 商品條目ID
    private String cartId;              // 购物车ID
    private String productId;           // 商品ID
    private int quantity;               // 商品數量
    private BigDecimal price;           // 商品單價
    private BigDecimal totalPrice;      // 商品總價 (單價 * 數量)

    // Getter 和 Setter 方法
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
