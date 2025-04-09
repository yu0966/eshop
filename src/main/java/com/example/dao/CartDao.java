package com.example.dao;

import com.example.pojo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Cart getCartByUserId(String userId) {
        Session session = sessionFactory.getCurrentSession();
        return (Cart) session.createQuery("FROM Cart WHERE userId = :userId")
                .setParameter("userId", userId)
                .uniqueResult();
    }

    public Cart getCartById(String cartId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cart.class, cartId);
    }

    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cart);
    }
}
