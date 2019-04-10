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
 * A TypeInsecte.
 */
@Entity
@Table(name = "type_insecte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeInsecte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_type_insecte")
    private String nomTypeInsecte;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "typeInsecte")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Insecte> typeInsectes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeInsecte() {
        return nomTypeInsecte;
    }

    public TypeInsecte nomTypeInsecte(String nomTypeInsecte) {
        this.nomTypeInsecte = nomTypeInsecte;
        return this;
    }

    public void setNomTypeInsecte(String nomTypeInsecte) {
        this.nomTypeInsecte = nomTypeInsecte;
    }

    public String getDescription() {
        return description;
    }

    public TypeInsecte description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Insecte> getTypeInsectes() {
        return typeInsectes;
    }

    public TypeInsecte typeInsectes(Set<Insecte> insectes) {
        this.typeInsectes = insectes;
        return this;
    }

    public TypeInsecte addTypeInsecte(Insecte insecte) {
        this.typeInsectes.add(insecte);
        insecte.setTypeInsecte(this);
        return this;
    }

    public TypeInsecte removeTypeInsecte(Insecte insecte) {
        this.typeInsectes.remove(insecte);
        insecte.setTypeInsecte(null);
        return this;
    }

    public void setTypeInsectes(Set<Insecte> insectes) {
        this.typeInsectes = insectes;
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
        TypeInsecte typeInsecte = (TypeInsecte) o;
        if (typeInsecte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeInsecte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeInsecte{" +
            "id=" + getId() +
            ", nomTypeInsecte='" + getNomTypeInsecte() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
