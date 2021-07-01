package com.galvanize.springjson;

import java.util.ArrayList;

public class Tickets {

    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
