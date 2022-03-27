package com.example.strona.model.Recorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.example.strona.model.Utils.DirectoryDeleteUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;

@Controller
public class RecorderController {

    @Autowired private RecorderService recorderService;
    @Autowired private DirectoryDeleteUtil directoryDeleteUtil;

   @GetMapping("/recorders")
   public String getRecorderList(Model model){
       List<Recorder> recorderList = recorderService.listRecorders();
       model.addAttribute("recorderList", recorderList);

       return "recorders";
   }

   @GetMapping("/recorders/new")
    public String recorderForm(Model model){
        model.addAttribute("recorder", new Recorder());
        model.addAttribute("title", "Add new recorder");
        return "recorder_form";
    }

    @PostMapping("/recorders/save")
    public String saveRecorder(@ModelAttribute(name = "recorder") Recorder recorder, 
    RedirectAttributes re,
    @RequestParam("fileImage") MultipartFile multipartFile)
    throws IOException{

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        recorder.setImage(fileName);

        Recorder savedRecorder = recorderService.save(recorder);

        String uploadDir = "./images/" + "recorders/" + savedRecorder.getId();
        String relativeRecorder = new File("").toURI().relativize(new File(uploadDir).toURI()).getPath();

        Path uploadPath = Paths.get(relativeRecorder);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        else uploadPath.toFile().delete();

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not save file: " + fileName);
        }

        re.addFlashAttribute("message", "Recorder was added succesfully.");
        return "redirect:/recorders";
    }

    @GetMapping("/recorders/edit/{id}")
        public String editRecorder(@PathVariable("id") Integer id, Model model, RedirectAttributes re){
            try{
                Recorder recorder = recorderService.get(id);
                model.addAttribute("recorder", recorder);
                model.addAttribute("title", "Edit recorder (ID: " + id + ")");
                return "recorder_form";
            } catch(NotFoundException e){
                re.addFlashAttribute("message", e.getMessage());
                return "redirect:/recorders";
            }
        }

    @GetMapping("/recorders/delete/{id}")
    public String deleteRecorder(@PathVariable("id") Integer id, RedirectAttributes re, Recorder recorder) throws IOException{
        try{
            recorderService.delete(id);
            Path imageUploadDir = Paths.get("./images/" + "recorders/" + recorder.getId() + "/");
            directoryDeleteUtil.cleanDirectory(imageUploadDir);
            re.addFlashAttribute("message", "Recorder was deleted successfully.");
        } catch(NotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/recorders";
        } 
    
}
