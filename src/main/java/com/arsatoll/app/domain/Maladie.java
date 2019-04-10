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
 * A Maladie.
 */
@Entity
@Table(name = "maladie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Maladie implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_maladie")
    private String nomMaladie;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "images_maladie")
    private String imagesMaladie;

    @OneToOne
    @JoinColumn(unique = true)
    private MethodeLutte maladieML;

    @OneToMany(mappedBy = "maladie")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageMaladie> maladies = new HashSet<>();
    @ManyToMany(mappedBy = "maladies")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Culture> cultures = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public Maladie nomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
        return this;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public String getDescription() {
        return description;
    }

    public Maladie description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesMaladie() {
        return imagesMaladie;
    }

    public Maladie imagesMaladie(String imagesMaladie) {
        this.imagesMaladie = imagesMaladie;
        return this;
    }

    public void setImagesMaladie(String imagesMaladie) {
        this.imagesMaladie = imagesMaladie;
    }

    public MethodeLutte getMaladieML() {
        return maladieML;
    }

    public Maladie maladieML(MethodeLutte methodeLutte) {
        this.maladieML = methodeLutte;
        return this;
    }

    public void setMaladieML(MethodeLutte methodeLutte) {
        this.maladieML = methodeLutte;
    }

    public Set<ImageMaladie> getMaladies() {
        return maladies;
    }

    public Maladie maladies(Set<ImageMaladie> imageMaladies) {
        this.maladies = imageMaladies;
        return this;
    }

    public Maladie addMaladie(ImageMaladie imageMaladie) {
        this.maladies.add(imageMaladie);
        imageMaladie.setMaladie(this);
        return this;
    }

    public Maladie removeMaladie(ImageMaladie imageMaladie) {
        this.maladies.remove(imageMaladie);
        imageMaladie.setMaladie(null);
        return this;
    }

    public void setMaladies(Set<ImageMaladie> imageMaladies) {
        this.maladies = imageMaladies;
    }

    public Set<Culture> getCultures() {
        return cultures;
    }

    public Maladie cultures(Set<Culture> cultures) {
        this.cultures = cultures;
        return this;
    }

    public Maladie addCulture(Culture culture) {
        this.cultures.add(culture);
        culture.getMaladies().add(this);
        return this;
    }

    public Maladie removeCulture(Culture culture) {
        this.cultures.remove(culture);
        culture.getMaladies().remove(this);
        return this;
    }

    public void setCultures(Set<Culture> cultures) {
        this.cultures = cultures;
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
        Maladie maladie = (Maladie) o;
        if (maladie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maladie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Maladie{" +
            "id=" + getId() +
            ", nomMaladie='" + getNomMaladie() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagesMaladie='" + getImagesMaladie() + "'" +
            "}";
    }
}
