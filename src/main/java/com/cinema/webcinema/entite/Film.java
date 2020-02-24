package com.cinema.webcinema.entite;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
private long idFilm;
private String titre;
private double duree;
private  String realisateur;
private String description;
private String photo;
private Date dateSortie;
@OneToMany(mappedBy = "film")
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
@ManyToOne
private Categorie categorie;

}
