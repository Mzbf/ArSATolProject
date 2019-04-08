package com.arsatoll.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUpload {


    public String uploadImage(MultipartFile file) throws IOException;
}


