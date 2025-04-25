package com.example.dao.impl;

import com.example.dao.CartDAO;
import com.example.pojo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart findByUserId(String userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Cart> query = session.createQuery("FROM Cart WHERE userId = :userId", Cart.class);
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }

    @Override
    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cart);
    }

    @Override
    public void update(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cart);
    }
}