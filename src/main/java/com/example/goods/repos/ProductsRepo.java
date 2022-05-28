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
    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT p FROM property pr\n" +
            "JOIN pr.product p\n" +
            "WHERE pr.type = :type\n" +
            "ORDER BY pr.value ASC")
    Page<Product> findAll(@Param("type") String type, Pageable pageable);

    @Query("SELECT p FROM property pr\n" +
            "JOIN pr.product p\n" +
            "WHERE pr.type=:type\n" +
            "AND p.name LIKE %:name%\n" +
            "ORDER BY pr.value ASC")
    Page<Product> findAll(@Param("name") String name, @Param("type") String type, Pageable pageable);

}
