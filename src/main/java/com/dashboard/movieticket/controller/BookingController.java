package com.dashboard.movieticket.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.movieticket.model.BookingResponse;
import com.dashboard.movieticket.model.ShowModel;
import com.dashboard.movieticket.model.Ticket;
import com.dashboard.movieticket.service.BookingService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/shows/{showId}")
    public ShowModel getShow(@PathVariable int showId) {
        return bookingService.getShow(showId);
    }

	@GetMapping("/shows")
    public Collection<ShowModel> getAllShows() {
        return bookingService.getAllShows();
    }
	
    @PostMapping("/shows/{showId}/book")
    public BookingResponse  bookSeats(@PathVariable int showId, @RequestBody Set<String> seats) {
        return bookingService.bookSeats(showId, seats);
    }

    @GetMapping("/sales")
    public Map<String, Double> getTotalSales() {
        return bookingService.getTotalSales();
    }
    
}
