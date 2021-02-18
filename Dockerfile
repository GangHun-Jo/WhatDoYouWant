FROM maven:3.6.3-openjdk-11
WORKDIR /
COPY . .
RUN mvn package

#FROM openjdk
#COPY --from=0 /usr/src/app/target/* /
#EXPOSE 8080
#CMD ["java", "-jar", "spring-boot-sample-web-ui-2.0.6.RELEASE.jar"]