package com.example.ldbc41.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudStorageConfig {

    @Bean
    public Storage googleCloudStorage() throws IOException {
        // Cargar el archivo JSON de credenciales desde el classpath
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("ldbc41-1a91a-firebase-adminsdk-cjaou-af2acb437c.json");

        if (serviceAccount == null) {
            throw new IOException("No se encontró el archivo de credenciales en 'src/main/resources'.");
        }

        // Inicializar las credenciales de Google Cloud
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        // Configurar el cliente de Google Cloud Storage
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
