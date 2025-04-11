package com.example.service.impl;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Cart addToCart(String cartId, String productId, int quantity) {
        // 查找購物車中的商品
        CartItem cartItem = cartItemDao.getCartItemByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            // 更新商品數量
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemDao.save(cartItem);
        } else {
            // 商品不存在，創建新的購物車商品條目
            CartItem newCartItem = new CartItem();
            newCartItem.setCartId(cartId);
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(quantity);
            // 你可以根據需要設置價格
            newCartItem.setPrice(BigDecimal.valueOf(100)); // 假設價格為100
            newCartItem.setTotalPrice(newCartItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartItemDao.save(newCartItem);
        }
        return cartDao.getCartById(cartId);
    }

    @Override
    public Cart updateCartItemQuantity(String cartId, String productId, int quantity) {
        CartItem cartItem = cartItemDao.getCartItemByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartItemDao.save(cartItem);
        }
        return cartDao.getCartById(cartId);
    }

    @Override
    public Cart removeCartItem(String cartId, String productId) {
        CartItem cartItem = cartItemDao.getCartItemByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            cartItemDao.delete(cartItem);
        }
        return cartDao.getCartById(cartId);
    }

    @Override
    public double getCartTotalPrice(String cartId) {
        List<CartItem> cartItems = cartItemDao.getCartItemsByCartId(cartId);
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice().doubleValue();
        }
        return totalPrice;
    }

    @Override
    public List<CartItem> getCartItems(String cartId) {
        return cartItemDao.getCartItemsByCartId(cartId);
    }
}
