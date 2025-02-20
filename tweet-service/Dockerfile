# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar el POM raíz y el código fuente
COPY pom.xml /app/pom.xml
COPY tweet-service/pom.xml /app/tweet-service/pom.xml
COPY tweet-service/src /app/tweet-service/src

# Construir el proyecto
WORKDIR /app/tweet-service
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/tweet-service/target/tweet-service-0.0.1-SNAPSHOT.jar /usr/local/lib/tweet-service.jar

# Exponer el puerto de Eureka Server
EXPOSE 8083

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/tweet-service.jar"]
