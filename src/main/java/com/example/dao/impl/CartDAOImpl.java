package com.example.dao.impl;

import com.example.dao.CartDAO;
import com.example.pojo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart findByUserId(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Cart> query = session.createQuery(
                "SELECT DISTINCT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = :userId", 
                Cart.class);
            query.setParameter("userId", userId);
            Cart cart = query.uniqueResult();
            
            // 確保集合初始化
            if (cart != null && cart.getCartItems() != null) {
                cart.getCartItems().size(); // 觸發初始化
            }
            return cart;
        } catch (Exception e) {
            throw new RuntimeException("查詢用戶購物車失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(Cart cart) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(cart);
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("保存購物車失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Cart cart) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.merge(cart);
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("更新購物車失敗: " + e.getMessage(), e);
        }
    }
}