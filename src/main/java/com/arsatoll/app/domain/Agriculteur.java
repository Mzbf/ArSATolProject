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

    @Column(name = "localistion")
    private String localistion;

    @OneToMany(mappedBy = "agriculteur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Image> imageSends = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalistion() {
        return localistion;
    }

    public Agriculteur localistion(String localistion) {
        this.localistion = localistion;
        return this;
    }

    public void setLocalistion(String localistion) {
        this.localistion = localistion;
    }

    public Set<Image> getImageSends() {
        return imageSends;
    }

    public Agriculteur imageSends(Set<Image> images) {
        this.imageSends = images;
        return this;
    }

    public Agriculteur addImageSend(Image image) {
        this.imageSends.add(image);
        image.setAgriculteur(this);
        return this;
    }

    public Agriculteur removeImageSend(Image image) {
        this.imageSends.remove(image);
        image.setAgriculteur(null);
        return this;
    }

    public void setImageSends(Set<Image> images) {
        this.imageSends = images;
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
            ", localistion='" + getLocalistion() + "'" +
            "}";
    }
}
