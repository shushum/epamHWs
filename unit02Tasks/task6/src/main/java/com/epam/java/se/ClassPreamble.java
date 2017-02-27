package com.epam.java.se;

import java.lang.annotation.*;

/**
 * Created by Yegor on 27.02.2017.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ClassPreamble {
    String author();
    String date();
    String lastModified();
}
