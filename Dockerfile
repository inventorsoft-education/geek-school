FROM java:8

#COPY ./targer/demo-0.0.1-SNAPSHOT.jar  /usr/src/geek-school/

ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
#WORKDIR /usr/src/geek-school/

ADD ${JAR_FILE} demo-0.0.1.jar
EXPOSE 8080

#CMD ["java","-jar","/usr/src/geek-school/demo-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-jar", "/demo-0.0.1.jar"]