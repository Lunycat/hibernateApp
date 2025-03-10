package org.example.apps;

import org.example.model.Item;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOne {

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);
            System.out.println(person.getPassport().getNumber());

            Passport passport = session.get(Passport.class, 2);
            System.out.println(passport.getPerson().getName());

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
