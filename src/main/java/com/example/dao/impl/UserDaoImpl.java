package com.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.pojo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 根據使用者的帳號和密碼，查詢使用者資料。
     * 使用 Hibernate 查詢並返回結果。
     * 
     * @param user 包含使用者登入資訊的物件，通常是帳號與密碼
     * @return 如果查詢到資料，回傳對應的 User 物件；若無則回傳 null
     */
    @Override
    public User getLoginUser(User user) {
        // 從 User 物件中提取 loginId 和 password
        String loginId = user.getLoginId();
        String password = user.getPassword();

        // 使用 Hibernate 查詢
        String hql = "FROM User u WHERE u.loginId = :loginId AND u.password = :password";
        return (User) sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("loginId", loginId)
                .setParameter("password", password)
                .uniqueResult();
    }

    /**
     * 新增一個使用者資料。
     * 
     * @param user 要新增的 User 物件，包含使用者的所有資料
     */
    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
