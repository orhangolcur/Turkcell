package com.turkcell;

import com.turkcell.model.CheckingAccount;
import com.turkcell.model.Customer;
import com.turkcell.repository.InMemoryCustomerRepository;
import com.turkcell.service.BankService;
import com.turkcell.service.IBankService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        IBankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        boolean appRunning = true;

        System.out.println("\n==== TURKCELL BANKA UYGULAMASINA HOŞ GELDİNİZ ====");

        while (appRunning) {
            System.out.println("\n----------------------------");
            System.out.println("==== HOŞ GELDİNİZ ====");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int appChoice = Integer.parseInt(scanner.nextLine());

            switch (appChoice) {
                case 1:
                    System.out.print("Kullanıcı adı: ");
                    String username = scanner.nextLine();
                    System.out.print("Şifre: ");
                    String password = scanner.nextLine();

                    Customer currentCustomer = repository.findByUsername(username);

                    if (currentCustomer == null || !currentCustomer.getPassword().equals(password)) {
                        System.out.println("Kullanıcı adı veya şifre hatalı!");
                        break;
                    }

                    System.out.println("Hoş geldiniz, " + currentCustomer.getFullName() + "!");

                    boolean menuRunning = true;
                    while (menuRunning) {
                        System.out.println("\n----------------------------");
                        System.out.println("==== MENÜ ====");
                        System.out.println("1. Para Yatır");
                        System.out.println("2. Para Çek");
                        System.out.println("3. Bakiye Sorgula");
                        System.out.println("0. Çıkış");
                        System.out.print("Seçiminiz: ");

                        int choice = Integer.parseInt(scanner.nextLine());

                        switch (choice) {
                            case 1:
                                System.out.print("Yatırılacak tutar: ");
                                double depositAmount = Double.parseDouble(scanner.nextLine());
                                bankService.deposit(currentCustomer, depositAmount);
                                break;
                            case 2:
                                System.out.print("Çekilecek tutar: ");
                                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                                bankService.withdraw(currentCustomer, withdrawAmount);
                                break;
                            case 3:
                                bankService.showBalance(currentCustomer);
                                break;
                            case 0:
                                System.out.println("Çıkış yapıldı.");
                                menuRunning = false;
                                break;
                            default:
                                System.out.println("Geçersiz seçim!");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Adınız Soyadınız: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Kullanıcı adı: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Şifre: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Açılış bakiyesi: ");
                    double initialBalance = Double.parseDouble(scanner.nextLine());

                    String accountNumber = repository.generateAccountNumber();
                    CheckingAccount newAccount = new CheckingAccount(accountNumber, initialBalance);
                    Customer newCustomer = new Customer(fullName, newUsername, newPassword, newAccount);
                    repository.save(newCustomer);

                    System.out.println("Kayıt başarılı! Hesap numaranız: " + accountNumber);
                    break;

                case 0:
                    System.out.println("Güle güle!");
                    appRunning = false;
                    break;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }

        scanner.close();
    }
}