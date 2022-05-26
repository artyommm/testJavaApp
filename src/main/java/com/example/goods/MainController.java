package com.example.goods;

import com.example.goods.models.Product;
import com.example.goods.models.Property;
import com.example.goods.repos.ProductsRepo;

import com.example.goods.repos.PropertiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Persistence;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

@Validated
@Controller
@RestController
public class MainController {
    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private PropertiesRepo propertiesRepo;

    @GetMapping("/")
    public Iterable<Product> main(Map<String, Object> model){
        model.put("products", this.productsRepo.findAll());
        Iterable<Property> properties = this.propertiesRepo.findAll();
        model.put("properties", properties);
        List<Property> productsTypes = new ArrayList<>();
        for (Property prop: properties) {
            if(!productsTypes.contains(prop.getType())){
                productsTypes.add(prop);
            }
        }
        model.put("productsTypes", productsTypes);
        model.put("filteredProductsList", properties);

        return productsRepo.findAll();
        //return "main";
    }

    @GetMapping("/filter") //1 - asc, all the rest - desc
    public List<Property> main(@RequestParam String type, @RequestParam Integer ordering, Map<String, Object> model){
        Iterable<Property> properties = this.propertiesRepo.findAll();
        List<Property> filteredProductsList = new ArrayList<Property>();
        for (Property prop: properties) {
            if(prop.getType().equals(type)){
                filteredProductsList.add(prop);
            }
        }
        if(ordering==1)
            filteredProductsList.sort((left, right) -> left.getProduct().getName().compareTo(right.getProduct().getName()));
        else
            filteredProductsList.sort((left, right) -> right.getProduct().getName().compareTo(left.getProduct().getName()));

        return filteredProductsList;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable Integer id,  Map<String, Object> model){
        Map<String, Object> result = new HashMap<>();
        result.put("id", productsRepo.findById(id).get().getId());
        result.put("name", productsRepo.findById(id).get().getName());
        List<Property> props = new ArrayList<>();
        Iterable<Property> properties = propertiesRepo.findAll();
        for (Property prop : properties) {
            if(prop.getProduct().getId().equals(id))
                props.add(prop);
        }
        result.put("properties", props);
        return result;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestParam String name, Map<String, Object> model){
        Product product = new Product(name);
        productsRepo.save(product);
        return product;
    }

    @PostMapping("/property")
    public Property addProperty(@RequestParam Integer product_id, @RequestParam String type,
                                @RequestParam @Min(0) Double price, @RequestParam String brand,
                                Map<String, Object> model){
        Product product = productsRepo.findById(product_id).get();
        Property property = new Property(product, type, price, brand);
        propertiesRepo.save(property);
        return property;
    }

    @DeleteMapping("delete/{id}")
    public Integer removeProduct(@PathVariable Integer id, Map<String, Object> model){
//        List<Property> props = new ArrayList();
        Iterable<Property> allProperties = propertiesRepo.findAll();
        for (Property prop : allProperties) {
            if(prop.getProduct().getId().equals(id))
                propertiesRepo.delete(prop);
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

    @PutMapping("/putProduct")
    public void putProduct(@RequestParam Integer id, @RequestParam String name){
        Product currentProduct = productsRepo.findById(id).get();
        currentProduct.setName(name);
        productsRepo.save(currentProduct);
    }

    @PatchMapping("/patchProperty")
    public void patchProperty(@RequestParam Integer id, @RequestParam Integer product_id,
                              @RequestParam String type, @RequestParam Double price,
                              @RequestParam String brand ){
        Property currentProperty = propertiesRepo.findById(id).get();
        currentProperty.setPrice(price);
        currentProperty.setType(type);
        currentProperty.setBrand(brand);
        currentProperty.setProduct(productsRepo.findById(product_id).get());
        propertiesRepo.save(currentProperty);
    }

    @PutMapping("/putProperty")
    public void putProperty(@RequestParam Integer id, @RequestParam Integer product_id,
                            @RequestParam String type, @RequestParam Double price,
                            @RequestParam String brand ){
        Property currentProperty = propertiesRepo.findById(id).get();
        currentProperty.setPrice(price);
        currentProperty.setType(type);
        currentProperty.setBrand(brand);
        currentProperty.setProduct(productsRepo.findById(product_id).get());
        propertiesRepo.save(currentProperty);
    }
}
