FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]
 Build stage
#FROM maven:3.8.6-openjdk-17-slim AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
## Run stage
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#
## Expose port (thường là 8080)
#EXPOSE 8080
#
## Run the jar
#ENTRYPOINT ["java", "-jar", "app.jar"]