FROM gradle:6.8.2-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean bootJar

FROM openjdk:8-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/* /
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "wdyw-0.0.1-SNAPSHOT.jar"]