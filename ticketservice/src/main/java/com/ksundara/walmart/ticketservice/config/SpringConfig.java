/**
 * 
 */
package com.ksundara.walmart.ticketservice.config;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kandavel
 *
 */
@Configuration
@ComponentScan(basePackageClasses = {com.ksundara.walmart.ticketservice.dao.MarkerClass.class, com.ksundara.walmart.ticketservice.MarkerClass1.class, com.ksundara.walmart.ticketservice.config.ConfigMarker.class}) // search the com.company package for @Component classes
public class SpringConfig {
	@Bean
	public TicketConfig ticketConfig() {
		TicketConfig cfg = ConfigFactory.create(TicketConfig.class);
		return cfg;
	}
	
}
