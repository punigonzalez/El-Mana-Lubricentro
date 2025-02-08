FROM gradle:8.1.2-jdk17 AS build 
WORKDIR /home/gradle/src 
COPY . /home/gradle/src
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/ElMana-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]