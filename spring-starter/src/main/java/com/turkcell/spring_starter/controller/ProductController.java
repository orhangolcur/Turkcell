package com.turkcell.spring_starter.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.service.ProductServiceImpl;

import jakarta.validation.Valid;

// Veritabanı nesneleri request'te ve response'ta kullanılmaz. DTO (Data Transfer Object) kullanılır.
@RestController // Uygulama gerektiğinde controller'ı newler
@RequestMapping("/api/product")
public class ProductController {

    // Dependency Injection (Bağımlılık Enjeksiyonu)
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    // Controller'da iş mantığı olmaz, sadece request'i alır ve service'e iletir. Service'de iş mantığı olur.
    @PostMapping
    public ProductCreatedResponse create (@RequestBody @Valid ProductForCreateDto productForCreateDto) {
        return productService.create(productForCreateDto);
    }
        
}
