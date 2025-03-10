package org.example.apps;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ManyToMany {

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Movie movie = new Movie("Новый человек паук: Высокое напряжение", 2014);
            Actor actor1 = session.get(Actor.class, 1);
            Actor actor2 = session.get(Actor.class, 2);

            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
            actor1.getMovies().add(movie);
            actor2.getMovies().add(movie);

            session.persist(movie);

            session.getTransaction().commit();
        }
    }
}
