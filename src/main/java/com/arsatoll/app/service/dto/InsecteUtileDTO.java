package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the InsecteUtile entity.
 */
public class InsecteUtileDTO implements Serializable {

    private Long id;


    private Long typeUtilId;

    private Long chercheurId;

    private Long familleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeUtilId() {
        return typeUtilId;
    }

    public void setTypeUtilId(Long insecteId) {
        this.typeUtilId = insecteId;
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

        InsecteUtileDTO insecteUtileDTO = (InsecteUtileDTO) o;
        if (insecteUtileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insecteUtileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsecteUtileDTO{" +
            "id=" + getId() +
            ", typeUtil=" + getTypeUtilId() +
            ", chercheur=" + getChercheurId() +
            ", famille=" + getFamilleId() +
            "}";
    }
}
