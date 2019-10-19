package com.tomimavrin.projectmanager.controller;


import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MainController {

    private final TicketService ticketService;

    @Autowired
    public MainController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping("/createTicket")
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.createTicket(ticket);
    }

    @GetMapping("/getAllTickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }
}
