package com.example.strona.model.camera;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

import com.example.strona.model.Utils.DirectoryDeleteUtil;
import com.example.strona.model.Utils.ImageUploadUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;


@Controller
public class CameraController {

   @Autowired private CameraService cameraService;
   @Autowired private DirectoryDeleteUtil directoryDeleteUtil;

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
        System.out.println(fileName);
        camera.setImage(fileName);

        Camera savedImage = cameraService.save(camera);
    
        String uploadDir = "images/" + "cameras/" + savedImage.getId();

        ImageUploadUtil.saveImg(uploadDir, fileName, multipartFile);
/* 
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        else uploadPath.toFile().delete();

        if(!camera.getImage().equals(null)){
            Files.list(uploadPath).forEach(file -> {
                if(!Files.isDirectory(file)) {
                    {
                        try {
                                Path imagePath = Paths.get(camera.getImage());
                                int value = imagePath.compareTo(file.getFileName());
                                if(value > 0)
                                    Files.delete(file);
                        } catch (IOException e) {
                            re.addFlashAttribute("message", "There was no files in directory.");
                        }
                    }
                }
            });
        }
        
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            re.addFlashAttribute("message", "There was an error adding a image. Please try again.");
            return "redirect:/cameras";
        }
    */
        re.addFlashAttribute("message", "Camera was added succesfully.");
        return "redirect:/cameras";
    
}

    @GetMapping("/cameras/edit/{id}")
        public String editCamera(@PathVariable("id") Integer id, Model model, RedirectAttributes re){
            try{
                Camera camera = cameraService.get(id);
                String cameraImage = camera.getImagePath();
                String cameraImg = camera.getImage();
                System.out.println(cameraImg);
                System.out.println(cameraImg.toString());
                System.out.println(cameraImage);
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
    
        @PostMapping("/cameras/image")
        public String addImageCamera(@PathVariable("id") Integer id, Model model, MultipartFile multipartFile, RedirectAttributes re,
        @ModelAttribute(name = "camera") Camera camera)
        {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if(camera.getId() != null){
                camera.setImage(fileName);
                cameraService.save(camera);
                re.addFlashAttribute("message", "Image added successfully.");
                return "redirect:/cameras";
            }
            else
                re.addFlashAttribute("message", "An error occured.");
            return "redirect:/switches";
        }
    }
    
