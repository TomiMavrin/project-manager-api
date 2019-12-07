package com.tomimavrin.projectmanager.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.response.Response;
import com.tomimavrin.projectmanager.service.BoardService;
import com.tomimavrin.projectmanager.service.TicketService;
import com.tomimavrin.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MainController {

    private final TicketService ticketService;
    private final UserService userService;
    private final BoardService boardService;

    @Autowired
    public MainController(TicketService ticketService, UserService userService, BoardService boardService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.boardService = boardService;
    }
}
