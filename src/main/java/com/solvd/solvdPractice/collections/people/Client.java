package com.solvd.solvdPractice.collections.people;

import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;
import com.solvd.solvdPractice.collections.exceptions.EmptyLinkedListException;
import com.solvd.solvdPractice.collections.service.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client extends Person implements IBuy {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private Cart cart;
    private Double money;

    public Client(String name, Integer age, Cart cart, Double money) {
        super(name, age);
        this.cart = cart;
        this.money = money;
    }
    @Override
    public void countOfProducts() {
        LOGGER.info("The cart have: " + cart.countOfProducts());
    }
    @Override
    public double amountToPay() {
        double accumulator = 0;
        for (int i = 0; i < cart.countOfProducts(); i++) {

            try {
                accumulator += cart.getProduct(i).getPrice();
            } catch (EmptyLinkedListException | ElementNotFound e) {
                LOGGER.error(e.getMessage());
            }
        }
        return accumulator;
    }
    @Override
    public void paymentForms(String wayToPay) {
        LOGGER.info("Payment form: " + wayToPay);
    }

    public Cart getCart() {
        return cart;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
