package com.epam.java.se;


/**
 * Created by Yegor on 27.02.2017.
 */
@ClassPreamble(
        author = "Yegor",
        date = "27.02.2017",
        lastModified = "27.02.2017"
)
public class AtomicSubmarine {

    private SubmarineEngine engine;

    public AtomicSubmarine() {
        this.engine =  new SubmarineEngine();
    }

    private class SubmarineEngine{
        void startEngine(){
            System.out.println("Engine has been started!");
        }
    }

    public void launch(){
        engine.startEngine();
        System.out.println("Submarine launch is successful!");
    }
}
