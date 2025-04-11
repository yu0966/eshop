package com.example.service;

import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;

import java.util.List;

public interface CartService {

    // 添加商品到購物車
    Cart addToCart(String cartId, String productId, int quantity);

    // 更新商品數量
    Cart updateCartItemQuantity(String cartId, String productId, int quantity);

    // 刪除購物車商品
    Cart removeCartItem(String cartId, String productId);

    // 獲取購物車的總價格
    double getCartTotalPrice(String cartId);

    // 獲取購物車中的所有商品
    List<CartItem> getCartItems(String cartId);
}
