package com.cinema.webcinema.entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idProjection;
private Date dateProjection;
private double prixProjection;
@ManyToOne
private Salle salle;
@ManyToOne
private  Film film;
@OneToMany(mappedBy = "projection")
private Collection<Ticket> tickets;
@ManyToOne
private  Seance seance;
}
