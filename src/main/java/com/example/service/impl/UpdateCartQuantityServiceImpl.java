package com.example.service.impl;

import java.math.BigDecimal;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.UpdateCartQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartQuantityServiceImpl implements UpdateCartQuantityService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Cart updateCartQuantity(String cartId, String productId, int quantity) {
        // 找到指定的購物車商品條目
        CartItem cartItem = cartItemDao.getCartItemByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);

            // ✅ 修正: 將 double 轉為 BigDecimal 再運算
            BigDecimal price = BigDecimal.valueOf(cartItem.getPrice());
            cartItem.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));

            cartItemDao.save(cartItem);
        }

        // 返回更新後的購物車
        return cartDao.getCartById(cartId);
    }
}
