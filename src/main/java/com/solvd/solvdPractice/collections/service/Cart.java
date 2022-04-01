package com.solvd.solvdPractice.collections.service;

import com.solvd.solvdPractice.collections.exceptions.CapacityCartException;
import com.solvd.solvdPractice.collections.exceptions.EmptyCartException;
import com.solvd.solvdPractice.collections.exceptions.ProductNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Objects;

public class Cart {
    //LinkedList of products
    private LinkedList <Product> products = new LinkedList<>();
    private static final Logger LOGGER = LogManager.getLogger(Cart.class);

    //region Products CRUD
    public void addProduct(Product newProduct) throws CapacityCartException {
        final int CAPACITY = 5;

        if (products.size() + 1 > CAPACITY) {
            throw new CapacityCartException("The cart can't add other product. Is full.");
        }
        if (products.size() + 1 == CAPACITY) {
            LOGGER.warn("The cart is full now.");
        }
            products.add(newProduct);
    }

    public void getProducts() throws EmptyCartException {
        if (!(products.size() > 0)) {
            throw new EmptyCartException("There aren't products in the cart.");
        }
        products.forEach(p -> LOGGER.trace("Name: " + p.getName() + ", id: " +
                p.getProductId() + ", price: " + p.getPrice()));
    }

    public Product getProduct(Integer id) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }

    public void removeProduct(Product product) throws ProductNotFoundException {
        if (!products.contains(product)) {
            throw new ProductNotFoundException("Product not found.");
        }
        products.remove(product);
    }
    public void removeProduct(String name) throws ProductNotFoundException {

        boolean containsProduct = false;

        for (Product p : products) {
            if (p.getName().equals(name)) {
                products.remove();
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            throw new ProductNotFoundException("Product not found.");
        }
    }

    public void removeProduct(Integer id) throws ProductNotFoundException {

        boolean containsProduct = false;

        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                products.remove();
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            throw new ProductNotFoundException("Product not found.");
        }
    }
    //endregion

}
