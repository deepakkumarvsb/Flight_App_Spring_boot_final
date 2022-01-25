package com.flightapp.UserService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.UserService.model.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>{

	public Iterable<TicketEntity> findByUserIdAndStatusCode(long userId, long statusCode);
	
	public Iterable<TicketEntity> findByFlightId(long fligthId);
	
	public TicketEntity findByTicketId(long ticketId);
	
	public TicketEntity findByPnrNumber(String pnrNumber);
	
}
