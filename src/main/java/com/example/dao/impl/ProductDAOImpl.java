package com.example.dao.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession(); // 使用 Spring 管理的 Hibernate Session
    }

    @Override
    public List<Product> findAll(int offset, int limit) {
        Query<Product> query = getSession().createQuery("FROM Product", Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public int count() {
        Query<Long> query = getSession().createQuery("SELECT COUNT(*) FROM Product", Long.class);
        return query.uniqueResult().intValue();
    }
}
