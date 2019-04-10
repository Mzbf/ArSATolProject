package com.arsatoll.app.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageEnvoye entity.
 */
public class ImageEnvoyeDTO implements Serializable {

    private Long id;

    private String urlImage;

    private Instant dateDAjout;

    private Instant dateValidation;

    private Boolean flag;


    private Long agriculteurId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Instant getDateDAjout() {
        return dateDAjout;
    }

    public void setDateDAjout(Instant dateDAjout) {
        this.dateDAjout = dateDAjout;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Long getAgriculteurId() {
        return agriculteurId;
    }

    public void setAgriculteurId(Long agriculteurId) {
        this.agriculteurId = agriculteurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageEnvoyeDTO imageEnvoyeDTO = (ImageEnvoyeDTO) o;
        if (imageEnvoyeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageEnvoyeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageEnvoyeDTO{" +
            "id=" + getId() +
            ", urlImage='" + getUrlImage() + "'" +
            ", dateDAjout='" + getDateDAjout() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", flag='" + isFlag() + "'" +
            ", agriculteur=" + getAgriculteurId() +
            "}";
    }
}
