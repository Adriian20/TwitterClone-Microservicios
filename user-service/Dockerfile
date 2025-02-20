# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY user-service/pom.xml /app/user-service/pom.xml
COPY user-service/src /app/user-service/src

# Construir el proyecto
WORKDIR /app/user-service
RUN mvn clean package

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/user-service/target/user-service-0.0.1-SNAPSHOT.jar /usr/local/lib/user-service.jar


# Exponer el puerto en el que la aplicación va a correr
EXPOSE 8082

# Definir el punto de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]
