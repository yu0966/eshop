package com.example.service;

import com.example.pojo.entity.Cart;

public interface CartService {
    Cart getCartByUserId(String userId);
    void addToCart(String userId, String productId, int quantity, double price);
    void updateCartItem(String userId, String productId, int quantity);
    void removeFromCart(String userId, String productId);
    int getCartItemCount(String userId);
}