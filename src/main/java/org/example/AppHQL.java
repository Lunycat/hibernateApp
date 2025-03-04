package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AppHQL {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            List<Person> people = session.createQuery("FROM Person WHERE name LIKE '%ий'").getResultList();

            for (Person person : people) {
                System.out.println(person);
            }

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
