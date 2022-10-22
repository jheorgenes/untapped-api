package com.pj.untapped.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.repositories.TicketRepository;

@Service
public class TicketService {

  @Autowired
  public TicketRepository ticketRepository;
}
