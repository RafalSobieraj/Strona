package com.example.strona.model.Recorder;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="recorders")
public class Recorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "recorder_model")
    private  String recorderModel;

    @Column(nullable = false, name = "recorder_type")
    private String recorderType;

    @Column(nullable = false, name = "canal_numbers")
    private int canalNumbers;

    @Column(nullable = false, name = "disk_count")
    private int diskCount;

    @Column(nullable = false, name = "storage_limit")
    private int storageLimit;

    @Column(nullable = false, name = "bandwidth")
    private int bandwidth;

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

        return "images/recorders/" + id + "/" + image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recorder recorder = (Recorder) o;
        return id != null && Objects.equals(id, recorder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
