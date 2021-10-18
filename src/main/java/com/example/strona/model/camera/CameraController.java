package com.example.strona.model.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;

import java.util.List;


@Controller
public class CameraController {

   @Autowired private CameraService cameraService;

   @GetMapping("/cameras")
   public String getCameraList(Model model){
       List<Camera> cameraList = cameraService.listCameras();
       model.addAttribute("cameraList", cameraList);

       return "cameras";
   }

   @GetMapping("/cameras/new")
    public String cameraForm(Model model){
        model.addAttribute("camera", new Camera());
        model.addAttribute("title", "Add new camera");
        return "camera_form";
    }

    @PostMapping("/cameras/save")
    public String saveCamera(Camera camera, RedirectAttributes re){
        cameraService.save(camera);
        re.addFlashAttribute("message", "Camera was added succesfully.");
        return "redirect:/cameras";
    }

    @GetMapping("/cameras/edit/{id}")
        public String editCamera(@PathVariable("id") Integer id, Model model, RedirectAttributes re){
            try{
                Camera camera = cameraService.get(id);
                model.addAttribute("camera", camera);
                model.addAttribute("title", "Edit camera (ID: " + id + ")");
                return "camera_form";
            } catch(NotFoundException e){
                re.addFlashAttribute("message", e.getMessage());
                return "redirect:/cameras";
            }
        }

    @GetMapping("/cameras/delete/{id}")
    public String deleteCamera(@PathVariable("id") Integer id, RedirectAttributes re){
        try{
            cameraService.delete(id);
            re.addFlashAttribute("message", "Camera was deleted successfully.");
        } catch(NotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cameras";
        } 
    
}
