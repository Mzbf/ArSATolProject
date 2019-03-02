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
 * A Administrateur.
 */
@Entity
@Table(name = "administrateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Administrateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Insecte> adminAjoutInsectes = new HashSet<>();
    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> adminAjoutAttaques = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Insecte> getAdminAjoutInsectes() {
        return adminAjoutInsectes;
    }

    public Administrateur adminAjoutInsectes(Set<Insecte> insectes) {
        this.adminAjoutInsectes = insectes;
        return this;
    }

    public Administrateur addAdminAjoutInsecte(Insecte insecte) {
        this.adminAjoutInsectes.add(insecte);
        insecte.setAdministrateur(this);
        return this;
    }

    public Administrateur removeAdminAjoutInsecte(Insecte insecte) {
        this.adminAjoutInsectes.remove(insecte);
        insecte.setAdministrateur(null);
        return this;
    }

    public void setAdminAjoutInsectes(Set<Insecte> insectes) {
        this.adminAjoutInsectes = insectes;
    }

    public Set<Attaque> getAdminAjoutAttaques() {
        return adminAjoutAttaques;
    }

    public Administrateur adminAjoutAttaques(Set<Attaque> attaques) {
        this.adminAjoutAttaques = attaques;
        return this;
    }

    public Administrateur addAdminAjoutAttaque(Attaque attaque) {
        this.adminAjoutAttaques.add(attaque);
        attaque.setAdministrateur(this);
        return this;
    }

    public Administrateur removeAdminAjoutAttaque(Attaque attaque) {
        this.adminAjoutAttaques.remove(attaque);
        attaque.setAdministrateur(null);
        return this;
    }

    public void setAdminAjoutAttaques(Set<Attaque> attaques) {
        this.adminAjoutAttaques = attaques;
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
        Administrateur administrateur = (Administrateur) o;
        if (administrateur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), administrateur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Administrateur{" +
            "id=" + getId() +
            "}";
    }
}
