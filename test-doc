    # Build stage
    #
    FROM  maven:3.8.6-eclipse-temurin-11-alpine AS build
    COPY . .
    RUN mvn clean package -Pprod -DskipTests
    
    #
    # Package stage
    #
    FROM openjdk:11-jre-slim
    COPY --from=build /target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar
    # ENV PORT=8080
    EXPOSE 8080
    ENTRYPOINT ["java","-jar","portfolio.jar"]