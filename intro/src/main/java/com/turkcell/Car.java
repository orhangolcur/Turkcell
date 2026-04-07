package com.turkcell;

public class Car extends Vehicle {

    private boolean hasSunroof;
    private String[] specs;
    

    // Constructor => nesne oluşturulurken çağrılır, nesnenin başlangıç durumunu belirler
    // Yazmazsak default constructor (parametresiz) oluşturulur
    public Car(boolean hasSunroof, String brand) {
        super(); // Vehicle'ın parametresiz constructor'ını çağırır
        this.setHasSunroof(hasSunroof);
        super.setBrand(brand);
    }

    public Car() {
    }

    // Encapsulation => dışarıdan manipülasyona kapalı
    public String[] getSpecs() {
        return specs.clone();
    }
    public void setSpecs(String[] specs) {
        this.specs = specs.clone();
    }

    // clone() metodu, nesnenin bir kopyasını oluşturur ve döner. Bu sayede, dışarıdan gelen array'in referansını değil, kopyasını kullanarak encapsulation sağlanır.

    public boolean isHasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

}
