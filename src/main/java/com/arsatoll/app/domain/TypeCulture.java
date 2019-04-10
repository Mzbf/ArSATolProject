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
 * A TypeCulture.
 */
@Entity
@Table(name = "type_culture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeCulture implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_type_culture")
    private String nomTypeCulture;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "typeCulture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Culture> typeCultures = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeCulture() {
        return nomTypeCulture;
    }

    public TypeCulture nomTypeCulture(String nomTypeCulture) {
        this.nomTypeCulture = nomTypeCulture;
        return this;
    }

    public void setNomTypeCulture(String nomTypeCulture) {
        this.nomTypeCulture = nomTypeCulture;
    }

    public String getDescription() {
        return description;
    }

    public TypeCulture description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Culture> getTypeCultures() {
        return typeCultures;
    }

    public TypeCulture typeCultures(Set<Culture> cultures) {
        this.typeCultures = cultures;
        return this;
    }

    public TypeCulture addTypeCulture(Culture culture) {
        this.typeCultures.add(culture);
        culture.setTypeCulture(this);
        return this;
    }

    public TypeCulture removeTypeCulture(Culture culture) {
        this.typeCultures.remove(culture);
        culture.setTypeCulture(null);
        return this;
    }

    public void setTypeCultures(Set<Culture> cultures) {
        this.typeCultures = cultures;
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
        TypeCulture typeCulture = (TypeCulture) o;
        if (typeCulture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeCulture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeCulture{" +
            "id=" + getId() +
            ", nomTypeCulture='" + getNomTypeCulture() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
