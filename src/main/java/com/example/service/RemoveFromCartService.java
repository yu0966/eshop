package com.example.service;

import com.example.pojo.entity.Cart;

public interface RemoveFromCartService {

    /**
     * 從購物車中刪除指定商品
     *
     * @param cartId      購物車ID
     * @param productId   商品ID
     * @return            更新後的購物車
     */
    Cart removeFromCart(String cartId, String productId);
}
