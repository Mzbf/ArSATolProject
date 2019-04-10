package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ZoneGeo entity.
 */
public class ZoneGeoDTO implements Serializable {

    private Long id;

    private String pays;

    private String region;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ZoneGeoDTO zoneGeoDTO = (ZoneGeoDTO) o;
        if (zoneGeoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zoneGeoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ZoneGeoDTO{" +
            "id=" + getId() +
            ", pays='" + getPays() + "'" +
            ", region='" + getRegion() + "'" +
            "}";
    }
}
