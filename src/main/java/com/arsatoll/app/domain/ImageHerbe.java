package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImageHerbe.
 */
@Entity
@Table(name = "image_herbe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImageHerbe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JsonIgnoreProperties("herbes")
    private Herbe herbe;

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

    public ImageHerbe imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Herbe getHerbe() {
        return herbe;
    }

    public ImageHerbe herbe(Herbe herbe) {
        this.herbe = herbe;
        return this;
    }

    public void setHerbe(Herbe herbe) {
        this.herbe = herbe;
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
        ImageHerbe imageHerbe = (ImageHerbe) o;
        if (imageHerbe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageHerbe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageHerbe{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
