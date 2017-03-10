package com.epam.java.se.unit04;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yegor on 10.03.2017.
 */

/**
 * A class designed to create collection of Movies, modify it, save in or load from file.
 */
public class MovieCollection implements Serializable {
    /**
     * A String identifier. Can be interpreted as owner or name of the Movie Collection.
     */
    private final String ID;
    /**
     * List of Movies in collection.
     */
    private List<Movie> collection;

    /**
     * Creates a Movie Collection with a preset of Movies.
     * @param ID identifier for collection.
     * @param collection a preset of movies that the collection will contain after creation.
     */
    public MovieCollection(String ID, ArrayList<Movie> collection) {
        movieCollectionIsNotNull(ID, collection);

        this.ID = ID;
        this.collection = collection;
    }

    /**
     * Add a new Movie to Movie Collection.
     *
     * If Movie Collection already has that specific Movie, it won't be added.
     * @param newMovie
     */
    public void addMovieToCollection(Movie newMovie) {
        Objects.requireNonNull(newMovie);

        if (collection.contains(newMovie)) {
            //todo add feedback?
            return;
        }
        collection.add(newMovie);
        //todo organize sorted adding?
    }

    /**
     * Removes the Movie from Movie Collection based on the Movie title.
     *
     * If Movie Collection doesn't contain such Movie title, Movie Collection won't be changed.
     * If Movie Collection contains multiple Movies with such title, only the one with the lowest index will be removed.
     * @param title
     */
    public void removeMovieFromCollectionByTitle(String title) {
        Objects.requireNonNull(title);

        for (Movie movie: collection) {

            if (movie.getTitle().equals(title)){
                collection.remove(movie);
                return;
            }
        }

        //todo add feedback on not existed in collection movie?
    }

    /**
     * Sorts all Movies in Movie Collection by title.
     */
    public void sortMovieCollectionByTitle() {
        Collections.sort(collection, (o1, o2) -> {
            String t1 = o1.getTitle();
            String t2 = o2.getTitle();
            return t1.compareTo(t2);
        });
    }

    /**
     * Needed for testing.
     * @return
     */
    public List<Movie> getCollection() {
        return collection;
    }

    /**
     * Saves current Movie Collection in file with specified name.
     */
    public void saveMovieCollectionToFile() {
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("Movie Collection of " + ID))) {

            save.writeObject(this);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Movie Collection from file for further work with it.
     * @param collectionID an ID of required Movie Collection.
     * @return loaded from Movie Collection.
     * @throws FileNotFoundException if there is no Movie Collection with such ID.
     * @throws ClassNotFoundException if loaded info is not Movie Collection type?
     */
    public static MovieCollection loadMovieCollectionFromFile(String collectionID) throws FileNotFoundException, ClassNotFoundException {
        MovieCollection loadedCollection = null;

        try (ObjectInputStream load = new ObjectInputStream(new FileInputStream("Movie Collection of " + collectionID))) {

            loadedCollection = (MovieCollection) load.readObject();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("Collection with ID [%s] doesn't exist.", collectionID));

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(String.format("Apparently, some classes are missing. That's not normal." +
                    "Supposed missed class is [%s].", e.getMessage()));
        }
        return loadedCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCollection that = (MovieCollection) o;

        return ID.equals(that.ID) && collection.equals(that.collection);
    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + collection.hashCode();
        return result;
    }

    private void movieCollectionIsNotNull(String ID, List<Movie> collection) {
        Objects.requireNonNull(ID);
        Objects.requireNonNull(collection);
    }
}
