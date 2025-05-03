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
            // 使用 JOIN FETCH 避免 N+1 查詢問題
            Query<Cart> query = session.createQuery(
                "SELECT DISTINCT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = :userId", 
                Cart.class);
            query.setParameter("userId", userId);
            
            Cart cart = query.getSingleResult();
            
            // 確保 Hibernate 會話中的集合初始化
            if (cart != null && cart.getCartItems() != null) {
                cart.getCartItems().size(); // 觸發初始化
            }
            return cart;
        } catch (NoResultException e) {
            return null; // 沒有找到結果是正常情況，返回null
        } catch (Exception e) {
            throw new RuntimeException("查詢用戶購物車失敗，用戶ID: " + userId, e);
        }
    }

    @Override
    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        try {
            // 使用 persist 而非 save 確保對象狀態正確
            session.persist(cart);
            // 立即刷新以捕捉可能的錯誤
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("保存購物車失敗，購物車ID: " + cart.getId(), e);
        }
    }

    @Override
    public void update(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        try {
            // 使用 merge 處理分離對象
            Cart mergedCart = (Cart) session.merge(cart);
            // 確保關聯對象也被更新
            if (mergedCart.getCartItems() != null) {
                mergedCart.getCartItems().forEach(session::merge);
            }
            session.flush();
        } catch (Exception e) {
            throw new RuntimeException("更新購物車失敗，購物車ID: " + cart.getId(), e);
        }
    }
}