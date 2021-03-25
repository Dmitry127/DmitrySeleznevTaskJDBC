package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService uss = new UserServiceImpl();
        uss.createUsersTable();
        uss.saveUser("Ivan", "Ivanov", (byte) 45);
        uss.saveUser("Ivan", "Moscow", (byte) 4);
        uss.saveUser("NotIvan", "34ev", (byte) 17);
        uss.saveUser("Name", "Testov", (byte) 45);
        List<User> list = uss.getAllUsers();
        for (User user : list) {
            System.out.println(user.toString());
        }
        uss.removeUserById(3L);
        uss.cleanUsersTable();
        uss.dropUsersTable();
    }
}
