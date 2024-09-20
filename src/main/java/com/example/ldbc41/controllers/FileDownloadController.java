package com.example.ldbc41.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

@RestController
public class FileDownloadController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileUrl) throws Exception {
        // Realiza la solicitud GET para obtener el archivo desde el URL
        byte[] fileBytes = restTemplate.getForObject(new URI(fileUrl), byte[].class);

        // Convierte los bytes en un InputStream
        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Construye la respuesta con los headers adecuados
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("attachment", "file");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .body(resource);
    }
}
