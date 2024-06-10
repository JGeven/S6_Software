FROM openjdk:21
EXPOSE 8090
WORKDIR /User
ADD target/User-0.0.1-SNAPSHOT.jar User-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","User-0.0.1-SNAPSHOT.jar"]