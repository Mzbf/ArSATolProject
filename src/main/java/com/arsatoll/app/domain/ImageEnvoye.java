package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ImageEnvoye.
 */
@Entity
@Table(name = "image_envoye")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImageEnvoye implements Serializable {

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
    @JsonIgnoreProperties("agriculteurs")
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

    public ImageEnvoye urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Instant getDateDAjout() {
        return dateDAjout;
    }

    public ImageEnvoye dateDAjout(Instant dateDAjout) {
        this.dateDAjout = dateDAjout;
        return this;
    }

    public void setDateDAjout(Instant dateDAjout) {
        this.dateDAjout = dateDAjout;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public ImageEnvoye dateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
        return this;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Boolean isFlag() {
        return flag;
    }

    public ImageEnvoye flag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Agriculteur getAgriculteur() {
        return agriculteur;
    }

    public ImageEnvoye agriculteur(Agriculteur agriculteur) {
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
        ImageEnvoye imageEnvoye = (ImageEnvoye) o;
        if (imageEnvoye.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageEnvoye.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageEnvoye{" +
            "id=" + getId() +
            ", urlImage='" + getUrlImage() + "'" +
            ", dateDAjout='" + getDateDAjout() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", flag='" + isFlag() + "'" +
            "}";
    }
}
