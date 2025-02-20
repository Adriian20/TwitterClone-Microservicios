# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY api-gateway/pom.xml /app/api-gateway/pom.xml
COPY api-gateway/src /app/api-gateway/src

# Construir el proyecto
WORKDIR /app/api-gateway
RUN mvn clean package

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar /usr/local/lib/api-gateway.jar


# Exponer el puerto en el que la aplicación va a correr
EXPOSE 8080

# Definir el punto de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]