package com.turkcell;

public class Car {

    public String model;
    public String brand;
    public int year;

    // Encapsulation
    private double pricePerDay;
    
    public void setPricePerDay(double pricePerDay) {
        if (pricePerDay < 0) {
            System.out.println("Fiyat negatif olamaz! 0'a eşitleniyor...");
            this.pricePerDay = 0.0;
            return;
        }
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

}
