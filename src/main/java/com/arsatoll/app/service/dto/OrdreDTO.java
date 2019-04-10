package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Ordre entity.
 */
public class OrdreDTO implements Serializable {

    private Long id;

    private String nomOrdre;

    @Lob
    private String description;

    private String imageOrdre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomOrdre() {
        return nomOrdre;
    }

    public void setNomOrdre(String nomOrdre) {
        this.nomOrdre = nomOrdre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageOrdre() {
        return imageOrdre;
    }

    public void setImageOrdre(String imageOrdre) {
        this.imageOrdre = imageOrdre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrdreDTO ordreDTO = (OrdreDTO) o;
        if (ordreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdreDTO{" +
            "id=" + getId() +
            ", nomOrdre='" + getNomOrdre() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageOrdre='" + getImageOrdre() + "'" +
            "}";
    }
}
