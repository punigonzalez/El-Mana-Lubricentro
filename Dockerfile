
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . /app  


FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/ElMana-0.0.1-SNAPSHOT.jar /app/ElMana-0.0.1-SNAPSHOT.jar 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ElMana-0.0.1-SNAPSHOT.jar"] 