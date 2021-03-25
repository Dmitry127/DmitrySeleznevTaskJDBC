package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util u = Util.getInstance();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = u.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT ," +
                "name varchar(255)," +
                "lastName varchar(255)," +
                "age TINYINT)").addEntity(User.class).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = u.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = u.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = u.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.load(User.class, id));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = u.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<User> list= session.createQuery("FROM " +User.class.getSimpleName()).list();
            transaction.commit();
            session.close();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = u.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
