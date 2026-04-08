package com.turkcell.model;

public class Account {

    private String accountNumber;
    private double balance;

    // Constructor
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Yatırılacak tutar 0'dan büyük olmalıdır.");
            return;
        } 
        setBalance(getBalance() + amount);
        System.out.println(amount + " TL hesabınıza yatırıldı. Yeni bakiye: " + getBalance() + " TL");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Çekilecek tutar 0'dan büyük olmalıdır.");
            return;
        }
        if (amount > getBalance()) {
            System.out.println("Yetersiz bakiye. Mevcut bakiye: " + getBalance() + " TL");
            return;
        }
        setBalance(getBalance() - amount);
        System.out.println(amount + " TL hesabınızdan çekildi. Yeni bakiye: " + getBalance() + " TL");
    }

}
