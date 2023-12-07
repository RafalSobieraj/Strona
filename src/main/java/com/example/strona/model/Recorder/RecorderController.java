package com.example.strona.model.Recorder;

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
public class RecorderController {

    private final RecorderService recorderService;
    private final DirectoryDeleteUtil directoryDeleteUtil;

    @Autowired
    public RecorderController(RecorderService recorderService, DirectoryDeleteUtil directoryDeleteUtil) {
        this.recorderService = recorderService;
        this.directoryDeleteUtil = directoryDeleteUtil;
    }

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

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        recorder.setImage(fileName);

        Recorder savedRecorder = recorderService.save(recorder);

        String uploadDir = "./images/" + "recorders/" + savedRecorder.getId();

        ImageUploadUtil.saveImg(uploadDir, fileName, multipartFile);

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
