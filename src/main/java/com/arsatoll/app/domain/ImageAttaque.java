package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImageAttaque.
 */
@Entity
@Table(name = "image_attaque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImageAttaque implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JsonIgnoreProperties("attaques")
    private Attaque attaque;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageAttaque imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Attaque getAttaque() {
        return attaque;
    }

    public ImageAttaque attaque(Attaque attaque) {
        this.attaque = attaque;
        return this;
    }

    public void setAttaque(Attaque attaque) {
        this.attaque = attaque;
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
        ImageAttaque imageAttaque = (ImageAttaque) o;
        if (imageAttaque.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageAttaque.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageAttaque{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
