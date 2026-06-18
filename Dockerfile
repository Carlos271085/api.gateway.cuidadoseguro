# ─── Etapa 1: Build ───────────────────────────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Instalar jwt-common en el repositorio Maven local
COPY jwt-common ./jwt-common
RUN mvn install -f jwt-common/pom.xml -DskipTests

# Descargar dependencias del proyecto principal (cacheado)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# ─── Etapa 2: Runtime ─────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-jammy

RUN groupadd -r appuser && useradd -r -g appuser appuser

WORKDIR /app

COPY --from=build /app/target/api-gateway-cuidadoseguro-3.2.5.jar app.jar

RUN chown appuser:appuser app.jar
USER appuser

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:+UseContainerSupport"

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=docker -jar app.jar"]
