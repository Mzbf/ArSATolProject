package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "culture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> attaques = new HashSet<>();
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

    public Set<Attaque> getAttaques() {
        return attaques;
    }

    public Culture attaques(Set<Attaque> attaques) {
        this.attaques = attaques;
        return this;
    }

    public Culture addAttaque(Attaque attaque) {
        this.attaques.add(attaque);
        attaque.setCulture(this);
        return this;
    }

    public Culture removeAttaque(Attaque attaque) {
        this.attaques.remove(attaque);
        attaque.setCulture(null);
        return this;
    }

    public void setAttaques(Set<Attaque> attaques) {
        this.attaques = attaques;
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
            "}";
    }
}
