package com.cinema.webcinema.services;

import com.cinema.webcinema.Dao.*;
import com.cinema.webcinema.entite.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImplimentation  implements ICinemaInitService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private  PlaceRepository placeRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private  Ticketrepository ticketrepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private  FilmRepository filmRepository;
    @Override
    public void initVilles() {
        Stream.of("Tunis","Sousse","Sfax","Gabes","gafsa").forEach(v->{Ville ville = new Ville();
        ville.setName(v);
        villeRepository.save(ville);

        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("netflix","megastream","palmaruim","voirfilms","newfilms").forEach(cinemaName->{
                Cinema cin = new Cinema();
                cin.setNameCinema(cinemaName);
                cin.setNombreSalles(3+(int)Math.random()*7);
                cin.setVille(v);

                cinemaRepository.save(cin);
            });
        });

    }

    @Override
    public void initSalles() {
       cinemaRepository.findAll().forEach(cinema -> {
for(int i=0;i<cinema.getNombreSalles();i++){
    Salle salle = new Salle();
    salle.setNomSalle("salle"+(i+1));
    salle.setCinema(cinema);
    salle.setNombrePlace(15+(int)(Math.random()*20));
salleRepository.save(salle);
}
       });

    }

    @Override
    public void initSeances() {

                DateFormat dateFormat=  new SimpleDateFormat("HH:mm");
                Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s -> {

                    try {
                        Seance seance=new Seance();
                seance.setHeureDebut(dateFormat.parse(s) );
                seanceRepository.save(seance);

            }

            catch (ParseException e){
                e.printStackTrace();
            }
                });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0; i<salle.getNombrePlace();i++ )
            {
                Place place = new Place();
                place.setNumPlace(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void initCategories() {
Stream.of("Histoire","Action","Fiction","Drama").forEach(cat->{
    Categorie categorie = new Categorie();
    categorie.setLibele(cat);
    categorieRepository.save(categorie);
});
    }

    @Override
    public void initFilms() {
   double[] duree = new double[] {1,1.5,2,2.5,3};
        List<Categorie> categories= categorieRepository.findAll();
Stream.of("GameofThrone", " LelivreDelit", "IronMan","spiderMan","BraveHeart").forEach(titrefilm-> {
    Film film = new Film();
    film.setTitre(titrefilm);
    film.setDuree(duree[new Random().nextInt(duree.length)]);
    film.setPhoto(titrefilm.replaceAll(" ",""));
    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
    filmRepository.save(film);
});
    }

    @Override
    public void initProjections() {
        double [] prices = new double[] {30,50,60,70,90,100};
villeRepository.findAll().forEach(ville -> {
    ville.getCinemas().forEach(cinema -> {
        cinema.getSalles().forEach(salle -> {
            filmRepository.findAll().forEach(film -> {
                seanceRepository.findAll().forEach(seance -> {
                    Projection projection = new Projection();
                    projection.setDateProjection(new Date());
                    projection.setFilm(film);
                    projection.setPrixProjection(prices[ new Random().nextInt(prices.length)]);
                    projection.setSalle(salle);
                    projection.setSeance(seance);
                    projectionRepository.save(projection);
                });
            });
        });
    });
});
    }

    @Override
    public void initTickets() {
projectionRepository.findAll().forEach(projection ->
{
  projection.getSalle().getPlaces().forEach(place -> {
      Ticket ticket = new Ticket();
      ticket.setPlace(place);
      ticket.setPrixAchat(projection.getPrixProjection());
      ticket.setProjection(projection);
      ticket.setReserver(false);
      ticketrepository.save(ticket);
  });
});
}
    }

