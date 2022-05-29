package com.example.goods;

import com.example.goods.models.Product;
import com.example.goods.models.Property;
import com.example.goods.repos.ProductsRepo;

import com.example.goods.repos.PropertiesRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Validated
@Controller
@RestController
public class MainController {
    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private PropertiesRepo propertiesRepo;

    @GetMapping(value = "/all", params = {"productName"}) //фильтр по имени
    public Iterable<Product> getFilteredProducts(@RequestParam(value = "productName") String productName,
                                                 @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return productsRepo.findByNameContaining(productName, pageable).getContent();
    }
    @GetMapping(value = "/all", params = {"productName", "propTypes"})//пустой productName - поиск по всем продуктам, работает LIKE
    public Iterable<Product> getFilteredProducts(@RequestParam(value = "productName") String productName,
                                                 @RequestParam(value = "propTypes") List<String> propTypes,
                                                 @PageableDefault(sort = {"product.name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return productsRepo.findAll(productName, propTypes, pageable).getContent(); //все продукты в которых содержится характеристики, типы которых содержатся в propTypes
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable Integer id) {
        Product product = productsRepo.findById(id).get();
        Map<String, Object> result = new HashMap<>();
        result.put("id", product.getId());
        result.put("name", product.getName());
        Iterable<Property> properties = propertiesRepo.findByProductId(productsRepo.findById(id).get().getId());
        result.put("properties", properties);
        return result;
    }

    @PostMapping("/product")
    public Product addNewProduct(@RequestBody String newProduct) throws JSONException {
        JSONObject obj = new JSONObject(newProduct);
        Product product = new Product(obj.getString("name"));
        productsRepo.save(product);
        Property priceProp = null, countryProp = null;
        List<Property> properties = new ArrayList<>(); //складываем сюда свойства, чтобы в БД поместить разом
        JSONArray jsonArr = obj.getJSONArray("properties");
        for(int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject objects = jsonArr.getJSONObject(i);
            Property prop = new Property(product, objects.getString("type"), objects.getString("value"));
            properties.add(prop);
            if(Objects.equals(prop.getType(), "price"))
                priceProp = prop;
            else if(Objects.equals(prop.getType(), "country"))
                countryProp = prop;
        }
        propertiesRepo.saveAll(properties);
        product.setPrice(priceProp);
        product.setCountry(countryProp);
        return productsRepo.save(product);
    }

    @PostMapping("/property")
    public Property addProperty(@RequestParam Integer product_id, @RequestParam String type, @RequestParam String value) {
        Product product = productsRepo.findById(product_id).get();
        Property property = new Property(product, type, value);
        propertiesRepo.save(property);
        return property;
    }

    @DeleteMapping("/delete/{id}")
    public Integer removeProduct(@PathVariable Integer id) {
        productsRepo.deleteById(id);
        return id;
    }

    @PatchMapping("/patchProduct")
    public void patchProduct(@RequestParam Integer id, @RequestParam String name) {
        Product currentProduct = productsRepo.findById(id).get();
        currentProduct.setName(name);
        productsRepo.save(currentProduct);
    }

    @PatchMapping("/patchProperty")
    public void patchProperty(@RequestParam Integer id, @RequestParam String type,
                              @RequestParam String value) {
        Property currentProperty = propertiesRepo.findById(id).get();
        currentProperty.setType(type);
        currentProperty.setValue(value);
        propertiesRepo.save(currentProperty);
    }

    @DeleteMapping("/deleteProp/{id}")
    public Integer removeProp(@PathVariable Integer id) {
        propertiesRepo.deleteById(id);
        return id;
    }
}
