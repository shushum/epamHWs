package com.epam.java.se.unit04;

import java.util.ArrayList;
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

    private void addLeadRolesToCast(ArrayList<Actor> starring) {
        for (Actor actor:starring) {
            actor.addLeadRole();
        }
    }

    private void movieIsNotNull(String title, String directorsName, ArrayList<Actor> starring) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(directorsName);
        Objects.requireNonNull(starring);
    }
}

enum Genre{
    COMEDY,
    DRAMA,
    HORROR,
    WAR,
    ADVENTURE,
    CRIME,
    MUSICAL
}
