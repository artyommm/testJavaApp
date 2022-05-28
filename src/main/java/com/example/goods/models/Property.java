package com.example.goods.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Table(name="property")
@Entity(name="property")
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
    private String value;

    public Property() {
    }

    public Property(Product product, String type, String value) {
        this.product = product;
        this.type = type;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProduct() {
        return product.getId();
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
