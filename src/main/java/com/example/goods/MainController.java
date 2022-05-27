package com.example.goods;

import com.example.goods.models.Product;
import com.example.goods.models.Property;
import com.example.goods.repos.ProductsRepo;

import com.example.goods.repos.PropertiesRepo;
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

    @GetMapping("/all")
    public Iterable<Product> getAllProducts(){
//        Iterable<Property> properties = this.propertiesRepo.findAll();
//        List<Property> productsTypes = new ArrayList<>();
//        for (Property prop: properties) {
//            if(!productsTypes.contains(prop.getType())){
//                productsTypes.add(prop);
//            }
//        }
        return productsRepo.findAll();
    }

    @GetMapping("/pages")
    public Iterable<Product> page(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        Page<Product> page;
//        page = productsRepo.findAll(pageable);
//        return page;
        return null;
    }

    @GetMapping("/filter") //1 - asc, all the rest - desc
    public List<Property> getFilteredProducts(@RequestParam String productName, @RequestParam String propType, @RequestParam String propValue){
//        Iterable<Property> properties = this.propertiesRepo.findAll();
//        List<Property> filteredProductsList = new ArrayList<Property>();
//        for (Property prop: properties) {
//            if(prop.getType().equals(type)){
//                filteredProductsList.add(prop);
//            }
//        }
//        if(ordering==1)
//            filteredProductsList.sort((left, right) -> left.getProduct().getName().compareTo(right.getProduct().getName()));
//        else
//            filteredProductsList.sort((left, right) -> right.getProduct().getName().compareTo(left.getProduct().getName()));

//        return filteredProductsList;
        //PropertiesRepo properties = propertiesRepo.findByType(propType).findByValue(propValue);
        //properties.findByValue(propValue);
        //return productsRepo.findByNameContaining(productName);

        return  propertiesRepo.findByTypeAndValue(propType, propValue);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable Integer id){
        Product product = productsRepo.findById(id).get();
        Map<String, Object> result = new HashMap<>();
        result.put("id", product.getId());
        result.put("name", product.getName());
        Iterable<Property> properties = propertiesRepo.findByProductId(productsRepo.findById(id).get().getId());
        result.put("properties", properties);
        return result;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestParam String name, @RequestParam String price, @RequestParam String country){
        Product product = new Product(name);
        productsRepo.save(product);
        Property priceProp = new Property(product, "price", price);
        Property countryProp = new Property(product, "country", country);
        propertiesRepo.save(priceProp);
        propertiesRepo.save(countryProp);
        product.setPrice(priceProp);
        product.setCountry(countryProp);
        productsRepo.save(product);
        return product;
    }

    @PostMapping("/property")
    public Property addProperty(@RequestParam Integer product_id, @RequestParam String type, @RequestParam String value){
        Product product = productsRepo.findById(product_id).get();
        Property property = new Property(product, type, value);
        propertiesRepo.save(property);
        return property;
    }

    @DeleteMapping("/delete/{id}")
    public Integer removeProduct(@PathVariable Integer id){
        Iterable<Property> allProperties = propertiesRepo.findAll();
        for (Property prop : allProperties) {
//            if(prop.getProduct().getId().equals(id))
//                propertiesRepo.delete(prop);
        }
        productsRepo.deleteById(id);
        return id;
    }

    @PatchMapping("/patchProduct")
    public void patchProduct(@RequestParam Integer id, @RequestParam String name){
        Product currentProduct = productsRepo.findById(id).get();
        currentProduct.setName(name);
        productsRepo.save(currentProduct);
    }

    @PatchMapping("/patchProperty")
    public void patchProperty(@RequestParam Integer id, @RequestParam Integer product_id,
                              @RequestParam String type, @RequestParam Double price,
                              @RequestParam String brand){
        Property currentProperty = propertiesRepo.findById(id).get();
//        currentProperty.setPrice(price);
//        currentProperty.setType(type);
//        currentProperty.setBrand(brand);
//        currentProperty.setProduct(productsRepo.findById(product_id).get());
        propertiesRepo.save(currentProperty);
    }

    @DeleteMapping("/deleteProp/{id}")
    public Integer removeProp(@PathVariable Integer id){
        propertiesRepo.deleteById(id);
        return id;
    }
}
