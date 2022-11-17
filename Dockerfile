FROM adoptopenjdk/openjdk11:ubi

COPY target/registration-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "registration-0.0.1-SNAPSHOT.jar"]