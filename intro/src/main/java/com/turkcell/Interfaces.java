package com.turkcell;

public class Interfaces {
    public static void main(String[] args) {
        CarRepository carRepository = new MsCarRepository();
        carRepository.add(new Car(true, "BMW"));
        carRepository.add(new Car(true, "Mercedes"));
        carRepository.add(new Car(false, "Ford"));
    }
}
