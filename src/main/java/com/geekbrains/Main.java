package com.geekbrains;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int CARS_COUNT = 4;
    protected static final CountDownLatch countDownForAnnouncement = new CountDownLatch(CARS_COUNT);
    protected static final CountDownLatch countDownForFinish  = new CountDownLatch(CARS_COUNT);
    protected static final Semaphore semaphore = new Semaphore(CARS_COUNT / 2);


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            countDownForAnnouncement.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            countDownForFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
