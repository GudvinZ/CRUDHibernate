package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public void addUser(User user) {
        Transaction trx = session.beginTransaction();
        session.save(user);
        trx.commit();
        session.close();
    }

    public void deleteUserById(Long id) {
        Transaction trx = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id=?")
                .setParameter(0, id)
                .executeUpdate();
        trx.commit();
        session.close();
    }

    public void deleteAllUsers(){
        Transaction trx = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        trx.commit();
        session.close();
    }

    public void updateUser(User user) {
        Transaction trx = session.beginTransaction();
        session.createQuery("UPDATE User SET login=?, password=?, name=? WHERE id=?")
                .setParameter(0, user.getLogin())
                .setParameter(1, user.getPassword())
                .setParameter(2, user.getName())
                .setParameter(3, user.getId())
                .executeUpdate();
        trx.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        return (List<User>)session.createQuery("FROM User").list();
    }
}
