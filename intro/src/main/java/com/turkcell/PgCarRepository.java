package com.turkcell;

public class PgCarRepository implements CarRepository {

    @Override
    public void add(Car car) {
        System.out.println("Araba nesnesi postgresql'e eklendi");
    }

}
