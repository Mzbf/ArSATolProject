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
 * A Agriculteur.
 */
@Entity
@Table(name = "agriculteur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Agriculteur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialite")
    private String specialite;

    @OneToOne
    @JoinColumn(unique = true)
    private ZoneGeo pays;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "agriculteur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageEnvoye> agriculteurs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Agriculteur specialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public ZoneGeo getPays() {
        return pays;
    }

    public Agriculteur pays(ZoneGeo zoneGeo) {
        this.pays = zoneGeo;
        return this;
    }

    public void setPays(ZoneGeo zoneGeo) {
        this.pays = zoneGeo;
    }

    public User getUser() {
        return user;
    }

    public Agriculteur user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ImageEnvoye> getAgriculteurs() {
        return agriculteurs;
    }

    public Agriculteur agriculteurs(Set<ImageEnvoye> imageEnvoyes) {
        this.agriculteurs = imageEnvoyes;
        return this;
    }

    public Agriculteur addAgriculteur(ImageEnvoye imageEnvoye) {
        this.agriculteurs.add(imageEnvoye);
        imageEnvoye.setAgriculteur(this);
        return this;
    }

    public Agriculteur removeAgriculteur(ImageEnvoye imageEnvoye) {
        this.agriculteurs.remove(imageEnvoye);
        imageEnvoye.setAgriculteur(null);
        return this;
    }

    public void setAgriculteurs(Set<ImageEnvoye> imageEnvoyes) {
        this.agriculteurs = imageEnvoyes;
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
        Agriculteur agriculteur = (Agriculteur) o;
        if (agriculteur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agriculteur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Agriculteur{" +
            "id=" + getId() +
            ", specialite='" + getSpecialite() + "'" +
            "}";
    }
}
