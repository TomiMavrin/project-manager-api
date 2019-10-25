package com.tomimavrin.projectmanager.controller;


import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.service.TicketService;
import com.tomimavrin.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public MainController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public class TestingResponse{
        final String name;

        public TestingResponse(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @PostMapping("/login")
    public TestingResponse login(){
        return new TestingResponse("testing testing");
    }


    @GetMapping("hello")
    public String hello(@RequestParam String name){
        return "Hello World" + name;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.createUser(user);
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
