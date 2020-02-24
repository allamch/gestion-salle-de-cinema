package com.cinema.webcinema;

import com.cinema.webcinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebcinemaApplication implements CommandLineRunner {
@Autowired
    private ICinemaInitService iCinemaInitService;
    public static void main(String[] args) {
        SpringApplication.run(WebcinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
iCinemaInitService.initVilles();
iCinemaInitService.initCinemas();
iCinemaInitService.initSalles();
iCinemaInitService.initPlaces();
iCinemaInitService.initCategories();
iCinemaInitService.initFilms();
iCinemaInitService.initProjections();
iCinemaInitService.initTickets();
    }
}
