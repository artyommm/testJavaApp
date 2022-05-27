package com.example.goods.repos;

import com.example.goods.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ProductsRepo extends CrudRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
}
