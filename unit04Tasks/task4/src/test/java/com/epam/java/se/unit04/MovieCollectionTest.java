package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 10.03.2017.
 */
public class MovieCollectionTest {

    Actor emma =
            new Actor("Emma", "Stone", LocalDate.of(1988, Month.NOVEMBER, 6), 28, Gender.FEMALE, 165);
    Actor ryan =
            new Actor("Ryan", "Gosling", LocalDate.of(1980, Month.NOVEMBER, 12), 36, Gender.MALE, 184);


    @Test
    public void addMovieToCollectionWorksRight() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(laLaLand);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        assertTrue(smallCollection.getCollection().size() == 1);

        Actor sharon =
                new Actor("Sharon", "Stone", LocalDate.of(1958, Month.MARCH, 10), 59, Gender.FEMALE, 174);
        Actor niro =
                new Actor("Robert", "De Niro", LocalDate.of(1943, Month.AUGUST, 17), 73, Gender.MALE, 177);
        ArrayList<Actor> diffCast = new ArrayList<>();
        diffCast.add(niro);
        diffCast.add(sharon);
        Movie casino = new Movie("Casino", "Martin Scorsese", Genre.DRAMA, diffCast);

        smallCollection.addMovieToCollection(casino);

        assertTrue(smallCollection.getCollection().size() == 2);
        assertTrue(smallCollection.getCollection().get(0).equals(laLaLand));
        assertTrue(smallCollection.getCollection().get(1).equals(casino));
    }

    @Test
    public void addTheSameMovieToCollectionDoNotAdding() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(laLaLand);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        assertTrue(smallCollection.getCollection().size() == 1);


        Movie laLaLandSequel = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        smallCollection.addMovieToCollection(laLaLandSequel);

        assertTrue(smallCollection.getCollection().size() == 1);
        assertTrue(smallCollection.getCollection().get(0).equals(laLaLand));
        assertTrue(smallCollection.getCollection().get(0).equals(laLaLandSequel));
    }

    @Test
    public void removeTheExistingMovieFromCollection() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie laLaLandSequel = new Movie("La-la-land: Moonlight shadow", "Damien Chazelle", Genre.MUSICAL, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(laLaLand);
        collection.add(laLaLandSequel);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        assertTrue(smallCollection.getCollection().size() == 2);

        smallCollection.removeMovieFromCollectionByTitle("La-la-land: Moonlight shadow");

        assertTrue(smallCollection.getCollection().size() == 1);
        assertTrue(smallCollection.getCollection().get(0).equals(laLaLand));
    }

    @Test
    public void removeNotExistingMovieFromCollection() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie laLaLandSequel = new Movie("La-la-land: Moonlight shadow", "Damien Chazelle", Genre.MUSICAL, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(laLaLand);
        collection.add(laLaLandSequel);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        assertTrue(smallCollection.getCollection().size() == 2);

        Movie notExistingInCollectionMovie = new Movie("Heavy rain", "Quantic Dream", Genre.DRAMA, cast);

        smallCollection.removeMovieFromCollectionByTitle("Heavy rain");

        assertTrue(smallCollection.getCollection().size() == 2);
        assertTrue(smallCollection.getCollection().get(0).equals(laLaLand));
        assertTrue(smallCollection.getCollection().get(1).equals(laLaLandSequel));
    }

    @Test
    public void sortingMoviesInCollectionWorksRight() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie laLaLandSequel = new Movie("BLa-bla-bland: Moonlight shadow", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie heavyRain = new Movie("Heavy rain", "Quantic Dream", Genre.DRAMA, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(heavyRain);
        collection.add(laLaLandSequel);
        collection.add(laLaLand);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        smallCollection.sortMovieCollectionByTitle();

        assertTrue(smallCollection.getCollection().get(0).equals(laLaLandSequel));
        assertTrue(smallCollection.getCollection().get(1).equals(heavyRain));
        assertTrue(smallCollection.getCollection().get(2).equals(laLaLand));
    }

    @Test
    public void saveMovieCollectionAutoPathTest() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie laLaLandSequel = new Movie("BLa-bla-bland: Moonlight shadow", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie heavyRain = new Movie("Heavy rain", "Quantic Dream", Genre.DRAMA, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(heavyRain);
        collection.add(laLaLandSequel);
        collection.add(laLaLand);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        smallCollection.saveMovieCollectionToFile();

        File check = new File("Movie Collection of My collection");
        assertTrue(check.exists());
    }

    @Test
    public void loadMovieCollectionFromFileTest() throws Exception {
        MovieCollection loadedCollection = MovieCollection.loadMovieCollectionFromFile("My collection");

        assertFalse(loadedCollection == null);

        assertFalse(loadedCollection.getCollection().isEmpty());

    }

    @Test(expected = FileNotFoundException.class)
    public void loadMovieCollectionFromWrongFile() throws Exception {
        MovieCollection loadedCollection = MovieCollection.loadMovieCollectionFromFile("Wrong ID of collection");
    }

    @Test
    public void saveLoadTest() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie laLaLandSequel = new Movie("BLa-bla-bland: Moonlight shadow", "Damien Chazelle", Genre.MUSICAL, cast);
        Movie heavyRain = new Movie("Heavy rain", "Quantic Dream", Genre.DRAMA, cast);

        ArrayList<Movie> collection = new ArrayList<>();
        collection.add(heavyRain);
        collection.add(laLaLandSequel);
        collection.add(laLaLand);

        MovieCollection smallCollection = new MovieCollection("My collection", collection);

        smallCollection.saveMovieCollectionToFile();


        MovieCollection loadedSmallCollection = MovieCollection.loadMovieCollectionFromFile("My collection");

        assertTrue(loadedSmallCollection.getCollection().size() == 3);

        assertTrue(loadedSmallCollection.equals(smallCollection));

        assertTrue(loadedSmallCollection.getCollection().get(2).equals(laLaLand));
        assertTrue(loadedSmallCollection.getCollection().get(1).getStarring().get(0).equals(ryan));
    }

    @Test
    public void loadModifySaveLoadTest() throws Exception {
        saveLoadTest();

        Actor michael =
                new Actor("Michael", "Cera", LocalDate.of(1988, Month.JUNE, 7), 28, Gender.MALE, 174);
        Actor mary =
                new Actor("Mary Elizabeth", "Winstead", LocalDate.of(1984, Month.NOVEMBER, 28), 32, Gender.FEMALE, 173);

        ArrayList<Actor> newCast = new ArrayList<>();
        newCast.add(mary);
        newCast.add(michael);

        MovieCollection backupCollection = MovieCollection.loadMovieCollectionFromFile("My collection");

        MovieCollection loadedSmallCollection = MovieCollection.loadMovieCollectionFromFile("My collection");
        loadedSmallCollection.addMovieToCollection(
                new Movie("Scott Pilgrim vs. the World", "Edgar Wright", Genre.ADVENTURE, newCast));
        loadedSmallCollection.removeMovieFromCollectionByTitle("BLa-bla-bland: Moonlight shadow");


        loadedSmallCollection.saveMovieCollectionToFile();


        MovieCollection modifiedSmallCollection = MovieCollection.loadMovieCollectionFromFile("My collection");

        assertTrue(modifiedSmallCollection.equals(loadedSmallCollection));
        assertFalse(modifiedSmallCollection.equals(backupCollection));
    }
}