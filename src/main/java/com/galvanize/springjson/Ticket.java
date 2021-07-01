package com.galvanize.springjson;

public class Ticket {
    //key
    private Passenger passenger;
    //key
    private int price;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
