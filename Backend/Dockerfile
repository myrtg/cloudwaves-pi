FROM openjdk:18
WORKDIR /app
COPY ./pfeconnect-0.0.1-SNAPSHOT.jar /app
EXPOSE 8083
CMD ["java", "-jar", "pfeconnect-0.0.1-SNAPSHOT.jar"]

# # Use a base image with Java and Maven installed
# FROM maven:3.8.4-openjdk-11-slim AS build
# WORKDIR /app
#
# # Copy the Maven project file
# COPY pom.xml .
#
# # Download the project dependencies
# RUN mvn dependency:go-offline -B
#
# # Copy the application source code
# COPY src ./src
#
# # Build the application
# RUN mvn package -DskipTests
#
# # Create the final Docker image
# FROM openjdk:18-jre-slim
# WORKDIR /app
#
# # Copy the built JAR file from the previous stage
# COPY --from=build /app/target/your-application.jar ./app.jar
#
# # Set the entry point for the container
# ENTRYPOINT ["java", "-jar", "app.jar"]
