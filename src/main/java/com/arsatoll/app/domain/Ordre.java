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
 * A Ordre.
 */
@Entity
@Table(name = "ordre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ordre implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_ordre")
    private String nomOrdre;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image_ordre")
    private String imageOrdre;

    @OneToMany(mappedBy = "ordre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Famille> ordres = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomOrdre() {
        return nomOrdre;
    }

    public Ordre nomOrdre(String nomOrdre) {
        this.nomOrdre = nomOrdre;
        return this;
    }

    public void setNomOrdre(String nomOrdre) {
        this.nomOrdre = nomOrdre;
    }

    public String getDescription() {
        return description;
    }

    public Ordre description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageOrdre() {
        return imageOrdre;
    }

    public Ordre imageOrdre(String imageOrdre) {
        this.imageOrdre = imageOrdre;
        return this;
    }

    public void setImageOrdre(String imageOrdre) {
        this.imageOrdre = imageOrdre;
    }

    public Set<Famille> getOrdres() {
        return ordres;
    }

    public Ordre ordres(Set<Famille> familles) {
        this.ordres = familles;
        return this;
    }

    public Ordre addOrdre(Famille famille) {
        this.ordres.add(famille);
        famille.setOrdre(this);
        return this;
    }

    public Ordre removeOrdre(Famille famille) {
        this.ordres.remove(famille);
        famille.setOrdre(null);
        return this;
    }

    public void setOrdres(Set<Famille> familles) {
        this.ordres = familles;
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
        Ordre ordre = (Ordre) o;
        if (ordre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ordre{" +
            "id=" + getId() +
            ", nomOrdre='" + getNomOrdre() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageOrdre='" + getImageOrdre() + "'" +
            "}";
    }
}
