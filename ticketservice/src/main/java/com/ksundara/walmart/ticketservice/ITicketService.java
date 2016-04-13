/**
 * 
 */
package com.ksundara.walmart.ticketservice;

import java.util.Optional;

import com.ksundara.walmart.ticketservice.data.SeatHold;


/**
 * @author Kandavel
 *
 */
public interface ITicketService {
	
	int numSeatsAvailable(Optional<Integer> venueLevel);
	SeatHold findAndHoldSeats(int numOfSeats, Optional<Integer> venueMinLevel, Optional<Integer> venueMaxLevel, String customerEmail );
	String reserveAndCommitSeats(SeatHold sh, String customerEmail);

}
