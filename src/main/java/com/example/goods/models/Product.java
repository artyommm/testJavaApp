package com.example.goods.models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_prop_id")
    private Property price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_prop_id")
    private Property country;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getPrice() {
        return price;
    }

    public Property getCountry() {
        return country;
    }

    public void setPrice(Property price) {
        this.price = price;
    }

    public void setCountry(Property country) {
        this.country = country;
    }
}
