# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo .jar generado por Maven
COPY target/bff-cuidadoseguro-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del BFF
EXPOSE 8090

# Comando para ejecutar el proyecto
ENTRYPOINT ["java", "-jar", "app.jar"]