package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util u = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT ," +
                    "name varchar(255)," +
                    "lastName varchar(255)," +
                    "age TINYINT)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }
    }

    public void dropUsersTable() {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("DROP TABLE users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            PreparedStatement ps = con.prepareStatement(" insert into users (name, lastName, age)"
                    + " values (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить user");
        }

    }

    public void removeUserById(long id) {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.println("User с id " + id + " удален");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить user с таким id");
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = u.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить список");
        }
        return list;
    }

        public void cleanUsersTable() {
            try (Connection con = u.getConnection(); Statement st = con.createStatement()) {
                st.executeUpdate("TRUNCATE users");
                System.out.println("Таблица очищена");
            } catch (SQLException e) {
                System.out.println("Не удалось очистить таблицу");
            }
    }
}
