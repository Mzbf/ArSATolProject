package com.arsatoll.app.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.arsatoll.app.domain.enumeration.Localisation;

/**
 * A DTO for the Attaque entity.
 */
public class AttaqueDTO implements Serializable {

    private Long id;

    @Lob
    private String description;

    private Boolean flag;

    private Localisation localisation;

    private Instant dateValidation;

    private Instant dateAjout;

    private String imagesAttaque;


    private Long insecteId;

    private Long cultureId;

    private Long chercheurId;

    private Long typeDegatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Instant getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Instant dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getImagesAttaque() {
        return imagesAttaque;
    }

    public void setImagesAttaque(String imagesAttaque) {
        this.imagesAttaque = imagesAttaque;
    }

    public Long getInsecteId() {
        return insecteId;
    }

    public void setInsecteId(Long insecteId) {
        this.insecteId = insecteId;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public Long getChercheurId() {
        return chercheurId;
    }

    public void setChercheurId(Long chercheurId) {
        this.chercheurId = chercheurId;
    }

    public Long getTypeDegatId() {
        return typeDegatId;
    }

    public void setTypeDegatId(Long typeDegatId) {
        this.typeDegatId = typeDegatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttaqueDTO attaqueDTO = (AttaqueDTO) o;
        if (attaqueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attaqueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttaqueDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", flag='" + isFlag() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            ", imagesAttaque='" + getImagesAttaque() + "'" +
            ", insecte=" + getInsecteId() +
            ", culture=" + getCultureId() +
            ", chercheur=" + getChercheurId() +
            ", typeDegat=" + getTypeDegatId() +
            "}";
    }
}
