package com.tomimavrin.projectmanager.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class BoardController {

    private final TicketService ticketService;
    private final UserService userService;
    private final BoardService boardService;

    @Autowired
    public BoardController(TicketService ticketService, UserService userService, BoardService boardService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.boardService = boardService;
    }


    @PostMapping("/createTicket")
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.createTicket(ticket);
    }

    @GetMapping("/getAllTickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }


    @PostMapping("/createBoard")
    public Response createBoard(@RequestBody Board board){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        int result = boardService.createBoard(board, userId);
        if(result == 1){
            return new Response("failure", "Something went wrong.");
        }
        else{
            return new Response("success", "Board successfully created");
        }
    }

    @GetMapping("/getAllUserBoards")
    public List<Board> getAllUserBoards(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        return boardService.getAllUserBoards(userId);
    }
}
