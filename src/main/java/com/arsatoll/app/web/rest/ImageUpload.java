package com.arsatoll.app.web.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageUpload {

    @RequestMapping(value="/upload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        File ajoutFile = new File("/home/mzbf/arsatoll/arsatollservice/image/" + file.getOriginalFilename());
        ajoutFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(ajoutFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        System.out.println(ajoutFile.getAbsolutePath());
        return new ResponseEntity<>("Image enregistré", HttpStatus.OK);

    }

   /* @RequestMapping("/")
    public String uploading(Model model) {
        File file = new File(uploadingDir);
        model.addAttribute("files", file.listFiles());
        return "uploading";
    }*/
}
