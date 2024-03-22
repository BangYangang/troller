FROM openjdk:17-alpine

COPY ./build/libs/troller-0.0.1-SNAPSHOT.jar /build/libs/troller-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/build/libs/troller-0.0.1-SNAPSHOT.jar"]