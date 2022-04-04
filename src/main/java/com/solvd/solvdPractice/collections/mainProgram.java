package com.solvd.solvdPractice.collections;

import com.solvd.solvdPractice.collections.exceptions.*;
import com.solvd.solvdPractice.collections.people.Cashier;
import com.solvd.solvdPractice.collections.people.Client;
import com.solvd.solvdPractice.collections.service.Cart;
import com.solvd.solvdPractice.collections.service.Product;
import com.solvd.solvdPractice.collections.service.Supermarket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: add debug configuration in log4j2.xml. Create the custom LinkedList and the Supermarket class with a queue.

public class mainProgram {

    private final static Logger LOGGER = LogManager.getLogger(mainProgram.class);

    public static void main(String[] args) {
        //region Initialization
        Product product1 = new Product("Noodles", 15.00);
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Client client1 = new Client("Matías", 20, cart1, 100.00);
        Client client2 = new Client("Milagros", 24, cart2, 300.00);

        Supermarket supermarket = new Supermarket();
        Cashier cashier1 = new Cashier("Joaquín", 32, 4, 200.00, "Cash box", 1);
        Cashier cashier2 = new Cashier("Esteban", 23,1, 200.00, "Cash box", 2);
        //endregion

        //region Supermarket
        supermarket.addCashierInBox(cashier1);
        supermarket.addClientInQueue(client1);
        supermarket.addCashierInBox(cashier2);

        try {
            supermarket.serveClient();
        } catch (EmptyQueueException | EmptyBoxException | UnableBoxesException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            supermarket.finishServingClient(cashier1);
        } catch (EmptyBoxException e) {
            LOGGER.error(e.getMessage());
        }
        //Throw error because there aren't people in the queue
        try {
            supermarket.serveClient();
        } catch (EmptyQueueException | EmptyBoxException | UnableBoxesException e) {
            LOGGER.error(e.getMessage());
        }
        supermarket.addClientInQueue(client2);
        try {
            supermarket.serveClient();
        } catch (EmptyQueueException | EmptyBoxException | UnableBoxesException e) {
            LOGGER.error(e.getMessage());
        }
        //endregion

        //region Cart
        //throw error because product isn't in the cart.
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
        try {
            cart1.getProducts();
        } catch (EmptyCartException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            cart1.removeProduct("Shampoo");
        } catch (ProductNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

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

        try {
            client1.getCart().addProduct(new Product("cheese", 20.0));
        } catch (CapacityCartException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            cart1.getProducts();
        } catch (EmptyCartException e) {
            LOGGER.error(e.getMessage());
        }
        //endregion
    }
}
