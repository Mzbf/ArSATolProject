package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Chercheur.
 */
@Entity
@Table(name = "chercheur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chercheur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "institut")
    private String institut;

    @OneToMany(mappedBy = "chercheur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Insecte> ajoutInsectes = new HashSet<>();
    @OneToMany(mappedBy = "chercheur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> ajoutAttaques = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitut() {
        return institut;
    }

    public Chercheur institut(String institut) {
        this.institut = institut;
        return this;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public Set<Insecte> getAjoutInsectes() {
        return ajoutInsectes;
    }

    public Chercheur ajoutInsectes(Set<Insecte> insectes) {
        this.ajoutInsectes = insectes;
        return this;
    }

    public Chercheur addAjoutInsecte(Insecte insecte) {
        this.ajoutInsectes.add(insecte);
        insecte.setChercheur(this);
        return this;
    }

    public Chercheur removeAjoutInsecte(Insecte insecte) {
        this.ajoutInsectes.remove(insecte);
        insecte.setChercheur(null);
        return this;
    }

    public void setAjoutInsectes(Set<Insecte> insectes) {
        this.ajoutInsectes = insectes;
    }

    public Set<Attaque> getAjoutAttaques() {
        return ajoutAttaques;
    }

    public Chercheur ajoutAttaques(Set<Attaque> attaques) {
        this.ajoutAttaques = attaques;
        return this;
    }

    public Chercheur addAjoutAttaque(Attaque attaque) {
        this.ajoutAttaques.add(attaque);
        attaque.setChercheur(this);
        return this;
    }

    public Chercheur removeAjoutAttaque(Attaque attaque) {
        this.ajoutAttaques.remove(attaque);
        attaque.setChercheur(null);
        return this;
    }

    public void setAjoutAttaques(Set<Attaque> attaques) {
        this.ajoutAttaques = attaques;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chercheur chercheur = (Chercheur) o;
        if (chercheur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chercheur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chercheur{" +
            "id=" + getId() +
            ", institut='" + getInstitut() + "'" +
            "}";
    }
}
