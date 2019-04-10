package com.arsatoll.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ZoneGeo.
 */
@Entity
@Table(name = "zone_geo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ZoneGeo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pays")
    private String pays;

    @Column(name = "region")
    private String region;

    @ManyToMany(mappedBy = "zones")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Culture> cultures = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public ZoneGeo pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public ZoneGeo region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Set<Culture> getCultures() {
        return cultures;
    }

    public ZoneGeo cultures(Set<Culture> cultures) {
        this.cultures = cultures;
        return this;
    }

    public ZoneGeo addCulture(Culture culture) {
        this.cultures.add(culture);
        culture.getZones().add(this);
        return this;
    }

    public ZoneGeo removeCulture(Culture culture) {
        this.cultures.remove(culture);
        culture.getZones().remove(this);
        return this;
    }

    public void setCultures(Set<Culture> cultures) {
        this.cultures = cultures;
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
        ZoneGeo zoneGeo = (ZoneGeo) o;
        if (zoneGeo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zoneGeo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ZoneGeo{" +
            "id=" + getId() +
            ", pays='" + getPays() + "'" +
            ", region='" + getRegion() + "'" +
            "}";
    }
}
