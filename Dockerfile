FROM openjdk:22
EXPOSE 9999
ADD target/ENAA-SUPPORT-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]