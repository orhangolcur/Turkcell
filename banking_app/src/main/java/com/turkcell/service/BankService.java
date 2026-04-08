package com.turkcell.service;

import com.turkcell.model.CheckingAccount;
import com.turkcell.model.Customer;

public class BankService implements IBankService{

    @Override
    public void deposit(Customer customer, double amount) {
        CheckingAccount account = customer.getAccount();
        account.deposit(amount);
    }

    @Override
    public void withdraw(Customer customer, double amount) {
        CheckingAccount account = customer.getAccount();
        account.withdraw(amount);
    }

    @Override
    public void showBalance(Customer customer) {
        CheckingAccount account = customer.getAccount();
        System.out.println("Bakiye: " + account.getBalance() + " TL");
    }

    @Override
    public void showBalance(Customer customer, String message) {
        CheckingAccount account = customer.getAccount();
        System.out.println(message + " Bakiye: " + account.getBalance() + " TL");
    }

}
