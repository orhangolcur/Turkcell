package com.turkcell;

public class Car extends Vehicle {

    private boolean hasSunroof;
    private String[] specs;

    // Encapsulation => dışarıdan manipülasyona kapalı
    public String[] getSpecs() {
        return specs.clone();
    }
    public void setSpecs(String[] specs) {
        this.specs = specs.clone();
    }

    // clone() metodu, nesnenin bir kopyasını oluşturur ve döner. Bu sayede, dışarıdan gelen array'in referansını değil, kopyasını kullanarak encapsulation sağlanır.

    

    

}
