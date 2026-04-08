package com.turkcell.service;

import com.turkcell.model.Customer;

public interface IBankService {
    void deposit(Customer customer, double amount);
    void withdraw(Customer customer, double amount);
    
    // method overloading
    void showBalance(Customer customer);
    void showBalance(Customer customer, String message);
}
