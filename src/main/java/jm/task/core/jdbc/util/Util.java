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
    private static Util instance;

    private Util() {}

    public static Util getInstance() {
        if (instance == null) {
            return new Util();
        }
        return instance;
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
