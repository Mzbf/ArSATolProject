package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Chercheur entity.
 */
public class ChercheurDTO implements Serializable {

    private Long id;

    private String institut;

    private String pays;

    private String specialite;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChercheurDTO chercheurDTO = (ChercheurDTO) o;
        if (chercheurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chercheurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChercheurDTO{" +
            "id=" + getId() +
            ", institut='" + getInstitut() + "'" +
            ", pays='" + getPays() + "'" +
            ", specialite='" + getSpecialite() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
