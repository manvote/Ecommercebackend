# Use JDK 21 as base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy the JAR built locally into the container
COPY target/ecom-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render or other hosts usually use 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
