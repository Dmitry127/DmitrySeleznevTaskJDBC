package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util u = Util.getInstance();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT ," +
                    "name varchar(255)," +
                    "lastName varchar(255)," +
                    "age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection con = u.getConnection()) {
            PreparedStatement ps = con.prepareStatement(" insert into users (name, lastName, age)"
                    + " values (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Connection con = u.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return list;
    }

        public void cleanUsersTable() {
            try (Connection con = u.getConnection(); Statement st = con.createStatement()) {
                st.executeUpdate("TRUNCATE users");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
