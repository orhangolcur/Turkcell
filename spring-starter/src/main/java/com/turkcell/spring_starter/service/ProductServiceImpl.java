package com.turkcell.spring_starter.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;

@Service
public class ProductServiceImpl {

    private final List<Product> productList = new ArrayList<>();

    public ProductCreatedResponse create (ProductForCreateDto productForCreateDto) {

        checkIfProductNameExists(productForCreateDto.getName());
        

        Product product = new Product();
        product.setId(productList.size() + 1);
        product.setName(productForCreateDto.getName());
        product.setPrice(productForCreateDto.getPrice());

        productList.add(product);

        ProductCreatedResponse response = new ProductCreatedResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;
    }

    public void update() {
        checkIfProductNameExists("");
    }

    private void checkIfProductNameExists(String name) {

        Product productWithSameName = productList
            .stream()
            .filter(p -> p.getName().equals(name))
            .findFirst()
            .orElse(null);

        if (productWithSameName != null) {
            throw new IllegalArgumentException("Aynı isimde bir ürün zaten mevcut");
        }

    }
}
