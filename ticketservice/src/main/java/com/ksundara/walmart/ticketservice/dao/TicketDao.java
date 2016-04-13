package com.ksundara.walmart.ticketservice.dao;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.ksundara.walmart.ticketservice.config.TicketConfig;
import com.ksundara.walmart.ticketservice.data.SeatHold;

/**
 * This class technically should be interacting with a Table or some persistance
 * mechanism. You can use spring to inject DS and get/update the details.
 * For a standalone application, you can use DataSourceManager to get connection.
 * 
 * @author Kandavel
 *
 */
@Component
public class TicketDao {
	
	/**
	 *  All these class variables should get the data from Tables or some persistence mechanism.
	 */
	
	int orchestra_availableSeats = 100;
	int main_availableSeats = 100;
	int balcony1_availableSeats = 100;
	int balcony2_availableSeats = 100;
	@Inject TicketConfig cfg;
	
	
	/**
	 * This is sample implementation just for illustration
	 * Here there should be sql query to table to get the available information.
	 * 
	 */
		
	void initSeatsAvailable() {
		orchestra_availableSeats = cfg.orchestra_availableSeats();
		main_availableSeats = cfg.main_availableSeats();
		balcony1_availableSeats = cfg.balcony1_availableSeats();
		balcony2_availableSeats = cfg.balcony2_availableSeats();
	}
	/**
	 * This is sample implementation just for illustration
	 * Here there should be sql query to table to get the available information.
	 * @param venueLevel
	 * @return
	 */
	
	public int getAvailableSeatsByVenueLevel(int venueLevel) {
		switch(venueLevel) {
		case 1: 
			return orchestra_availableSeats;
		case 2: 
			return main_availableSeats;
		case 3: 
			return balcony1_availableSeats;
		case 4: 
			return balcony2_availableSeats;
		default:
			return getCurrentTotalAvailableSeats();
		}
	}
	
	public int getCurrentTotalAvailableSeats() {
		return orchestra_availableSeats + main_availableSeats + balcony1_availableSeats + balcony2_availableSeats;
	}
	
	/**
	 * Make this method synchronized to make sure only one thread 
	 * updates the information.
	 * This should also interact with persisting mechanism (Database) in RealTime
	 * @param noOfSeats
	 * @param venueLevel
	 */
	public synchronized void holdAndUpdate(SeatHold sh) {
		int noOfSeats = sh.getSeatsHeld().size();
		int venueLevel = sh.getSeatsHeld().get(0).venueLevel;
		if(venueLevel == 1) {
			orchestra_availableSeats = orchestra_availableSeats - noOfSeats ;
		}
		else if(venueLevel == 2) {
			main_availableSeats = main_availableSeats - noOfSeats;
		}
		else if(venueLevel == 3) {
			balcony1_availableSeats = balcony1_availableSeats - noOfSeats;
		}
		else if(venueLevel == 4) {
			balcony2_availableSeats = balcony2_availableSeats - noOfSeats;
		}
	}
	
	public synchronized void reserveSeatsAndCommit(SeatHold sh, String custEmail) {
		int noOfSeats = sh.getSeatsHeld().size();
		int venueLevel = sh.getSeatsHeld().get(0).venueLevel;
		long timeStampOfSeatsHeldObj = sh.getTimeStamp();
		Calendar cal = Calendar.getInstance();
		
		// 
		if (cal.getTimeInMillis()- timeStampOfSeatsHeldObj > cfg.rollbacktimeInterval() ) { // RESET totalCount.
			if(venueLevel == 1) {
				orchestra_availableSeats = orchestra_availableSeats + noOfSeats ;
			}
			else if(venueLevel == 2) {
				main_availableSeats = main_availableSeats + noOfSeats;
			}
			else if(venueLevel == 3) {
				balcony1_availableSeats = balcony1_availableSeats + noOfSeats;
			}
			else if(venueLevel == 4) {
				balcony2_availableSeats = balcony2_availableSeats + noOfSeats;
			}
		} else { // seats confirmation is being sent to customerEmail.
			// Send Out email communication.
			//email.send(custEmail);
		}
		
	}
}
