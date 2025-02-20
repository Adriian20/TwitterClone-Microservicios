# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY discovery-server/pom.xml /app/discovery-server/pom.xml
COPY discovery-server/src /app/discovery-server/src

# Construir el proyecto
WORKDIR /app/discovery-server
RUN mvn clean package

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar /usr/local/lib/discovery-server.jar

# Exponer el puerto de Eureka Server
EXPOSE 8761

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/discovery-server.jar"]
