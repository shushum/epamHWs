package com.epam.java.se.unit07;

import java.util.Random;

/**
 * Created by Yegor on 19.03.2017.
 */
public class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private boolean run;
    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    public void run() {
        int action;

        try {
            while (run) {
                action = rand.nextInt(1000);

                //forcing random to be more pleasant for setters
                if (action % 2 == 0 && !resource.getList().isEmpty()) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;

        synchronized (resource) {
            System.out.println("Поток " + getName() + " хочет извлечь число.");
            number = resource.getElement();

            //limit cycle for escaping getter status
            for (int i = 0; i < 5 && number == null; i++) {
                System.out.println("Поток " + getName() + " ждет пока очередь заполнится.");
                //timeout wait for escaping getter status
                resource.wait(100);
                System.out.println("Поток " + getName() + " возобновил работу.");
                number = resource.getElement();
            }
            if (number == null) {
                System.out.println("Поток не извлек число");
            } else {
                System.out.println("Поток " + getName() + " извлек число " + number);
            }
        }
    }

    private void setIntegersIntoResource() throws InterruptedException {
        Integer number = rand.nextInt(500);

        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число " + number);
            resource.notify();
        }
    }

}
