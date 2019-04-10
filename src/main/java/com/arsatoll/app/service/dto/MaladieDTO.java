package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Maladie entity.
 */
public class MaladieDTO implements Serializable {

    private Long id;

    private String nomMaladie;

    @Lob
    private String description;

    private String imagesMaladie;


    private Long maladieMLId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesMaladie() {
        return imagesMaladie;
    }

    public void setImagesMaladie(String imagesMaladie) {
        this.imagesMaladie = imagesMaladie;
    }

    public Long getMaladieMLId() {
        return maladieMLId;
    }

    public void setMaladieMLId(Long methodeLutteId) {
        this.maladieMLId = methodeLutteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaladieDTO maladieDTO = (MaladieDTO) o;
        if (maladieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maladieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaladieDTO{" +
            "id=" + getId() +
            ", nomMaladie='" + getNomMaladie() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagesMaladie='" + getImagesMaladie() + "'" +
            ", maladieML=" + getMaladieMLId() +
            "}";
    }
}
