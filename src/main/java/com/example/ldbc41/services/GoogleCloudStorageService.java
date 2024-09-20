package com.example.ldbc41.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.apache.catalina.manager.StatusTransformer.setContentType;



@Service
public class GoogleCloudStorageService {

    private final Storage storage;
    @Value("${var.path}")
    private String path;

    @Autowired
    public GoogleCloudStorageService(Storage storage) {
        this.storage = storage;
    }

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        // Generar un nombre único para el archivo
        String fileName = path+UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Crear los metadatos del archivo
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
        .setContentType("image/jpeg").build();

        // Subir el archivo a Google Cloud Storage
        storage.create(blobInfo, file.getBytes());

        // Retornar la URL del archivo subido
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
