package com.turkcell;

public class Vehicle {
    private String model;
    private String brand;
    private int year;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
