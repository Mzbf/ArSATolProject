package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the MethodeLutte entity.
 */
public class MethodeLutteDTO implements Serializable {

    private Long id;

    private String type;

    @Lob
    private String methodeCulturale;

    @Lob
    private String traitement;

    private String imageML;

    private String video;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethodeCulturale() {
        return methodeCulturale;
    }

    public void setMethodeCulturale(String methodeCulturale) {
        this.methodeCulturale = methodeCulturale;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getImageML() {
        return imageML;
    }

    public void setImageML(String imageML) {
        this.imageML = imageML;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MethodeLutteDTO methodeLutteDTO = (MethodeLutteDTO) o;
        if (methodeLutteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), methodeLutteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MethodeLutteDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", methodeCulturale='" + getMethodeCulturale() + "'" +
            ", traitement='" + getTraitement() + "'" +
            ", imageML='" + getImageML() + "'" +
            ", video='" + getVideo() + "'" +
            "}";
    }
}
