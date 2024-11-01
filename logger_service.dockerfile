FROM maven:3.9.9-eclipse-temurin-21-alpine AS BUILDER

COPY ./logger_service/pom.xml /app/logger_service/pom.xml
COPY ./fflix_service/pom.xml /app/fflix_service/pom.xml
COPY ./pom.xml /app/pom.xml

WORKDIR /app

RUN mvn dependency:go-offline -B -e -DexcludeArtifactIds=fflix_service

COPY ./logger_service /app/logger_service

WORKDIR logger_service

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine as run

COPY --from=BUILDER /app/logger_service/target/logger_service-1.0-SNAPSHOT.jar logger_service.jar

ENTRYPOINT ["java", "-jar", "logger_service.jar"]