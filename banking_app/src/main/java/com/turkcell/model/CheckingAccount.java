package com.turkcell.model;

// Inheritance
public class CheckingAccount extends Account{

    public CheckingAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    public String getAccountType() {
        return "Vadesiz Hesap";
    }
}
