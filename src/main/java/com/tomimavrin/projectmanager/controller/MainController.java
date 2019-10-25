package com.tomimavrin.projectmanager.controller;


import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.service.TicketService;
import com.tomimavrin.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> login(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(auth.getName().toString());
    }


    @GetMapping("hello")
    public String hello(@RequestParam String name){
        return "Hello World" + name;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user, @RequestParam String password){
        userService.createUser(user, password);
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
