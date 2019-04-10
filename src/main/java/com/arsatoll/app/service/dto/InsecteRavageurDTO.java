package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the InsecteRavageur entity.
 */
public class InsecteRavageurDTO implements Serializable {

    private Long id;


    private Long typeRavId;

    private Long ravageurMLId;

    private Long chercheurId;

    private Long familleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeRavId() {
        return typeRavId;
    }

    public void setTypeRavId(Long insecteId) {
        this.typeRavId = insecteId;
    }

    public Long getRavageurMLId() {
        return ravageurMLId;
    }

    public void setRavageurMLId(Long methodeLutteId) {
        this.ravageurMLId = methodeLutteId;
    }

    public Long getChercheurId() {
        return chercheurId;
    }

    public void setChercheurId(Long chercheurId) {
        this.chercheurId = chercheurId;
    }

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsecteRavageurDTO insecteRavageurDTO = (InsecteRavageurDTO) o;
        if (insecteRavageurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insecteRavageurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsecteRavageurDTO{" +
            "id=" + getId() +
            ", typeRav=" + getTypeRavId() +
            ", ravageurML=" + getRavageurMLId() +
            ", chercheur=" + getChercheurId() +
            ", famille=" + getFamilleId() +
            "}";
    }
}
