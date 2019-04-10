package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Famille entity.
 */
public class FamilleDTO implements Serializable {

    private Long id;

    private String nomFamille;

    @Lob
    private String description;

    private String imageFamiile;


    private Long ordreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFamiile() {
        return imageFamiile;
    }

    public void setImageFamiile(String imageFamiile) {
        this.imageFamiile = imageFamiile;
    }

    public Long getOrdreId() {
        return ordreId;
    }

    public void setOrdreId(Long ordreId) {
        this.ordreId = ordreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamilleDTO familleDTO = (FamilleDTO) o;
        if (familleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), familleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FamilleDTO{" +
            "id=" + getId() +
            ", nomFamille='" + getNomFamille() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageFamiile='" + getImageFamiile() + "'" +
            ", ordre=" + getOrdreId() +
            "}";
    }
}
