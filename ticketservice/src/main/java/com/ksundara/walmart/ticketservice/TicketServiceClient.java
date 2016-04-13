package com.ksundara.walmart.ticketservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ksundara.walmart.ticketservice.config.SpringConfig;
import com.ksundara.walmart.ticketservice.dao.TicketDao;

public class TicketServiceClient {
	
	public static void main(String args[]) {
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(SpringConfig.class);
		//ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Config.xml");
		TicketDao dao = (TicketDao) ctx.getBean(TicketDao.class);
		dao.getCurrentTotalAvailableSeats();
		//cfg.orchestra_availableSeats();
	}

}
