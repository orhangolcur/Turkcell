package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.model.Product;

@RestController // Bu class bir rest contollerdır, içini uygulama başladığında tara, http=> function tanımlarını al
@RequestMapping("/api/product") // http://localhost:8080/api/product => sayHi() çalışır
public class ProductController {

    @GetMapping("") // http://localhost:8080/api/product => sayHi() çalışır
    public String sayHi(String name, int age) {
        return "Hi, " + name + " yaşınız: " + age;
    }

    // http://localhost:8080/api/product/hello => sayHello() çalışır 
    @GetMapping("hello/{name}/{age}") // http://localhost:8080/api/product/hello/John/30 => sayHello() çalışır, name=John, age=30 olur
    public String sayHello(@PathVariable String name, @PathVariable int age) {
        return "Hello, " + name + " yaşınız: " + age;
    } 

    @PostMapping // http://localhost:8080/api/product => add() çalışır, body kısmına json formatında product bilgisi gönderilir
    public String add(@RequestBody Product product) { // Json => Java objesine
        return product.getId() + " ID'li ürün başarıyla eklendi, fiyatı: " + product.getPrice();
    }
}
