package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private Connection con;

    public Util() {

    }

    public Connection getConnection() {
        try {
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к БД");
        }
        return con;
    }
}
