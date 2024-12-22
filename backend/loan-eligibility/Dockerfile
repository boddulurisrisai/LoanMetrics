# Use the official openjdk image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY ./target/*.jar app.jar

# Expose the port your backend will be running on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
