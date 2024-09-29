FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/security-0.0.1-SNAPSHOT.jar security.jar
ENTRYPOINT ["java","-jar","/security.jar"]
