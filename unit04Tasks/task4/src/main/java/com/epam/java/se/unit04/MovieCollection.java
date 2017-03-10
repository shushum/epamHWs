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
