package com.turkcell.model;

public class Customer {
    private String fullName;
    private String username;
    private String password;
    private CheckingAccount account;

    public Customer(String fullName, String username, String password, CheckingAccount account) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public CheckingAccount getAccount() {
        return account;
    }
    

}
