package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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

    @Column(name = "nom_insecte")
    private String nomInsecte;

    @Column(name = "nom_scien_insecte")
    private String nomScienInsecte;

    @Column(name = "cycle_bio")
    private String cycleBio;

    @Column(name = "flag")
    private Boolean flag;

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
    private Set<Image> imageInsectes = new HashSet<>();
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

    public Boolean isFlag() {
        return flag;
    }

    public Insecte flag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
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

    public Set<Image> getImageInsectes() {
        return imageInsectes;
    }

    public Insecte imageInsectes(Set<Image> images) {
        this.imageInsectes = images;
        return this;
    }

    public Insecte addImageInsecte(Image image) {
        this.imageInsectes.add(image);
        image.setInsecte(this);
        return this;
    }

    public Insecte removeImageInsecte(Image image) {
        this.imageInsectes.remove(image);
        image.setInsecte(null);
        return this;
    }

    public void setImageInsectes(Set<Image> images) {
        this.imageInsectes = images;
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
            ", cycleBio='" + getCycleBio() + "'" +
            ", flag='" + isFlag() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            "}";
    }
}
