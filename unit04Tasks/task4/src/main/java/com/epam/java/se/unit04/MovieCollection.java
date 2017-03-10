package com.epam.java.se.unit04;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yegor on 10.03.2017.
 */
public class MovieCollection implements Serializable {
    private final String ID;
    private List<Movie> collection;

    public MovieCollection(String ID, List<Movie> collection) {
        movieCollectionIsNotNull(ID, collection);

        this.ID = ID;
        this.collection = collection;
    }

    public void addMovieToCollection(Movie newMovie) {
        Objects.requireNonNull(newMovie);

        if (collection.contains(newMovie)) {
            //todo add feedback?
            return;
        }
        collection.add(newMovie);
        //todo organize sorted adding?
    }

    public void removeMovieFromCollection(Movie movie) {
        Objects.requireNonNull(movie);

        if (collection.remove(movie)) {
            return;
        }
        //todo add feedback?
    }

    public void sortMovieCollectionByTitle() {
        Collections.sort(collection, (o1, o2) -> {
            String t1 = o1.getTitle();
            String t2 = o2.getTitle();
            return t1.compareTo(t2);
        });
    }

    public List<Movie> getCollection() {
        return collection;
    }

    public void saveMovieCollectionToFile() {

        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("Movie Collection of " + ID))) {

            save.writeObject(this);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

   /* private File fileExistCheck() {
        File file = new File("Movie Collection " + ID);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }*/

    private void movieCollectionIsNotNull(String ID, List<Movie> collection) {
        Objects.requireNonNull(ID);
        Objects.requireNonNull(collection);
    }
}
