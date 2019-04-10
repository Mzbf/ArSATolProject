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

    @Column(name = "pays")
    private String pays;

    @Column(name = "specialite")
    private String specialite;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

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

    public String getPays() {
        return pays;
    }

    public Chercheur pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Chercheur specialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public User getUser() {
        return user;
    }

    public Chercheur user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
            ", pays='" + getPays() + "'" +
            ", specialite='" + getSpecialite() + "'" +
            "}";
    }
}
