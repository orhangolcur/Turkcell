package com.turkcell.spring_starter.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.turkcell.spring_starter.dto.product.CreateProductRequest;
import com.turkcell.spring_starter.dto.product.CreatedProductResponse;
import com.turkcell.spring_starter.dto.product.GetByIdProductResponse;
import com.turkcell.spring_starter.dto.product.ListProductResponse;
import com.turkcell.spring_starter.dto.product.UpdateProductRequest;
import com.turkcell.spring_starter.dto.product.UpdatedProductResponse;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public CreatedProductResponse createProduct(CreateProductRequest request) {
        
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        Category category = this.categoryRepository.findById(request.getCategoryId()).orElseThrow();
        product.setCategory(category);

        this.productRepository.save(product);

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCategoryName(category.getName());
        return response;
    }

    public List<ListProductResponse> getAllProducts() {
        List<Product> products = this.productRepository.findAll();

        List<ListProductResponse> responses = products.stream().map(product -> {
            ListProductResponse listProductResponse = new ListProductResponse();
            listProductResponse.setId(product.getId());
            listProductResponse.setName(product.getName());
            listProductResponse.setCategoryName(product.getCategory().getName());
            return listProductResponse;
        }).toList();

        return responses;
    }

    public GetByIdProductResponse getById(UUID id) {
        Product product = this.productRepository.findById(id).orElseThrow();

        GetByIdProductResponse response = new GetByIdProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryName(product.getCategory().getName());

        return response;
    }

    public UpdatedProductResponse update(UUID id,UpdateProductRequest request) {
        Product product = this.productRepository.findById(id).orElseThrow();
        product.setName(request.getName());
        product.setDescription(request.getDescription());

        this.productRepository.save(product);

        UpdatedProductResponse response = new UpdatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());

        return response;
    }

    public void delete(UUID id) {
        Product product = this.productRepository.findById(id).orElseThrow();
        this.productRepository.delete(product);
    }
}
