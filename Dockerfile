FROM eclipse-temurin:17-focal

RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]