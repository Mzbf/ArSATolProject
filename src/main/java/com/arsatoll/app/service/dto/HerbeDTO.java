package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Herbe entity.
 */
public class HerbeDTO implements Serializable {

    private Long id;

    private String nomHerbe;

    @Lob
    private String description;

    private String imagesHerbe;


    private Long herbeMLId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomHerbe() {
        return nomHerbe;
    }

    public void setNomHerbe(String nomHerbe) {
        this.nomHerbe = nomHerbe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesHerbe() {
        return imagesHerbe;
    }

    public void setImagesHerbe(String imagesHerbe) {
        this.imagesHerbe = imagesHerbe;
    }

    public Long getHerbeMLId() {
        return herbeMLId;
    }

    public void setHerbeMLId(Long methodeLutteId) {
        this.herbeMLId = methodeLutteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HerbeDTO herbeDTO = (HerbeDTO) o;
        if (herbeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), herbeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HerbeDTO{" +
            "id=" + getId() +
            ", nomHerbe='" + getNomHerbe() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagesHerbe='" + getImagesHerbe() + "'" +
            ", herbeML=" + getHerbeMLId() +
            "}";
    }
}
