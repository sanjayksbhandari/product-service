# Use a lightweight JDK base image
FROM openjdk:11-jdk-slim

# Set a working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/product-service-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
