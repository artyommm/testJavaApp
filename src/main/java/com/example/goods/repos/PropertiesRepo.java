package com.example.goods.repos;

import com.example.goods.models.Product;
import com.example.goods.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PropertiesRepo extends JpaRepository<Property, Integer> {
    List<Property> findByProductId(Integer id);
}
