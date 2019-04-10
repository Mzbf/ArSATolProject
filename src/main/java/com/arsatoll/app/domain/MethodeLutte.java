package com.arsatoll.app.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MethodeLutte.
 */
@Entity
@Table(name = "methode_lutte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MethodeLutte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_type")
    private String type;

    @Lob
    @Column(name = "methode_culturale")
    private String methodeCulturale;

    @Lob
    @Column(name = "traitement")
    private String traitement;

    @Column(name = "image_ml")
    private String imageML;

    @Column(name = "video")
    private String video;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public MethodeLutte type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethodeCulturale() {
        return methodeCulturale;
    }

    public MethodeLutte methodeCulturale(String methodeCulturale) {
        this.methodeCulturale = methodeCulturale;
        return this;
    }

    public void setMethodeCulturale(String methodeCulturale) {
        this.methodeCulturale = methodeCulturale;
    }

    public String getTraitement() {
        return traitement;
    }

    public MethodeLutte traitement(String traitement) {
        this.traitement = traitement;
        return this;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getImageML() {
        return imageML;
    }

    public MethodeLutte imageML(String imageML) {
        this.imageML = imageML;
        return this;
    }

    public void setImageML(String imageML) {
        this.imageML = imageML;
    }

    public String getVideo() {
        return video;
    }

    public MethodeLutte video(String video) {
        this.video = video;
        return this;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MethodeLutte methodeLutte = (MethodeLutte) o;
        if (methodeLutte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), methodeLutte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MethodeLutte{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", methodeCulturale='" + getMethodeCulturale() + "'" +
            ", traitement='" + getTraitement() + "'" +
            ", imageML='" + getImageML() + "'" +
            ", video='" + getVideo() + "'" +
            "}";
    }
}
