package org.example.apps;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToMany {

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = new Person("Великий чел", 50);
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            Item item3 = new Item("item3");

            person.addItem(item1);
            person.addItem(item2);
            person.addItem(item3);

            session.persist(person);

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}

