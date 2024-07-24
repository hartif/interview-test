# Use the official Maven image to build the application
FROM openjdk:17-jre-slim
WORKDIR /app

FROM postgres:latest
ENV POSTGRES_DB=interviewtestdb
ENV POSTGRES_USER=interviewtest
ENV POSTGRES_PASSWORD=password
COPY ./init.sql /docker-entrypoint-initdb.d/

# Copy the pom.xml file and the source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

