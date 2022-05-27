package com.example.goods.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotBlank
    private String type;

    @NotBlank
    private String brand;

    //@Min(0)
    //@DecimalMin(value = "0.0", inclusive = true)
    @Min(0)
    private Double price;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property() {
    }

    public Property(Product product, String type, Double price, String brand) {
        this.product = product;
        this.type = type;
        this.price = price;
        this.brand = brand;
    }

    public Property(Integer id, Product product, String type, Double price, String brand) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.price = price;
        this.brand = brand;
    }

    public Product getProduct() {
        return product;
    }

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
