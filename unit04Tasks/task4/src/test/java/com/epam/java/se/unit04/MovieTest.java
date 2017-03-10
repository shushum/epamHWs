package com.epam.java.se.unit04;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 10.03.2017.
 */
public class MovieTest {
    Actor emma =
            new Actor("Emma", "Stone", LocalDate.of(1988, Month.NOVEMBER, 6), 28, Gender.FEMALE, 165);
    Actor ryan =
            new Actor("Ryan", "Gosling", LocalDate.of(1980, Month.NOVEMBER, 12), 36, Gender.MALE, 184);

    @Test
    public void sortingCastWhileInitializationWorks() throws Exception {
        Actor sharon =
                new Actor("Sharon", "Stone", LocalDate.of(1958, Month.MARCH, 10), 59, Gender.FEMALE, 174);

        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(sharon);
        cast.add(emma);
        cast.add(ryan);

        Movie notLaLaLand = new Movie("La-la-land???", "Damien Chazelle", Genre.MUSICAL, cast);

        assertTrue(notLaLaLand.getStarring().get(0).equals(ryan));
        assertTrue(notLaLaLand.getStarring().get(1).equals(emma));
        assertTrue(notLaLaLand.getStarring().get(2).equals(sharon));
    }

    @Test
    public void equalsOnTheSameFilms() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);


        Actor emmaCopy =
                new Actor("Emma", "Stone", LocalDate.of(1988, Month.NOVEMBER, 6), 28, Gender.FEMALE, 165);
        Actor ryanCopy =
                new Actor("Ryan", "Gosling", LocalDate.of(1980, Month.NOVEMBER, 12), 36, Gender.MALE, 184);

        ArrayList<Actor> sequelCast = new ArrayList<>();

        sequelCast.add(ryanCopy);
        sequelCast.add(emmaCopy);

        Movie laLaLandSequel = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, sequelCast);

        assertTrue(laLaLand.equals(laLaLandSequel));
    }

    @Test
    public void equalsOnTheDifferentFilmsWithOneCast() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        ArrayList<Actor> sequelCast = new ArrayList<>();
        sequelCast.add(ryan);
        sequelCast.add(emma);
        Movie laLaLandSequel = new Movie("Bla-bla-bland", "Damien Chazelle", Genre.MUSICAL, sequelCast);

        assertFalse(laLaLand.equals(laLaLandSequel));
    }

    @Test
    public void equalsOnTheOneFilmsWithDifferentCast() throws Exception {
        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);
        Movie laLaLand = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        Actor sharon =
                new Actor("Sharon", "Stone", LocalDate.of(1958, Month.MARCH, 10), 59, Gender.FEMALE, 174);
        ArrayList<Actor> sequelCast = new ArrayList<>();
        sequelCast.add(ryan);
        sequelCast.add(sharon);
        Movie laLaLandSequel = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, sequelCast);

        assertFalse(laLaLand.equals(laLaLandSequel));
    }
}