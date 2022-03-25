package com.example.strona.model.switchPOE;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "switches")
public class SwitchPOE{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "switch_model")
    private  String SwitchModel;

    @Column(nullable = false, name = "switch_interface")
    private String SwitchInterface;

    @Column(nullable = false, name = "port_speed")
    private int PortSpeed;

    @Column(nullable = false, name = "switch_band")
    private int SwitchBand;

    @Column(nullable = true)
    private String Image;

    @Column(nullable = true, name = "url")
    private String Link;

    public SwitchPOE(Integer id, String switchModel, String switchInterface, int portSpeed, int switchBand,
            String image, String link) {
        super();
        this.id = id;
        SwitchModel = switchModel;
        SwitchInterface = switchInterface;
        PortSpeed = portSpeed;
        SwitchBand = switchBand;
        Image = image;
        Link = link;
    }

    public SwitchPOE() {
        super();
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSwitchModel() {
        return SwitchModel;
    }

    public void setSwitchModel(String switchModel) {
        SwitchModel = switchModel;
    }

    public String getSwitchInterface() {
        return SwitchInterface;
    }

    public void setSwitchInterface(String switchInterface) {
        SwitchInterface = switchInterface;
    }

    public int getPortSpeed() {
        return PortSpeed;
    }

    public void setPortSpeed(int portSpeed) {
        PortSpeed = portSpeed;
    }

    public int getSwitchBand() {
        return SwitchBand;
    }

    public void setSwitchBand(int switchBand) {
        SwitchBand = switchBand;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Transient
    public String getImagePath(){
        if(Image == null || id == null) return null;

        return "/images/" + "switches/" + id + "/" + Image;
    }

    @Override
    public String toString() {
        return "SwitchPOE [Image=" + Image + ", PortSpeed=" + PortSpeed + ", SwitchBand=" + SwitchBand
                + ", SwitchInterface=" + SwitchInterface + ", SwitchModel=" + SwitchModel + ", id=" + id + "]";
    }
    
}