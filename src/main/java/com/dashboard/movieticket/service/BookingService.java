package com.dashboard.movieticket.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.dashboard.movieticket.model.BookingResponse;
import com.dashboard.movieticket.model.ShowModel;
import com.dashboard.movieticket.model.Ticket;

@Service
public class BookingService {
	
	private Map<Integer, ShowModel> shows = new HashMap<>();
    private double totalRevenue = 0.0;
    private double totalServiceTax = 0.0;
    private double totalSwachhBharatCess = 0.0;
    private double totalKrishiKalyanCess = 0.0;


    public BookingService() {
        shows.put(1, new ShowModel(1, "audi1", new LinkedHashSet<>(Arrays.asList(
                "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9",
                "B1", "B2", "B3", "B4", "B5", "B6",
                "C2", "C3", "C4", "C5", "C6", "C7"
        ))));
        shows.put(2, new ShowModel(2, "audi2", new LinkedHashSet<>(Arrays.asList(
                "A1", "A2", "A3", "A4", "A5", "A6", "A7",
                "B2", "B3", "B4", "B5", "B6",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7"
        ))));
        shows.put(3, new ShowModel(3, "audi3", new LinkedHashSet<>(Arrays.asList(
                "A1", "A2", "A3", "A4", "A5", "A6", "A7",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9"
        ))));
    }

    public ShowModel getShow(int showId) {
        return shows.get(showId);
    }

    public Collection<ShowModel> getAllShows() {
        return shows.values();
    }
    
    public BookingResponse bookSeats(int showId, Set<String> seats) {
        ShowModel show = shows.get(showId);
        if (show != null) {
            Set<String> unavailableSeats = new HashSet<>(seats);
            unavailableSeats.removeAll(show.getAvailableSeats());
            if (!unavailableSeats.isEmpty()) {
                return new BookingResponse("Seats not available: " + unavailableSeats, null);
            }

            if (show.bookSeats(seats)) {
            	CalculateService calculate = new CalculateService();
                double subtotal = calculate.calculateSubtotal(seats);
                Ticket ticket = new Ticket(showId, subtotal);
                totalRevenue += subtotal;
                totalServiceTax += ticket.getServiceTax();
                totalSwachhBharatCess += ticket.getSwachhBharatCess();
                totalKrishiKalyanCess += ticket.getKrishiKalyanCess();
                return new BookingResponse("Booking successful", ticket);
            }
        }
        return new BookingResponse("Show not found or booking failed", null);
    }
    
//    private double calculateSubtotal(Set<String> seats) {
//        double subtotal = 0.0;
//        for (String seat : seats) {
//            char row = seat.charAt(0);
//            switch (row) {
//                case 'A':
//                    subtotal += 320;
//                    break;
//                case 'B':
//                    subtotal += 280;
//                    break;
//                case 'C':
//                    subtotal += 240;
//                    break;
//                default:
//                    break;
//            }
//        }
//        return subtotal;
//    }

    public Map<String, Double> getTotalSales() {
        Map<String, Double> sales = new HashMap<>();
        sales.put("Revenue", totalRevenue);
        sales.put("Service Tax", totalServiceTax);
        sales.put("Swachh Bharat Cess", totalSwachhBharatCess);
        sales.put("Krishi Kalyan Cess", totalKrishiKalyanCess);
        return sales;
    }
}
