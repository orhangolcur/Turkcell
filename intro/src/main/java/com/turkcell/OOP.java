package com.turkcell;

public class OOP {
    public static void main(String[] args) {

        Car car1 = new Car(false, "BMW"); // car1, Car instance'ıdır

        String[] specs = {"Sunroof", "Leather seats", "Navigation system"};
        car1.setSpecs(specs);

        String[] x = car1.getSpecs();
        x[0] = "Panoramic sunroof"; // car1'in specs'i değişmez, çünkü getSpecs() metodu specs'in bir kopyasını döner

        System.out.println(car1.getSpecs()[0]); 
        System.out.println(car1.getBrand());
        System.out.println(car1.getPricePerDay());
        

        Car car2 = new Car(true, "Audi");
        System.out.println(car2.getBrand());
    }
}
