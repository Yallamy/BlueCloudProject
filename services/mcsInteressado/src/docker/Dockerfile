FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} MCSInteressado.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","MCSInteressado.jar"]