package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Image.
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "date_d_ajout")
    private Instant dateDAjout;

    @Column(name = "date_validation")
    private Instant dateValidation;

    @Column(name = "flag")
    private Boolean flag;

    @ManyToOne
    @JsonIgnoreProperties("imageInsectes")
    private Insecte insecte;

    @ManyToOne
    @JsonIgnoreProperties("imageAttaques")
    private Attaque attaque;

    @ManyToOne
    @JsonIgnoreProperties("imageSends")
    private Agriculteur agriculteur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Image urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Instant getDateDAjout() {
        return dateDAjout;
    }

    public Image dateDAjout(Instant dateDAjout) {
        this.dateDAjout = dateDAjout;
        return this;
    }

    public void setDateDAjout(Instant dateDAjout) {
        this.dateDAjout = dateDAjout;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public Image dateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
        return this;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Boolean isFlag() {
        return flag;
    }

    public Image flag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Insecte getInsecte() {
        return insecte;
    }

    public Image insecte(Insecte insecte) {
        this.insecte = insecte;
        return this;
    }

    public void setInsecte(Insecte insecte) {
        this.insecte = insecte;
    }

    public Attaque getAttaque() {
        return attaque;
    }

    public Image attaque(Attaque attaque) {
        this.attaque = attaque;
        return this;
    }

    public void setAttaque(Attaque attaque) {
        this.attaque = attaque;
    }

    public Agriculteur getAgriculteur() {
        return agriculteur;
    }

    public Image agriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
        return this;
    }

    public void setAgriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
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
        Image image = (Image) o;
        if (image.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), image.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", urlImage='" + getUrlImage() + "'" +
            ", dateDAjout='" + getDateDAjout() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", flag='" + isFlag() + "'" +
            "}";
    }
}
