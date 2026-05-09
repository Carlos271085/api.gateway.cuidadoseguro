# Imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar generado por Maven
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto del API Gateway
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]