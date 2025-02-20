# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY auth-service/pom.xml /app/auth-service/pom.xml
COPY auth-service/src /app/auth-service/src

# Construir el proyecto
WORKDIR /app/auth-service
RUN mvn clean package

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar /usr/local/lib/auth-service.jar


# Exponer el puerto en el que la aplicación va a correr
EXPOSE 8081

# Definir el punto de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]