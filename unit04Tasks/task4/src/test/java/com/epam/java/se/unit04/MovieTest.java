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

    @Test
    public void addLeadRolesToCastCheck() throws Exception {
        Actor emma =
                new Actor("Emma", "Stone", LocalDate.of(1988, Month.NOVEMBER, 6), 28, Gender.FEMALE, 165);
        Actor ryan =
                new Actor("Ryan", "Gosling", LocalDate.of(1980, Month.NOVEMBER,12), 36, Gender.MALE, 184);

        assertTrue(emma.getAmountOfLeadRoles() == 0);
        assertTrue(ryan.getAmountOfLeadRoles() == 0);

        ArrayList<Actor> cast = new ArrayList<>();
        cast.add(emma);
        cast.add(ryan);

        Movie lalaland = new Movie("La-la-land", "Damien Chazelle", Genre.MUSICAL, cast);

        assertTrue(emma.getAmountOfLeadRoles() == 1);
        assertTrue(ryan.getAmountOfLeadRoles() == 1);
    }

}