FROM gradle:6.8.2-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean bootJar

FROM openjdk:8-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/* /
EXPOSE 8080
# java -jar wdyw-0.0.1-SNAPSHOT.jar --DB_HOST='localhost' --DB_NAME='wdyw' --DB_USER='root' --DB_PASSWORD='root'
# java -jar wdyw-0.0.1-SNAPSHOT.jar --spring.datasource.url='jdbc:mysql://localhost:3306/wdyw?serverTimezone=Asia/Seoul&characterEncoding=UTF-8' --spring.datasource.username='root' --spring.datasource.password='root'
CMD ["java", "-jar", "wdyw-0.0.1-SNAPSHOT.jar"]