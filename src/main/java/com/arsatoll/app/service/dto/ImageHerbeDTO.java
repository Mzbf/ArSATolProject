package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageHerbe entity.
 */
public class ImageHerbeDTO implements Serializable {

    private Long id;

    private String imageUrl;


    private Long herbeId;

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

    public Long getHerbeId() {
        return herbeId;
    }

    public void setHerbeId(Long herbeId) {
        this.herbeId = herbeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageHerbeDTO imageHerbeDTO = (ImageHerbeDTO) o;
        if (imageHerbeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageHerbeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageHerbeDTO{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", herbe=" + getHerbeId() +
            "}";
    }
}
