package com.example.dao.impl;

import com.example.dao.CartDAO;
import com.example.pojo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import javax.persistence.NoResultException;
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
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery(
                "SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = :userId", 
                Cart.class)
                .setParameter("userId", userId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        try {
            // 重要修改：使用merge而不是persist
            Cart mergedCart = (Cart) session.merge(cart);
            
            // 处理关联的购物车项
            if(mergedCart.getCartItems() != null) {
                mergedCart.getCartItems().forEach(item -> {
                    item.setCart(mergedCart); // 确保双向关联
                    session.merge(item); // 使用merge处理可能已存在的项
                });
            }
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("保存购物车失败，用户ID: " + cart.getUserId(), e);
        }
    }

    @Override
    public void update(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Cart mergedCart = (Cart) session.merge(cart);
            // 处理购物车项
            mergedCart.getCartItems().forEach(item -> {
                item.setCart(mergedCart);
                session.merge(item);
            });
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("更新购物车失败，购物车ID: " + cart.getId(), e);
        }
    }
}