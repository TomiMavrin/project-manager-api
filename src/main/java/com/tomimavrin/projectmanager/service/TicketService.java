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
    public TicketService(@Qualifier("tickets") TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket createTicket(Ticket ticket, UUID userId){
        return this.ticketDao.createTicket(ticket, userId);
    }

    public List<Ticket> getColumnTickets(UUID columnId){
        return ticketDao.getColumnTickets(columnId);
    }

    public int deleteTicket(UUID id){
        return this.ticketDao.deleteTicket(id);
    }

    public int editTicket(UUID id){
        return this.ticketDao.editTicket(id);
    }

    public Ticket moveTicket(Ticket ticket){
        return this.ticketDao.moveTicket(ticket);
    }

}
