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
 * A TypeDegat.
 */
@Entity
@Table(name = "type_degat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeDegat implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type_deg", nullable = false)
    private String typeDeg;

    @OneToMany(mappedBy = "typeDegat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attaque> degats = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDeg() {
        return typeDeg;
    }

    public TypeDegat typeDeg(String typeDeg) {
        this.typeDeg = typeDeg;
        return this;
    }

    public void setTypeDeg(String typeDeg) {
        this.typeDeg = typeDeg;
    }

    public Set<Attaque> getDegats() {
        return degats;
    }

    public TypeDegat degats(Set<Attaque> attaques) {
        this.degats = attaques;
        return this;
    }

    public TypeDegat addDegat(Attaque attaque) {
        this.degats.add(attaque);
        attaque.setTypeDegat(this);
        return this;
    }

    public TypeDegat removeDegat(Attaque attaque) {
        this.degats.remove(attaque);
        attaque.setTypeDegat(null);
        return this;
    }

    public void setDegats(Set<Attaque> attaques) {
        this.degats = attaques;
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
        TypeDegat typeDegat = (TypeDegat) o;
        if (typeDegat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeDegat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeDegat{" +
            "id=" + getId() +
            ", typeDeg='" + getTypeDeg() + "'" +
            "}";
    }
}
