package com.example.strona.model.camera;

import com.example.strona.model.Utils.DirectoryDeleteUtil;
import com.example.strona.model.Utils.ImageUploadUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Controller
public class CameraController {

    private final CameraService cameraService;
    private final DirectoryDeleteUtil directoryDeleteUtil;

    @Autowired
    public CameraController(CameraService cameraService, DirectoryDeleteUtil directoryDeleteUtil) {
        this.cameraService = cameraService;
        this.directoryDeleteUtil = directoryDeleteUtil;
    }

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

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        camera.setImage(fileName);

        Camera savedImage = cameraService.save(camera);
    
        String uploadDir = "images/" + "cameras/" + savedImage.getId();

        ImageUploadUtil.saveImg(uploadDir, fileName, multipartFile);

        re.addFlashAttribute("message", "Camera was added succesfully.");
        return "redirect:/cameras";
    
}

    @GetMapping("/cameras/edit/{id}")
        public String editCamera(@PathVariable("id") Integer id, Model model, RedirectAttributes re){
            try{
                Camera camera = cameraService.get(id);
                String cameraImage = camera.getImagePath();
                model.addAttribute("camera", camera);
                model.addAttribute("cameraImage", cameraImage);
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
            Path imageUploadDir = Paths.get("./images/" + "cameras/" + camera.getId() + "/");
            directoryDeleteUtil.cleanDirectory(imageUploadDir);
            re.addFlashAttribute("message", "Camera was deleted successfully.");
        } catch(NotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cameras";
        }

        @GetMapping("/cameras/image")
        public String addImageCamera(@PathVariable("id") Integer id, Model model, MultipartFile multipartFile, RedirectAttributes re)
        {
            try{
                Camera camera = cameraService.get(id);
                String cameraImage = camera.getImage();
                model.addAttribute("camera", cameraImage);
                model.addAttribute("title", "Add image for camera (ID: " + id + ")");
                return "addImageCamera";
            } catch(NotFoundException e){
                re.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/cameras";
            
        }
}
    
