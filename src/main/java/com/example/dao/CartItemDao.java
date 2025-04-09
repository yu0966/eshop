package com.example.dao;

import com.example.pojo.entity.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CartItem> getCartItemsByCartId(String cartId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM CartItem WHERE cartId = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .list();
    }

    public CartItem getCartItemByCartIdAndProductId(String cartId, String productId) {
        Session session = sessionFactory.getCurrentSession();
        return (CartItem) session.createQuery("FROM CartItem WHERE cartId = :cartId AND productId = :productId")
                .setParameter("cartId", cartId)
                .setParameter("productId", productId)
                .uniqueResult();
    }

    public void save(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
    }

    public void delete(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartItem);
    }
}
