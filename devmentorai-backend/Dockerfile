FROM maven:3.9.7-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/devmentorai-backend-*.jar app.jar
EXPOSE 8080
ENV OPENAI_API_KEY=""
CMD ["java", "-jar", "app.jar"]
