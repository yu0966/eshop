package com.example.service.impl;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.CartTotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartTotalPriceServiceImpl implements CartTotalPriceService {

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public BigDecimal calculateTotalPrice(String cartId) {
        // 获取所有购物车商品
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemDao.getCartItemsByCartId(cartId)) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }
}
