package com.ksundara.walmart.ticketservice.data;

public enum VenueLevel {
	Orchestra(1),

	Main(2),
	
	Balcony1(3),
	
	Balcony2(4);
	
	private int seatLevel;

	VenueLevel(int seatLevel) {
		this.seatLevel = seatLevel;
	}

	public int getSeatLevel() {
		return seatLevel;
	}

}
