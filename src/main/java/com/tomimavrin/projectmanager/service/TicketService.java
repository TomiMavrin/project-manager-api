package com.tomimavrin.projectmanager.service;

import com.tomimavrin.projectmanager.dao.TicketDao;
import com.tomimavrin.projectmanager.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketDao ticketDao;

    @Autowired
    public TicketService(@Qualifier("postgres") TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public int createTicket(Ticket ticket){
        return this.ticketDao.createTicket(ticket);
    }

    public List<Ticket> getAllTickets(UUID columnId){
        return ticketDao.getAllTickets(columnId);
    }

    public int deleteTicket(UUID id){
        return this.ticketDao.deleteTicket(id);
    }

    public int editTicket(UUID id){
        return this.ticketDao.editTicket(id);
    }


}
