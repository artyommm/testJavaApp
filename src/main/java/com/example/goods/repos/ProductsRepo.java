package com.example.goods.repos;

import com.example.goods.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductsRepo extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String name);

//    @Query("SELECT p FROM product p\n" +
//            "JOIN property pr\n" +
//            "WHERE pr.type = :type\n" +
//            "ORDER BY pr.value ASC")
    @Query("SELECT p FROM property pr\n" +
            "JOIN pr.product p\n" +
            "WHERE pr.type = :type\n" +
            "ORDER BY pr.value ASC")
    List<Product> findAll(@Param("type") String type);

}
