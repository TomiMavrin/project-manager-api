FROM openjdk:11
ADD target/project-manager-api.jar project-manager-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project-manager-api.jar"]