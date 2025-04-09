package com.example.service.impl;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.RemoveFromCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveFromCartServiceImpl implements RemoveFromCartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Cart removeFromCart(String cartId, String productId) {
        // 找到並刪除指定商品條目
        CartItem cartItem = cartItemDao.getCartItemByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            cartItemDao.delete(cartItem);
        }

        // 返回更新後的購物車
        return cartDao.getCartById(cartId);
    }
}
