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
 * A Herbe.
 */
@Entity
@Table(name = "herbe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Herbe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_herbe")
    private String nomHerbe;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "images_herbe")
    private String imagesHerbe;

    @OneToOne
    @JoinColumn(unique = true)
    private MethodeLutte herbeML;

    @OneToMany(mappedBy = "herbe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageHerbe> herbes = new HashSet<>();
    @ManyToMany(mappedBy = "herbes")
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

    public String getNomHerbe() {
        return nomHerbe;
    }

    public Herbe nomHerbe(String nomHerbe) {
        this.nomHerbe = nomHerbe;
        return this;
    }

    public void setNomHerbe(String nomHerbe) {
        this.nomHerbe = nomHerbe;
    }

    public String getDescription() {
        return description;
    }

    public Herbe description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesHerbe() {
        return imagesHerbe;
    }

    public Herbe imagesHerbe(String imagesHerbe) {
        this.imagesHerbe = imagesHerbe;
        return this;
    }

    public void setImagesHerbe(String imagesHerbe) {
        this.imagesHerbe = imagesHerbe;
    }

    public MethodeLutte getHerbeML() {
        return herbeML;
    }

    public Herbe herbeML(MethodeLutte methodeLutte) {
        this.herbeML = methodeLutte;
        return this;
    }

    public void setHerbeML(MethodeLutte methodeLutte) {
        this.herbeML = methodeLutte;
    }

    public Set<ImageHerbe> getHerbes() {
        return herbes;
    }

    public Herbe herbes(Set<ImageHerbe> imageHerbes) {
        this.herbes = imageHerbes;
        return this;
    }

    public Herbe addHerbe(ImageHerbe imageHerbe) {
        this.herbes.add(imageHerbe);
        imageHerbe.setHerbe(this);
        return this;
    }

    public Herbe removeHerbe(ImageHerbe imageHerbe) {
        this.herbes.remove(imageHerbe);
        imageHerbe.setHerbe(null);
        return this;
    }

    public void setHerbes(Set<ImageHerbe> imageHerbes) {
        this.herbes = imageHerbes;
    }

    public Set<Culture> getCultures() {
        return cultures;
    }

    public Herbe cultures(Set<Culture> cultures) {
        this.cultures = cultures;
        return this;
    }

    public Herbe addCulture(Culture culture) {
        this.cultures.add(culture);
        culture.getHerbes().add(this);
        return this;
    }

    public Herbe removeCulture(Culture culture) {
        this.cultures.remove(culture);
        culture.getHerbes().remove(this);
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
        Herbe herbe = (Herbe) o;
        if (herbe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), herbe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Herbe{" +
            "id=" + getId() +
            ", nomHerbe='" + getNomHerbe() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagesHerbe='" + getImagesHerbe() + "'" +
            "}";
    }
}
