package com.solvd.solvdPractice.collections.people;

import com.solvd.solvdPractice.collections.service.Cart;

public class Client extends Person implements IBuy {
    private Cart cart;
    private Double money;

    public Client(String name, Integer age, Cart cart, Double money) {
        super(name, age);
        this.cart = cart;
        this.money = money;
    }
    @Override
    public void countOfProducts() {

    }
    @Override
    public void amountToPay() {

    }
    @Override
    public void paymentForms(String wayToPay) {

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
