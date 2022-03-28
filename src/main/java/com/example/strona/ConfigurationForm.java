package com.example.strona;

import java.beans.JavaBean;
import java.io.Serializable;

import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.camera.Camera;
import com.example.strona.model.switchPOE.SwitchPOE;

@JavaBean
public class ConfigurationForm implements Serializable{

        private Camera Camera1;
        private Recorder Recorder1;
        private SwitchPOE SwitchPOE1;


        public ConfigurationForm(){
            super();
        }
        

        public ConfigurationForm(Camera camera1, Recorder recorder1, SwitchPOE switchPOE1) {
            super();
            Camera1 = camera1;
            Recorder1 = recorder1;
            SwitchPOE1 = switchPOE1;
        }




        public Camera getCamera1() {
            return Camera1;
        }




        public void setCamera1(Camera camera1) {
            Camera1 = camera1;
        }




        public Recorder getRecorder1() {
            return Recorder1;
        }




        public void setRecorder1(Recorder recorder1) {
            Recorder1 = recorder1;
        }




        public SwitchPOE getSwitchPOE1() {
            return SwitchPOE1;
        }




        public void setSwitchPOE1(SwitchPOE switchPOE1) {
            SwitchPOE1 = switchPOE1;
        }




        @Override
        public String toString() {
            return "ConfigurationForm [Camera1=" + Camera1 + ", Recorder1=" + Recorder1 + ", SwitchPOE1=" + SwitchPOE1
                    + "]";
        }


       
        
}