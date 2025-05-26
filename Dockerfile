# Stage 1 - builder

#maven Image
FROM maven:3.8.3-openjdk-17 AS builder

# set working directory
WORKDIR /productserviceapp

#copy source from local to container
COPY . /productserviceapp

# build application and skip test cases
RUN mvn clean install -DskipTests=true


# Stage 2 - Execute jar files from build
# Use a base image with a suitable JRE (Java Runtime Environment)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /productserviceapp

# Copy the JAR file into the container
COPY --from=builder /productserviceapp/target/*.jar /productserviceapp/productservice.jar

# Expose the port that your Spring Boot application runs on (usually 8080)
EXPOSE 8000

# Define the command to run the application
CMD ["java", "-jar", "productservice.jar"]