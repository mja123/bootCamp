package com.solvd.solvdPractice.collections.service;

import com.solvd.solvdPractice.collections.exceptions.*;
import com.solvd.solvdPractice.collections.genericLinkedList.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;


public class Cart {
    //LinkedList of products
    private CustomLinkedList<Product> products = new CustomLinkedList<>();
    private static final Logger LOGGER = LogManager.getLogger(Cart.class);

    //region Products CRUD
    public void addProduct(Product newProduct) throws CapacityCartException {
        final int CAPACITY = 5;

        if (products.getSize() + 1 > CAPACITY) {
            throw new CapacityCartException("The cart can't add other product. Is full.");
        }
        if (products.getSize() + 1 == CAPACITY) {
            LOGGER.warn("The cart is full now.");
        }
            products.addElementAtStart(newProduct);
    }

    public void getProducts() throws EmptyCartException, ElementNotFound {
        if (!(products.getSize() > 0)) {
            throw new EmptyCartException("There aren't products in the cart.");
        }
        for (int i = 0; i < products.getSize(); i++) {
            LOGGER.info("Name: " + products.getOne(i).getData().getName() +
                    ", price: " + products.getOne(i).getData().getPrice() +
                    ", id: " + products.getOne(i).getData().getProductId() );
        }
    }

    public Product getProduct(Integer id) throws EmptyLinkedListException, ElementNotFound {

        if (products.getSize() < 0) {
            throw new EmptyLinkedListException("The LinkedList is empty.");
        }

        for (int i = 0; i < products.getSize(); i++) {

            if(products.getOne(i).getData().getProductId().equals(id)) {
                return products.getOne(i).getData();
            }
        }
        throw new ElementNotFound("Product not found.");
    }

    public void removeProduct(Product product) throws ElementNotFound, EmptyLinkedListException {
        if (!products.containsElement(product)) {
            throw new ElementNotFound("Product not found.");
        }
        products.deleteElement(product);
    }
    public void removeProduct(String name) throws EmptyLinkedListException, ElementNotFound {

        boolean containsProduct = false;

        for (int i = 0; i < products.getSize(); i++) {
            if (products.getOne(i).getData().getName().equals(name)) {
                products.deleteElement(products.getOne(i).getData());
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            throw new ElementNotFound("Product not found.");
        }
    }

    public void removeProduct(Integer id) throws EmptyLinkedListException, ElementNotFound {

        boolean containsProduct = false;

        for (int i = 0; i < products.getSize(); i++) {
            if (products.getOne(i).getData().getProductId().equals(id)) {
                products.deleteAt(products.getOne(i).getData().getProductId());
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            throw new ElementNotFound("Product not found.");
        }
    }

    public int countOfProducts() {
        return products.getSize();
    }
    //endregion

}
