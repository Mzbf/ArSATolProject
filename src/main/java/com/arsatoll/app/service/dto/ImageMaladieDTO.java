package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageMaladie entity.
 */
public class ImageMaladieDTO implements Serializable {

    private Long id;

    private String imageUrl;


    private Long maladieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMaladieId() {
        return maladieId;
    }

    public void setMaladieId(Long maladieId) {
        this.maladieId = maladieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageMaladieDTO imageMaladieDTO = (ImageMaladieDTO) o;
        if (imageMaladieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageMaladieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageMaladieDTO{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", maladie=" + getMaladieId() +
            "}";
    }
}
