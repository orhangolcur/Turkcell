package com.turkcell.spring_starter.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turkcell.spring_starter.dto.product.CreateProductRequest;
import com.turkcell.spring_starter.dto.product.CreatedProductResponse;
import com.turkcell.spring_starter.dto.product.GetByIdProductResponse;
import com.turkcell.spring_starter.dto.product.ListProductResponse;
import com.turkcell.spring_starter.dto.product.UpdateProductRequest;
import com.turkcell.spring_starter.dto.product.UpdatedProductResponse;
import com.turkcell.spring_starter.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductServiceImpl productService;

    public ProductsController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public CreatedProductResponse create(@RequestBody CreateProductRequest request) {
        return this.productService.createProduct(request);
    }

    @GetMapping
    public List<ListProductResponse> getAll() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GetByIdProductResponse getById(@PathVariable UUID id) {
        return this.productService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedProductResponse update(@PathVariable UUID id, @RequestBody UpdateProductRequest request) {
        return this.productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.productService.delete(id);
    }
}
