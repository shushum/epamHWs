package com.epam.java.se.unit04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yegor on 10.03.2017.
 */
public class Movie {
    private final String title;
    private final String directorsName;
    private final Genre genre;
    private final List<Actor> starring;

    public Movie(String title, String directorsName, Genre genre, ArrayList<Actor> starring) {
        movieIsNotNull(title, directorsName, starring);

        this.title = title;
        this.directorsName = directorsName;
        this.genre = genre;
        this.starring = starring;

        addLeadRolesToCast(starring);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!title.equals(movie.title)) return false;
        if (!directorsName.equals(movie.directorsName)) return false;
        if (genre != movie.genre) return false;
        return starring.equals(movie.starring);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + directorsName.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + starring.hashCode();
        return result;
    }

    private void sortCastByNames(ArrayList<Actor> cast) {
        Collections.sort(cast, (o1, o2) -> {
            String lastName1 = o1.getLastName();
            String lastName2 = o2.getLastName();

            int result = lastName1.compareTo(lastName2);
            if (result!=0){
                return result;
            }

            String firstName1 = o1.getFirstName();
            String firstName2 = o2.getFirstName();

            return firstName1.compareTo(firstName2);
        });
    }

    private void addLeadRolesToCast(ArrayList<Actor> starring) {
        for (Actor actor : starring) {
            actor.addLeadRole();
        }
    }

    private void movieIsNotNull(String title, String directorsName, ArrayList<Actor> starring) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(directorsName);
        Objects.requireNonNull(starring);
    }
}

enum Genre {
    COMEDY,
    DRAMA,
    HORROR,
    WAR,
    ADVENTURE,
    CRIME,
    MUSICAL
}
