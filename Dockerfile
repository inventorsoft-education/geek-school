FROM java:8

ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} demo-0.0.1.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/demo-0.0.1.jar"]
