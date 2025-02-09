
FROM gradle:8.2.1-jdk17 AS builder  
WORKDIR /home/gradle/src 
COPY . /home/gradle/src 


RUN chmod +x /home/gradle/src/gradlew

RUN ./gradlew clean build -x test


FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/ElMana-0.0.1-SNAPSHOT.jar /app/ElMana-0.0.1-SNAPSHOT.jar 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ElMana-0.0.1-SNAPSHOT.jar"] 