package com.example.ldbc41.controllers;

import com.example.ldbc41.services.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final GoogleCloudStorageService googleCloudStorageService;

    @Autowired
    public FileUploadController(GoogleCloudStorageService googleCloudStorageService) {
        this.googleCloudStorageService = googleCloudStorageService;
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String bucketName = "ldbc41-1a91a.appspot.com";
            //gs://ldbc41-1a91a.appspot.com/ldbc41CedulaJugadores
            String fileUrl = googleCloudStorageService.uploadFile(file, bucketName);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir la imagen: " + e.getMessage());
        }
    }
}
