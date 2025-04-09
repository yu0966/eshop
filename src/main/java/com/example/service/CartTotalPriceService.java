package com.example.service;

import com.example.pojo.entity.Cart;

import java.math.BigDecimal;

public interface CartTotalPriceService {

    /**
     * 計算購物車的總價
     *
     * @param cartId      購物車ID
     * @return            購物車總價
     */
    BigDecimal calculateTotalPrice(String cartId);
}
