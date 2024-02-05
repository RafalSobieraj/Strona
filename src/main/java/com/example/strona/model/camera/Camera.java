package com.example.strona.model.camera;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cameras")

public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "camera_model")
    private  String cameraModel;

    @Column(nullable = false, name = "camera_type")
    private String cameraType;

    @Column(nullable = false, name = "resolution")
    private int cameraResolution;

    @Column()
    private String image;

    @Column(name = "url")
    private String link;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double price;

    @Column(name = "availability")
    private String availability;

    @Column(name = "installation")
    private String installation;


    @Transient
    public String getImagePath(){
        if(image == null || id == null) return null;

        return "images/cameras/" + image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Camera camera = (Camera) o;
        return id != null && Objects.equals(id, camera.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
