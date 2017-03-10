package com.epam.java.se.unit04;

import java.util.List;
import java.util.Objects;

/**
 * Created by Yegor on 10.03.2017.
 */
public class MovieCollection {
    private final String ID;
    private List<Movie> collection;

    public MovieCollection(String ID, List<Movie> collection) {
        movieCollectionIsNotNull(ID, collection);

        this.ID = ID;
        this.collection = collection;
    }

    public void addMovieToCollection(Movie newMovie){
        Objects.requireNonNull(newMovie);
        if (collection.contains(newMovie)){
            return;
        }
        collection.add(newMovie);
    }

    private void movieCollectionIsNotNull(String ID, List<Movie> collection) {
        Objects.requireNonNull(ID);
        Objects.requireNonNull(collection);
    }
}
