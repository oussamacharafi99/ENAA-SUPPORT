package com.ENAA_SUPPORT.Service;
import com.ENAA_SUPPORT.Repository.TicketRepo;
import com.ENAA_SUPPORT.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private UserRepo userRepo;

}
