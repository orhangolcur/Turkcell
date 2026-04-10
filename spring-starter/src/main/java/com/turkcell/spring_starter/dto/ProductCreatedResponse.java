package com.turkcell.spring_starter.dto;

public class ProductCreatedResponse {
    private int id; // Response'da id de dönebilir. Çünkü oluşturulan ürünün id'sine ihtiyaç duyulabilir. Örneğin, oluşturulan ürünün detay sayfasına yönlendirme yapılabilir.
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
