package com.example.service.impl;

import com.example.dao.CartDAO;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDao;

    @Override
    public Cart getCartByUserId(String userId) {
        Cart cart = cartDao.findByUserId(userId);
        if (cart != null && cart.getCartItems() != null) {
            // 計算每個項目的總價
            for (CartItem item : cart.getCartItems()) {
                item.setTotalPrice(BigDecimal.valueOf(item.getPrice() * item.getQuantity()));
            }
        }
        return cart;
    }

    @Override
    public void addToCart(String userId, String productId, int quantity, double price) {
        // 確保參數有效性
        if (userId == null || productId == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        Cart cart = cartDao.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setId(UUID.randomUUID().toString());
            cart.setUserId(userId);
            cart.setCreateDate(new Date());
            cart.setCartItems(new ArrayList<>()); // 初始化購物車項目列表
            cartDao.save(cart);
        }

        // 使用 BigDecimal 進行精確計算
        BigDecimal itemPrice = BigDecimal.valueOf(price);
        BigDecimal totalPrice = itemPrice.multiply(BigDecimal.valueOf(quantity));

        // 檢查是否已存在相同商品
        boolean itemExists = false;
        List<CartItem> items = cart.getCartItems();
        for (CartItem item : items) {
            if (productId.equals(item.getProductId())) {
                int newQuantity = item.getQuantity() + quantity;
                item.setQuantity(newQuantity);
                item.setTotalPrice(itemPrice.multiply(BigDecimal.valueOf(newQuantity)));
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            CartItem newItem = new CartItem();
            newItem.setId(UUID.randomUUID().toString());
            newItem.setCartId(cart.getId());
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            newItem.setTotalPrice(totalPrice);
            items.add(newItem); // 直接使用已初始化的列表
        }

        cart.setUpdateDate(new Date());
        cartDao.update(cart);
    }

    @Override
    public void updateCartItem(String userId, String productId, int quantity) {
        Cart cart = cartDao.findByUserId(userId);
        if (cart != null && cart.getCartItems() != null) {
            for (CartItem item : cart.getCartItems()) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(quantity);
                    item.setTotalPrice(BigDecimal.valueOf(item.getPrice() * quantity));
                    break;
                }
            }
            cart.setUpdateDate(new Date());
            cartDao.update(cart);
        }
    }

    @Override
    public void removeFromCart(String userId, String productId) {
        Cart cart = cartDao.findByUserId(userId);
        if (cart != null && cart.getCartItems() != null) {
            cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
            cart.setUpdateDate(new Date());
            cartDao.update(cart);
        }
    }

    @Override
    public int getCartItemCount(String userId) {
        Cart cart = cartDao.findByUserId(userId);
        if (cart == null || cart.getCartItems() == null) {
            return 0;
        }
        return cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
    }
}