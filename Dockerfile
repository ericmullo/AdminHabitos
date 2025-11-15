# Etapa 1: construir el JAR con Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom y código
COPY pom.xml .
COPY src ./src

# Compilamos sin tests
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera solo con el JAR
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/*.jar app.jar

# Render normalmente usa el PORT del entorno (10000 por defecto)
# Spring Boot lo leerá si lo configuramos en application.properties
ENV PORT=10000

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
