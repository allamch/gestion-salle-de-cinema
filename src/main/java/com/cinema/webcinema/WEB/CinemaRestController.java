package com.cinema.webcinema.WEB;


import com.cinema.webcinema.Dao.FilmRepository;
import com.cinema.webcinema.Dao.Ticketrepository;
import com.cinema.webcinema.entite.Film;
import com.cinema.webcinema.entite.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private Ticketrepository ticketrepository;
    @GetMapping(path="/imageFilm/{id}",produces= MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name="id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName=f.getPhoto();
        File file = new  File("C:/Users/alexanra/Downloads/cima/webcinema/images/"+photoName+".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);

    }
    @GetMapping("/listFilms")
public List <Film> listFilm(){
        return filmRepository.findAll();
}

 /*   @PostMapping("/payerTickets")
    @Transactional
    public  List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
List<Ticket> ticketList = new ArrayList<>();
       ticketForm.getTickets().forEach(idTicket->{
           Ticket ticket=ticketrepository.findById(idTicket).get();
           ticket.setNomClient(ticketForm.getNomClient());
           ticket.setReserver(true);
           ticket.setCodePayment(ticketForm.getCodePayement());
          ticketrepository.save(ticket);
          ticketList.add(ticket);

       });

return ticketList;
    }
*/

}
@Data
class TicketForm {
    private Integer codePayement;
    private String nomClient;
    private List<Long> tickets = new ArrayList<>();
}
