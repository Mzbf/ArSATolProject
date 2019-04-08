package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUploasImpl implements ImageUpload {

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        File ajoutFile = new File("/home/mzbf/arsatoll/arsatollservice/image/" + file.getOriginalFilename() +" teste");
        ajoutFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(ajoutFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return ajoutFile.getAbsolutePath();
      //  return new ResponseEntity<>("Image enregistré", HttpStatus.OK);
    }
}
