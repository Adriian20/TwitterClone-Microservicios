FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar los archivos necesarios
COPY pom.xml .
COPY discovery-server/pom.xml discovery-server/
COPY discovery-server/src discovery-server/src

# Construir el proyecto desde el directorio raíz
RUN mvn -f discovery-server/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

# Copiar el JAR generado
COPY --from=build /app/discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]
