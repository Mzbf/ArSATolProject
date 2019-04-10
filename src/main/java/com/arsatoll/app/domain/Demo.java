package com.arsatoll.app.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Demo.
 */
@Entity
@Table(name = "demo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Demo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_demo")
    private String imageDemo;

    @Column(name = "audio")
    private String audio;

    @Column(name = "video_demo")
    private String videoDemo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDemo() {
        return imageDemo;
    }

    public Demo imageDemo(String imageDemo) {
        this.imageDemo = imageDemo;
        return this;
    }

    public void setImageDemo(String imageDemo) {
        this.imageDemo = imageDemo;
    }

    public String getAudio() {
        return audio;
    }

    public Demo audio(String audio) {
        this.audio = audio;
        return this;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideoDemo() {
        return videoDemo;
    }

    public Demo videoDemo(String videoDemo) {
        this.videoDemo = videoDemo;
        return this;
    }

    public void setVideoDemo(String videoDemo) {
        this.videoDemo = videoDemo;
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
        Demo demo = (Demo) o;
        if (demo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demo{" +
            "id=" + getId() +
            ", imageDemo='" + getImageDemo() + "'" +
            ", audio='" + getAudio() + "'" +
            ", videoDemo='" + getVideoDemo() + "'" +
            "}";
    }
}
