/**
 * Objects which stores the no of seats and timestamp of HeldSeats
 * Have an identifier for the total held seats collections.
 * This identifier can be used as a confirmation Id;
 */

package com.ksundara.walmart.ticketservice.data;

import java.util.List;
import java.util.UUID;

public class SeatHold {
	

	private UUID seatHeldId;
	private List<Seat> seatsHeld;
	private long timeStamp;
	
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public UUID getSeatHeldId() {
		return seatHeldId;
	}
	public void setSeatHeldId(UUID seatHeldId) {
		this.seatHeldId = seatHeldId;
	}
	public List<Seat> getSeatsHeld() {
		return seatsHeld;
	}
	public void setSeatsHeld(List<Seat> seatsHeld) {
		this.seatsHeld = seatsHeld;
	}
	
	@Override
	public String toString() {
		return "SeatHold [seatHeldId=" + seatHeldId + ", seatsHeld="
				+ seatsHeld + "]";
	}
}	
