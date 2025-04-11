// ProductDAOImpl.java
package com.example.dao.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    
    private SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product", Product.class);
            return query.getResultList();
        }
    }

    @Override
    public Product getProductById(int productId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, productId);
        }
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where category = :category", Product.class);
            query.setParameter("category", category);
            return query.getResultList();
        }
    }
}
