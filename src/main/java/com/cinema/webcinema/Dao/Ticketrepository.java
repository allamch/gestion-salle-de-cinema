package com.cinema.webcinema.Dao;

import com.cinema.webcinema.entite.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Ticketrepository extends JpaRepository<Ticket,Long> {
}
