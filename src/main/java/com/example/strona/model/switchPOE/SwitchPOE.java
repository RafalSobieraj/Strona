package com.example.strona.model.switchPOE;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.beans.Transient;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "switches")
public class SwitchPOE{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "switch_model")
    private  String switchModel;

    @Column(nullable = false, name = "switch_interface")
    private String switchInterface;

    @Column(nullable = false, name = "port_speed")
    private int portSpeed;

    @Column(nullable = false, name = "switch_band")
    private int switchBand;

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

    public String getImagePath(){
        if(image == null || id == null) return null;

        return "/images/switches/" + image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SwitchPOE switchPOE = (SwitchPOE) o;
        return id != null && Objects.equals(id, switchPOE.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}