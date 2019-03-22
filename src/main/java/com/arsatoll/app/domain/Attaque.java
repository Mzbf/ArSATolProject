package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.arsatoll.app.domain.enumeration.Localisation;

import com.arsatoll.app.domain.enumeration.TypeDegat;

/**
 * A Attaque.
 */
@Entity
@Table(name = "attaque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attaque implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "localisation", nullable = false)
    private Localisation localisation;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "flag")
    private Boolean flag;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_degat", nullable = false)
    private TypeDegat typeDegat;

    @Column(name = "date_validation")
    private Instant dateValidation;

    @Column(name = "date_ajout")
    private Instant dateAjout;

    @ManyToOne
    @JsonIgnoreProperties("attaques")
    private Insecte insecte;

    @OneToMany(mappedBy = "attaque")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageAttaque> imageAttaques = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("attaques")
    private Culture culture;

    @ManyToOne
    @JsonIgnoreProperties("ajoutAttaques")
    private Chercheur chercheur;

    @ManyToOne
    @JsonIgnoreProperties("adminAjoutAttaques")
    private Administrateur administrateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public Attaque localisation(Localisation localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public Attaque description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isFlag() {
        return flag;
    }

    public Attaque flag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public TypeDegat getTypeDegat() {
        return typeDegat;
    }

    public Attaque typeDegat(TypeDegat typeDegat) {
        this.typeDegat = typeDegat;
        return this;
    }

    public void setTypeDegat(TypeDegat typeDegat) {
        this.typeDegat = typeDegat;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public Attaque dateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
        return this;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Instant getDateAjout() {
        return dateAjout;
    }

    public Attaque dateAjout(Instant dateAjout) {
        this.dateAjout = dateAjout;
        return this;
    }

    public void setDateAjout(Instant dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Insecte getInsecte() {
        return insecte;
    }

    public Attaque insecte(Insecte insecte) {
        this.insecte = insecte;
        return this;
    }

    public void setInsecte(Insecte insecte) {
        this.insecte = insecte;
    }

    public Set<ImageAttaque> getImageAttaques() {
        return imageAttaques;
    }

    public Attaque imageAttaques(Set<ImageAttaque> imageAttaques) {
        this.imageAttaques = imageAttaques;
        return this;
    }


    public Attaque removeImageAttaque(ImageAttaque imageAttaque) {
        this.imageAttaques.remove(imageAttaque);
        imageAttaque.setAttaque(null);
        return this;
    }

    public void setImageAttaques(Set<ImageAttaque> imageAttaques) {
        this.imageAttaques = imageAttaques;
    }

    public Culture getCulture() {
        return culture;
    }

    public Attaque culture(Culture culture) {
        this.culture = culture;
        return this;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public Chercheur getChercheur() {
        return chercheur;
    }

    public Attaque chercheur(Chercheur chercheur) {
        this.chercheur = chercheur;
        return this;
    }

    public void setChercheur(Chercheur chercheur) {
        this.chercheur = chercheur;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public Attaque administrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
        return this;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
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
        Attaque attaque = (Attaque) o;
        if (attaque.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attaque.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attaque{" +
            "id=" + getId() +
            ", localisation='" + getLocalisation() + "'" +
            ", description='" + getDescription() + "'" +
            ", flag='" + isFlag() + "'" +
            ", typeDegat='" + getTypeDegat() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            "}";
    }
}
