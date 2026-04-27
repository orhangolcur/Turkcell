package com.turkcell.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{

}
