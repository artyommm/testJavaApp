package com.example.goods.repos;

import com.example.goods.models.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductsRepo extends CrudRepository<Product, Integer> {

}
