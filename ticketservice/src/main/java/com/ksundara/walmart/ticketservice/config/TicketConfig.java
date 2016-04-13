/**
 * 
 */
package com.ksundara.walmart.ticketservice.config;

import org.aeonbits.owner.Config;

/**
 * This aeonbits API is widely used in our project and it abstracts/simplifies the Properties file handling in a big way.
 * 
 * @author Kandavel
 *
 */

public interface TicketConfig extends Config {
	
	int rollbacktimeInterval();
	int orchestra_availableSeats();
	int main_availableSeats();
	int balcony1_availableSeats();
	int balcony2_availableSeats();
    String hostname();
    @DefaultValue("42")
    int maxThreads();
}
