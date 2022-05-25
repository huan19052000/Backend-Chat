package com.example.bruce.controller;

import com.example.bruce.security.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/image")
    public String postImage(@RequestParam MultipartFile imageFile) {
        try {
            return firebaseService.uploadFile(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String imageName) {
        return firebaseService.getImage(imageName);
    }
}
