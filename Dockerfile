# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

ARG CACHEBUST=1

# Add Maintainer Info
LABEL maintainer="techmonks@gmail.com"

# Add a volume pointing to /code-quality-service
VOLUME /code-quality-service

#ADD ext_lib/elastic-apm-agent-1.11.0.jar elastic-apm-agent-1.11.0.jar

# Make port 8080 available to the world outside this container
EXPOSE 8086

# The application's jar file
ARG JAR_FILE=build/libs/code-metrics-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} code-metrics.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=default", "/code-metrics.jar"]
#ENTRYPOINT ["java","-javaagent:/elastic-apm-agent-1.11.0.jar","-Delastic.apm.service_name=user-service","-Delastic.apm.server_url=http://15.206.79.121:8200","-Delastic.apm.application_packages=com.ts","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=default", "/userservice.jar"]
