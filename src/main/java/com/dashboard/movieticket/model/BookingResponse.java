package com.dashboard.movieticket.model;

public class BookingResponse {
	private String message;
    private Ticket ticket;
    
    public BookingResponse(String message, Ticket ticket) {
        this.message = message;
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
