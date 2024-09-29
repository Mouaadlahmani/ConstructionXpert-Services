FROM openjdk:17
VOLUME /tmp
EXPOSE 8091
COPY target/Ressources-0.0.1-SNAPSHOT.jar /ressources.jar
ENTRYPOINT ["java", "-jar", "/ressources.jar"]
