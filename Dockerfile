FROM adoptopenjdk/openjdk11:ubi

COPY target/pessoafisica-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "pessoafisica-0.0.1-SNAPSHOT.jar"]