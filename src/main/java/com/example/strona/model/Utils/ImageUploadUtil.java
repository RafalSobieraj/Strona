package com.example.strona.model.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtil {

    public static void saveImg(String uploadDirectory, String fileName, MultipartFile multipartFile) throws IOException{

        Path uploadPath = Paths.get(uploadDirectory);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("There was an error adding a image. Please try again.");
        }
    }
}
