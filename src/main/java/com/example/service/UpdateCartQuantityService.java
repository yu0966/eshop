package com.example.service;

import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;

public interface UpdateCartQuantityService {

    /**
     * 更新購物車內商品的數量
     *
     * @param cartId      購物車ID
     * @param productId   商品ID
     * @param quantity    新的商品數量
     * @return            更新後的購物車
     */
    Cart updateCartQuantity(String cartId, String productId, int quantity);
}
