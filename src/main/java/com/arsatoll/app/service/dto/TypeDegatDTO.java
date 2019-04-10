package com.arsatoll.app.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TypeDegat entity.
 */
public class TypeDegatDTO implements Serializable {

    private Long id;

    @NotNull
    private String typeDeg;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDeg() {
        return typeDeg;
    }

    public void setTypeDeg(String typeDeg) {
        this.typeDeg = typeDeg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeDegatDTO typeDegatDTO = (TypeDegatDTO) o;
        if (typeDegatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeDegatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeDegatDTO{" +
            "id=" + getId() +
            ", typeDeg='" + getTypeDeg() + "'" +
            "}";
    }
}
