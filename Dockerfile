# Etapa 1: construir el JAR con el wrapper de Maven del proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom y el wrapper de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Copiamos el código fuente
COPY src ./src

# Compilamos usando el wrapper y saltando los tests
RUN ./mvnw clean package -DskipTests

# Etapa 2: imagen ligera solo con el JAR
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR construido en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Render usa PORT, pero por si acaso ponemos 10000
ENV PORT=10000
EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
