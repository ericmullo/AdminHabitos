FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR ya compilado
COPY app.jar app.jar

# Render usa la variable PORT, y Spring lee server.port=${PORT:8080}
ENV PORT=10000
EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
