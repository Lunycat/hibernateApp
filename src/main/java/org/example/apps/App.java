package org.example.apps;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Выводим человечков
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getAge());

            session.getTransaction().commit();
        }

        // Сохраняем человечков
        /*try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person1 = new Person(null, "Виталий", 40);
            Person person2 = new Person(null, "Артём", 25);
            Person person3 = new Person(null, "Александр", 36);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();
        }*/

        // Обновляем человечков
        /*try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            person.setName("Сергей");

            session.getTransaction().commit();
        }*/


        // Удаление человечков
        /*try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            session.remove(person);

            session.getTransaction().commit();
        }*/

        sessionFactory.close();
    }
}
