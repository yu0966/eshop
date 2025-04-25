package com.example.dao;

import com.example.pojo.entity.Cart;

public interface CartDAO {
    Cart findByUserId(String userId);
    void save(Cart cart);
    void update(Cart cart);
}