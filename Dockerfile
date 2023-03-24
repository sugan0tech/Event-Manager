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


# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/Event-Manager/target/*.jar"]
