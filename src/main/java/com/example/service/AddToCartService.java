package com.example.service;

import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.pojo.entity.Product;

public interface AddToCartService {

    /**
     * 將商品加入購物車
     *
     * @param userId      使用者ID
     * @param product     商品物件
     * @param quantity    商品數量
     * @return            購物車物件
     */
    Cart addToCart(String userId, Product product, int quantity);
}
