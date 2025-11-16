FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el jar ya compilado
COPY app.jar app.jar

ENV PORT=10000
EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
