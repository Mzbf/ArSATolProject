package com.arsatoll.app.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Demo entity.
 */
public class DemoDTO implements Serializable {

    private Long id;

    private String imageDemo;

    private String audio;

    private String videoDemo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDemo() {
        return imageDemo;
    }

    public void setImageDemo(String imageDemo) {
        this.imageDemo = imageDemo;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideoDemo() {
        return videoDemo;
    }

    public void setVideoDemo(String videoDemo) {
        this.videoDemo = videoDemo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemoDTO demoDTO = (DemoDTO) o;
        if (demoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemoDTO{" +
            "id=" + getId() +
            ", imageDemo='" + getImageDemo() + "'" +
            ", audio='" + getAudio() + "'" +
            ", videoDemo='" + getVideoDemo() + "'" +
            "}";
    }
}
