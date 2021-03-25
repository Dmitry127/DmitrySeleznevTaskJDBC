package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private Connection con;
    private static Util instance;
    private static SessionFactory sessionFactory;

    private Util() {}

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
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

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
