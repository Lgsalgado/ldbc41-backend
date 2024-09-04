# Usa una imagen base con OpenJDK
FROM openjdk:17-jdk

# Copia el archivo JAR al contenedor
COPY ldbc41-0.0.1-SNAPSHOT.jar /app/ldbc41-0.0.1-SNAPSHOT.jar

# Establece el directorio de trabajo
WORKDIR /app

# Comando para ejecutar el archivo JAR
CMD ["java", "-jar", "ldbc41-0.0.1-SNAPSHOT.jar"]
