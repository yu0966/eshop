package com.example.service.impl;

import com.example.dao.CartDAO;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
            for (CartItem item : cart.getCartItems()) {
                item.calculateTotalPrice();
            }
        }
        return cart;
    }

    @Override
    public void addToCart(String userId, String productId, int quantity, double price) {
        if (userId == null || productId == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        Cart cart = cartDao.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setId(UUID.randomUUID().toString());
            cart.setUserId(userId);
            cart.setCreateDate(new Date());
            cart.setCartItems(new HashSet<>()); // 使用HashSet初始化
            cartDao.save(cart);
        }

        // 檢查是否已存在相同商品
        boolean itemExists = false;
        for (CartItem item : cart.getCartItems()) {
            if (productId.equals(item.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.calculateTotalPrice();
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            CartItem newItem = new CartItem();
            newItem.setId(UUID.randomUUID().toString());
            newItem.setCart(cart); // 設置關聯的Cart對象
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            newItem.calculateTotalPrice();
            cart.getCartItems().add(newItem);
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
                    item.calculateTotalPrice();
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