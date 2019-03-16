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

    @Lob
    @Column(name = "insecte_image")
    private byte[] insecteImage;

    @Column(name = "insecte_image_content_type")
    private String insecteImageContentType;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "cycle_bio")
    private String cycleBio;

    @Lob
    @Column(name = "autre_plante")
    private String autrePlante;

    @Lob
    @Column(name = "image_cycle")
    private byte[] imageCycle;

    @Column(name = "image_cycle_content_type")
    private String imageCycleContentType;

    @Column(name = "date_validation")
    private Instant dateValidation;

    @Column(name = "date_ajout")
    private Instant dateAjout;

    @OneToOne
    @JoinColumn(unique = true)
    private MethodeLutte methode;

    @OneToMany(mappedBy = "insecte")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> attaques = new HashSet<>();
    @OneToMany(mappedBy = "insecte")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImageAttaque> imageInsectes = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("ajoutInsectes")
    private Chercheur chercheur;

    @ManyToOne
    @JsonIgnoreProperties("adminAjoutInsectes")
    private Administrateur administrateur;

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

    public byte[] getInsecteImage() {
        return insecteImage;
    }

    public Insecte insecteImage(byte[] insecteImage) {
        this.insecteImage = insecteImage;
        return this;
    }

    public void setInsecteImage(byte[] insecteImage) {
        this.insecteImage = insecteImage;
    }

    public String getInsecteImageContentType() {
        return insecteImageContentType;
    }

    public Insecte insecteImageContentType(String insecteImageContentType) {
        this.insecteImageContentType = insecteImageContentType;
        return this;
    }

    public void setInsecteImageContentType(String insecteImageContentType) {
        this.insecteImageContentType = insecteImageContentType;
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

    public byte[] getImageCycle() {
        return imageCycle;
    }

    public Insecte imageCycle(byte[] imageCycle) {
        this.imageCycle = imageCycle;
        return this;
    }

    public void setImageCycle(byte[] imageCycle) {
        this.imageCycle = imageCycle;
    }

    public String getImageCycleContentType() {
        return imageCycleContentType;
    }

    public Insecte imageCycleContentType(String imageCycleContentType) {
        this.imageCycleContentType = imageCycleContentType;
        return this;
    }

    public void setImageCycleContentType(String imageCycleContentType) {
        this.imageCycleContentType = imageCycleContentType;
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

    public MethodeLutte getMethode() {
        return methode;
    }

    public Insecte methode(MethodeLutte methodeLutte) {
        this.methode = methodeLutte;
        return this;
    }

    public void setMethode(MethodeLutte methodeLutte) {
        this.methode = methodeLutte;
    }

    public Set<Attaque> getAttaques() {
        return attaques;
    }

    public Insecte attaques(Set<Attaque> attaques) {
        this.attaques = attaques;
        return this;
    }

    public Insecte addAttaque(Attaque attaque) {
        this.attaques.add(attaque);
        attaque.setInsecte(this);
        return this;
    }

    public Insecte removeAttaque(Attaque attaque) {
        this.attaques.remove(attaque);
        attaque.setInsecte(null);
        return this;
    }

    public void setAttaques(Set<Attaque> attaques) {
        this.attaques = attaques;
    }

    public Set<ImageAttaque> getImageInsectes() {
        return imageInsectes;
    }

    public Insecte imageInsectes(Set<ImageAttaque> imageAttaques) {
        this.imageInsectes = imageAttaques;
        return this;
    }

    public Insecte addImageInsecte(ImageAttaque imageAttaque) {
        this.imageInsectes.add(imageAttaque);
        imageAttaque.setInsecte(this);
        return this;
    }

    public Insecte removeImageInsecte(ImageAttaque imageAttaque) {
        this.imageInsectes.remove(imageAttaque);
        imageAttaque.setInsecte(null);
        return this;
    }

    public void setImageInsectes(Set<ImageAttaque> imageAttaques) {
        this.imageInsectes = imageAttaques;
    }

    public Chercheur getChercheur() {
        return chercheur;
    }

    public Insecte chercheur(Chercheur chercheur) {
        this.chercheur = chercheur;
        return this;
    }

    public void setChercheur(Chercheur chercheur) {
        this.chercheur = chercheur;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public Insecte administrateur(Administrateur administrateur) {
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
            ", insecteImageContentType='" + getInsecteImageContentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", cycleBio='" + getCycleBio() + "'" +
            ", autrePlante='" + getAutrePlante() + "'" +
            ", imageCycle='" + getImageCycle() + "'" +
            ", imageCycleContentType='" + getImageCycleContentType() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            "}";
    }
}
