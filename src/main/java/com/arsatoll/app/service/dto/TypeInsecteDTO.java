package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TypeInsecte entity.
 */
public class TypeInsecteDTO implements Serializable {

    private Long id;

    private String nomTypeInsecte;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeInsecte() {
        return nomTypeInsecte;
    }

    public void setNomTypeInsecte(String nomTypeInsecte) {
        this.nomTypeInsecte = nomTypeInsecte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeInsecteDTO typeInsecteDTO = (TypeInsecteDTO) o;
        if (typeInsecteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeInsecteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeInsecteDTO{" +
            "id=" + getId() +
            ", nomTypeInsecte='" + getNomTypeInsecte() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
