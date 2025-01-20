#
# Build stage
#
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copiar el código fuente y el archivo pom.xml
COPY src /home/app/src
COPY pom.xml /home/app

# Construir el proyecto con Maven
RUN mvn -f /home/app/pom.xml clean package

# Listar el contenido del directorio target para depuración
RUN ls -la /home/app/target

#
# Package stage
#
FROM eclipse-temurin:21-jre

# Copiar el archivo .jar desde el contenedor de construcción
COPY --from=build /home/app/target/user-service-1.0-SNAPSHOT.jar /usr/local/lib/demo.jar

# Exponer el puerto en el que la aplicación va a correr
EXPOSE 8082

# Definir el punto de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]
