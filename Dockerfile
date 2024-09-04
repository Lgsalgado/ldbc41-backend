# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR desde el directorio correcto
COPY target/ldbc41-0.0.1-SNAPSHOT.jar /app/ldbc41-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que la aplicación va a correr
EXPOSE 8080

# Define el comando para correr tu aplicación
ENTRYPOINT ["java", "-jar", "/app/ldbc41-0.0.1-SNAPSHOT.jar"]
