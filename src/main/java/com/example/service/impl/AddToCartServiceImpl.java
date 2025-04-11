package com.example.service.impl;

import java.math.BigDecimal;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.pojo.entity.Product;
import com.example.service.AddToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddToCartServiceImpl implements AddToCartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Cart addToCart(String userId, Product product, int quantity) {
        // 找到用戶的購物車
        Cart cart = cartDao.getCartByUserId(userId);
        if (cart == null) {
            // 如果購物車不存在，創建新的購物車
            cart = new Cart();
            cart.setUserId(userId);
            cartDao.save(cart);
        }

        // 檢查商品是否已經在購物車中
        CartItem existingCartItem = cartItemDao.getCartItemByCartIdAndProductId(cart.getId(), String.valueOf(product.getId()));
        if (existingCartItem != null) {
            // 更新商品數量
            int newQuantity = existingCartItem.getQuantity() + quantity;
            existingCartItem.setQuantity(newQuantity);
            existingCartItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(newQuantity)));
            cartItemDao.save(existingCartItem);
        } else {
            // 創建新的商品條目
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setProductId(String.valueOf(product.getId()));  // 將 int 轉為 String
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice().doubleValue()); // ✅ 修正：轉成 double
            cartItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartItemDao.save(cartItem);
        }

        return cart;
    }
}
