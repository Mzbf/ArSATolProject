package com.arsatoll.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Insecte entity.
 */
public class InsecteDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomInsecte;

    @NotNull
    private String nomScienInsecte;

    private String insecteImage;

    @Lob
    private String description;

    @Lob
    private String cycleBio;

    @Lob
    private String autrePlante;

    private String imageCycle;

    private Instant dateValidation;

    private Instant dateAjout;


    private Long typeInsecteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomInsecte() {
        return nomInsecte;
    }

    public void setNomInsecte(String nomInsecte) {
        this.nomInsecte = nomInsecte;
    }

    public String getNomScienInsecte() {
        return nomScienInsecte;
    }

    public void setNomScienInsecte(String nomScienInsecte) {
        this.nomScienInsecte = nomScienInsecte;
    }

    public String getInsecteImage() {
        return insecteImage;
    }

    public void setInsecteImage(String insecteImage) {
        this.insecteImage = insecteImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCycleBio() {
        return cycleBio;
    }

    public void setCycleBio(String cycleBio) {
        this.cycleBio = cycleBio;
    }

    public String getAutrePlante() {
        return autrePlante;
    }

    public void setAutrePlante(String autrePlante) {
        this.autrePlante = autrePlante;
    }

    public String getImageCycle() {
        return imageCycle;
    }

    public void setImageCycle(String imageCycle) {
        this.imageCycle = imageCycle;
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

    public Long getTypeInsecteId() {
        return typeInsecteId;
    }

    public void setTypeInsecteId(Long typeInsecteId) {
        this.typeInsecteId = typeInsecteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsecteDTO insecteDTO = (InsecteDTO) o;
        if (insecteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insecteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsecteDTO{" +
            "id=" + getId() +
            ", nomInsecte='" + getNomInsecte() + "'" +
            ", nomScienInsecte='" + getNomScienInsecte() + "'" +
            ", insecteImage='" + getInsecteImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", cycleBio='" + getCycleBio() + "'" +
            ", autrePlante='" + getAutrePlante() + "'" +
            ", imageCycle='" + getImageCycle() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", dateAjout='" + getDateAjout() + "'" +
            ", typeInsecte=" + getTypeInsecteId() +
            "}";
    }
}
