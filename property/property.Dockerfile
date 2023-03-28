FROM eclipse-temurin:19-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ./target/property-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
