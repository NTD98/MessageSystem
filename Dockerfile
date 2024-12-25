# Runtime Stage
FROM openjdk:23

# Working directory for runtime
WORKDIR /app

# Copy JAR file from the build stage
COPY target/demo-0.0.1-SNAPSHOT.jar test-metric.jar

# Command to run the application
CMD ["java", "-jar", "test-metric.jar"]