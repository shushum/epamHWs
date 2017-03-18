package com.epam.java.se.unit07;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 19.03.2017.
 */
public class SharedResource {
    private List<Integer> list;

    public SharedResource() {
        list = new ArrayList<Integer>();
    }

    public void setElement(Integer element) {
        list.add(element);
    }

    public Integer getElement() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }

}
