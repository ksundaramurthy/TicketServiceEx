/**
 * 
 */
package com.ksundara.walmart.ticketservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import com.ksundara.walmart.ticketservice.dao.TicketDao;
import com.ksundara.walmart.ticketservice.data.Seat;
import com.ksundara.walmart.ticketservice.data.SeatHold;

/**
 * @author Kandavel
 *
 */


public class TicketServiceImpl implements ITicketService {
	@Inject TicketDao ticketDaoObj;
	
	/**
	 * This Service call should be used by UI to make certain decision's pertaining to availability.
	 * This check has to be used when user is trying to hold seats, to deal with updates made by other users.
	 */
	
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		int totalNumAvailable = 0;
		if(venueLevel.isPresent()) {
			totalNumAvailable = ticketDaoObj.getAvailableSeatsByVenueLevel(venueLevel.get().intValue());
		}
		else {
			totalNumAvailable = ticketDaoObj.getCurrentTotalAvailableSeats();
		}
		return totalNumAvailable;
	}
	
	/**
	 * If user didnt commit within a specific timelimit which is driven from TicketConfig.properties
	 * Reversal of tickets count should happen.
	 */
	@Override
	public String reserveAndCommitSeats(SeatHold sh, String customerEmail) {
		ticketDaoObj.reserveSeatsAndCommit(sh, customerEmail);
		return customerEmail;
	}
	
	private void updateSeatsAvailable(SeatHold sh) {
		ticketDaoObj.holdAndUpdate(sh);
	}
	
	/**
	 * Construct SeatHoldObject with timeStamp.
	 * 
	 * This service call has to happen when user decides to Hold Seats
	 * after looking at UI.
	 * 
	 * Here the logic is to reduce the totalCount after constructing the object with the timestamp.
	 */
	@Override
	public SeatHold findAndHoldSeats(int requestedNumOfSeats, Optional<Integer> venueMinLevel, Optional<Integer> venueMaxLevel,
			String customerEmail) {
		int availableSeats = 0;
		availableSeats = numSeatsAvailable(venueMinLevel);
		if(requestedNumOfSeats < availableSeats ) {
			// Hold Seats at that level
			List<Seat> nn = new ArrayList<>();
			for(int num = 0 ; num < requestedNumOfSeats; num++) {
				nn.add(new Seat(num,venueMinLevel.get().intValue()));
			}
			// Generate ID for SeatsHeld 
			UUID seatsHeldId = UUID.randomUUID();
			SeatHold sh = new SeatHold();
			sh.setSeatHeldId(seatsHeldId);
			sh.setSeatsHeld(nn);
			Calendar ct = Calendar.getInstance();
			sh.setTimeStamp(ct.getTimeInMillis());
			//ticketDaoObj.reserveAndUpdate(numOfSeats, venueMinLevel.get().intValue());
			updateSeatsAvailable(sh);
		}
		
		return null;
	}
}
