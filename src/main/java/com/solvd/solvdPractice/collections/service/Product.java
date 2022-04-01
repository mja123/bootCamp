package com.solvd.solvdPractice.collections.service;

import com.solvd.solvdPractice.collections.exceptions.ProductNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Objects;

public class Product {
    private static Integer id = 0;
    private Integer productId;
    private String name;
    private Double price;

    public Product() {
        this.productId = id;
        id += 1;
    }

    public Product(String name, Double price) {
        id += 1;
        this.name = name;
        this.price = price;
        this.productId = id;
    }

    //region getters and setters

    public Integer getProductId() {
        return productId;
    }

    public static Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
