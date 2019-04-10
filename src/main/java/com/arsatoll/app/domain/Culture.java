package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Culture.
 */
@Entity
@Table(name = "culture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Culture implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_culture", nullable = false)
    private String nomCulture;

    @Column(name = "image_culture")
    private String imageCulture;

    @OneToMany(mappedBy = "culture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageCulture> imagecultures = new HashSet<>();
    @OneToMany(mappedBy = "culture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> listattaques = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "culture_maladie",
               joinColumns = @JoinColumn(name = "culture_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "maladie_id", referencedColumnName = "id"))
    private Set<Maladie> maladies = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "culture_herbe",
               joinColumns = @JoinColumn(name = "culture_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "herbe_id", referencedColumnName = "id"))
    private Set<Herbe> herbes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "culture_zone",
               joinColumns = @JoinColumn(name = "culture_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "zone_id", referencedColumnName = "id"))
    private Set<ZoneGeo> zones = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("typeCultures")
    private TypeCulture typeCulture;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public Culture nomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
        return this;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public String getImageCulture() {
        return imageCulture;
    }

    public Culture imageCulture(String imageCulture) {
        this.imageCulture = imageCulture;
        return this;
    }

    public void setImageCulture(String imageCulture) {
        this.imageCulture = imageCulture;
    }

    public Set<ImageCulture> getImagecultures() {
        return imagecultures;
    }

    public Culture imagecultures(Set<ImageCulture> imageCultures) {
        this.imagecultures = imageCultures;
        return this;
    }

    public Culture addImageculture(ImageCulture imageCulture) {
        this.imagecultures.add(imageCulture);
        imageCulture.setCulture(this);
        return this;
    }

    public Culture removeImageculture(ImageCulture imageCulture) {
        this.imagecultures.remove(imageCulture);
        imageCulture.setCulture(null);
        return this;
    }

    public void setImagecultures(Set<ImageCulture> imageCultures) {
        this.imagecultures = imageCultures;
    }

    public Set<Attaque> getListattaques() {
        return listattaques;
    }

    public Culture listattaques(Set<Attaque> attaques) {
        this.listattaques = attaques;
        return this;
    }

    public Culture addListattaque(Attaque attaque) {
        this.listattaques.add(attaque);
        attaque.setCulture(this);
        return this;
    }

    public Culture removeListattaque(Attaque attaque) {
        this.listattaques.remove(attaque);
        attaque.setCulture(null);
        return this;
    }

    public void setListattaques(Set<Attaque> attaques) {
        this.listattaques = attaques;
    }

    public Set<Maladie> getMaladies() {
        return maladies;
    }

    public Culture maladies(Set<Maladie> maladies) {
        this.maladies = maladies;
        return this;
    }

    public Culture addMaladie(Maladie maladie) {
        this.maladies.add(maladie);
        maladie.getCultures().add(this);
        return this;
    }

    public Culture removeMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
        maladie.getCultures().remove(this);
        return this;
    }

    public void setMaladies(Set<Maladie> maladies) {
        this.maladies = maladies;
    }

    public Set<Herbe> getHerbes() {
        return herbes;
    }

    public Culture herbes(Set<Herbe> herbes) {
        this.herbes = herbes;
        return this;
    }

    public Culture addHerbe(Herbe herbe) {
        this.herbes.add(herbe);
        herbe.getCultures().add(this);
        return this;
    }

    public Culture removeHerbe(Herbe herbe) {
        this.herbes.remove(herbe);
        herbe.getCultures().remove(this);
        return this;
    }

    public void setHerbes(Set<Herbe> herbes) {
        this.herbes = herbes;
    }

    public Set<ZoneGeo> getZones() {
        return zones;
    }

    public Culture zones(Set<ZoneGeo> zoneGeos) {
        this.zones = zoneGeos;
        return this;
    }

    public Culture addZone(ZoneGeo zoneGeo) {
        this.zones.add(zoneGeo);
        zoneGeo.getCultures().add(this);
        return this;
    }

    public Culture removeZone(ZoneGeo zoneGeo) {
        this.zones.remove(zoneGeo);
        zoneGeo.getCultures().remove(this);
        return this;
    }

    public void setZones(Set<ZoneGeo> zoneGeos) {
        this.zones = zoneGeos;
    }

    public TypeCulture getTypeCulture() {
        return typeCulture;
    }

    public Culture typeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
        return this;
    }

    public void setTypeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
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
        Culture culture = (Culture) o;
        if (culture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), culture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Culture{" +
            "id=" + getId() +
            ", nomCulture='" + getNomCulture() + "'" +
            ", imageCulture='" + getImageCulture() + "'" +
            "}";
    }
}
