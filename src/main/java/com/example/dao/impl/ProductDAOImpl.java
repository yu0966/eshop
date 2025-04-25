package com.example.dao.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll(int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product ORDER BY id", Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<Product> findByCategory(int categoryId, int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product WHERE category.id = :categoryId ORDER BY id", Product.class);
        query.setParameter("categoryId", categoryId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product", Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public int countByCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product WHERE category.id = :categoryId", Long.class);
        query.setParameter("categoryId", categoryId);
        return query.uniqueResult().intValue();
    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public List<Category> findAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("FROM Category ORDER BY id", Category.class);
        return query.getResultList();
    }
}