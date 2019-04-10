package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageInsecte entity.
 */
public class ImageInsecteDTO implements Serializable {

    private Long id;

    private String imageUrl;


    private Long insecteId;

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

    public Long getInsecteId() {
        return insecteId;
    }

    public void setInsecteId(Long insecteId) {
        this.insecteId = insecteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageInsecteDTO imageInsecteDTO = (ImageInsecteDTO) o;
        if (imageInsecteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageInsecteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageInsecteDTO{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", insecte=" + getInsecteId() +
            "}";
    }
}
