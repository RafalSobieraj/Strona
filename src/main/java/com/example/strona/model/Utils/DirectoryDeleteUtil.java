package com.example.strona.model.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class DirectoryDeleteUtil {
    
    public void cleanDirectory(Path path) throws IOException {
        File filepath = path.toFile();
        if(filepath.listFiles() != null) {
            try {
                Files.list(path).forEach(file -> {
                    if(!Files.isDirectory(file)) {
                        try {
                            Files.delete(file);
                            path.toFile().delete();
                        }catch(IOException ex) {
                            try {
                                throw new IOException("Could not delete file: " + file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }catch(IOException e) {
                throw new IOException("Could not list directory: " + path);

            }
        }
        else
            path.toFile().delete();
    }
}
