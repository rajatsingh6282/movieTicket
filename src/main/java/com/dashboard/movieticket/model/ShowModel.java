package com.dashboard.movieticket.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShowModel {
	private int showId;
    private String audi;
    private Set<String> availableSeats;
    private Set<String> bookedSeats;
    
    public ShowModel(int showId, String audi, LinkedHashSet<String> availableSeats) {
        this.showId = showId;
        this.audi = audi;
        this.availableSeats = new LinkedHashSet<>(availableSeats);
        this.bookedSeats = new HashSet<>();
    }
    
    public int getShowId() {
        return showId;
    }

    public String getAudi() {
        return audi;
    }

    public Set<String> getAvailableSeats() {
        return availableSeats;
    }

    public Set<String> getBookedSeats() {
        return bookedSeats;
    }
    
    public boolean bookSeats(Set<String> seats) {
        if (availableSeats.containsAll(seats)) {
            //availableSeats.removeAll(seats);
            bookedSeats.addAll(seats);
            return true;
        }
        return false;
    }
    
    
}
