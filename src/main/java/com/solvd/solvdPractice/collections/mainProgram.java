package com.solvd.solvdPractice.collections;

import com.solvd.solvdPractice.collections.exceptions.CapacityCartException;
import com.solvd.solvdPractice.collections.exceptions.EmptyCartException;
import com.solvd.solvdPractice.collections.exceptions.ProductNotFoundException;
import com.solvd.solvdPractice.collections.people.Client;
import com.solvd.solvdPractice.collections.service.Cart;
import com.solvd.solvdPractice.collections.service.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: add debug configuration in log4j2.xml. Create the custom LinkedList and the Supermarket class with a queue.

public class mainProgram {

    private final static Logger LOGGER = LogManager.getLogger(mainProgram.class);

    public static void main(String[] args) {
        Product product1 = new Product("Noodles", 15.00);
        Cart cart1 = new Cart();
        Client client1 = new Client("Matías", 20, cart1, 100.00);

        try {
            cart1.getProduct(1);
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            cart1.addProduct(new Product("Shampoo", 16.00));
        } catch (CapacityCartException e) {
            LOGGER.error(e.getMessage());
        }

        /*
        try {
            cart1.removeProduct("Shampoo");
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        */

        try {
            //Should throw an error
            cart1.removeProduct(2);
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            cart1.getProducts();
        } catch (EmptyCartException e) {
            LOGGER.error(e.getMessage());
        }
        //region products
        /*
        try {
            product1.deleteProduct(2);
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            System.out.println(product1.getProductPrice(1));
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            System.out.println(product1.getProductPrice(2));
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(product1.getProducts());
        */
        //endregion

        //LOGIC ERROR: product is an object
        try {
            client1.getCart().addProduct(product1);
        } catch (CapacityCartException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
