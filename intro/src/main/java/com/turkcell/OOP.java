package com.turkcell;

public class OOP {
    public static void main(String[] args) {

        Car car1 = new Car(); // car1, Car instance'ıdır

        car1.model = "BMW";
        car1.brand = "X5";
        car1.year = 2020;
        
        car1.setPricePerDay(-500.0);

        System.out.println(car1.brand);
        System.out.println(car1.getPricePerDay());
        
    }
}
