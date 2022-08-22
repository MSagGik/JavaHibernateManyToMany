package ru.msaggik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.msaggik.hibernate.model.Actor;
import ru.msaggik.hibernate.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        // подключение файла конфигурации hibernate.properties и классов Actor и Movie
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        // создание сессии из configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // новый синтаксис Java9 исключения с ресурсами (аналогично finally)
        try (sessionFactory) {
            // сессия
            Session session = sessionFactory.getCurrentSession();
            // начало транзакции
            session.beginTransaction();

//            // добавление нового фильма и его актёров:
//            // создание нового фильма
//            Movie movie = new Movie("Pulp fiction", 1994);
//            // актёры снимавшиеся в данном фильме
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//            // объединение объектов (добавление актёров в список фильма)
//            // ( List.of() - не изменяемый список (Java9)
//            // Arrays.asList() - изменяемый, но не расширяемый список )
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            // балансировка кэша
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            // сохранение данных
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            // вывод списка октёров для созданного в БД фильма:
//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());
//
//            // добавление нового фильма и связывание его с существующим актёром:
//            // 1) добавление нового фильма
//            Movie movie2 = new Movie("Reservoir Dogs", 1992);
//            // 2) вызов из БД существующего актёра
//            Actor actor3 = session.get(Actor.class, 1);
//            // 3) добавление данного актёра в созданный фильм
//            movie2.setActors(new ArrayList<>(Collections.singletonList(actor3)));
//            // балансировка кэша
//            actor3.getMovies().add(movie2);
//            // сохранение данных
//            session.save(movie2);

//            // удаление связи фильма и актёра:
//            // 1) получение из БД актёра
//            Actor actor4 = session.get(Actor.class, 2);
//            // просмотр фильмов данного актёра
//            System.out.println(actor4.getMovies());
//            // 2) получение из БД фильма с индексом 0
//            Movie movieToRemove = actor4.getMovies().get(0);
//            // 3) удаление связи актёра и фильма
//            actor4.getMovies().remove(0);
//            // балансировка кэша
//            // ( в данном случае для метода remove() изпользуются хэшькод и иголс,
//            // их нужно добавить в классах Movies и Actor )
//            movieToRemove.getActors().remove(actor4);

            // закрытие транзакции
            session.getTransaction().commit();
        }
    }
}