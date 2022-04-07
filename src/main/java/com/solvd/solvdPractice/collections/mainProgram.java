package com.solvd.solvdPractice.collections;

import com.solvd.solvdPractice.collections.exceptions.*;
import com.solvd.solvdPractice.collections.people.Cashier;
import com.solvd.solvdPractice.collections.people.Client;
import com.solvd.solvdPractice.collections.service.Cart;
import com.solvd.solvdPractice.collections.service.Product;
import com.solvd.solvdPractice.collections.service.Supermarket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        try {
            cart1.addProduct(new Product("Shampoo", 16.00));
        } catch (CapacityCartException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            cart1.addProduct(new Product("Rice", 20.00));
        } catch (CapacityCartException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            cart1.getProducts();
        } catch (EmptyCartException | ElementNotFound | EmptyLinkedListException e) {
            LOGGER.error(e.getMessage());
        }
        //should throw error because product isn't in the cart.
        try {
            LOGGER.info(cart1.getProduct(5));
        } catch (EmptyLinkedListException | ElementNotFound e) {
            LOGGER.error(e.getMessage() + " Error id ");
        }
        //endregion
    }
}
