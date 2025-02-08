FROM gradle:8.12.1-jdk17 AS build  # Imagen con Gradle y JDK
WORKDIR /home/gradle/src  # Directorio de trabajo
COPY . /home/gradle/src
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/ElMana-0.0.1-SNAPSHOT.jar /app.jar # Ruta absoluta

ENTRYPOINT ["java", "-jar", "/app.jar"]