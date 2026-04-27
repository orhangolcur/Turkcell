package com.turkcell.spring_starter.dto.product;

import java.util.UUID;

public class CreatedProductResponse {
    private UUID id;
    private String name;
    private String categoryName;

    public UUID getId() {
        return id;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
