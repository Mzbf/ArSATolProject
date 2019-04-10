package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImageAttaque entity.
 */
public class ImageAttaqueDTO implements Serializable {

    private Long id;

    private String imageUrl;


    private Long attaqueId;

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

    public Long getAttaqueId() {
        return attaqueId;
    }

    public void setAttaqueId(Long attaqueId) {
        this.attaqueId = attaqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageAttaqueDTO imageAttaqueDTO = (ImageAttaqueDTO) o;
        if (imageAttaqueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageAttaqueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageAttaqueDTO{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", attaque=" + getAttaqueId() +
            "}";
    }
}
