FROM openjdk:16-alpine
COPY target/web-app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]