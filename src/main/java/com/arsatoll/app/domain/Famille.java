package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Famille.
 */
@Entity
@Table(name = "famille")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_famille")
    private String nomFamille;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image_famiile")
    private String imageFamiile;

    @ManyToOne
    @JsonIgnoreProperties("ordres")
    private Ordre ordre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public Famille nomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
        return this;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getDescription() {
        return description;
    }

    public Famille description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFamiile() {
        return imageFamiile;
    }

    public Famille imageFamiile(String imageFamiile) {
        this.imageFamiile = imageFamiile;
        return this;
    }

    public void setImageFamiile(String imageFamiile) {
        this.imageFamiile = imageFamiile;
    }

    public Ordre getOrdre() {
        return ordre;
    }

    public Famille ordre(Ordre ordre) {
        this.ordre = ordre;
        return this;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
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
        Famille famille = (Famille) o;
        if (famille.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), famille.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Famille{" +
            "id=" + getId() +
            ", nomFamille='" + getNomFamille() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageFamiile='" + getImageFamiile() + "'" +
            "}";
    }
}
