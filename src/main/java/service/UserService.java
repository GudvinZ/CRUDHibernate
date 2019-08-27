package service;

import dao.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.w3c.dom.UserDataHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserService instance;
    private static SessionFactory sessionFactory;

    private UserService() {
            sessionFactory = DBHelper.INSTANCE.getSessionFactory();
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    public void addUser(User user) {
            createUserDAO().addUser(user);
    }

    public void deleteUser(Long id) {
            createUserDAO().deleteUserById(id);
    }

    public void deleteAllUsers() {
            createUserDAO().deleteAllUsers();
    }

    public void updateUser(User user) {
            createUserDAO().updateUser(user);
    }

    public List<User> getAllUsers() {
            return createUserDAO().getAllUsers();
    }

    private UserDAO createUserDAO() {
        return new UserDAO(sessionFactory.openSession());
    }
}
