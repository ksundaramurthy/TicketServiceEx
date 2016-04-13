/**
 * 
 */
package com.ksundara.walmart.ticketservice.dao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ksundara.walmart.ticketservice.config.SpringConfig;
import com.ksundara.walmart.ticketservice.config.TicketConfig;

/**
 * @author Kandavel
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TicketDaoTest {
	@Autowired  
	TicketDao dao;
	@Inject TicketConfig cfg;
	int orchestra_availableSeats;
	int main_availableSeats;
	int balcony1_availableSeats;
	int balcony2_availableSeats;
	
	@Before public void initialize() {
		orchestra_availableSeats = cfg.orchestra_availableSeats();
		main_availableSeats = cfg.main_availableSeats();
		balcony1_availableSeats = cfg.balcony1_availableSeats();
		balcony2_availableSeats = cfg.balcony2_availableSeats();
	}
	@Test
	public void getAvailableSeatsByVenueLevel() {
		int venueLevel = 1;
		//dao.initSeatsAvailable();
		int no = dao.getAvailableSeatsByVenueLevel(venueLevel);
		Assert.assertEquals(no, 100);
	}

}
