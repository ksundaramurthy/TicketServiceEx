/**
 * 
 */
package com.ksundara.walmart.ticketservice.data;

/**
 * @author Kandavel
 *
 */
public class Seat {
	

	public int seatId;
	public int venueLevel;
	
	public Seat(int seatId, int seatLevel) {
		this.seatId = seatId;
		this.venueLevel = seatLevel;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getVenueLevel() {
		return venueLevel;
	}

	public void setVenueLevel(int venueLevel) {
		this.venueLevel = venueLevel;
	}
	

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", venueLevel=" + venueLevel + "]";
	}
	
}
