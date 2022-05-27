package com.example.goods.repos;

import com.example.goods.models.Product;
import com.example.goods.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PropertiesRepo extends JpaRepository<Property, Integer> {
    List<Property> findByProductId(Integer id);
    List<Property> findByTypeAndValue(String type, String value);
    //List<Property> findByType(String type);
    PropertiesRepo findByValue(String value);
}
