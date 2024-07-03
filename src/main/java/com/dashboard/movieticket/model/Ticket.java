package com.dashboard.movieticket.model;

public class Ticket {
	private int showId;
    private double subtotal;
    private double serviceTax;
    private double swachhBharatCess;
    private double krishiKalyanCess;
    private double total;
    
    public Ticket(int showId, double subtotal) {
        this.showId = showId;
        this.subtotal = subtotal;
        this.serviceTax = subtotal * 0.14;
        this.swachhBharatCess = subtotal * 0.005;
        this.krishiKalyanCess = subtotal * 0.005;
        this.total = subtotal + serviceTax + swachhBharatCess + krishiKalyanCess;
    }
    
    public int getShowId() {
        return showId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public double getSwachhBharatCess() {
        return swachhBharatCess;
    }

    public double getKrishiKalyanCess() {
        return krishiKalyanCess;
    }

    public double getTotal() {
        return total;
    }
    
}
