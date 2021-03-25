package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("User с id " + id + " удален");
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
