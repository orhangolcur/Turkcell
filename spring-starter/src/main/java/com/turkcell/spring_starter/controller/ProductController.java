package com.turkcell.spring_starter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;

// Veritabanı nesneleri request'te ve response'ta kullanılmaz. DTO (Data Transfer Object) kullanılır.
@RestController
@RequestMapping("/api/product")
public class ProductController {

    // In-Memory 
    private List<Product> productList = new ArrayList<>();

    @GetMapping()
    public List<Product> getAll() {
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Request-Response Pattern => Her istek-cevap kendine has bir modele sahip olmak zorundadır. (DTO)
    @PostMapping()
    public ProductCreatedResponse createProduct(@RequestBody ProductForCreateDto productDto) {
        // Dışarıdan alınan DTO'yu domain modeline(entity, model) dönüştürme işlemi yapılır.
        // MANUEL MAPPING
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(productList.size() + 1);
        productList.add(product);

        // Domain Nesnesi => DTO
        ProductCreatedResponse response = new ProductCreatedResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;

    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {

        Product existingProduct = productList.stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst()
                .orElseThrow();

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());        
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productList.removeIf(p -> p.getId() == id);
    }
        
}
