package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
