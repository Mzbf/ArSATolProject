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

/**
 * A Insecte.
 */
@Entity
@Table(name = "insecte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Insecte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_insecte", nullable = false)
    private String nomInsecte;

    @NotNull
    @Column(name = "nom_scien_insecte", nullable = false)
    private String nomScienInsecte;

    @Column(name = "insecte_image")
    private String insecteImage;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "cycle_bio")
    private String cycleBio;

    @Lob
    @Column(name = "autre_plante")
    private String autrePlante;

    @Column(name = "image_cycle")
    private String imageCycle;

    @Column(name = "date_validation")
    private Instant dateValidation;

    @Column(name = "date_ajout")
    private Instant dateAjout;

    @OneToMany(mappedBy = "insecte")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageInsecte> insectes = new HashSet<>();
    @OneToMany(mappedBy = "insecte")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> listattaques = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("typeInsectes")
    private TypeInsecte typeInsecte;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomInsecte() {
        return nomInsecte;
    }

    public Insecte nomInsecte(String nomInsecte) {
        this.nomInsecte = nomInsecte;
        return this;
    }

    public void setNomInsecte(String nomInsecte) {
        this.nomInsecte = nomInsecte;
    }

    public String getNomScienInsecte() {
        return nomScienInsecte;
    }

    public Insecte nomScienInsecte(String nomScienInsecte) {
        this.nomScienInsecte = nomScienInsecte;
        return this;
    }

    public void setNomScienInsecte(String nomScienInsecte) {
        this.nomScienInsecte = nomScienInsecte;
    }

    public String getInsecteImage() {
        return insecteImage;
    }

    public Insecte insecteImage(String insecteImage) {
        this.insecteImage = insecteImage;
        return this;
    }

    public void setInsecteImage(String insecteImage) {
        this.insecteImage = insecteImage;
    }

    public String getDescription() {
        return description;
    }

    public Insecte description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCycleBio() {
        return cycleBio;
    }

    public Insecte cycleBio(String cycleBio) {
        this.cycleBio = cycleBio;
        return this;
    }

    public void setCycleBio(String cycleBio) {
        this.cycleBio = cycleBio;
    }

    public String getAutrePlante() {
        return autrePlante;
    }

    public Insecte autrePlante(String autrePlante) {
        this.autrePlante = autrePlante;
        return this;
    }

    public void setAutrePlante(String autrePlante) {
        this.autrePlante = autrePlante;
    }

    public String getImageCycle() {
        return imageCycle;
    }

    public Insecte imageCycle(String imageCycle) {
        this.imageCycle = imageCycle;
        return this;
    }

    public void setImageCycle(String imageCycle) {
        this.imageCycle = imageCycle;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public Insecte dateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
        return this;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Instant getDateAjout() {
        return dateAjout;
    }

    public Insecte dateAjout(Instant dateAjout) {
        this.dateAjout = dateAjout;
        return this;
    }

    public void setDateAjout(Instant dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Set<ImageInsecte> getInsectes() {
        return insectes;
    }

    public Insecte insectes(Set<ImageInsecte> imageInsectes) {
        this.insectes = imageInsectes;
        return this;
    }

    public Insecte addInsecte(ImageInsecte imageInsecte) {
        this.insectes.add(imageInsecte);
        imageInsecte.setInsecte(this);
        return this;
    }

    public Insecte removeInsecte(ImageInsecte imageInsecte) {
        this.insectes.remove(imageInsecte);
        imageInsecte.setInsecte(null);
        return this;
    }

    public void setInsectes(Set<ImageInsecte> imageInsectes) {
        this.insectes = imageInsectes;
    }

    public Set<Attaque> getListattaques() {
        return listattaques;
    }

    public Insecte listattaques(Set<Attaque> attaques) {
        this.listattaques = attaques;
        return this;
    }

    public Insecte addListattaque(Attaque attaque) {
        this.listattaques.add(attaque);
        attaque.setInsecte(this);
        return this;
    }

    public Insecte removeListattaque(Attaque attaque) {
        this.listattaques.remove(attaque);
        attaque.setInsecte(null);
        return this;
    }

    public void setListattaques(Set<Attaque> attaques) {
        this.listattaques = attaques;
    }

    public TypeInsecte getTypeInsecte() {
        return typeInsecte;
    }

    public Insecte typeInsecte(TypeInsecte typeInsecte) {
        this.typeInsecte = typeInsecte;
        return this;
    }

    public void setTypeInsecte(TypeInsecte typeInsecte) {
        this.typeInsecte = typeInsecte;
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
        Insecte insecte = (Insecte) o;
        if (insecte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insecte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Insecte{" +
            "id=" + getId() +
            ", nomInsecte='" + getNomInsecte() + "'" +
            ", nomScienInsecte='" + getNomScienInsecte() + "'" +
            ", insecteImage='" + getInsecteImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", cycleBio='" + getCycleBio() + "'" +
            ", autrePlante='" + getAutrePlante() + "'" +
            ", imageCycle='" + getImageCycle() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            "}";
    }
}
