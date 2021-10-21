package com.example.strona.model.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String saveCamera(@ModelAttribute(name = "camera") Camera camera, 
    RedirectAttributes re,
    @RequestParam("fileImage") MultipartFile multipartFile)
    throws IOException{

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        camera.setImage(fileName);
        Camera savedImage = cameraService.save(camera);

        String uploadDir = "./images/" + savedImage.getId();

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not save file: " + fileName);
        }

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
    public String deleteCamera(@PathVariable("id") Integer id, RedirectAttributes re, Camera camera) throws IOException{
        try{
            cameraService.delete(id);
            Path imageUploadDir = Paths.get("./images/" + camera.getId() + "/");
            cameraService.cleanDirectory(imageUploadDir);
            Files.delete(imageUploadDir);
            re.addFlashAttribute("message", "Camera was deleted successfully.");
        } catch(NotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cameras";
        } 
    
}
