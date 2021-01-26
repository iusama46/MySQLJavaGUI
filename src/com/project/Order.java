package com.project;

/**
 * Created by Ussama Iftikhar on 21-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */
public class Order {
    int orderId;
    int burgerQuantity;
    int coffeeQuantity;
    int coldDrinkQuantity;
    int muffinsQuantity;
    String date;
    String time;
    double totalPrice;

    public Order(int burgerQuantity, int coffeeQuantity, int coldDrinkQuantity, int muffinsQuantity, String date, String time) {

        this.burgerQuantity = burgerQuantity;
        this.coffeeQuantity = coffeeQuantity;
        this.coldDrinkQuantity = coldDrinkQuantity;
        this.muffinsQuantity = muffinsQuantity;
        this.date = date;
        this.time = time;

    }

    public Order(int orderId, int burgerQuantity) {
        this.orderId = orderId;
        this.burgerQuantity = burgerQuantity;
    }

    public Order() {
    }

    public int getMuffinsQuantity() {
        return muffinsQuantity;
    }

    public void setMuffinsQuantity(int muffinsQuantity) {
        this.muffinsQuantity = muffinsQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBurgerQuantity() {
        return burgerQuantity;
    }

    public void setBurgerQuantity(int burgerQuantity) {
        this.burgerQuantity = burgerQuantity;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void setCoffeeQuantity(int coffeeQuantity) {
        this.coffeeQuantity = coffeeQuantity;
    }

    public int getColdDrinkQuantity() {
        return coldDrinkQuantity;
    }

    public void setColdDrinkQuantity(int coldDrinkQuantity) {
        this.coldDrinkQuantity = coldDrinkQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
