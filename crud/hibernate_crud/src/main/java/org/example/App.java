package org.example;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class App 
{
    public static void main( String[] args )
    {
        createUser("Jay", "jaychavda.com");

        getUser(1);

        updateUser(1, "Akshay Updated", "jayesh@gmail.com");

        deleteUser(1);

        HibernateUtil.getSessionFactory().close();
    }

    public static void createUser(String name, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = new User(name, email);
        session.save(user);

        tx.commit();
        session.close();

        System.out.println("User created with ID: " + user.getId());
    }

    public static void getUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = session.get(User.class, id);

        if (user != null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
        } else {
            System.out.println("User not found!");
        }

        session.close();
    }
    public static void updateUser(int id, String newName, String newEmail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);
        if (user == null) {
            System.out.println("User not found!");
            session.close();
            return;
        }

        user.setName(newName);
        user.setEmail(newEmail);

        session.update(user);

        tx.commit();
        session.close();

        System.out.println("User updated!");
    }
    public static void deleteUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);
        if (user == null) {
            System.out.println("User not found!");
            session.close();
            return;
        }

        session.delete(user);

        tx.commit();
        session.close();

        System.out.println("User deleted!");
    }

}
