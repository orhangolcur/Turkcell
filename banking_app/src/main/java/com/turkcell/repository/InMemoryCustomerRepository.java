package com.turkcell.repository;

import java.util.ArrayList;
import java.util.List;

import com.turkcell.model.CheckingAccount;
import com.turkcell.model.Customer;

public class InMemoryCustomerRepository {

    private List<Customer> customers = new ArrayList<>();
    private int accountCounter = 100;

    public InMemoryCustomerRepository() {
        // Sample data
        customers.add(new Customer("Orhan Gölcür", "orhangolcur", "1234", new CheckingAccount("TR001", 1000.0)));
    }

    public void save(Customer customer) {
        customers.add(customer);
    }

    public Customer findByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    public String generateAccountNumber() {
        accountCounter++;
        return "TR" + accountCounter;
    }

}
