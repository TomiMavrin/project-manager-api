package com.tomimavrin.projectmanager.controller;

import com.tomimavrin.projectmanager.model.Board;
import com.tomimavrin.projectmanager.model.Column;
import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.response.Response;
import com.tomimavrin.projectmanager.service.BoardService;
import com.tomimavrin.projectmanager.service.ColumnService;
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
public class BoardController {

    private final TicketService ticketService;
    private final UserService userService;
    private final BoardService boardService;
    private final ColumnService columnService;

    @Autowired
    public BoardController(TicketService ticketService, UserService userService, BoardService boardService, ColumnService columnService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.boardService = boardService;
        this.columnService = columnService;
    }

    @PostMapping("/board/create")
    public Response createBoard(@RequestBody Board board){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        int result = boardService.createBoard(board, userId);
        if(result == 0){
            return new Response("success", "Board successfully created");
        }
        else{
            return new Response("failure", "Something went wrong.");
        }
    }

    @GetMapping("/user/boards")
    public List<Board> getAllUserBoards(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        return boardService.getAllUserBoards(userId);
    }


    @GetMapping("/user/board")
    public Board getBoard(@RequestParam UUID boardId){
        Board board = null;
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        if(this.boardService.checkBoard(boardId,userId)){
            board = this.boardService.getBoard(boardId);
            board.setColumnList(this.columnService.getBoardColumns(boardId));
            for (Column column: board.getColumnList()) {
                column.setTickets(this.ticketService.getColumnTickets(column.getId()));
            }
        }
        return board;
    }

    @PostMapping("/column/create")
    public Column createColumn(@RequestBody Column column){
        return this.columnService.createColumn(column);
    }

    @PostMapping("/column/delete")
    public Response deleteColumn(@RequestParam UUID columnId){
        int response =  this.columnService.deleteColumn(columnId);
        if(response == 1){
            return new Response("success", "Column successfully deleted");
        }
        else{
            return new Response("failure", "Something went wrong.");
        }
    }

    @PostMapping("/ticket/create")
    public Ticket createTicket(@RequestBody Ticket ticket){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(auth.getName());
        UUID userId = user.get().getId();
        return this.ticketService.createTicket(ticket, userId);
    }

    @PostMapping("/ticket/move")
    public Ticket moveTicket(@RequestBody Ticket ticket){
        return this.ticketService.moveTicket(ticket);
    }

    @PostMapping("/ticket/delete")
    public Response deleteTicket(@RequestParam UUID ticketId){
        int result =  this.ticketService.deleteTicket(ticketId);
        if(result == 1){
            return new Response("success", "Ticket successfully deleted!");
        }
        else {
            return new Response("failure", "Failed to delete a ticket!");
        }
    }
}
