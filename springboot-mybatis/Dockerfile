FROM openjdk:8-jdk-alpine
MAINTAINER jupierboy
RUN mkdir -p /jupiterboy/springboot/mybatis
WORKDIR /jupiterboy/springboot/mybatis
EXPOSE 8001
ADD ./target/springboot-mybatis-1.0-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
