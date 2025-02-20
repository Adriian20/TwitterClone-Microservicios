# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY follow-service/pom.xml /app/follow-service/pom.xml
COPY follow-service/src /app/follow-service/src

# Construir el proyecto
WORKDIR /app/follow-service
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/follow-service/target/follow-service-0.0.1-SNAPSHOT.jar /usr/local/lib/follow-service.jar

# Exponer el puerto de Eureka Server
EXPOSE 8084

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/follow-service.jar"]
