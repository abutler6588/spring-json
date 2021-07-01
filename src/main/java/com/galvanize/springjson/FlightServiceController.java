package com.galvanize.springjson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightServiceController {

    @PostMapping("/flights/tickets/total")
    public int testTicketTotal(@RequestBody Tickets tickets)  {
        int sum = 0;

        for(Ticket ticket: tickets.getTickets()) {
            sum += ticket.getPrice();
        }
        return sum;
    }

}
