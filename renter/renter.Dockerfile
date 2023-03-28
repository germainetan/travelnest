FROM eclipse-temurin:19-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ./target/renter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
