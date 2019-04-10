package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Agriculteur entity.
 */
public class AgriculteurDTO implements Serializable {

    private Long id;

    private String specialite;


    private Long paysId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Long getPaysId() {
        return paysId;
    }

    public void setPaysId(Long zoneGeoId) {
        this.paysId = zoneGeoId;
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

        AgriculteurDTO agriculteurDTO = (AgriculteurDTO) o;
        if (agriculteurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agriculteurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AgriculteurDTO{" +
            "id=" + getId() +
            ", specialite='" + getSpecialite() + "'" +
            ", pays=" + getPaysId() +
            ", user=" + getUserId() +
            "}";
    }
}
