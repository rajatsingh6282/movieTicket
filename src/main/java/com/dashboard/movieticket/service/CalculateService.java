package com.dashboard.movieticket.service;

import java.util.Set;


public class CalculateService {
	
	public double calculateSubtotal(Set<String> seats) {
      double subtotal = 0.0;
      for (String seat : seats) {
          char row = seat.charAt(0);
          switch (row) {
              case 'A':
                  subtotal += 320;
                  break;
              case 'B':
                  subtotal += 280;
                  break;
              case 'C':
                  subtotal += 240;
                  break;
              default:
                  break;
          }
      }
      return subtotal;
  }
}
