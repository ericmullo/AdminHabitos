# Etapa 1: construir el JAR con Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom.xml y el wrapper
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# 🔥 IMPORTANTE: Dar permisos de ejecución al mvnw
RUN chmod +x mvnw

# Copiamos el código fuente
COPY src ./src

# Compilamos usando el wrapper y saltando tests
RUN ./mvnw clean package -DskipTests

# Etapa 2: imagen ligera con solo el JAR
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=10000
EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
