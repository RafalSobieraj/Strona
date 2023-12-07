package com.example.strona.model.switchPOE;

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
public class SwitchPOEController {

    private final SwitchPOEService switchPOEService;
    private final DirectoryDeleteUtil directoryDeleteUtil;

    @Autowired
    public SwitchPOEController(SwitchPOEService switchPOEService, DirectoryDeleteUtil directoryDeleteUtil) {
        this.switchPOEService = switchPOEService;
        this.directoryDeleteUtil = directoryDeleteUtil;
    }

   @GetMapping("/switches")
   public String getSwitchPOEList(Model model){
       List<SwitchPOE> switchPOEList = switchPOEService.listSwitches();
       model.addAttribute("switchPOEList", switchPOEList);

       return "switches";
   }

   @GetMapping("/switches/new")
    public String switchPOEForm(Model model){
        model.addAttribute("switchPOE", new SwitchPOE());
        model.addAttribute("title", "Add new switch POE");
        return "switch_form";
    }

    @PostMapping("/switches/save")
    public String saveSwitchPOE(@ModelAttribute(name = "switchPOE") SwitchPOE switchPOE, 
    RedirectAttributes re,
    @RequestParam("fileImage") MultipartFile multipartFile)
    throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        switchPOE.setImage(fileName);

        SwitchPOE savedImage = switchPOEService.save(switchPOE);

        String uploadDir = "./images/" + "switches/" + savedImage.getId();

        ImageUploadUtil.saveImg(uploadDir, fileName, multipartFile);

        re.addFlashAttribute("message", "Switch POE was added succesfully.");
        return "redirect:/switches";
    }

    @GetMapping("/switches/edit/{id}")
        public String editSwitchPOE(@PathVariable("id") Integer id, Model model, RedirectAttributes re){
            try{
                SwitchPOE switchPOE = switchPOEService.get(id);
                model.addAttribute("switchPOE", switchPOE);
                model.addAttribute("title", "Edit switch POE (ID: " + id + ")");
                return "switch_form";
            } catch(NotFoundException e){
                re.addFlashAttribute("message", e.getMessage());
                return "redirect:/switches";
            }
        }

    @GetMapping("/switches/delete/{id}")
    public String deleteSwitchPOE(@PathVariable("id") Integer id, RedirectAttributes re, SwitchPOE switchPOE) throws IOException{
        try{
            switchPOEService.delete(id);
            Path imageUploadDir = Paths.get("./images/" + "switches/" + switchPOE.getId() + "/");
            directoryDeleteUtil.cleanDirectory(imageUploadDir);
            re.addFlashAttribute("message", "Switch POE was deleted successfully.");
        } catch(NotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/switches";
        } 
    
}
