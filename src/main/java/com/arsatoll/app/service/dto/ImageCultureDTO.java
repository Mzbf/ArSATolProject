package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageCulture entity.
 */
public class ImageCultureDTO implements Serializable {

    private Long id;

    private String imageUrl;


    private Long cultureId;

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

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageCultureDTO imageCultureDTO = (ImageCultureDTO) o;
        if (imageCultureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageCultureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageCultureDTO{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", culture=" + getCultureId() +
            "}";
    }
}
