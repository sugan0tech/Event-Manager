FROM maven

# Set the working directory to /app
WORKDIR /Event-Manager

# Copy the pom.xml file to the container
COPY pom.xml .


# Copy the source code to the container
COPY src/ /Event-Manager/src/

# Run mvn dependency:resolve to download the dependencies
RUN mvn dependency:resolve

# Package the application into a JAR file
RUN mvn package


# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["mvn", "spring-boot:run"]