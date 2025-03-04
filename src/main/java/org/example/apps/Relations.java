package org.example.apps;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Relations {

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            List<Item> items = person.getItems();

            for (Item item : items) {
                System.out.println(item);
            }

            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Item item = session.get(Item.class, 2);
            Person person = item.getOwner();

            System.out.println(person);

            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 4);
            Item item1 = session.get(Item.class, 1);
            Item item2 = session.get(Item.class, 2);

            item1.setOwner(person);
            item2.setOwner(person);

            person.getItems().addAll(List.of(item1, item2));

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}

