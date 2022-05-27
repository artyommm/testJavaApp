package com.example.goods.repos;

import com.example.goods.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductsRepo extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String name);
}
