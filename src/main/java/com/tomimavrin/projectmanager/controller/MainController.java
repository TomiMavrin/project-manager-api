package com.tomimavrin.projectmanager.controller;


import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.response.Response;
import com.tomimavrin.projectmanager.service.TicketService;
import com.tomimavrin.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

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
    public Response login(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> response = userService.getUser(auth.getName());
        if(response.isEmpty()){
            return new Response("failure", "Invalid login info.");
        }
        else{
            return new Response("success", response);
        }
    }


    @GetMapping("hello")
    public String hello(@RequestParam String name){
        return "Hello World" + name;
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        try {
            userService.createUser(user);
            return new Response("success", "User registered successfully");
        }
        catch (Exception e){
            return new Response("failure", e.toString());
        }
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
