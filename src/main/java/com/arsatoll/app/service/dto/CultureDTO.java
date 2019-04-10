package com.arsatoll.app.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Culture entity.
 */
public class CultureDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomCulture;

    private String imageCulture;


    private Set<MaladieDTO> maladies = new HashSet<>();

    private Set<HerbeDTO> herbes = new HashSet<>();

    private Set<ZoneGeoDTO> zones = new HashSet<>();

    private Long typeCultureId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public String getImageCulture() {
        return imageCulture;
    }

    public void setImageCulture(String imageCulture) {
        this.imageCulture = imageCulture;
    }

    public Set<MaladieDTO> getMaladies() {
        return maladies;
    }

    public void setMaladies(Set<MaladieDTO> maladies) {
        this.maladies = maladies;
    }

    public Set<HerbeDTO> getHerbes() {
        return herbes;
    }

    public void setHerbes(Set<HerbeDTO> herbes) {
        this.herbes = herbes;
    }

    public Set<ZoneGeoDTO> getZones() {
        return zones;
    }

    public void setZones(Set<ZoneGeoDTO> zoneGeos) {
        this.zones = zoneGeos;
    }

    public Long getTypeCultureId() {
        return typeCultureId;
    }

    public void setTypeCultureId(Long typeCultureId) {
        this.typeCultureId = typeCultureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CultureDTO cultureDTO = (CultureDTO) o;
        if (cultureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cultureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CultureDTO{" +
            "id=" + getId() +
            ", nomCulture='" + getNomCulture() + "'" +
            ", imageCulture='" + getImageCulture() + "'" +
            ", typeCulture=" + getTypeCultureId() +
            "}";
    }
}
