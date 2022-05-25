package com.example.bruce.controller;

import com.example.bruce.model.Pair;
import com.example.bruce.security.FirebaseService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping(value = "/image/{imageName}",  produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(
            @PathVariable String imageName
    ){
        Pair<byte[], String> content =  firebaseService.getImage(imageName);
        return content.getT1();
    }
}
