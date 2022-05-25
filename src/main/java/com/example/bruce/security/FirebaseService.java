package com.example.bruce.security;

import com.example.bruce.model.Pair;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FirebaseService {
    {
        try {
            File file = new ClassPathResource("chat-project.json").getFile();
            InputStream inputStream = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://chat-project-776f8.firebaseio.com")
                    .setStorageBucket("chat-project-776f8.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        bucket.create(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), multipartFile.getContentType());
        return multipartFile.getOriginalFilename();
    }

    public Pair<byte[], String> getImage(String name) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob bl = bucket.get(name);
        return new Pair<>(bucket.get(name).getContent(), bl.getContentType());
    }

}
