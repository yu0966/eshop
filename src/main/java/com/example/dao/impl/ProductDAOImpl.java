package com.example.dao.impl;

import com.example.dao.ProductDAO;
import com.example.pojo.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 商品資料存取實作類
 * 使用Hibernate實現與資料庫的交互
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    /**
     * 建構子注入SessionFactory
     * @param sessionFactory Hibernate Session工廠
     */
    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 查詢商品列表（分頁）
     * @param offset 起始位置 (從0開始)
     * @param limit 每頁數量
     * @return 商品列表
     */
    @Override
    public List<Product> findAll(int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product ORDER BY id", Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    /**
     * 計算商品總數量
     * @return 商品總數
     */
    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product", Long.class);
        return query.uniqueResult().intValue();
    }

    /**
     * 根據商品ID查詢單一商品
     * @param id 商品ID
     * @return 商品物件，若找不到則返回null
     */
    @Override
    public Product findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }
}