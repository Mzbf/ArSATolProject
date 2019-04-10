package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TypeCulture entity.
 */
public class TypeCultureDTO implements Serializable {

    private Long id;

    private String nomTypeCulture;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeCulture() {
        return nomTypeCulture;
    }

    public void setNomTypeCulture(String nomTypeCulture) {
        this.nomTypeCulture = nomTypeCulture;
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

        TypeCultureDTO typeCultureDTO = (TypeCultureDTO) o;
        if (typeCultureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeCultureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeCultureDTO{" +
            "id=" + getId() +
            ", nomTypeCulture='" + getNomTypeCulture() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
